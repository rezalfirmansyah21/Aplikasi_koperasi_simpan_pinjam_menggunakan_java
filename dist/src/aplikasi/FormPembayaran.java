/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author LENOVO-PC
 */
public class FormPembayaran extends javax.swing.JFrame {
    Statement st;
    ResultSet rs;
    Connection cn = koneksi.getKoneksi();
    String username = session.getUserLogin(); 
    java.util.Date tglsekarang = new java.util.Date();
    private SimpleDateFormat smpdtfmt = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
    //diatas adalah pengaturan format penulisan, bisa diubah sesuai keinginan.
    private String tanggal = smpdtfmt.format(tglsekarang);
    
     private DefaultTableModel model;
    /**
     * Creates new form TransaksiPinjaman
     */
    public FormPembayaran() {
        initComponents();
         model = new DefaultTableModel();

        tabelpinjaman.setModel(model);
        model.addColumn("No Anggota");
        model.addColumn("Nama");
        model.addColumn("Jenis Pinjaman");
        model.addColumn("Max Pinjaman");
        model.addColumn("Sisa Angsuran");
        model.addColumn("Bunga");
        model.addColumn("Angsuran");
        
        loadData();
        angsuran();
        kode();
        setJam();
        txtuser.setText(username);
        tgl2.setText(tanggal);
    }
    
    public void angsuran() {
     
     try {
            st = cn.createStatement();
            String idd = txtnoanggota.getText();
            rs = st.executeQuery("select maxangsuran from pinjaman where id='"+idd+"'");
            while(rs.next()) {
             String hg = rs.getString("maxangsuran");    
                txtmaxangsuran.setText(hg);
            }
        } catch(Exception e) {
            
        }
    }      

