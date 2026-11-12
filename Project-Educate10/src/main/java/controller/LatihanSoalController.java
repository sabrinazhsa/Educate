package controller;

import model.LatihanSoal;
import model.Materi;
import model.PaketSoal;
import model.Jawaban;
import dao.LatihanSoalDao;
import dao.JawabanDao;
import java.util.List;

public class LatihanSoalController {

    private LatihanSoalDao latihanSoalDao = new LatihanSoalDao();
    private JawabanDao jawabanDao = new JawabanDao();


    public boolean buatSoalDanJawaban(String pertanyaan, int idMateri, int idPaketSoal, List<Jawaban> daftarJawaban) {
        LatihanSoal soal = new LatihanSoal();
        soal.setPertanyaan(pertanyaan);
  
        Materi materi = new Materi();
        materi.setIdMateri(idMateri);
        soal.setMateri(materi);
        

        PaketSoal paketSoal = new PaketSoal();
        paketSoal.setIdPaketSoal(idPaketSoal);
        soal.setPaketSoal(paketSoal);
        

        boolean soalBerhasil = latihanSoalDao.insert(soal);
        
        if (soalBerhasil) {

            for (Jawaban jb : daftarJawaban) {
                jb.setLatihanSoal(soal); // Menghubungkan jawaban dengan soal yang baru dibuat
                jawabanDao.insert(jb);
            }
            System.out.println("Soal latihan dan jawaban berhasil ditambahkan.");
            return true;
        } else {
            System.out.println("Gagal menambahkan soal latihan.");
            return false;
        }
    }
}