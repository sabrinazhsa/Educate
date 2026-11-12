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
public class Pelajar extends Pengguna {
    private LocalDate tanggalDaftar;
    private int kelas;
    
    public Pelajar() {
        super("Pelajar");
    }

public Pelajar(int idPengguna, String nama, String email, String username, 
        String password, String jenisKelamin, LocalDate tanggalLahir, 
        String role, String keahlian, String statusPengajar, Double gaji){
    super(idPengguna, nama, email, username, password, jenisKelamin, tanggalLahir, role);
    this.tanggalDaftar = tanggalDaftar;
    this.kelas = kelas;
}

    public LocalDate getTanggalDaftar() {
        return tanggalDaftar;
    }

    public void setTanggalDaftar(LocalDate tanggalDaftar) {
        this.tanggalDaftar = tanggalDaftar;
    }

    public int getKelas() {
        return kelas;
    }

    public void setKelas(int kelas) {
        this.kelas = kelas;
    }    
}