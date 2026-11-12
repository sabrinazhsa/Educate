package dao;

import config.EducateDB;
import model.Pengguna;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PenggunaDao {


    public Pengguna login(String usernameAtauEmail, String password) {
    Pengguna pengguna = null;
    String sql = "SELECT * FROM pengguna WHERE (username = ? OR email = ?) AND password = ?";

    try (Connection conn = EducateDB.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, usernameAtauEmail);
        stmt.setString(2, usernameAtauEmail);
        stmt.setString(3, password);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                pengguna = map(rs);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return pengguna;
}
    
    private Pengguna map(ResultSet rs) throws SQLException {
    String role = rs.getString("role");
    Pengguna p = "pengajar".equalsIgnoreCase(role)
            ? new model.Pengajar()
            : new model.Pelajar();

    p.setIdPengguna(rs.getInt("id_pengguna"));
    p.setNama(rs.getString("nama"));
    p.setEmail(rs.getString("email"));
    p.setUsername(rs.getString("username"));
    p.setRole(role);
    p.setJenisKelamin(rs.getString("jenis_kelamin"));
    java.sql.Date tanggalLahir = rs.getDate("tanggal_lahir");
    if (tanggalLahir != null) {
        p.setTanggalLahir(tanggalLahir.toLocalDate());
    }
    return p;
}
    
    public boolean updateProfil(model.Pelajar p, String passwordBaru) {
    boolean gantiPassword = passwordBaru != null && !passwordBaru.isEmpty();
    String sqlPengguna = "UPDATE pengguna SET nama = ?, email = ?"
            + (gantiPassword ? ", password = ?" : "")
            + " WHERE id_pengguna = ?";
    String sqlPelajar = "UPDATE pelajar SET kelas = ? WHERE id_pengguna = ?";

    try (java.sql.Connection conn = config.EducateDB.getConnection()) {
        conn.setAutoCommit(false);
        try (java.sql.PreparedStatement ps1 = conn.prepareStatement(sqlPengguna);
             java.sql.PreparedStatement ps2 = conn.prepareStatement(sqlPelajar)) {
            int i = 1;
            ps1.setString(i++, p.getNama());
            ps1.setString(i++, p.getEmail());
            if (gantiPassword) ps1.setString(i++, passwordBaru);
            ps1.setInt(i, p.getIdPengguna());
            ps1.executeUpdate();

            ps2.setInt(1, p.getKelas());
            ps2.setInt(2, p.getIdPengguna());
            ps2.executeUpdate();

            conn.commit();
            return true;
        } catch (java.sql.SQLException ex) {
            conn.rollback();
            throw ex;
        }
        } catch (java.sql.SQLException e) {
        e.printStackTrace();
        return false;
    }
}   
    
    public boolean updateProfilPengajar(int idPengguna, String nama, String email,
                                    String passwordBaru, String status) {
    boolean gantiPassword = passwordBaru != null && !passwordBaru.isEmpty();
    String sqlPengguna = "UPDATE pengguna SET nama = ?, email = ?"
            + (gantiPassword ? ", password = ?" : "")
            + " WHERE id_pengguna = ?";
    String sqlPengajar = "UPDATE pengajar SET status_pengajar = ? WHERE id_pengguna = ?";

    try (java.sql.Connection conn = config.EducateDB.getConnection()) {
        conn.setAutoCommit(false);
        try (java.sql.PreparedStatement ps1 = conn.prepareStatement(sqlPengguna);
             java.sql.PreparedStatement ps2 = conn.prepareStatement(sqlPengajar)) {

            int i = 1;
            ps1.setString(i++, nama);
            ps1.setString(i++, email);
            if (gantiPassword) ps1.setString(i++, passwordBaru);
            ps1.setInt(i, idPengguna);
            ps1.executeUpdate();

            ps2.setString(1, status);
            ps2.setInt(2, idPengguna);
            ps2.executeUpdate();

            conn.commit();
            return true;
        } catch (java.sql.SQLException e) {
            conn.rollback();
            e.printStackTrace();
            return false;
        }
    } catch (java.sql.SQLException e) {
        e.printStackTrace();
        return false;
    }
}

    public boolean usernameSudahAda(String username) {
        return ada("SELECT 1 FROM pengguna WHERE username = ?", username);
    }

public boolean emailSudahAda(String email) {
    return ada("SELECT 1 FROM pengguna WHERE email = ?", email);
}

private boolean ada(String sql, String nilai) {
    try (Connection conn = EducateDB.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, nilai);
        try (ResultSet rs = ps.executeQuery()) {
            return rs.next();
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return true;   // kalau query gagal, anggap ada supaya tidak membuat akun ganda
    }
}

public boolean registrasiPelajar(String nama, String username, String email,
                                 String password, String jenisKelamin,
                                 java.time.LocalDate tanggalLahir, int kelas) {
    String sqlPengguna = "INSERT INTO pengguna "
        + "(nama, email, username, password, role, jenis_kelamin, tanggal_lahir) "
        + "VALUES (?, ?, ?, ?, 'Pelajar', ?, ?)";
    String sqlPelajar = "INSERT INTO pelajar (id_pengguna, kelas, tanggal_daftar) "
        + "VALUES (?, ?, CURDATE())";

    try (Connection conn = EducateDB.getConnection()) {
        conn.setAutoCommit(false);
        try {
            int id;
            try (PreparedStatement ps = conn.prepareStatement(
                    sqlPengguna, java.sql.Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, nama);
                ps.setString(2, email);
                ps.setString(3, username);
                ps.setString(4, password);
                ps.setString(5, jenisKelamin);
                ps.setDate(6, java.sql.Date.valueOf(tanggalLahir));
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    rs.next();
                    id = rs.getInt(1);
                }
            }
            try (PreparedStatement ps = conn.prepareStatement(sqlPelajar)) {
                ps.setInt(1, id);
                ps.setInt(2, kelas);
                ps.executeUpdate();
            }
            conn.commit();
            return true;
        } catch (SQLException ex) {
            conn.rollback();
            throw ex;
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
    
    
}
}
