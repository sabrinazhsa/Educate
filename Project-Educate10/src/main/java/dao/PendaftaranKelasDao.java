/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.EducateDB;
import model.PendaftaranKelasOnline;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PendaftaranKelasDao {
    public boolean daftarKelas(int idPendaftaran, PendaftaranKelasOnline pendaftaran) {
    String sql = "INSERT INTO pendaftaran_kelas_online (id_pendaftaran, tanggal_daftar, harga_bayar, status_pembayaran, pelajar_id_pengguna, kelas_online_id_kelas) " +
                 "VALUES (?, ?, (SELECT harga FROM kelas_online WHERE id_kelas = ?), ?, ?, ?)";
                 
    try (Connection conn = EducateDB.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setInt(1, idPendaftaran); 
        stmt.setDate(2, java.sql.Date.valueOf(pendaftaran.getTanggalDaftar())); 
        stmt.setInt(3, pendaftaran.getKelasOnline().getIdKelas()); // Parameter ke-3 untuk mencari harga kelas di dalam subquery
        stmt.setString(4, pendaftaran.getStatusPembayaran());
        stmt.setInt(5, pendaftaran.getPelajar().getIdPengguna());
        stmt.setInt(6, pendaftaran.getKelasOnline().getIdKelas()); 
        
        stmt.executeUpdate();
        return true;
        
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
}