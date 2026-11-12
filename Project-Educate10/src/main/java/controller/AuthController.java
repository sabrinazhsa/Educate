package controller;

import dao.PenggunaDao;
import model.Pengguna;

public class AuthController {
    private PenggunaDao dao = new PenggunaDao();

    public Pengguna prosesLogin(String usernameAtauEmail, String password) {
    if (usernameAtauEmail == null || password == null
            || usernameAtauEmail.trim().isEmpty() || password.isEmpty()) {
        System.out.println("Username/Email dan Password tidak boleh kosong!");
        return null;
    }

    Pengguna user = dao.login(usernameAtauEmail.trim(), password);
    if (user != null) {
        SessionManagerController.masuk(user);   // simpan siapa yang login
        System.out.println("Login Berhasil! Selamat datang, " + user.getNama() + " (" + user.getRole() + ")");
    } else {
        System.out.println("Login Gagal! Username/Email atau Password salah.");
    }
    return user;
}

    public void logout() {
        SessionManagerController.keluar();
    }
    
        public String prosesRegistrasi(String nama, String username, String email,
                                   String password, String jenisKelamin,
                                   String tanggalLahir, String kelas) {
        if (dao.usernameSudahAda(username)) {
            return "Username sudah dipakai";
        }
        if (dao.emailSudahAda(email)) {
            return "Email sudah terdaftar";
        }

        java.time.LocalDate tgl;
        int kelasInt;
        try {
            tgl = java.time.LocalDate.parse(tanggalLahir);
            kelasInt = Integer.parseInt(kelas.replaceAll("\\D", ""));
        } catch (RuntimeException e) {
            return "Data tanggal lahir atau kelas tidak valid";
        }

        boolean ok = dao.registrasiPelajar(nama, username, email, password,
                                           jenisKelamin, tgl, kelasInt);
        return ok ? null : "Pendaftaran gagal, coba lagi";
    }
}