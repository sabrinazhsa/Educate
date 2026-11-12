/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP VICTUS
 */
public class HasilBelajar {
    private int idHasil;
    private LocalDate tanggalPengerjaan;
    private int totalSoal;
    private int jumlahBenar;
    private int skor;         
    private Pelajar pelajar;
    private int pelajar_id_pengguna;
    private List<DetailLatihan> daftarDetail = new ArrayList<>();


    public HasilBelajar() {
    }

    // Konstruktor dengan parameter
    public HasilBelajar(int idHasil, LocalDate tanggalPengerjaan, int totalSoal,
                        int jumlahBenar, int skor, Pelajar pelajar) {
        this.idHasil = idHasil;
        this.tanggalPengerjaan = tanggalPengerjaan;
        this.totalSoal = totalSoal;
        this.jumlahBenar = jumlahBenar;
        this.skor = skor;
        this.pelajar = pelajar;
    }

    public int getIdHasil() {
        return idHasil;
    }

    public void setIdHasil(int idHasil) {
        this.idHasil = idHasil;
    }

    public LocalDate getTanggalPengerjaan() {
        return tanggalPengerjaan;
    }

    public void setTanggalPengerjaan(LocalDate tanggalPengerjaan) {
        this.tanggalPengerjaan = tanggalPengerjaan;
    }

    public int getTotalSoal() {
        return totalSoal;
    }

    public void setTotalSoal(int totalSoal) {
        this.totalSoal = totalSoal;
    }

    public int getJumlahBenar() {
        return jumlahBenar;
    }

    public void setJumlahBenar(int jumlahBenar) {
        this.jumlahBenar = jumlahBenar;
    }

    public int getSkor() {
        return skor;
    }

    public void setSkor(int skor) {
        this.skor = skor;
    }

    public Pelajar getPelajar() {
        return pelajar;
    }

    public void setPelajar(Pelajar pelajar) {
        this.pelajar = pelajar;
    }

    public int getPelajarIdPengguna() {
        return pelajar_id_pengguna;
    }

    public void setPelajarIdPengguna(int pelajar_id_pengguna) {
        this.pelajar_id_pengguna = pelajar_id_pengguna;
    }

    public List<DetailLatihan> getDaftarDetail() {
        return daftarDetail;
    }

    public void setDaftarDetail(List<DetailLatihan> daftarDetail) {
        this.daftarDetail = daftarDetail;
    }
}