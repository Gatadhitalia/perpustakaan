/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package perpustakaan;

import java.util.*;
import java.text.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author lenovo
 */
public class frmTransaksi extends javax.swing.JFrame {
    Connection Con; 
    ResultSet RsBuku; 
    Statement stm; 
    
    double total=0;
    String sStatus;
    String tgl,tgl1; 
    Boolean ada = false; 
    Boolean edit =false; 

    private Object[][] dataTable = null; 
    private String[] header = {"Kode Pengembalian","NIM","Nama","Kode Buku","Judul Buku","Kode Peminjaman","Tanggal Peminjaman","Tanggal Pengembalian"};
    private String[] header1 = {"Kode Transaksi","Kode Pengembalian","NIM","Nama","Kode Buku","Judul Buku","Kode Peminjaman","Tanggal Peminjaman","Tanggal Pengembalian", "Jumlah", "Total"};
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * Creates new form frmPeminjaman
     */
    public frmTransaksi() {
        initComponents();
        open_db(); 
        baca_data();
        baca_data_transaksi();
        aktif(false);
        setTombol(true); 
    }
    private void setField() {
        int row = tblKembali.getSelectedRow();
        txtKdKembali.setText((String)tblKembali.getValueAt(row,0));
        txtNim.setText((String)tblKembali.getValueAt(row,1));
        txtNama.setText((String)tblKembali.getValueAt(row,2));
        txtKode.setText((String)tblKembali.getValueAt(row, 3));
        txtJudul.setText((String)tblKembali.getValueAt(row,4));
        txtKdPinjam.setText((String)tblKembali.getValueAt(row,5));
        dcTgl.setText((String)tblKembali.getValueAt(row,6));
        dcTglKembali.setText((String)tblKembali.getValueAt(row,7));
    }
    private void setField_transaksi() {
        int row = tblTransaksi.getSelectedRow();
        txtKdTransaksi.setText((String)tblTransaksi.getValueAt(row,0));
        txtKdKembali.setText((String)tblTransaksi.getValueAt(row,1));
        txtNim.setText((String)tblTransaksi.getValueAt(row,2));
        txtNama.setText((String)tblTransaksi.getValueAt(row,3));
        txtKode.setText((String)tblTransaksi.getValueAt(row, 4));
        txtJudul.setText((String)tblTransaksi.getValueAt(row,5));
        txtKdPinjam.setText((String)tblTransaksi.getValueAt(row,6));
        Date dcTgl = (Date) tblTransaksi.getValueAt(row, 7);
        Date dcTglKembali = (Date) tblTransaksi.getValueAt(row, 8);
        txtJml.setText((String)tblTransaksi.getValueAt(row,9));
        txtTotal.setText((String)tblTransaksi.getValueAt(row,10));
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
    //metod untuk menentukan format pada tanggal
    private void format_tanggal(){
        String DATE_FORMAT = "yyyy-MM-dd";
        java.text.SimpleDateFormat sdf = new
        java.text.SimpleDateFormat(DATE_FORMAT);
        Calendar c1 = Calendar.getInstance();
        int year = c1.get(Calendar.YEAR);
        int month = c1.get(Calendar.MONTH)+1;
        int day = c1.get(Calendar.DAY_OF_MONTH);
        String tgl=dcTgl.getText();
        String tgl1= dcTglKembali.getText();
    }
    //method baca data dari Mysql dimasukkan ke table pada form
    private void baca_data(){
        try{
            stm = Con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            RsBuku = stm.executeQuery("select * from pengembalian");
            
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
                dataTable[x][0] = RsBuku.getString("kd_kembali");
                dataTable[x][1] = RsBuku.getString("nim");
                dataTable[x][2] = RsBuku.getString("nm_mhs");
                dataTable[x][3] = RsBuku.getString("kd_buku");
                dataTable[x][4] = RsBuku.getString("judul_buku");
                dataTable[x][5] = RsBuku.getString("kd_pinjam");
                dataTable[x][6] = RsBuku.getString("tgl_pinjam");
                dataTable[x][7] = RsBuku.getString("tgl_kembali");
                x++;
            }
            tblKembali.setModel(new DefaultTableModel(dataTable,header));
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void baca_data_transaksi(){
        try{
            stm = Con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            RsBuku = stm.executeQuery("select * from transaksi");
            
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
            tblTransaksi.setModel(new DefaultTableModel(dataTable,header1));
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    //untuk mengkosongkan isian data
    private void kosong(){
        txtKdTransaksi.setText("");
        txtKdKembali.setText("");
        txtNim.setText("");
        txtNama.setText("");
        txtKode.setText("");
        txtJudul.setText("");
        txtKdPinjam.setText("");
        txtJml.setText("");
        txtTotal.setText("");
        txtBayar.setText("");
        txtKembalian.setText("");     
        }
    //mengset aktif tidak isian data
    private void aktif(boolean x){
        txtKdTransaksi.setEditable(x);
        txtKdKembali.setEditable(x);
        txtNim.setEditable(x);
        txtNama.setEditable(x);
        txtKode.setEditable(x);
        txtJudul.setEditable(x);
        txtKdPinjam.setEditable(x);
        txtJml.setEditable(x);
        txtHarga.setEditable(x);
        txtTotal.setEditable(x);
        txtBayar.setEditable(x);
        txtKembalian.setEditable(x);
    }
    //mengset tombol on/off
    private void setTombol(boolean t){
        cmdTambah.setEnabled(t);
        cmdHitung.setEnabled(!t);
        cmdHapus.setEnabled(t);
        cmdSimpan.setEnabled(!t);
        cmdBatal.setEnabled(!t);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txtJudul = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cmdSimpan = new javax.swing.JButton();
        cmdHitung = new javax.swing.JButton();
        txtKode = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cmdHapus = new javax.swing.JButton();
        cmdBatal = new javax.swing.JButton();
        cmdKeluar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cmdTambah = new javax.swing.JButton();
        txtNim = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtKdTransaksi = new javax.swing.JTextField();
        txtNama = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblKembali = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtKdPinjam = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        txtBayar = new javax.swing.JTextField();
        txtKembalian = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtJml = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtHarga = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblTransaksi = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        txtKdKembali = new javax.swing.JTextField();
        dcTgl = new javax.swing.JTextField();
        dcTglKembali = new javax.swing.JTextField();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtJudul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtJudulActionPerformed(evt);
            }
        });

        jLabel2.setText("Kode Buku");

        cmdSimpan.setText("Simpan");
        cmdSimpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmdSimpanMouseClicked(evt);
            }
        });
        cmdSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSimpanActionPerformed(evt);
            }
        });

        cmdHitung.setText("Hitung");
        cmdHitung.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmdHitungMouseClicked(evt);
            }
        });
        cmdHitung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdHitungActionPerformed(evt);
            }
        });

        txtKode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKodeActionPerformed(evt);
            }
        });

        jLabel4.setText("Judul Buku");

        cmdHapus.setText("Hapus");
        cmdHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdHapusActionPerformed(evt);
            }
        });

        cmdBatal.setText("Batal");
        cmdBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdBatalActionPerformed(evt);
            }
        });

        cmdKeluar.setText("Keluar");
        cmdKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdKeluarActionPerformed(evt);
            }
        });

        jLabel1.setText("FORM DATA TRANSAKSI KERUSAKAN BUKU OLEH PEMINJAM");

        cmdTambah.setText("Tambah");
        cmdTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdTambahActionPerformed(evt);
            }
        });

        txtNim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNimActionPerformed(evt);
            }
        });

        jLabel8.setText("Kode Transaksi");

        jLabel9.setText("NIM");

        txtKdTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKdTransaksiActionPerformed(evt);
            }
        });

        txtNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaActionPerformed(evt);
            }
        });

        jLabel10.setText("Nama");

        jLabel11.setText("Tanggal Peminjaman");

        tblKembali.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode Pengembalian", "NIM", "Nama", "Kode Buku", "Judul Buku", "Kode Peminjaman", "Tanggal Peminjaman", "Tanggal Pengembalian"
            }
        ));
        tblKembali.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKembaliMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblKembali);

        jLabel12.setText("TABEL DATA TRANSAKSI");

        jLabel13.setText("Tanggal Pengembalian");

        jLabel14.setText("Total");

        jLabel15.setText("TABEL DATA PEMINJAMAN DAN PENGEMBALIAN");

        txtKdPinjam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKdPinjamActionPerformed(evt);
            }
        });

        jLabel16.setText("Kode Peminjaman");

        jLabel3.setText("Bayar");

        jLabel5.setText("Kembali");

        txtBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBayarActionPerformed(evt);
            }
        });

        txtKembalian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKembalianActionPerformed(evt);
            }
        });

        jLabel6.setText("Kerusakan");

        jLabel7.setText("X");

        txtHarga.setText("5000.0");
        txtHarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHargaActionPerformed(evt);
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

        jLabel17.setText("Kode Pengembalian");

        txtKdKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKdKembaliActionPerformed(evt);
            }
        });

        dcTglKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dcTglKembaliActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel13)
                                        .addComponent(jLabel11))
                                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtJudul, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                                    .addComponent(txtKode, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNama, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtKdPinjam, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNim)
                                    .addComponent(txtKdTransaksi, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtKdKembali, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dcTgl)
                                    .addComponent(dcTglKembali))
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel15)
                                    .addComponent(jScrollPane2)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cmdTambah)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmdHitung)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmdSimpan)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmdBatal)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmdHapus)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmdKeluar))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel6)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(txtJml, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel5)
                                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(txtKembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGap(16, 16, 16)
                                                        .addComponent(txtBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel7)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtHarga)))))
                        .addGap(23, 23, 23))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel10, jLabel11, jLabel13, jLabel16, jLabel17, jLabel2, jLabel4, jLabel8, jLabel9});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtKdTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel17)
                                    .addComponent(txtKdKembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(txtNim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(txtKode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txtJudul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel16)
                                    .addComponent(txtKdPinjam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dcTgl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(txtJml, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7)
                                    .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtKembalian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmdTambah)
                        .addComponent(cmdHitung)
                        .addComponent(cmdSimpan)
                        .addComponent(cmdBatal)
                        .addComponent(cmdHapus)
                        .addComponent(cmdKeluar))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(dcTglKembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel10, jLabel11, jLabel13, jLabel16, jLabel17, jLabel2, jLabel4, jLabel8, jLabel9});

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtJudulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtJudulActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtJudulActionPerformed

    private void cmdSimpanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdSimpanMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cmdSimpanMouseClicked

    private void cmdSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSimpanActionPerformed
        // TODO add your handling code here:
        String tKdTrans=txtKdTransaksi.getText();
        String tKdKembali=txtKdKembali.getText();
        String tNim=txtNim.getText();
        String nama=txtNama.getText();
        String kode=txtKode.getText();
        String judul=txtJudul.getText();
        String KdPinjam=txtKdPinjam.getText();
        String tgl= dcTgl.getText();
        String tgl1=dcTglKembali.getText();
        String jml=txtJml.getText();
        String tot=txtTotal.getText();
        
        try{
            stm.executeUpdate("INSERT into transaksi VALUES('"+tKdTrans+"','"+tKdKembali+"','"+tNim+"','"+nama+"','"+kode+"','"+judul+"','"+KdPinjam+"','"+tgl+"','"+tgl1+"','"+jml+"','"+tot+"')");
            tblTransaksi.setModel(new DefaultTableModel(dataTable,header1));
            baca_data_transaksi();
            aktif(false);
            setTombol(true);
            kosong();
            JOptionPane.showMessageDialog(null, "Data Tersimpan");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            JOptionPane.showMessageDialog(null, "Data belum dihitung");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Data belum dipilih");
        }
    }//GEN-LAST:event_cmdSimpanActionPerformed

    private void txtKodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKodeActionPerformed

    private void cmdHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdHapusActionPerformed
        // TODO add your handling code here:
        try{
            String sql="delete from transaksi where kd_transaksi='" + txtKdTransaksi.getText()
            + "'";
            stm.executeUpdate(sql);
            baca_data_transaksi();
            JOptionPane.showMessageDialog(null, "Hapus Data Berhasil");
            kosong();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_cmdHapusActionPerformed

    private void cmdBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBatalActionPerformed
        // TODO add your handling code here:
        aktif(false);
        setTombol(true);
        kosong();
    }//GEN-LAST:event_cmdBatalActionPerformed

    private void cmdKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdKeluarActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_cmdKeluarActionPerformed

    private void cmdTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdTambahActionPerformed
        // TODO add your handling code here:
        aktif(false);
        setTombol(false);
        kosong();  
        txtKdTransaksi.setEditable(true);
        txtJml.setEditable(true);  
        txtBayar.setEditable(true);              
        
    }//GEN-LAST:event_cmdTambahActionPerformed

    private void txtNimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNimActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNimActionPerformed

    private void txtKdTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKdTransaksiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKdTransaksiActionPerformed

    private void txtNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaActionPerformed

    private void tblKembaliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKembaliMouseClicked
        // TODO add your handling code here:
        setField();
    }//GEN-LAST:event_tblKembaliMouseClicked

    private void txtKdPinjamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKdPinjamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKdPinjamActionPerformed

    private void cmdHitungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdHitungActionPerformed
        // TODO add your handling code here:     
        double xtot,xhrg;
        //ini bagian harga
        xhrg=5000.0;
        String xharga=Double.toString(xhrg); 
        txtHarga.setText(xharga); 
        
        //ini bagian jumlah
        int denda = Integer.valueOf(txtJml.getText());
        
        //ini total harga dari harga x jumlah
        xtot=denda*xhrg; 
        String xtotal=Double.toString(xtot); 
        txtTotal.setText(xtotal); 
        total = total+xtot; 
        txtTotal.setText(Double.toString(total)); 
        