    private void kode() {
        try {
            Connection c = koneksi.getKoneksi();
            Statement s = c.createStatement();

            String sql = "SELECT * FROM pembayaran ORDER by notransaksi desc";
            ResultSet r = s.executeQuery(sql);

            if (r.next()) {
                String nofak = r.getString("notransaksi").substring(1);
                String AN = "" + (Integer.parseInt(nofak) + 1);
                String Nol = "";

                if (AN.length() == 1) {
                    Nol = "000";
                } else if (AN.length() == 2) {
                    Nol = "00";
                } else if (AN.length() == 3) {
                    Nol = "0";
                } else if (AN.length() == 4) {
                    Nol = "";
                }

                txtnotransaksi.setText("P" + Nol + AN);
            } else {
                txtnotransaksi.setText("P0001");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
     
    }
    
           public final void setJam(){
ActionListener taskPerformer = new ActionListener() {

            @Override
public void actionPerformed(ActionEvent evt) {
String nol_jam = "", nol_menit = "",nol_detik = "";

java.util.Date dateTime = new java.util.Date();
int nilai_jam = dateTime.getHours();
int nilai_menit = dateTime.getMinutes();
int nilai_detik = dateTime.getSeconds();

if(nilai_jam <= 9) nol_jam= "0";
if(nilai_menit <= 9) nol_menit= "0";
if(nilai_detik <= 9) nol_detik= "0";

String jam = nol_jam + Integer.toString(nilai_jam);
String menit = nol_menit + Integer.toString(nilai_menit);
String detik = nol_detik + Integer.toString(nilai_detik);

lbljam.setText(jam+":"+menit+":"+detik+"");
}
};
new Timer(1000, taskPerformer).start();
}
    

    
   public final void loadData() {
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try {
            Connection c = koneksi.getKoneksi();
            Statement s = c.createStatement();

            String sql = "SELECT id,nama,jenis,maxpinjaman,maxangsuran,bunga,angsuran FROM pinjaman";
            ResultSet r = s.executeQuery(sql);

            while (r.next()) {
                Object[] o = new Object[7];
                o[0] = r.getString("id");
                o[1] = r.getString("nama");
                o[2] = r.getString("jenis");
                o[3] = r.getString("maxpinjaman");
                o[4] = r.getString("maxangsuran");
                o[5] = r.getString("bunga");
                o[6] = r.getString("angsuran");
               
                model.addRow(o);
            }
            r.close();
            s.close();
        } catch (SQLException e) {
            System.out.println("Terjadi Error");
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

        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        tgl2 = new javax.swing.JLabel();
        lbljam = new javax.swing.JLabel();
        txtuser = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtnotransaksi = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtnoanggota = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtmaxpinjaman = new javax.swing.JLabel();
        txtmaxangsuran = new javax.swing.JLabel();
        txtbunga = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelpinjaman = new javax.swing.JTable();
        txtbunga1 = new javax.swing.JLabel();
        txtbunga2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtangsuran = new javax.swing.JLabel();
        txtcari = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtnama = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnsimpan = new javax.swing.JButton();
        btncetak = new javax.swing.JButton();
        txtbayar = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtkembali = new javax.swing.JLabel();
        btncetak1 = new javax.swing.JButton();
        txtjumlah = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        btncetak2 = new javax.swing.JButton();
        txttotal = new javax.swing.JLabel();
        btntransaksi = new javax.swing.JButton();
        txtjenis = new javax.swing.JLabel();

        jCheckBox1.setText("jCheckBox1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Form Pembayaran");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(283, 283, 283)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));

        tgl2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tgl2.setForeground(new java.awt.Color(255, 255, 255));
        tgl2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/tanggal.png"))); // NOI18N
        tgl2.setText("Tanggal");

        lbljam.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbljam.setForeground(new java.awt.Color(255, 255, 255));
        lbljam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/jam.png"))); // NOI18N
        lbljam.setText("Jam");

        txtuser.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtuser.setForeground(new java.awt.Color(255, 255, 255));
        txtuser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/user.png"))); // NOI18N
        txtuser.setText("User");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(tgl2)
                .addGap(188, 188, 188)
                .addComponent(txtuser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 228, Short.MAX_VALUE)
                .addComponent(lbljam)
                .addGap(107, 107, 107))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tgl2)
                    .addComponent(lbljam)
                    .addComponent(txtuser))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("No Transaksi");

        txtnotransaksi.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtnotransaksi.setForeground(new java.awt.Color(0, 0, 0));
        txtnotransaksi.setText("........");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("No Anggota");

        txtnoanggota.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtnoanggota.setForeground(new java.awt.Color(0, 0, 0));
        txtnoanggota.setText("........");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Jenis Pinjaman");

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Max Pinjaman");

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Sisa Angsuran");

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Bunga");

        txtmaxpinjaman.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtmaxpinjaman.setForeground(new java.awt.Color(0, 0, 0));
        txtmaxpinjaman.setText("........");

        txtmaxangsuran.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtmaxangsuran.setForeground(new java.awt.Color(0, 0, 0));
        txtmaxangsuran.setText("........");

        txtbunga.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtbunga.setForeground(new java.awt.Color(0, 0, 0));
        txtbunga.setText("........");

        jButton5.setBackground(new java.awt.Color(255, 255, 255));
        jButton5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(0, 0, 0));
        jButton5.setText("Kembali");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(255, 255, 255));
        jButton6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(0, 0, 0));
        jButton6.setText("Exit");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        tabelpinjaman.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelpinjaman.setSelectionBackground(new java.awt.Color(0, 0, 0));
        tabelpinjaman.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelpinjamanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelpinjaman);

        txtbunga1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtbunga1.setForeground(new java.awt.Color(0, 0, 0));
        txtbunga1.setText("%");

        txtbunga2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtbunga2.setForeground(new java.awt.Color(0, 0, 0));
        txtbunga2.setText("Bulan");

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Angsuran");

        txtangsuran.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtangsuran.setForeground(new java.awt.Color(0, 0, 0));
        txtangsuran.setText("........");

        txtcari.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtcari.setForeground(new java.awt.Color(0, 0, 0));
        txtcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcariKeyReleased(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Cari Data");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Nama");

        txtnama.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtnama.setForeground(new java.awt.Color(0, 0, 0));
        txtnama.setText("........");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnsimpan.setBackground(new java.awt.Color(0, 0, 0));
        btnsimpan.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnsimpan.setForeground(new java.awt.Color(255, 255, 255));
        btnsimpan.setText("Simpan");
        btnsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanActionPerformed(evt);
            }
        });

        btncetak.setBackground(new java.awt.Color(0, 0, 0));
        btncetak.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btncetak.setForeground(new java.awt.Color(255, 255, 255));
        btncetak.setText("Cetak");
        btncetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncetakActionPerformed(evt);
            }
        });

        txtbayar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtbayar.setForeground(new java.awt.Color(0, 0, 0));

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Bayar");

        txtkembali.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtkembali.setForeground(new java.awt.Color(0, 0, 0));
        txtkembali.setText(".................");

        btncetak1.setBackground(new java.awt.Color(255, 255, 255));
        btncetak1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btncetak1.setForeground(new java.awt.Color(0, 0, 0));
        btncetak1.setText("Kembalian");
        btncetak1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncetak1ActionPerformed(evt);
            }
        });

        txtjumlah.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtjumlah.setForeground(new java.awt.Color(0, 0, 0));

        jLabel15.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Jumlah");

        jLabel16.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Bulan");

        btncetak2.setBackground(new java.awt.Color(255, 255, 255));
        btncetak2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btncetak2.setForeground(new java.awt.Color(0, 0, 0));
        btncetak2.setText("Total");
        btncetak2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncetak2ActionPerformed(evt);
            }
        });

        txttotal.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txttotal.setForeground(new java.awt.Color(0, 0, 0));
        txttotal.setText(".................");

        btntransaksi.setBackground(new java.awt.Color(0, 0, 0));
        btntransaksi.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btntransaksi.setForeground(new java.awt.Color(255, 255, 255));
        btntransaksi.setText("Selesai Transaksi");
        btntransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntransaksiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnsimpan, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btncetak1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15)
                                    .addComponent(btncetak2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel14)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtkembali, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(txtjumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel16))
                                    .addComponent(txttotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtbayar)))
                            .addComponent(btntransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(btncetak, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtjumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btncetak2)
                    .addComponent(txttotal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtbayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btncetak1)
                    .addComponent(txtkembali))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btntransaksi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnsimpan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btncetak)
                .addGap(23, 23, 23))
        );

        txtjenis.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtjenis.setForeground(new java.awt.Color(0, 0, 0));
        txtjenis.setText("........");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(250, 250, 250)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane1)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4))
                                .addComponent(jLabel7)
                                .addComponent(jLabel8)
                                .addComponent(jLabel9)
                                .addComponent(jLabel10)
                                .addComponent(jLabel5))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(23, 23, 23)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtnotransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addComponent(txtnoanggota, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(0, 0, Short.MAX_VALUE))))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(txtjenis, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtnama, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGap(64, 64, 64))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(23, 23, 23)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(txtmaxangsuran, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtbunga2))
                                        .addComponent(txtmaxpinjaman, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(txtbunga, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtbunga1))
                                        .addComponent(txtangsuran, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel11))
                                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel11)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txtnoanggota))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(txtnama))
                                .addGap(22, 22, 22)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(txtjenis))
                                .addGap(22, 22, 22)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(txtmaxpinjaman))
                                .addGap(26, 26, 26)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(txtmaxangsuran)
                                    .addComponent(txtbunga2))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(txtbunga)
                                    .addComponent(txtbunga1))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(txtangsuran)))
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txtnotransaksi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5)
                    .addComponent(jButton6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 785, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        home n = new home(); 
                    n.setVisible(true);
                    this.setVisible(false);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanActionPerformed
        // TODO add your handling code here:
          if(txtnotransaksi.getText().equals("") ||txtnama.getText().equals("") || txtjenis.getText().equals("")|| txtbayar.getText().equals("")|| txttotal.getText().equals("")|| txtkembali.getText().equals("")){
            JOptionPane.showMessageDialog(null, "LENGKAPI DATA !", "Koperasi", JOptionPane.INFORMATION_MESSAGE);
        }else{
            String notrans = txtnotransaksi.getText();
            String idd = txtnoanggota.getText();
            String namaa = txtnama.getText();
            String jeniss = txtjenis.getText();
            String angsuran = txtangsuran.getText();
            String bayar = txtbayar.getText();
            String kembali = txtkembali.getText();
            String sisa = txtmaxangsuran.getText();
            
            try {
                long millis=System.currentTimeMillis();  
                java.sql.Date date=new java.sql.Date(millis);  
                System.out.println(date); 
                String tgl = date.toString();
                Connection c = koneksi.getKoneksi();

                String sql = "INSERT INTO pembayaran VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

                PreparedStatement p = c.prepareStatement(sql);
                p.setString(1, notrans);
                p.setString(2, idd);
                p.setString(3, namaa);
                p.setString(4, jeniss);
                p.setString(5, angsuran);
                p.setString(6, bayar);
                p.setString(7, kembali);
                p.setString(8, sisa);
                p.setString(9, tgl);
                p.executeUpdate();
                p.close();
              
                angsuran();
            } catch (SQLException e) {
                System.out.println("Terjadi Error");
            } finally {
                try {
                        String sqla ="TRUNCATE `tmp_pembayaran";
                        java.sql.Connection conn=(Connection)koneksi.getKoneksi();
                        java.sql.PreparedStatement pst=conn.prepareStatement(sqla);
                        pst.execute();
                        JOptionPane.showMessageDialog(null, "TRANSAKSI DISIMPAN", "Koperasi", JOptionPane.INFORMATION_MESSAGE);
                        loadData();
                        btncetak.setEnabled(true);
                    } catch (Exception e) {
              
                JOptionPane.showMessageDialog(null, "Selesai Transaksi", "koperasi", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnsimpanActionPerformed
  }
    private static String path = System.getProperty("user.dir")+"/src/laporan/";
    private void btncetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncetakActionPerformed
        // TODO add your handling code here:
        String filename = path+"strukpembayaran.jrxml";
        JasperReport jasrep;
        JasperPrint japri;
        JasperViewer javie = null;
        HashMap param = new HashMap(2);
        JasperDesign jasdes;
        try {
            param.put("transaksi",txtnotransaksi.getText());
            param.put("user",txtuser.getText());
            File report = new File(filename);
            jasdes = JRXmlLoader.load(report);
            jasrep = JasperCompileManager.compileReport(jasdes);
            japri = JasperFillManager.fillReport(jasrep,param,aplikasi.koneksi.getKoneksi());
            javie.viewReport(japri,false);
            kode();
        } catch (Exception e) {
            System.out.print("gagal ngprint");
        }

    }//GEN-LAST:event_btncetakActionPerformed

    private void txtcariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcariKeyReleased
        // TODO add your handling code here:
         model.getDataVector().removeAllElements();
        model.fireTableDataChanged();

        try {
            Connection c = koneksi.getKoneksi();
            Statement s = c.createStatement();

            String sql = "select id,nama,jenis,maxpinjaman,maxangsuran,bunga,angsuran from pinjaman where jenis like'%" + txtcari.getText() + "%' or maxpinjaman like'" + txtcari.getText() + "%' or maxangsuran like'" + txtcari.getText() + "%' or bunga like'%" + txtcari.getText() + "%' or angsuran like'%" + txtcari.getText() + "%' or id like'%" + txtcari.getText() + "%' or nama like'%" + txtcari.getText() +"%'";
            ResultSet r = s.executeQuery(sql);

            while (r.next()) {
                Object[] o = new Object[7];
                o[0] = r.getString("id");
                o[1] = r.getString("nama");
                o[2] = r.getString("jenis");
                o[3] = r.getString("maxpinjaman");
                o[4] = r.getString("maxangsuran");
                o[5] = r.getString("bunga");
                o[6] = r.getString("angsuran");
                               
                model.addRow(o);
            }
            r.close();
            s.close();
        } catch (SQLException e) {
            System.out.println("Terjadi Error");
        }
    }//GEN-LAST:event_txtcariKeyReleased

    private void tabelpinjamanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelpinjamanMouseClicked
        // TODO add your handling code here:
        int i = tabelpinjaman.getSelectedRow();
        if (i == -1) {
            return;
        }
        String idd = (String) model.getValueAt(i, 0);
        txtnoanggota.setText(idd);
        
        String namaa = (String) model.getValueAt(i, 1);
        txtnama.setText(namaa);

        String jenis = (String) model.getValueAt(i, 2);
        txtjenis.setText(jenis);

        String pinjaman = (String) model.getValueAt(i, 3);
        txtmaxpinjaman.setText(pinjaman);
        
        String maxangsuran = (String) model.getValueAt(i, 4);
        txtmaxangsuran.setText(maxangsuran);

        String bungaa = (String) model.getValueAt(i, 5);
        txtbunga.setText(bungaa);
        
        String angsuran = (String) model.getValueAt(i, 6);
        txtangsuran.setText(angsuran);
    }//GEN-LAST:event_tabelpinjamanMouseClicked

    private void btncetak1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncetak1ActionPerformed
        // TODO add your handling code here:
         int a=Integer.parseInt(txtbayar.getText());
        int b=Integer.parseInt(txttotal.getText());

        int hitung=a-b;
        txtkembali.setText(String.valueOf(hitung));
    }//GEN-LAST:event_btncetak1ActionPerformed

    private void btncetak2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncetak2ActionPerformed
        // TODO add your handling code here:
         int a=Integer.parseInt(txtjumlah.getText());
        int b=Integer.parseInt(txtangsuran.getText());

        int hitung=a*b;
        txttotal.setText(String.valueOf(hitung));
    }//GEN-LAST:event_btncetak2ActionPerformed

    private void btntransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntransaksiActionPerformed
        // TODO add your handling code here:
         if(txtnotransaksi.getText().equals("") ||txtnama.getText().equals("") || txtjenis.getText().equals("")|| txtbayar.getText().equals("")|| txttotal.getText().equals("")|| txtkembali.getText().equals("")){
            JOptionPane.showMessageDialog(null, "LENGKAPI DATA !", "Koperasi", JOptionPane.INFORMATION_MESSAGE);
        }else{
            String notrans = txtnotransaksi.getText();
            String idd = txtnoanggota.getText();
            String namaa = txtnama.getText();
            String jeniss = txtjenis.getText();
            String jml = txtjumlah.getText();
            String bayar = txtbayar.getText();
            String kembali = txtkembali.getText();
            
            try {
                long millis=System.currentTimeMillis();  
                java.sql.Date date=new java.sql.Date(millis);  
                System.out.println(date); 
                String tgl = date.toString();
                Connection c = koneksi.getKoneksi();

                String sql = "INSERT INTO tmp_pembayaran VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

                PreparedStatement p = c.prepareStatement(sql);
                p.setString(1, null);
                p.setString(2, notrans);
                p.setString(3, idd);
                p.setString(4, namaa);
                p.setString(5, jeniss);
                p.setString(6, jml);
                p.setString(7, bayar);
                p.setString(8, kembali);
                p.setString(9, tgl);
                p.executeUpdate();
                p.close();
              
                angsuran();
            } catch (SQLException e) {
                System.out.println("Terjadi Error");
            } finally {
                loadData();
              
                JOptionPane.showMessageDialog(null, "Selesai Transaksi", "koperasi", JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }//GEN-LAST:event_btntransaksiActionPerformed

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
            java.util.logging.Logger.getLogger(FormPembayaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormPembayaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormPembayaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormPembayaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormPembayaran().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncetak;
    private javax.swing.JButton btncetak1;
    private javax.swing.JButton btncetak2;
    private javax.swing.JButton btnsimpan;
    private javax.swing.JButton btntransaksi;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbljam;
    private javax.swing.JTable tabelpinjaman;
    private javax.swing.JLabel tgl2;
    private javax.swing.JLabel txtangsuran;
    private javax.swing.JTextField txtbayar;
    private javax.swing.JLabel txtbunga;
    private javax.swing.JLabel txtbunga1;
    private javax.swing.JLabel txtbunga2;
    private javax.swing.JTextField txtcari;
    private javax.swing.JLabel txtjenis;
    private javax.swing.JTextField txtjumlah;
    private javax.swing.JLabel txtkembali;
    private javax.swing.JLabel txtmaxangsuran;
    private javax.swing.JLabel txtmaxpinjaman;
    private javax.swing.JLabel txtnama;
    private javax.swing.JLabel txtnoanggota;
    private javax.swing.JLabel txtnotransaksi;
    private javax.swing.JLabel txttotal;
    private javax.swing.JLabel txtuser;
    // End of variables declaration//GEN-END:variables
}
