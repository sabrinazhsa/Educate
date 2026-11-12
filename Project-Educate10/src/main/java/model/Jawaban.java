/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author HP VICTUS
 */
public class Jawaban {
    public static final String benar = "Benar";
    public static final String salah = "Salah";

    private int idJawaban;
    private int idLatihan;
    private int idPelajar;
    private String pilihanJawaban;
    private String kunciJawaban;
    private String opsi;            
    private String status;
    private LatihanSoal latihanSoal;


    public Jawaban(int idJawaban, int idLatihan, int idPelajar, String pilihanJawaban, String kunciJawaban, String opsi, String status, LatihanSoal latihanSoal) {
        this.idJawaban = idJawaban;
        this.idLatihan = idLatihan;
        this.idPelajar = idPelajar;
        this.pilihanJawaban = pilihanJawaban;
        this.kunciJawaban = kunciJawaban;
        this.opsi = opsi;
        this.status = status;
        this.latihanSoal = latihanSoal;
    }

    public Jawaban() {
    }

    // Getter dan Setter
    public int getIdJawaban() {
        return idJawaban;
    }

    public void setIdJawaban(int idJawaban) {
        this.idJawaban = idJawaban;
    }

    public int getIdLatihan() {
        return idLatihan;
    }

    public void setIdLatihan(int idLatihan) {
        this.idLatihan = idLatihan;
    }

    public int getIdPelajar() {
        return idPelajar;
    }

    public void setIdPelajar(int idPelajar) {
        this.idPelajar = idPelajar;
    }

    public String getPilihanJawaban() {
        return pilihanJawaban;
    }

    public void setPilihanJawaban(String pilihanJawaban) {
        this.pilihanJawaban = pilihanJawaban;
    }

    public String getKunciJawaban() {
        return kunciJawaban;
    }

    public void setKunciJawaban(String kunciJawaban) {
        this.kunciJawaban = kunciJawaban;
    }

    public String getOpsi() {
        return opsi;
    }

    public void setOpsi(String opsi) {
        this.opsi = opsi;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LatihanSoal getLatihanSoal() {
        return latihanSoal;
    }

    public void setLatihanSoal(LatihanSoal latihanSoal) {
        this.latihanSoal = latihanSoal;
    }
}