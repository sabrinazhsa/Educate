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
public class Pengajar extends Pengguna {
    String keahlian;
    String statusPengajar;
   
public Pengajar() {
        super("Pengajar");
    }

public Pengajar(int idPengguna, String nama, String email, String username, 
        String password, String jenisKelamin, LocalDate tanggalLahir, String role, 
        String keahlian, String statusPengajar){
    super(idPengguna, nama, email, username, password, jenisKelamin, tanggalLahir, role);
    this.keahlian = keahlian;
    this.statusPengajar = statusPengajar;
}

    public String getKeahlian() {
        return keahlian;
    }

    public void setKeahlian(String keahlian) {
        this.keahlian = keahlian;
    }

    public String getStatusPengajar() {
        return statusPengajar;
    }

    public void setStatusPengajar(String statusPengajar) {
        this.statusPengajar = statusPengajar;
    }
}