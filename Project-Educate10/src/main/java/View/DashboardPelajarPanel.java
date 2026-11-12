/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

/**
 *
 * @author HP VICTUS
 */
public class DashboardPelajarPanel extends javax.swing.JPanel {

       private View.DetailMateriPanel halamanDetailMateri;
       private View.DetailLatihanSoalPanel halamanDetailLatihan;
       private View.RiwayatPelajarPanel halamanHasil;
       private View.HalamanPelajarPanel halamanBeranda;

    /**
     * Creates new form DashboardPengajarPanel
     */
    public DashboardPelajarPanel() {
    initComponents();
    halamanBeranda = beranda;
    halamanHasil   = hasilBelajar;
    panelKonten.add(halamanBeranda, "beranda");
    panelKonten.add(kelasOnline, "kelas");
    panelKonten.add(halamanHasil, "hasil");
    panelKonten.add(hasilLatihan, "hasilLatihan");
    panelKonten.add(editProfil, "editProfil");
    
    DetailMateriPanel detailMateri = new DetailMateriPanel();
    hubungkanDetailMateri(detailMateri);

    DetailLatihanSoalPanel detailLatihan = new DetailLatihanSoalPanel();
    hubungkanDetailLatihan(detailLatihan);
    
    halamanHasil.setAksiDetail(r -> {
    hasilLatihan.setHasil(r);
    ((java.awt.CardLayout) panelKonten.getLayout()).show(panelKonten, "hasilLatihan");
});
    hasilLatihan.setAksiKembali(() ->
    ((java.awt.CardLayout) panelKonten.getLayout()).show(panelKonten, "hasil"));
    
    hasilLatihan.setAksiPelajari(topik -> {
    detailMateri.setTopik(topik);
    ((java.awt.CardLayout) panelKonten.getLayout()).show(panelKonten, "detailMateri");
});
    hasilLatihan.setAksiUlangi(namaPaket -> {
        for (model.PaketSoal p : new dao.PaketSoalDao().getAll()) {
            if (namaPaket.equals(p.getNamaPaketSoal())) {
                detailLatihan.setPaketSoal(p);
                ((java.awt.CardLayout) panelKonten.getLayout()).show(panelKonten, "detailLatihan");
                return;
            }
        }
    javax.swing.JOptionPane.showMessageDialog(this, "Paket soal tidak ditemukan.");
});


}
    
    
    
    
 
    /**
     * Panggil setelah DetailMateriPanel dimasukkan ke dashboard.
     * GUI Builder tidak perlu diubah; panel didaftarkan sebagai kartu runtime.
     */
    public void hubungkanDetailMateri(DetailMateriPanel detail) {
        this.halamanDetailMateri = detail;
        if (detail.getParent() == panelKonten) panelKonten.remove(detail);
        panelKonten.add(detail, "detailMateri");

        materi.setAksiPilihTopik(topik -> {
            detail.setTopik(topik);
            ((java.awt.CardLayout) panelKonten.getLayout()).show(panelKonten, "detailMateri");
        });
        detail.setAksiKembali(() ->
            ((java.awt.CardLayout) panelKonten.getLayout()).show(panelKonten, "card2")
        );
        panelKonten.revalidate();
        panelKonten.repaint();
    }

    /**
     * Panggil setelah DetailLatihanSoalPanel dimasukkan ke dashboard.
     */
    public void hubungkanDetailLatihan(DetailLatihanSoalPanel detail) {
        this.halamanDetailLatihan = detail;
        if (detail.getParent() == panelKonten) panelKonten.remove(detail);
        panelKonten.add(detail, "detailLatihan");

        latihanSoal.setAksiPilihPaket(paket -> {
            detail.setPaketSoal(paket);
            ((java.awt.CardLayout) panelKonten.getLayout()).show(panelKonten, "detailLatihan");
        });
        latihanSoal.setAksiKembali(() ->
            ((java.awt.CardLayout) panelKonten.getLayout()).show(panelKonten, "card2")
        );
        detail.setAksiKembali(() ->
            ((java.awt.CardLayout) panelKonten.getLayout()).show(panelKonten, "card3")
        );
        panelKonten.revalidate();
        panelKonten.repaint();
    }

    public void tampilkanHalaman(String nama) {
    java.awt.CardLayout cl = (java.awt.CardLayout) panelKonten.getLayout();

    switch (nama) {
        case "beranda":
            halamanBeranda.refreshData();
            cl.show(panelKonten, "beranda");
            break;
        case "materi":
            cl.show(panelKonten, "card2");
            break;
        case "kelas":
            kelasOnline.refreshData();
            cl.show(panelKonten, "kelas");
            break;
        case "paketSoal":
        case "soal":
            latihanSoal.refreshData();
            cl.show(panelKonten, "card3");
            break;
        case "detailMateri":
            if (halamanDetailMateri != null) cl.show(panelKonten, "detailMateri");
            break;
        case "detailLatihan":
            if (halamanDetailLatihan != null) cl.show(panelKonten, "detailLatihan");
            break;
        case "hasilBelajar":
            halamanHasil.refreshData();
            cl.show(panelKonten, "hasil");
            break;
           case "editProfil":
           editProfil.refreshData();
           cl.show(panelKonten, "editProfil");
           break;
        default:
            javax.swing.JOptionPane.showMessageDialog(
                this, "Halaman " + nama + " belum tersedia."
            );
    }
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        sidebarPelajarPanel1 = new View.SidebarPelajarPanel();
        panelKonten = new javax.swing.JPanel();
        materi = new View.MateriPelajarPanel();
        latihanSoal = new View.LatihanSoalPelajarPanel();
        hasilBelajar = new View.RiwayatPelajarPanel();
        editProfil = new View.EditProfilPelajarrPanel();
        beranda = new View.HalamanPelajarPanel();
        hasilLatihan = new View.HasilLatihanSoalPelajarPanel();
        kelasOnline = new View.KelasOnlinePelajarPanel1();

        setLayout(new java.awt.BorderLayout());

        jPanel1.add(sidebarPelajarPanel1);

        add(jPanel1, java.awt.BorderLayout.WEST);

        panelKonten.setLayout(new java.awt.CardLayout());
        panelKonten.add(materi, "card2");
        panelKonten.add(latihanSoal, "card3");
        panelKonten.add(hasilBelajar, "card6");
        panelKonten.add(editProfil, "card7");
        panelKonten.add(beranda, "card7");
        panelKonten.add(hasilLatihan, "card8");
        panelKonten.add(kelasOnline, "card8");

        add(panelKonten, java.awt.BorderLayout.CENTER);

        getAccessibleContext().setAccessibleDescription("");
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private View.HalamanPelajarPanel beranda;
    private View.EditProfilPelajarrPanel editProfil;
    private View.RiwayatPelajarPanel hasilBelajar;
    private View.HasilLatihanSoalPelajarPanel hasilLatihan;
    private javax.swing.JPanel jPanel1;
    private View.KelasOnlinePelajarPanel1 kelasOnline;
    private View.LatihanSoalPelajarPanel latihanSoal;
    private View.MateriPelajarPanel materi;
    private javax.swing.JPanel panelKonten;
    private View.SidebarPelajarPanel sidebarPelajarPanel1;
    // End of variables declaration//GEN-END:variables
}
