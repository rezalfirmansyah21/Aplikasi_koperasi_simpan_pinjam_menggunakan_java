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
public class FormPinjaman extends javax.swing.JFrame {
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
    public FormPinjaman() {
        initComponents();
         model = new DefaultTableModel();

        tabelpinjaman.setModel(model);
        model.addColumn("No Transaksi");
        model.addColumn("No Anggota");
        model.addColumn("Nama");
        model.addColumn("Jenis Pinjaman");
        model.addColumn("Max Pinjaman");
        model.addColumn("Sisa Angsuran");
        model.addColumn("Bunga");
        model.addColumn("Angsuran");
        
        loadData();
        kode();
        setJam();
        tampil_combo();
        txtuser.setText(username);
        tgl2.setText(tanggal);
    }

    private void kode() {
        try {
            Connection c = koneksi.getKoneksi();
            Statement s = c.createStatement();

            String sql = "SELECT * FROM pinjaman ORDER by notransaksi desc";
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
    
public void tampil_combo()
      {
          try{
              Connection conn = koneksi.getKoneksi();
              Statement st = conn.createStatement();
              String sql = "select jenis from jenispinjaman order by jenis asc";
              ResultSet rs = st.executeQuery(sql);
              
              while(rs.next()){
                  Object[] ob = new Object[3];
                  ob[0] = rs.getString(1);
                  
                  cjenis.addItem((String) ob[0]);
              }
              rs.close(); st.close();
          }catch(Exception e){
              System.out.println(e.getMessage());
              
          }
      }
    
    public void tampil()
      {
          try{
              Connection conn = koneksi.getKoneksi();
              Statement st = conn.createStatement();
              String sql = "select maxpinjaman,maxangsuran,bunga from jenispinjaman where jenis='"+cjenis.getSelectedItem()+"'";
              ResultSet rs = st.executeQuery(sql);
              
              while(rs.next()){
                  Object[] ob = new Object[3];
                  ob[0] = rs.getString(1);
                  ob[1] = rs.getString(2);
                  ob[2] = rs.getString(3);
                  
                  txtmaxpinjaman.setText((String) ob[0]);
                  txtmaxangsuran.setText((String) ob[1]);
                  txtbunga.setText((String) ob[2]);
                  
              }
              rs.close(); st.close();
          }catch(Exception e){
              System.out.println(e.getMessage());
          }
      }
    
   public final void loadData() {
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try {
            Connection c = koneksi.getKoneksi();
            Statement s = c.createStatement();

            String sql = "SELECT * FROM pinjaman";
            ResultSet r = s.executeQuery(sql);

            while (r.next()) {
                Object[] o = new Object[8];
                o[0] = r.getString("notransaksi");
                o[1] = r.getString("id");
                o[2] = r.getString("nama");
                o[3] = r.getString("jenis");
                o[4] = r.getString("maxpinjaman");
                o[5] = r.getString("maxangsuran");
                o[6] = r.getString("bunga");
                o[7] = r.getString("angsuran");
               
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
        cjenis = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtmaxpinjaman = new javax.swing.JLabel();
        txtmaxangsuran = new javax.swing.JLabel();
        txtbunga = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        txtcarianggota = new javax.swing.JTextField();
        btncari = new javax.swing.JButton();
        btnsimpan = new javax.swing.JButton();
        btncetak = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelpinjaman = new javax.swing.JTable();
        btnubah = new javax.swing.JButton();
        btnhapus = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        txtbunga1 = new javax.swing.JLabel();
        txtbunga2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtangsuran = new javax.swing.JLabel();
        txtcari = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtnama = new javax.swing.JLabel();

        jCheckBox1.setText("jCheckBox1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Form Pinjaman");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(305, 305, 305)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addGap(38, 38, 38)
                .addComponent(tgl2)
                .addGap(191, 191, 191)
                .addComponent(txtuser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 217, Short.MAX_VALUE)
                .addComponent(lbljam)
                .addGap(118, 118, 118))
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

        cjenis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "[- Pilih pinjaman -]" }));
        cjenis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cjenisActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Jenis Pinjaman");

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Max Pinjaman");

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Max Angsuran");

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

        txtcarianggota.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtcarianggota.setForeground(new java.awt.Color(0, 0, 0));

        btncari.setBackground(new java.awt.Color(255, 255, 255));
        btncari.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btncari.setForeground(new java.awt.Color(0, 0, 0));
        btncari.setText("Cari");
        btncari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncariActionPerformed(evt);
            }
        });

        btnsimpan.setBackground(new java.awt.Color(255, 255, 255));
        btnsimpan.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnsimpan.setForeground(new java.awt.Color(0, 0, 0));
        btnsimpan.setText("Simpan");
        btnsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanActionPerformed(evt);
            }
        });

        btncetak.setBackground(new java.awt.Color(255, 255, 255));
        btncetak.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btncetak.setForeground(new java.awt.Color(0, 0, 0));
        btncetak.setText("Cetak");
        btncetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncetakActionPerformed(evt);
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

        btnubah.setBackground(new java.awt.Color(255, 255, 255));
        btnubah.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnubah.setForeground(new java.awt.Color(0, 0, 0));
        btnubah.setText("Ubah");
        btnubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnubahActionPerformed(evt);
            }
        });

        btnhapus.setBackground(new java.awt.Color(255, 255, 255));
        btnhapus.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnhapus.setForeground(new java.awt.Color(0, 0, 0));
        btnhapus.setText("Hapus");
        btnhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapusActionPerformed(evt);
            }
        });

        jButton12.setBackground(new java.awt.Color(255, 255, 255));
        jButton12.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton12.setForeground(new java.awt.Color(0, 0, 0));
        jButton12.setText("Hitung");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(250, 250, 250)
                                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel10)
                                            .addComponent(jLabel5))
                                        .addGap(52, 52, 52)
                                        .addComponent(txtangsuran, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(65, 65, 65)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel11))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(btnsimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtcarianggota, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(btncetak, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(btnubah, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(btnhapus, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btncari, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(15, 15, 15))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addGap(18, 18, 18)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8))
                                .addGap(23, 23, 23)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtmaxpinjaman, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(503, 503, 503))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtnotransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtnoanggota, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(txtnama, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(cjenis, javax.swing.GroupLayout.Alignment.LEADING, 0, 168, Short.MAX_VALUE)))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(507, 507, 507))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtbunga, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtbunga1)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtmaxangsuran, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtbunga2)))
                                .addGap(0, 0, Short.MAX_VALUE))))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtnotransaksi)
                    .addComponent(txtcarianggota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btncari))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtnoanggota)
                    .addComponent(btnsimpan))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btncetak)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(txtnama)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cjenis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(btnubah))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(txtmaxpinjaman))
                    .addComponent(btnhapus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtmaxangsuran)
                    .addComponent(txtbunga2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtbunga)
                    .addComponent(jButton12)
                    .addComponent(txtbunga1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)
                        .addComponent(txtangsuran))
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton6))
                .addGap(18, 18, 18)
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

    private void cjenisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cjenisActionPerformed
        // TODO add your handling code here:
        tampil();
    }//GEN-LAST:event_cjenisActionPerformed

    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanActionPerformed
        // TODO add your handling code here:
            if(txtnotransaksi.getText().equals("") ||txtnama.getText().equals("") || txtmaxpinjaman.getText().equals("")|| txtbunga.getText().equals("")|| txtmaxangsuran.getText().equals("")|| txtangsuran.getText().equals("")|| txtbunga.getText().equals("")){
            JOptionPane.showMessageDialog(null, "LENGKAPI DATA !", "Koperasi", JOptionPane.INFORMATION_MESSAGE);
        }else{
            String notrans = txtnotransaksi.getText();
            String idd = txtnoanggota.getText();
            String namaa = txtnama.getText();
            String jeniss = (String) cjenis.getSelectedItem();
            String maxpin = txtmaxpinjaman.getText();
            String maxang = txtmaxangsuran.getText();
            String bungaa = txtbunga.getText();
            String angsur = txtangsuran.getText();
            
            try {
                long millis=System.currentTimeMillis();  
                java.sql.Date date=new java.sql.Date(millis);  
                System.out.println(date); 
                String tgl = date.toString();
                Connection c = koneksi.getKoneksi();

                String sql = "INSERT INTO pinjaman VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

                PreparedStatement p = c.prepareStatement(sql);
                p.setString(1, notrans);
                p.setString(2, idd);
                p.setString(3, namaa);
                p.setString(4, jeniss);
                p.setString(5, maxpin);
                p.setString(6, maxang);
                p.setString(7, bungaa);
                p.setString(8, angsur);
                p.setString(9, tgl);
                p.executeUpdate();
                p.close();
               
            } catch (SQLException e) {
                System.out.println("Terjadi Error");
            } finally {
                loadData();
              
                JOptionPane.showMessageDialog(null, "Data berhasil tersimpan", "koperasi", JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }//GEN-LAST:event_btnsimpanActionPerformed

    private static String path = System.getProperty("user.dir")+"/src/laporan/";
    private void btncetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncetakActionPerformed
        // TODO add your handling code here:
        String filename = path+"strukPinjaman.jrxml";
        JasperReport jasrep;
        JasperPrint japri;
        JasperViewer javie = null;
        HashMap param = new HashMap(2);
        JasperDesign jasdes;
        try {
            param.put("user",txtuser.getText());
            param.put("transaksi",txtnotransaksi.getText());
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

    private void btncariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncariActionPerformed
        // TODO add your handling code here:
        String cri = txtcarianggota.getText();
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            com.mysql.jdbc.Connection koneksi = (com.mysql.jdbc.Connection) DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/koperasi", "root", "");
            com.mysql.jdbc.Statement statement = (com.mysql.jdbc.Statement) koneksi.createStatement();
            String sql="SELECT * FROM anggota WHERE id like '"+cri+"'";
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()){
                String idd = rs.getString("id");
                String nm = rs.getString("nama");

                txtnoanggota.setText(idd);
                txtnama.setText(nm);
            }else{
                JOptionPane.showMessageDialog(null,"Data tidak ditemukan");

                txtnama.setText("");

                statement.close();
                koneksi.close();
            }
        }catch(Exception ex){
            //            JOptionPane.showMessageDialog(null,"Data tidak ditemukan"+ex);
        }
    }//GEN-LAST:event_btncariActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        double a=Double.parseDouble(txtmaxpinjaman.getText());
        double b=Double.parseDouble(txtmaxangsuran.getText());
        double c=Double.parseDouble(txtbunga.getText());
        
        double bunga=((c/100)*a/b);
        double angsuran=a/b;
        double hitung=bunga+angsuran; 
        txtangsuran.setText(String.valueOf(hitung));
    }//GEN-LAST:event_jButton12ActionPerformed

    private void btnhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapusActionPerformed
        // TODO add your handling code here:
            if(txtnoanggota.getText().equals("") ||txtnama.getText().equals("") || txtmaxpinjaman.getText().equals("")|| txtbunga.getText().equals("")|| txtmaxangsuran.getText().equals("")|| txtangsuran.getText().equals("")){
            JOptionPane.showMessageDialog(null, "LENGKAPI DATA !", "Koperasi", JOptionPane.INFORMATION_MESSAGE);
        }else{
        try {
            String sql ="delete from pinjaman where notransaksi='"+txtnotransaksi.getText()+"'";
            java.sql.Connection conn=(Connection)koneksi.getKoneksi();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            kode();
            JOptionPane.showMessageDialog(this, "berhasil di hapus");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        loadData();
       txtnama.setText("");
       txtnoanggota.setText("");
        txtmaxpinjaman.setText("");
        txtbunga.setText("");
        txtmaxangsuran.setText("");
        }
    }//GEN-LAST:event_btnhapusActionPerformed

    private void btnubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnubahActionPerformed
        // TODO add your handling code here:
            int i = tabelpinjaman.getSelectedRow();
        if (i == -1) {
            return;
        }
        String idd = (String) model.getValueAt(i, 0);
        try {
            long millis=System.currentTimeMillis();  
                java.sql.Date date=new java.sql.Date(millis);  
                System.out.println(date); 
                String tgl = date.toString();
            Connection c = koneksi.getKoneksi();
            String sql = "UPDATE pinjaman SET id =  '" + txtnoanggota.getText() + "',nama='" + txtnama.getText() + "',jenis='" + cjenis.getSelectedItem() + "',maxpinjaman='" + txtmaxpinjaman.getText() + "',maxangsuran='" + txtmaxangsuran.getText() +  "',bunga='" + txtbunga.getText() +  "',angsuran='" + txtangsuran.getText() +  "',tanggal='" + tgl +"' WHERE  notransaksi ='" + idd + "'";
            PreparedStatement p = c.prepareStatement(sql);
            p.executeUpdate();
            p.close();
        } catch (SQLException e) {
            System.out.println("Terjadi Error");
        } finally {
            loadData();
        
            btnsimpan.setEnabled(true);
            JOptionPane.showMessageDialog(null, "Data berhasil diubah", "koperasi", JOptionPane.INFORMATION_MESSAGE);
            txtnotransaksi.requestFocus();
        }
        
    }//GEN-LAST:event_btnubahActionPerformed

    private void txtcariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcariKeyReleased
        // TODO add your handling code here:
         model.getDataVector().removeAllElements();
        model.fireTableDataChanged();

        try {
            Connection c = koneksi.getKoneksi();
            Statement s = c.createStatement();

            String sql = "select * from pinjaman where notransaksi like '%" + txtcari.getText() + "%' or jenis like'%" + txtcari.getText() + "%' or maxpinjaman like'" + txtcari.getText() + "%' or maxangsuran like'" + txtcari.getText() + "%' or bunga like'%" + txtcari.getText() + "%' or angsuran like'%" + txtcari.getText() +"%'";
            ResultSet r = s.executeQuery(sql);

            while (r.next()) {
                Object[] o = new Object[8];
                o[0] = r.getString("notransaksi");
                o[1] = r.getString("id");
                o[2] = r.getString("nama");
                o[3] = r.getString("jenis");
                o[4] = r.getString("maxpinjaman");
                o[5] = r.getString("maxangsuran");
                o[6] = r.getString("bunga");
                o[7] = r.getString("angsuran");
                               
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
        btnsimpan.setEnabled(false);
        btnubah.setEnabled(true);
        
        int i = tabelpinjaman.getSelectedRow();
        if (i == -1) {
            return;
        }
        String notr = (String) model.getValueAt(i, 0);
        txtnotransaksi.setText(notr);
        
        String idd = (String) model.getValueAt(i, 1);
        txtnoanggota.setText(idd);
        
        String namaa = (String) model.getValueAt(i, 2);
        txtnama.setText(namaa);

        String jenis = (String) model.getValueAt(i, 3);
        cjenis.setSelectedItem(jenis);

        String pinjaman = (String) model.getValueAt(i, 4);
        txtmaxpinjaman.setText(pinjaman);
        
        String maxangsuran = (String) model.getValueAt(i, 5);
        txtmaxangsuran.setText(maxangsuran);

        String bungaa = (String) model.getValueAt(i, 6);
        txtbunga.setText(bungaa);
        
        String angsuran = (String) model.getValueAt(i, 7);
        txtangsuran.setText(angsuran);
    }//GEN-LAST:event_tabelpinjamanMouseClicked

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
            java.util.logging.Logger.getLogger(FormPinjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormPinjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormPinjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormPinjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormPinjaman().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncari;
    private javax.swing.JButton btncetak;
    private javax.swing.JButton btnhapus;
    private javax.swing.JButton btnsimpan;
    private javax.swing.JButton btnubah;
    private javax.swing.JComboBox<String> cjenis;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbljam;
    private javax.swing.JTable tabelpinjaman;
    private javax.swing.JLabel tgl2;
    private javax.swing.JLabel txtangsuran;
    private javax.swing.JLabel txtbunga;
    private javax.swing.JLabel txtbunga1;
    private javax.swing.JLabel txtbunga2;
    private javax.swing.JTextField txtcari;
    private javax.swing.JTextField txtcarianggota;
    private javax.swing.JLabel txtmaxangsuran;
    private javax.swing.JLabel txtmaxpinjaman;
    private javax.swing.JLabel txtnama;
    private javax.swing.JLabel txtnoanggota;
    private javax.swing.JLabel txtnotransaksi;
    private javax.swing.JLabel txtuser;
    // End of variables declaration//GEN-END:variables
}
