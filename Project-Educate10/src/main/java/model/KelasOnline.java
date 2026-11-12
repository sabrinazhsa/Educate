/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author HP VICTUS
 */
public class KelasOnline {
    private int idKelas;
    private String namaKelas;
    private String deskripsiKelas;
    private LocalDate tanggalKelas;
    private LocalTime waktuMulai;
    private LocalTime waktuSelesai;
    private String linkKelas;
    private Double harga;               
    private String statusKelas;      
    private int kuotaMaksimal;
    private Pengajar pengajar;

    public KelasOnline(int idKelas, String namaKelas, String deskripsiKelas,
                       LocalDate tanggalKelas, LocalTime waktuMulai, LocalTime waktuSelesai,
                       String linkKelas, Double harga, String statusKelas,
                       int kuotaMaksimal, Pengajar pengajar) {
        this.idKelas = idKelas;
        this.namaKelas = namaKelas;
        this.deskripsiKelas = deskripsiKelas;
        this.tanggalKelas = tanggalKelas;
        this.waktuMulai = waktuMulai;
        this.waktuSelesai = waktuSelesai;
        this.linkKelas = linkKelas;
        this.harga = harga;
        this.statusKelas = statusKelas;
        this.kuotaMaksimal = kuotaMaksimal;
        this.pengajar = pengajar;
    }

    public KelasOnline() {
    }

    public int getIdKelas() {
        return idKelas;
    }

    public void setIdKelas(int idKelas) {
        this.idKelas = idKelas;
    }

    public String getNamaKelas() {
        return namaKelas;
    }

    public void setNamaKelas(String namaKelas) {
        this.namaKelas = namaKelas;
    }

    public String getDeskripsiKelas() {
        return deskripsiKelas;
    }

    public void setDeskripsiKelas(String deskripsiKelas) {
        this.deskripsiKelas = deskripsiKelas;
    }

    public LocalDate getTanggalKelas() {
        return tanggalKelas;
    }

    public void setTanggalKelas(LocalDate tanggalKelas) {
        this.tanggalKelas = tanggalKelas;
    }

    public LocalTime getWaktuMulai() {
        return waktuMulai;
    }

    public void setWaktuMulai(LocalTime waktuMulai) {
        this.waktuMulai = waktuMulai;
    }

    public LocalTime getWaktuSelesai() {
        return waktuSelesai;
    }

    public void setWaktuSelesai(LocalTime waktuSelesai) {
        this.waktuSelesai = waktuSelesai;
    }

    public String getLinkKelas() {
        return linkKelas;
    }

    public void setLinkKelas(String linkKelas) {
        this.linkKelas = linkKelas;
    }

    public Double getHarga() {
        return harga;
    }

    public void setHarga(Double harga) {
        this.harga = harga;
    }

    public String getStatusKelas() {
        return statusKelas;
    }

    public void setStatusKelas(String statusKelas) {
        this.statusKelas = statusKelas;
    }

    public int getKuotaMaksimal() {
        return kuotaMaksimal;
    }

    public void setKuotaMaksimal(int kuotaMaksimal) {
        this.kuotaMaksimal = kuotaMaksimal;
    }

    public Pengajar getPengajar() {
        return pengajar;
    }

    public void setPengajar(Pengajar pengajar) {
        this.pengajar = pengajar;
    }
}