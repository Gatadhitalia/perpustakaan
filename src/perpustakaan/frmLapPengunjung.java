/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package perpustakaan;

import java.awt.event.WindowAdapter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
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
public class frmLapPengunjung extends javax.swing.JFrame {
    Connection Con; 
    ResultSet RsBuku; 
    Statement stm;
    
    String tgl1, tgl2;
    
    private Object[][] dataTable = null; 
    private String[] header = {"NIM","Nama","Tanggal Kedatangan"};
    DefaultTableModel tableModel = new DefaultTableModel(new Object[][]{},header);
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");

    /**
     * Creates new form frmPengunjung
     */
    public frmLapPengunjung() {
        initComponents();
        open_db(); 
        baca_data(); 
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        dtAwal.setDateFormat(sdf);
        dtAkhir.setDateFormat(sdf);
        setTombol(true);
    }
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
    private void baca_data(){
        try{
            stm = Con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            RsBuku = stm.executeQuery("select * from pengunjung order by nim");
            
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
                dataTable[x][0] = RsBuku.getString("nim");
                dataTable[x][1] = RsBuku.getString("nm_mhs");
                dataTable[x][2] = RsBuku.getString("tanggal");
                x++;
            }
            tblPengunjung.setModel(new DefaultTableModel(dataTable,header));
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
            tgl1=dtAwal.getText();
            tgl2=dtAkhir.getText();
            String query = "select * from pengunjung where date(tanggal)>= date('"+ tgl1+"') and date(tanggal)<= date('"+tgl2+"') order by tanggal desc";
            stm = Con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
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
                dataTable[x][0] = RsBuku.getString("nim");
                dataTable[x][1] = RsBuku.getString("nm_mhs");
                dataTable[x][2] = RsBuku.getString("tanggal");
                x++;
            }
            tblPengunjung.setModel(new DefaultTableModel(dataTable,header));
            if(baris>=1){
                JOptionPane.showMessageDialog(null, "Data Ditemukan");
            }else{
                JOptionPane.showMessageDialog(null, "Data Tidak Ditemukan");
            }
        //aturTabel();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }catch (NumberFormatException e) {
        // Penanganan kesalahan
        System.out.println("String tidak valid: " + e.getMessage());
        }         
    }
    
    //mengset tombol on/off
    private void setTombol(boolean t){
        btnSearch.setEnabled(t);
        cmdCetak.setEnabled(t);
        cmdTampil.setEnabled(t);
        cmdKeluar.setEnabled(t);
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
        btnSearch = new javax.swing.JButton();
        cmdTampil = new javax.swing.JButton();
        dtAwal = new datechooser.beans.DateChooserCombo();
        dtAkhir = new datechooser.beans.DateChooserCombo();
        cmdCetak = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPengunjung = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        cmdKeluar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

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

        cmdTampil.setText("Tampil");
        cmdTampil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmdTampilMouseClicked(evt);
            }
        });
        cmdTampil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdTampilActionPerformed(evt);
            }
        });

        cmdCetak.setText("Cetak");
        cmdCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCetakActionPerformed(evt);
            }
        });

        tblPengunjung.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NIM", "Nama", "Tanggal Kedatangan"
            }
        ));
        tblPengunjung.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPengunjungMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPengunjung);

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("s/d");

        cmdKeluar.setText("Keluar");
        cmdKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdKeluarActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Gill Sans Ultra Bold Condensed", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("LAPORAN DATA PENGUNJUNG PERPUSTAKAAN");
        jLabel1.setAutoscrolls(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 763, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(200, 200, 200)
                        .addComponent(cmdTampil)
                        .addGap(18, 18, 18)
                        .addComponent(cmdCetak, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cmdKeluar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(162, 162, 162)
                        .addComponent(dtAwal, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addGap(27, 27, 27)
                        .addComponent(dtAkhir, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSearch)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnSearch)
                    .addComponent(dtAkhir, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dtAwal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdTampil)
                    .addComponent(cmdCetak)
                    .addComponent(cmdKeluar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tblPengunjungMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPengunjungMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblPengunjungMouseClicked

    private void cmdKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdKeluarActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_cmdKeluarActionPerformed

    private void btnSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchMouseClicked
        // TODO add your handling code here:
        cari_data();
    }//GEN-LAST:event_btnSearchMouseClicked

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        cari_data();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void cmdTampilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdTampilMouseClicked
        // TODO add your handling code here:
        baca_data();
    }//GEN-LAST:event_cmdTampilMouseClicked

    private void cmdTampilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdTampilActionPerformed
        // TODO add your handling code here:
        baca_data();
    }//GEN-LAST:event_cmdTampilActionPerformed

    private void cmdCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCetakActionPerformed
        // TODO add your handling code here:
        try {
  
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/perpustakaan", "root", "");
            String reportPath = "src/perpustakaan/LapPengunjung.jrxml";
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
            java.util.logging.Logger.getLogger(frmLapBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }catch (Exception e) {
            System.out.println("Error : "+e);
        }
    }//GEN-LAST:event_cmdCetakActionPerformed

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
            java.util.logging.Logger.getLogger(frmLapPengunjung.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmLapPengunjung.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmLapPengunjung.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmLapPengunjung.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmLapPengunjung().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton cmdCetak;
    private javax.swing.JButton cmdKeluar;
    private javax.swing.JButton cmdTampil;
    private datechooser.beans.DateChooserCombo dtAkhir;
    private datechooser.beans.DateChooserCombo dtAwal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblPengunjung;
    // End of variables declaration//GEN-END:variables
}
