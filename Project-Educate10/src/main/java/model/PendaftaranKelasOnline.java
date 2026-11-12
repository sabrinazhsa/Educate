/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.time.LocalDate;

/**
 *
 * @author HP VICTUS
 */
public class PendaftaranKelasOnline {
    public static final String lunas = "Lunas";
    public static final String pending = "Pending";
    
    private int idPendaftaran;
    private LocalDate tanggalDaftar;
    private Double hargaBayar;         
    private String statusPembayaran;  
    private KelasOnline kelasOnline;
    private Pelajar pelajar;

    public PendaftaranKelasOnline() {
    }

    public PendaftaranKelasOnline(int idPendaftaran, LocalDate tanggalDaftar, Double hargaBayar, String statusPembayaran, KelasOnline kelasOnline, Pelajar pelajar) {
        this.idPendaftaran = idPendaftaran;
        this.tanggalDaftar = tanggalDaftar;
        this.hargaBayar = hargaBayar;
        this.statusPembayaran = statusPembayaran;
        this.kelasOnline = kelasOnline;
        this.pelajar = pelajar;
    }

    public int getIdPendaftaran() {
        return idPendaftaran;
    }

    public void setIdPendaftaran(int idPendaftaran) {
        this.idPendaftaran = idPendaftaran;
    }

    public LocalDate getTanggalDaftar() {
        return tanggalDaftar;
    }

    public void setTanggalDaftar(LocalDate tanggalDaftar) {
        this.tanggalDaftar = tanggalDaftar;
    }

    public Double getHargaBayar() {
        return hargaBayar;
    }

    public void setHargaBayar(Double hargaBayar) {
        this.hargaBayar = hargaBayar;
    }

    public String getStatusPembayaran() {
        return statusPembayaran;
    }

    public void setStatusPembayaran(String statusPembayaran) {
        this.statusPembayaran = statusPembayaran;
    }

    public KelasOnline getKelasOnline() {
        return kelasOnline;
    }

    public void setKelasOnline(KelasOnline kelasOnline) {
        this.kelasOnline = kelasOnline;
    }

    public Pelajar getPelajar() {
        return pelajar;
    }

    public void setPelajar(Pelajar pelajar) {
        this.pelajar = pelajar;
    }
}