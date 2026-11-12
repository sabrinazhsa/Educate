package dao;

import config.EducateDB;
import model.Rekomendasi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.HasilBelajar;
import model.Pengajar;

public class RekomendasiDao {

    public boolean insert(Rekomendasi r) {
    String sql = "INSERT INTO rekomendasi (nilai_minimum, nilai_maksimum, isi_rekomendasi, pengajar_id_pengguna, hasil_belajar_id_hasil) VALUES (?, ?, ?, ?, ?)";
    try (Connection conn = EducateDB.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setInt(1, r.getNilaiMinimum());
        stmt.setInt(2, r.getNilaiMaksimum());
        stmt.setString(3, r.getIsiRekomendasi());
        stmt.setInt(4, r.getPengajar().getIdPengguna()); 
        stmt.setInt(5, r.getHasilBelajar().getIdHasil()); 
        
        return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

    public List<Rekomendasi> getByHasil(int idHasil) {
        return query("SELECT * FROM rekomendasi WHERE hasil_belajar_id_hasil = ?", idHasil);
    }


    public List<Rekomendasi> getByPelajar(int idPelajar) {
        return query("SELECT r.* FROM rekomendasi r JOIN hasil_belajar h ON r.hasil_belajar_id_hasil = h.id_hasil "
                + "WHERE h.pelajar_id_pengguna = ? ORDER BY r.id_rekomendasi DESC", idPelajar);
    }

    private List<Rekomendasi> query(String sql, int param) {
    List<Rekomendasi> list = new ArrayList<>();
    try (Connection conn = EducateDB.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setInt(1, param);
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Rekomendasi r = new Rekomendasi();
                r.setIdRekomendasi(rs.getInt("id_rekomendasi"));
                r.setNilaiMinimum(rs.getInt("nilai_minimum"));
                r.setNilaiMaksimum(rs.getInt("nilai_maksimum"));
                r.setIsiRekomendasi(rs.getString("isi_rekomendasi"));
                
                Pengajar p = new Pengajar();
                p.setIdPengguna(rs.getInt("pengajar_id_pengguna"));
                r.setPengajar(p);
                
                HasilBelajar hb = new HasilBelajar();
                hb.setIdHasil(rs.getInt("hasil_belajar_id_hasil"));
                r.setHasilBelajar(hb);
                
                list.add(r);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}
}
