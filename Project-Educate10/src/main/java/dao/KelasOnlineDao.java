/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.EducateDB;
import model.KelasOnline;
import model.Pengajar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KelasOnlineDao {

    public List<KelasOnline> getAllKelas() {
    List<KelasOnline> daftar = new ArrayList<>();

    String sql =
        "SELECT k.*, " +
        "p.nama AS nama_pengajar, " +
        "pg.keahlian AS keahlian_pengajar " +
        "FROM kelas_online k " +
        "JOIN pengguna p ON k.pengajar_id_pengguna = p.id_pengguna " +
        "LEFT JOIN pengajar pg ON k.pengajar_id_pengguna = pg.id_pengguna " +
        "WHERE k.status_kelas = 'Terjadwal' " +
        "ORDER BY k.tanggal_kelas ASC, k.id_kelas ASC";

    try (Connection conn = EducateDB.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            KelasOnline kelas = new KelasOnline();

            kelas.setIdKelas(rs.getInt("id_kelas"));
            kelas.setNamaKelas(rs.getString("nama_kelas"));
            kelas.setDeskripsiKelas(rs.getString("deskripsi_kelas"));

            if (rs.getDate("tanggal_kelas") != null) {
                kelas.setTanggalKelas(
                    rs.getDate("tanggal_kelas").toLocalDate()
                );
            }

            kelas.setWaktuMulai(
                java.time.LocalTime.parse(
                    rs.getString("waktu_mulai").substring(0, 5)
                )
            );

            kelas.setWaktuSelesai(
                java.time.LocalTime.parse(
                    rs.getString("waktu_selesai").substring(0, 5)
                )
            );

            kelas.setLinkKelas(rs.getString("link_kelas"));
            kelas.setHarga(Double.parseDouble(
                rs.getString("harga").replaceAll("[^0-9]", "")
            ));
            kelas.setStatusKelas(rs.getString("status_kelas"));
            kelas.setKuotaMaksimal(rs.getInt("kuota_maksimal"));

            Pengajar pengajar = new Pengajar();
            pengajar.setIdPengguna(
                rs.getInt("pengajar_id_pengguna")
            );
            pengajar.setNama(rs.getString("nama_pengajar"));
            pengajar.setKeahlian(
                rs.getString("keahlian_pengajar")
            );

            kelas.setPengajar(pengajar);

            daftar.add(kelas);
        }

    } catch (SQLException | RuntimeException e) {
        e.printStackTrace();
    }

    return daftar;
}
}
           