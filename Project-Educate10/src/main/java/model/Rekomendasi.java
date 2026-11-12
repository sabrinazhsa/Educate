/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author HP VICTUS
 */
public class Rekomendasi {
    private int idRekomendasi;
    private int nilaiMinimum;
    private int nilaiMaksimum;
    private String isiRekomendasi;
    private Pengajar pengajar;
    private HasilBelajar hasilBelajar;

    public Rekomendasi(int idRekomendasi, int nilaiMinimum, int nilaiMaksimum,
                        String isiRekomendasi, Pengajar pengajar, HasilBelajar hasilBelajar) {
        this.idRekomendasi = idRekomendasi;
        this.nilaiMinimum = nilaiMinimum;  
        this.nilaiMaksimum = nilaiMaksimum;
        this.isiRekomendasi = isiRekomendasi;
        this.pengajar = pengajar;
        this.hasilBelajar = hasilBelajar;
    }

    public Rekomendasi() {
    }

    public int getIdRekomendasi() {
        return idRekomendasi;
    }

    public void setIdRekomendasi(int idRekomendasi) {
        this.idRekomendasi = idRekomendasi;
    }

    public int getNilaiMinimum() {
        return nilaiMinimum;
    }

    public void setNilaiMinimum(int nilaiMinimum) {
        this.nilaiMinimum = nilaiMinimum;
    }

    public int getNilaiMaksimum() {
        return nilaiMaksimum;
    }

    public void setNilaiMaksimum(int nilaiMaksimum) {
        this.nilaiMaksimum = nilaiMaksimum;
    }

    public String getIsiRekomendasi() {
        return isiRekomendasi;
    }

    public void setIsiRekomendasi(String isiRekomendasi) {
        this.isiRekomendasi = isiRekomendasi;
    }

    public Pengajar getPengajar() {
        return pengajar;
    }

    public void setPengajar(Pengajar pengajar) {
        this.pengajar = pengajar;
    }

    public HasilBelajar getHasilBelajar() {
        return hasilBelajar;
    }

    public void setHasilBelajar(HasilBelajar hasilBelajar) {
        this.hasilBelajar = hasilBelajar;
    }

}