package dao;

import config.EducateDB;
import model.PaketSoal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.PaketSoal;

public class PaketSoalDao {

    public List<PaketSoal> getAll() {
        List<PaketSoal> list = new ArrayList<>();
        String sql = "SELECT * FROM paket_soal ORDER BY id_paket_soal";
        try (Connection conn = EducateDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) list.add(map(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public PaketSoal getById(int idPaket) {
        String sql = "SELECT * FROM paket_soal WHERE id_paket_soal = ?";
        try (Connection conn = EducateDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPaket);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return map(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private PaketSoal map(ResultSet rs) throws SQLException {
        PaketSoal p = new PaketSoal();
        p.setIdPaketSoal(rs.getInt("id_paket_soal"));
        p.setNamaPaketSoal(rs.getString("nama_paket_soal"));
        p.setTingkatKesulitan(rs.getString("tingkat_kesulitan"));
        return p;
    }


public List<Object[]> getRingkasanPaket(String topik) {

    List<Object[]> list = new ArrayList<>();

    String sql =
        "SELECT p.id_paket_soal, p.nama_paket_soal, " +
        "p.tingkat_kesulitan, " +
        "COALESCE(GROUP_CONCAT(DISTINCT m.topik), '-') AS topik, " +
        "COUNT(DISTINCT ls.id_latihan_soal) AS jumlah_soal " +
        "FROM paket_soal p " +
        "LEFT JOIN latihan_soal ls " +
        "ON p.id_paket_soal = ls.paket_soal_id_paket_soal " +
        "LEFT JOIN materi m " +
        "ON ls.materi_id_materi = m.id_materi ";

    if (topik != null && !topik.equals("Semua")) {
        sql += "WHERE EXISTS ( " +
               "SELECT 1 FROM latihan_soal l2 " +
               "JOIN materi m2 ON l2.materi_id_materi = m2.id_materi " +
               "WHERE l2.paket_soal_id_paket_soal = p.id_paket_soal " +
               "AND m2.topik = ? ) ";
    }

    sql += "GROUP BY p.id_paket_soal, p.nama_paket_soal, " +
           "p.tingkat_kesulitan ORDER BY p.id_paket_soal";

    try (Connection conn = config.EducateDB.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        if (conn == null) {
            return list;
        }

        if (topik != null && !topik.equals("Semua")) {
            ps.setString(1, topik);
        }

        try (ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                PaketSoal paket = new PaketSoal();

                paket.setIdPaketSoal(
                    rs.getInt("id_paket_soal")
                );

                paket.setNamaPaketSoal(
                    rs.getString("nama_paket_soal")
                );

                paket.setTingkatKesulitan(
                    rs.getString("tingkat_kesulitan")
                );

                list.add(new Object[]{
                    paket,
                    rs.getString("topik"),
                    rs.getInt("jumlah_soal")
                });
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return list;
}
}