//        try {
//            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
//            String tglsatu = String.valueOf(date.format(dcTgl.getDate()));            
//            Date tglPinjam = (Date) date.parse(tglsatu);
//            String tgldua = String.valueOf(date.format(dcTglKembali.getDate()));            
//            Date tglKembali = (Date) date.parse(tgldua);
//            long telat = Math.abs (tglKembali.getTime() - tglPinjam.getTime());
//            
//            txtJml.setText(""+TimeUnit.MILLISECONDS.toDays(telat));
//        }catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "Error: "+e);
//        }         
//        String dendaString=txtJml.getText();
//        int denda = Integer.parseInt(dendaString);
          
    }//GEN-LAST:event_cmdHitungActionPerformed

    private void txtHargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHargaActionPerformed

    private void txtBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBayarActionPerformed
        // TODO add your handling code here:
        float bayar = Float.valueOf(txtBayar.getText());
        float total = Float.valueOf(txtTotal.getText());
        float kembalian = bayar - total;
        txtKembalian.setText(Float.toString(kembalian));
    }//GEN-LAST:event_txtBayarActionPerformed

    private void tblTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTransaksiMouseClicked
        // TODO add your handling code here:
        setField_transaksi();
    }//GEN-LAST:event_tblTransaksiMouseClicked

    private void txtKdKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKdKembaliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKdKembaliActionPerformed

    private void txtKembalianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKembalianActionPerformed
        // TODO add your handling code here:      
    }//GEN-LAST:event_txtKembalianActionPerformed

    private void cmdHitungMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdHitungMouseClicked
        // TODO add your handling code here:           
    }//GEN-LAST:event_cmdHitungMouseClicked

    private void dcTglKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dcTglKembaliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dcTglKembaliActionPerformed

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
            java.util.logging.Logger.getLogger(frmTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmTransaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdBatal;
    private javax.swing.JButton cmdHapus;
    private javax.swing.JButton cmdHitung;
    private javax.swing.JButton cmdKeluar;
    private javax.swing.JButton cmdSimpan;
    private javax.swing.JButton cmdTambah;
    private javax.swing.JTextField dcTgl;
    private javax.swing.JTextField dcTglKembali;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tblKembali;
    private javax.swing.JTable tblTransaksi;
    private javax.swing.JTextField txtBayar;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtJml;
    private javax.swing.JTextField txtJudul;
    private javax.swing.JTextField txtKdKembali;
    private javax.swing.JTextField txtKdPinjam;
    private javax.swing.JTextField txtKdTransaksi;
    private javax.swing.JTextField txtKembalian;
    private javax.swing.JTextField txtKode;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNim;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}