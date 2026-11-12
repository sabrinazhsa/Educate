package controller;

import dao.HasilBelajarDao;
import java.time.LocalDate;
import java.util.List;
import model.DetailLatihan;
import model.HasilBelajar;
import model.Pelajar;

public class HasilBelajarController {

    private final HasilBelajarDao hasilDao = new HasilBelajarDao();

    public boolean simpanHasilBelajarPelajar(int idPelajar, int totalSoal, int jumlahBenar,
                                             double skor, List<DetailLatihan> listDetailJawaban) {
        // DAO membaca ID dari objek Pelajar, jadi objeknya harus dibuat dulu
        Pelajar pelajar = new Pelajar();
        pelajar.setIdPengguna(idPelajar);

        HasilBelajar hasil = new HasilBelajar();
        hasil.setTanggalPengerjaan(LocalDate.now());
        hasil.setTotalSoal(totalSoal);
        hasil.setJumlahBenar(jumlahBenar);
        hasil.setSkor((int) Math.round(skor));
        hasil.setPelajar(pelajar);

        // 1. Simpan data hasil belajar (ID hasil terisi otomatis oleh DAO)
        if (!hasilDao.insertHasil(hasil)) {
            return false;
        }

        // 2. Simpan jawaban tiap soal, terhubung ke hasil yang baru dibuat
        for (DetailLatihan detail : listDetailJawaban) {
            detail.setHasilBelajar(hasil);
            if (!hasilDao.insertDetail(detail)) {
                return false;
            }
        }
        return true;
    }

    public void tampilkanRiwayatPelajar(int idPelajar) {
        System.out.println("Memuat riwayat hasil belajar untuk pelajar ID: " + idPelajar);
    }
}