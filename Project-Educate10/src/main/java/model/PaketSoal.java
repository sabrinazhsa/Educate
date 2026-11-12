/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author HP VICTUS
 */
public class PaketSoal {
    private int idPaketSoal;
    private String namaPaketSoal;
    private String tingkatKesulitan;


    public PaketSoal(int idPaketSoal, String namaPaketSoal, String tingkatKesulitan) {
        this.idPaketSoal = idPaketSoal;
        this.namaPaketSoal = namaPaketSoal;
        this.tingkatKesulitan = tingkatKesulitan;
    }

    public PaketSoal() {
    }

    public int getIdPaketSoal() {
        return idPaketSoal;
    }

    public void setIdPaketSoal(int idPaketSoal) {
        this.idPaketSoal = idPaketSoal;
    }

    public String getNamaPaketSoal() {
        return namaPaketSoal;
    }

    public void setNamaPaketSoal(String namaPaketSoal) {
        this.namaPaketSoal = namaPaketSoal;
    }

    public String getTingkatKesulitan() {
        return tingkatKesulitan;
    }

    public void setTingkatKesulitan(String tingkatKesulitan) {
        this.tingkatKesulitan = tingkatKesulitan;
    }
}