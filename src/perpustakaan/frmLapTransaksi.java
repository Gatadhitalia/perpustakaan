/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package perpustakaan;

import datechooser.beans.DateChooserCombo;
import java.awt.event.WindowAdapter;
import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import javafx.stage.WindowEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;


/**
 *
 * @author lenovo
 */
public class frmLapTransaksi extends javax.swing.JFrame {
    Connection Con; 
    ResultSet RsBuku; 
    Statement stm;
     
    String cari;
    private Object[][] dataTable = null; 
    private String[] header = {"Kode Transaksi","Kode Pengembalian","NIM","Nama","Kode Buku","Judul Buku","Kode Peminjaman","Tanggal Peminjaman","Tanggal Pengembalian", "Jumlah", "Total"};
    DefaultTableModel tableModel = new DefaultTableModel(new Object[][]{},header);
    
    /**
     * Creates new form frmLapBuku
     */
    public frmLapTransaksi() {
        initComponents();
        open_db();
        baca_data();
    }
    //method membuka database server, user, pass, database disesuaikan
    private void open_db(){ 
        try{
            KoneksiMysql kon = new KoneksiMysql
            ("localhost","root","","perpustakaan");
            Con = kon.getConnection();
            System.out.println("Berhasil koneksi dengan database");
        }catch (Exception e) {
            System.out.println("Error : "+e);
        }
    }
    //method baca data dari Mysql dimasukkan ke table pada form
    private void baca_data(){
        try{
            String query = "select * from transaksi order by kd_transaksi";
            stm = Con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            RsBuku = stm.executeQuery(query);
            
            ResultSetMetaData meta = RsBuku.getMetaData();
            int col = meta.getColumnCount();
            int baris = 0;
            while(RsBuku.next()) {
                baris = RsBuku.getRow();
            }
            
            dataTable = new Object[baris][col];
            int x = 0;
            RsBuku.beforeFirst();
            while(RsBuku.next()) {
                dataTable[x][0] = RsBuku.getString("kd_transaksi");
                dataTable[x][1] = RsBuku.getString("kd_kembali");
                dataTable[x][2] = RsBuku.getString("nim");
                dataTable[x][3] = RsBuku.getString("nm_mhs");
                dataTable[x][4] = RsBuku.getString("kd_buku");
                dataTable[x][5] = RsBuku.getString("judul_buku");
                dataTable[x][6] = RsBuku.getString("kd_pinjam");
                dataTable[x][7] = RsBuku.getString("tgl_pinjam");
                dataTable[x][8] = RsBuku.getString("tgl_kembali");
                dataTable[x][9] = RsBuku.getString("jml");
                dataTable[x][10] = RsBuku.getString("total");
                x++;
            }
            tblTransaksi.setModel(new DefaultTableModel(dataTable,header)); 
            if(baris>=1){
                JOptionPane.showMessageDialog(null, "Data Ditemukan");
            }else{
                JOptionPane.showMessageDialog(null, "Data Tidak Ditemukan");
            }
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
     
    private void cari_data(){
        try{
            cari = txtSearch.getText();
            stm = Con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            RsBuku = stm.executeQuery("select * from transaksi WHERE kd_transaksi LIKE '%"+cari+"%' "
                    + "or kd_kembali LIKE '%"+cari+"%'"
                    + "or nim LIKE '%"+cari+"%'"
                    + "or nm_mhs LIKE '%"+cari+"%'"
                    + "or kd_buku LIKE '%"+cari+"%'"
                    + "or kd_pinjam LIKE '%"+cari+"%'"
                    + "or judul_buku LIKE '%"+cari+"%' ");
            
            ResultSetMetaData meta = RsBuku.getMetaData();
            int col = meta.getColumnCount();
            int baris = 0;
            while(RsBuku.next()) {
                baris = RsBuku.getRow();
            }
            
            dataTable = new Object[baris][col];
            int x = 0;
            RsBuku.beforeFirst();
            while(RsBuku.next()) {
                dataTable[x][0] = RsBuku.getString("kd_transaksi");
                dataTable[x][1] = RsBuku.getString("kd_kembali");
                dataTable[x][2] = RsBuku.getString("nim");
                dataTable[x][3] = RsBuku.getString("nm_mhs");
                dataTable[x][4] = RsBuku.getString("kd_buku");
                dataTable[x][5] = RsBuku.getString("judul_buku");
                dataTable[x][6] = RsBuku.getString("kd_pinjam");
                dataTable[x][7] = RsBuku.getString("tgl_pinjam");
                dataTable[x][8] = RsBuku.getString("tgl_kembali");
                dataTable[x][9] = RsBuku.getString("jml");
                dataTable[x][10] = RsBuku.getString("total");
                x++;
            }
            tblTransaksi.setModel(new DefaultTableModel(dataTable,header));
            if(baris>=1){
                JOptionPane.showMessageDialog(null, "Data Ditemukan");
            }else{
                JOptionPane.showMessageDialog(null, "Data Tidak Ditemukan");
            }
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
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

        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        jPanel1 = new javax.swing.JPanel();
        txtSearch = new javax.swing.JTextField();
        cmdCetak = new javax.swing.JButton();
        cmdKeluar = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblTransaksi = new javax.swing.JTable();
        cmdTampil = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jMenu1.setText("jMenu1");

        jMenu2.setText("jMenu2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        cmdCetak.setText("Cetak");
        cmdCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCetakActionPerformed(evt);
            }
        });

        cmdKeluar.setText("Keluar");
        cmdKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdKeluarActionPerformed(evt);
            }
        });

        btnSearch.setText("Search");
        btnSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSearchMouseClicked(evt);
            }
        });
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        tblTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode Transaksi", "Kode Pengembalian", "NIM", "Nama", "Kode Buku", "Judul Buku", "Kode Peminjaman", "Tanggal Peminjaman", "Tanggal Pengembalian", "Kerusakan", "Total"
            }
        ));
        tblTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTransaksiMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblTransaksi);

        cmdTampil.setText("Tampil");
        cmdTampil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdTampilActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Gill Sans Ultra Bold Condensed", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("LAPORAN DATA TRANSAKSI");
        jLabel1.setAutoscrolls(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSearch)
                .addGap(233, 233, 233))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 746, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(250, 250, 250)
                        .addComponent(cmdTampil, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(cmdCetak, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(cmdKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(165, 165, 165)
                        .addComponent(jLabel1)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdTampil)
                    .addComponent(cmdCetak)
                    .addComponent(cmdKeluar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cmdCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCetakActionPerformed
        // TODO add your handling code here:
        try {       
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/perpustakaan", "root", "");
            String reportPath = "src/perpustakaan/LapTransaksi.jrxml";
            JasperReport jr = JasperCompileManager.compileReport(reportPath);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, con);
//            JasperViewer.viewReport(jp);
            JasperViewer jasperViewer=new JasperViewer(jp,false);
            jasperViewer.setVisible(true);
            jasperViewer.addWindowListener(new WindowAdapter(){
                public void WindowClosed(WindowEvent e){
                    setVisible(true);
                }});
            con.close();
        } catch (JRException ex) {
            java.util.logging.Logger.getLogger(frmLapTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }catch (Exception e) {
            System.out.println("Error : "+e);
        }
    }//GEN-LAST:event_cmdCetakActionPerformed

    private void cmdKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdKeluarActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_cmdKeluarActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        cari_data();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void cmdTampilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdTampilActionPerformed
        // TODO add your handling code here:
        baca_data();
    }//GEN-LAST:event_cmdTampilActionPerformed

    private void btnSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchMouseClicked
        // TODO add your handling code here:
        cari_data();
    }//GEN-LAST:event_btnSearchMouseClicked

    private void tblTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTransaksiMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblTransaksiMouseClicked

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmLapTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmLapTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmLapTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmLapTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmLapTransaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton cmdCetak;
    private javax.swing.JButton cmdKeluar;
    private javax.swing.JButton cmdTampil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tblTransaksi;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables

}