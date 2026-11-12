package dao;

import config.EducateDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.LatihanSoal;
import model.Materi;
import model.PaketSoal;

public class LatihanSoalDao implements CrudDao<LatihanSoal> {

    @Override
    public boolean insert(LatihanSoal latihan) {
        String sql = "INSERT INTO latihan_soal (pertanyaan, materi_id_materi, paket_soal_id_paket_soal) VALUES (?, ?, ?)";
        try (Connection conn = EducateDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, latihan.getPertanyaan());
            stmt.setInt(2, latihan.getMateri().getIdMateri()); // Mengambil ID dari objek Materi
            stmt.setInt(3, latihan.getPaketSoal().getIdPaketSoal()); // Mengambil ID dari objek PaketSoal
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(LatihanSoal latihan) {
        String sql = "UPDATE latihan_soal SET pertanyaan = ?, materi_id_materi = ?, paket_soal_id_paket_soal = ? WHERE id_latihan_soal = ?";
        try (Connection conn = EducateDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, latihan.getPertanyaan());
            stmt.setInt(2, latihan.getMateri().getIdMateri());
            stmt.setInt(3, latihan.getPaketSoal().getIdPaketSoal());
            stmt.setInt(4, latihan.getIdLatihanSoal());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int idLatihanSoal) {
        String sql = "DELETE FROM latihan_soal WHERE id_latihan_soal = ?";
        try (Connection conn = EducateDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idLatihanSoal);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Overload: hapus dengan objek LatihanSoal
    public boolean delete(LatihanSoal latihan) {
        return latihan != null && delete(latihan.getIdLatihanSoal());
    }

    @Override
    public LatihanSoal getById(int idLatihanSoal) {
        String sql = "SELECT * FROM latihan_soal WHERE id_latihan_soal = ?";
        try (Connection conn = EducateDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idLatihanSoal);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return map(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<LatihanSoal> getAll() {
        List<LatihanSoal> list = new ArrayList<>();
        String sql = "SELECT * FROM latihan_soal";
        try (Connection conn = EducateDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(map(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Method asli kamu (tidak diubah): dipakai DetailLatihanSoalPanel
    public List<LatihanSoal> getByPaketId(int idPaket) {

        List<LatihanSoal> list = new ArrayList<>();

        String sql =
            "SELECT ls.id_latihan_soal, ls.pertanyaan, " +
            "m.id_materi, m.nama_materi, m.topik, " +
            "m.tipe_materi, m.deskripsi_materi " +
            "FROM latihan_soal ls " +
            "JOIN materi m ON ls.materi_id_materi = m.id_materi " +
            "WHERE ls.paket_soal_id_paket_soal = ? " +
            "ORDER BY ls.id_latihan_soal";

        Connection conn = EducateDB.getConnection();

        if (conn == null) {
            return list;
        }

        try (conn;
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idPaket);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    LatihanSoal soal = new LatihanSoal();
                    soal.setIdLatihanSoal(rs.getInt("id_latihan_soal"));
                    soal.setPertanyaan(rs.getString("pertanyaan"));

                    Materi materi = new Materi();
                    materi.setIdMateri(rs.getInt("id_materi"));
                    materi.setNamaMateri(rs.getString("nama_materi"));
                    materi.setTopik(rs.getString("topik"));
                    materi.setTipeMateri(rs.getString("tipe_materi"));
                    materi.setDeskripsiMateri(rs.getString("deskripsi_materi"));
                    soal.setMateri(materi);

                    list.add(soal);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // Helper: mengubah satu baris tabel latihan_soal menjadi objek LatihanSoal
    private LatihanSoal map(ResultSet rs) throws SQLException {
        LatihanSoal latihan = new LatihanSoal();
        latihan.setIdLatihanSoal(rs.getInt("id_latihan_soal"));
        latihan.setPertanyaan(rs.getString("pertanyaan"));

        Materi materi = new Materi();
        materi.setIdMateri(rs.getInt("materi_id_materi"));
        latihan.setMateri(materi);

        PaketSoal paketSoal = new PaketSoal();
        paketSoal.setIdPaketSoal(rs.getInt("paket_soal_id_paket_soal"));
        latihan.setPaketSoal(paketSoal);
        return latihan;
    }
}