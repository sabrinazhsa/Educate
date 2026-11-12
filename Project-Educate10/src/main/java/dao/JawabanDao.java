package dao;

import config.EducateDB;
import model.Jawaban;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JawabanDao {
    private Connection connection;

    public JawabanDao() {
        this.connection = EducateDB.getConnection();
    }


    public boolean insert(Jawaban jawaban) {
        String query = "INSERT INTO jawaban (id_jawaban, id_latihan, id_pelajar, pilihan_jawaban, status) VALUES (?, ?, ?, ?, ?)";;
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, jawaban.getIdJawaban());
            stmt.setInt(2, jawaban.getIdLatihan());
            stmt.setInt(3, jawaban.getIdPelajar());
            stmt.setString(4, jawaban.getPilihanJawaban());
            stmt.setString(5, jawaban.getStatus());
            
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
            
        } catch (SQLException e) {
            System.out.println("Gagal menyimpan jawaban: " + e.getMessage());
            return false;
        }
    }


    public List<Jawaban> getByPelajar(int idPelajar) {
        List<Jawaban> listJawaban = new ArrayList<>();
        String query = "SELECT * FROM jawaban WHERE id_pelajar = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idPelajar);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Jawaban jwb = new Jawaban();
                jwb.setIdJawaban(rs.getInt("id_jawaban"));
                jwb.setIdLatihan(rs.getInt("id_latihan"));
                jwb.setIdPelajar(rs.getInt("id_pelajar"));
                jwb.setPilihanJawaban(rs.getString("pilihan_jawaban"));
                jwb.setStatus(rs.getString("status"));
                
                listJawaban.add(jwb);
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengambil data jawaban: " + e.getMessage());
        }
        
        return listJawaban;
    }
public List<String> getPilihanByLatihanId(int idLatihan) {

    List<String> pilihan = new ArrayList<>();

    String sql =
        "SELECT pilihan_jawaban FROM jawaban " +
        "WHERE latihan_soal_id_latihan_soal = ? " +
        "ORDER BY id_jawaban";

    try (Connection conn = EducateDB.getConnection()) {

        if (conn == null) {
            return pilihan;
        }

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idLatihan);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    pilihan.add(
                        rs.getString("pilihan_jawaban")
                    );
                }
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return pilihan;
}

public List<Jawaban> getByLatihanId(int idLatihan) {

    List<Jawaban> list = new ArrayList<>();

    String sql = "SELECT id_jawaban, pilihan_jawaban, "
            + "kunci_jawaban, latihan_soal_id_latihan_soal "
            + "FROM jawaban "
            + "WHERE latihan_soal_id_latihan_soal = ? "
            + "ORDER BY id_jawaban";

    try (Connection conn = EducateDB.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, idLatihan);

        try (ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Jawaban j = new Jawaban();

                j.setIdJawaban(rs.getInt("id_jawaban"));
                j.setPilihanJawaban(
                    rs.getString("pilihan_jawaban")
                );
                j.setKunciJawaban(
                    rs.getString("kunci_jawaban")
                );
                j.setIdLatihan(
                    rs.getInt("latihan_soal_id_latihan_soal")
                );

                list.add(j);
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return list;
}
}