/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author HP VICTUS
 */
public class DetailLatihan {
    public static final String benar = "Benar";
    public static final String salah = "Salah";

    private int idDetailLatihan;
    private String jawabanPelajar;       
    private String statusJawaban;      
    private LatihanSoal latihanSoal;
    private HasilBelajar hasilBelajar;


    public DetailLatihan(int idDetailLatihan, String jawabanPelajar, String statusJawaban, LatihanSoal latihanSoal, HasilBelajar hasilBelajar) {
        this.idDetailLatihan = idDetailLatihan;
        this.jawabanPelajar = jawabanPelajar;
        this.statusJawaban = statusJawaban;
        this.latihanSoal = latihanSoal;
        this.hasilBelajar = hasilBelajar;
    }

   
    public DetailLatihan() {
    }

    public int getIdDetailLatihan() {
        return idDetailLatihan;
    }

    public void setIdDetailLatihan(int idDetailLatihan) {
        this.idDetailLatihan = idDetailLatihan;
    }

    public String getJawabanPelajar() {
        return jawabanPelajar;
    }

    public void setJawabanPelajar(String jawabanPelajar) {
        this.jawabanPelajar = jawabanPelajar;
    }

    public String getStatusJawaban() {
        return statusJawaban;
    }

    public void setStatusJawaban(String statusJawaban) {
        this.statusJawaban = statusJawaban;
    }

    public LatihanSoal getLatihanSoal() {
        return latihanSoal;
    }

    public void setLatihanSoal(LatihanSoal latihanSoal) {
        this.latihanSoal = latihanSoal;
    }

    public HasilBelajar getHasilBelajar() {
        return hasilBelajar;
    }

    public void setHasilBelajar(HasilBelajar hasilBelajar) {
        this.hasilBelajar = hasilBelajar;
    }    
}