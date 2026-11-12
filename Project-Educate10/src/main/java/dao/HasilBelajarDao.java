/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.EducateDB;
import model.HasilBelajar;
import model.DetailLatihan;
import model.Pelajar;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HasilBelajarDao {


    public boolean insertHasil(HasilBelajar hb) {
        String sql = "INSERT INTO hasil_belajar (tanggal_pengerjaan, total_soal, jumlah_benar, skor, pelajar_id_pengguna) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = EducateDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setDate(1, java.sql.Date.valueOf(hb.getTanggalPengerjaan()));
            stmt.setInt(2, hb.getTotalSoal());
            stmt.setInt(3, hb.getJumlahBenar());
            stmt.setDouble(4, hb.getSkor());
            stmt.setInt(5, hb.getPelajar().getIdPengguna());
            stmt.executeUpdate();
            

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                hb.setIdHasil(rs.getInt(1));
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean insertDetail(DetailLatihan dl) {
        String sql = "INSERT INTO detail_latihan (jawaban_pelajar, status_jawaban, latihan_soal_id_latihan_soal, hasil_belajar_id_hasil) VALUES (?, ?, ?, ?)";
        try (Connection conn = EducateDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, dl.getJawabanPelajar());
            stmt.setString(2, dl.getStatusJawaban());
            stmt.setInt(3, dl.getLatihanSoal().getIdLatihanSoal()); // Mengambil ID dari objek LatihanSoal
            stmt.setInt(4, dl.getHasilBelajar().getIdHasil());     // Mengambil ID dari objek HasilBelajar
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public List<HasilBelajar> getByPelajar(int idPelajar) {
        List<HasilBelajar> list = new ArrayList<>();
        String sql = "SELECT * FROM hasil_belajar WHERE pelajar_id_pengguna = ?";
        try (Connection conn = EducateDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPelajar);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                HasilBelajar hb = new HasilBelajar();
                hb.setIdHasil(rs.getInt("id_hasil"));
                hb.setTanggalPengerjaan(rs.getDate("tanggal_pengerjaan").toLocalDate());
                hb.setTotalSoal(rs.getInt("total_soal"));
                hb.setJumlahBenar(rs.getInt("jumlah_benar"));
                hb.setSkor((int) rs.getDouble("skor")); // Sesuaikan jika tipenya double di model
                
                // Set objek Pelajar beserta ID-nya
                Pelajar pelajar = new Pelajar();
                pelajar.setIdPengguna(rs.getInt("pelajar_id_pengguna"));
                hb.setPelajar(pelajar);
                
                list.add(hb);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<Object[]> getRiwayatPelajar(int idPelajar) {
        List<Object[]> list = new ArrayList<>();
        String sql =
            "SELECT hb.id_hasil, hb.tanggal_pengerjaan, hb.total_soal, "
          + "       hb.jumlah_benar, hb.skor, "
          + "       COALESCE(MAX(ps.nama_paket_soal), '-') AS nama_paket "
          + "FROM hasil_belajar hb "
          + "LEFT JOIN detail_latihan dl ON dl.hasil_belajar_id_hasil = hb.id_hasil "
          + "LEFT JOIN latihan_soal ls ON ls.id_latihan_soal = dl.latihan_soal_id_latihan_soal "
          + "LEFT JOIN paket_soal ps ON ps.id_paket_soal = ls.paket_soal_id_paket_soal "
          + "WHERE hb.pelajar_id_pengguna = ? "
          + "GROUP BY hb.id_hasil, hb.tanggal_pengerjaan, hb.total_soal, hb.jumlah_benar, hb.skor "
          + "ORDER BY hb.tanggal_pengerjaan DESC, hb.id_hasil DESC";

        java.time.format.DateTimeFormatter fmt =
            java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy");

        try (Connection conn = EducateDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPelajar);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String tanggal = rs.getDate("tanggal_pengerjaan").toLocalDate().format(fmt);
                    int skor = (int) Math.round(rs.getDouble("skor"));
                    String benar = rs.getInt("jumlah_benar") + "/" + rs.getInt("total_soal");
                    list.add(new Object[]{ tanggal, rs.getString("nama_paket"), skor, benar, rs.getInt("id_hasil") });
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<Object[]> getDetailSoal(int idHasil) {
    List<Object[]> list = new ArrayList<>();
    String sql =
        "SELECT ls.pertanyaan, dl.jawaban_pelajar, "
      + "  (SELECT j.pilihan_jawaban FROM jawaban j "
      + "   WHERE j.latihan_soal_id_latihan_soal = ls.id_latihan_soal "
      + "   AND LOWER(j.kunci_jawaban) = 'benar' LIMIT 1) AS kunci "
      + "FROM detail_latihan dl "
      + "JOIN latihan_soal ls ON ls.id_latihan_soal = dl.latihan_soal_id_latihan_soal "
      + "WHERE dl.hasil_belajar_id_hasil = ? ORDER BY dl.id_detail_latihan";
    try (Connection conn = EducateDB.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, idHasil);
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(new Object[]{ rs.getString("pertanyaan"),
                        rs.getString("jawaban_pelajar"), rs.getString("kunci") });
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}
}