/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author HP VICTUS
 */
public class Materi {
    private int idMateri;
    private String namaMateri;
    private String topik;          
    private String tipeMateri;    
    private String deskripsiMateri;
    private Pengajar pengajar;

    public Materi(int idMateri, String namaMateri, String topik, String tipeMateri, String deskripsiMateri, Pengajar pengajar) {
        this.idMateri = idMateri;
        this.namaMateri = namaMateri;
        this.topik = topik;
        this.tipeMateri = tipeMateri;
        this.deskripsiMateri = deskripsiMateri;
        this.pengajar = pengajar;
    }

    public Materi() {
    }

    public int getIdMateri() {
        return idMateri;
    }

    public void setIdMateri(int idMateri) {
        this.idMateri = idMateri;
    }

    public String getNamaMateri() {
        return namaMateri;
    }

    public void setNamaMateri(String namaMateri) {
        this.namaMateri = namaMateri;
    }

    public String getTopik() {
        return topik;
    }

    public void setTopik(String topik) {
        this.topik = topik;
    }

    public String getTipeMateri() {
        return tipeMateri;
    }

    public void setTipeMateri(String tipeMateri) {
        this.tipeMateri = tipeMateri;
    }

    public String getDeskripsiMateri() {
        return deskripsiMateri;
    }

    public void setDeskripsiMateri(String deskripsiMateri) {
        this.deskripsiMateri = deskripsiMateri;
    }

    public Pengajar getPengajar() {
        return pengajar;
    }

    public void setPengajar(Pengajar pengajar) {
        this.pengajar = pengajar;
    }
}