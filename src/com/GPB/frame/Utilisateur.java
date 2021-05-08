/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.GPB.frame;

import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JTable;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author anouer
 */
public class Utilisateur extends javax.swing.JInternalFrame {
 Connection conn = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    static String test;
     public String sexee;
      public String types;
    /**
     * Creates new form Examen
     */
    public Utilisateur() {
       
        initComponents();
         remove_title_bar();
        conn = ConexionBD.Conexion();
        Affichage();
            ImageIcon img = new ImageIcon(getClass().getResource("txt2.png"));
//        txtbachground.setIcon(img);
       txtrechercher.setText("Taper Cin Utilisateur");
       ImageIcon img2 = new ImageIcon(getClass().getResource("txt2.png"));
//        txtbackground1.setIcon(img2);
//       txtrechercher1.setText("Taper Nom Utilisateur"); 
//       btnsupprimer.setEnabled(false);
//       btnmodifier.setEnabled(false);
//       btnenregistrer.setEnabled(false);
       buttonGroup1.add(Radiohomme);
       buttonGroup1.add(Radiofemme);
    }
 private void remove_title_bar(){
        putClientProperty("Utilisateur.isPalette", Boolean.TRUE);
        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        ((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        this.setBorder(null);
    }
  private void Affichage() {
        try {
            String requete = "select cin as 'CIN',nom as 'Nom' ,prenom as 'Prenom' ,sexe As 'Sexe',tel as 'GSM',login as 'Login',password as 'Mot de passe',image as ' Photo' from login_table ";
            ps = conn.prepareStatement(requete);
            rs = ps.executeQuery();
//            Table.setModel(DbUtils.resultSetToTableModel(rs));
ps.close();
                rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {

            try {
                ps.close();
                rs.close();

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erreur BD");
            }
        }

    }
//  public void Deplace() {
//        try {
//
//            int row = Table.getSelectedRow();
//            Utilisateur.test = (Table.getModel().getValueAt(row, 5).toString());
//            String requet = " select * from  login_table where login = '" + test + "' ";
//            ps = conn.prepareStatement(requet);
//            rs = ps.executeQuery();
//
//            if (rs.next()) {
//                String t1 = rs.getString("login");
//                txtlogin.setText(t1);
//                String t2 = rs.getString("nom");
//                txtnom.setText(t2);
//                String t3 = rs.getString("prenom");
//                txtprenom.setText(t3);
//                String t4 = rs.getString("password");
//                txtpasse.setText(t4);
//                String t5 = rs.getString("tel");
//                txtgsm1.setText(t5);
//                String t = rs.getString("sexe");
//                if (t.equals("Masculin")) {
//                    Radiohomme.setSelected(true);
//                    sexee = "Masculin";
//                } else if (t.equals("Féminin")) {
//                    Radiofemme.setSelected(true);
//                    sexee = "Féminin";
//                }
//                String t7 = rs.getString("cin");
//                txtcin.setText(t7);
//                String t10 = rs.getString("image");
//                System.out.println(t10);
//                if (t10.equals("")) {
//                      ImageIcon img202 = new ImageIcon(getClass().getResource("file_image_1.png"));
//        image.setIcon(img202);
//                } else  {
//                   image.setIcon(new ImageIcon(t10));
//                   txtpath.setText(t10);
//                }
//            }
//ps.close();
//                rs.close();
//        } catch (SQLException e) {
//            System.out.println(e);
//            
//        }finally {
//
//            try {
//                ps.close();
//                rs.close();
//
//            } catch (SQLException e) {
//                JOptionPane.showMessageDialog(null, "erreur BD");
//            }
//        }
//    }
  public void clear() {
        try {

            txtlogin.setText("");
            txtnom.setText("");
            txtprenom.setText("");
            txtcin.setText("");
            txtpasse.setText("");
//            txtgsm1.setText("");
            txtpath.setText("");
            ImageIcon img202 = new ImageIcon(getClass().getResource("file_image_1.png"));
        image.setIcon(img202);
        } catch (Exception e) {
            System.out.println(e);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtnom = new javax.swing.JTextField();
        txtprenom = new javax.swing.JTextField();
        txtpasse = new javax.swing.JTextField();
        Radiohomme = new javax.swing.JRadioButton();
        Radiofemme = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        txtlogin = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtcin = new javax.swing.JTextField();
        txtrechercher = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        image = new javax.swing.JLabel();
        btnpath = new javax.swing.JButton();
        txtpath = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();

        setBorder(null);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        getContentPane().setLayout(null);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true), "Information Utilisateurs :", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 3, 12))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(0, 0, 204));

        jLabel3.setText("Nom  :");

        jLabel4.setText("Mot de passe :");

        jLabel5.setText("Prenom  :");

        jLabel9.setText("Sexe  :");

        txtnom.setText("Super");

        txtprenom.setText("Administrateur");

        txtpasse.setText("..............");

        Radiohomme.setText("Masculin");
        Radiohomme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadiohommeActionPerformed(evt);
            }
        });

        Radiofemme.setText("Féminin");
        Radiofemme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadiofemmeActionPerformed(evt);
            }
        });

        jLabel1.setText("Login:");

        txtlogin.setText("admin");

        jLabel7.setText("CIN :");

        txtcin.setText("101031025497");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                            .addComponent(txtlogin, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtprenom, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                                .addComponent(txtnom)))
                        .addComponent(txtcin, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66)
                        .addComponent(Radiohomme)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Radiofemme))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtpasse, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel7))
                .addGap(0, 15, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtcin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtprenom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtlogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtpasse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(Radiohomme)
                    .addComponent(Radiofemme))
                .addGap(86, 86, 86))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(220, 130, 270, 310);

        txtrechercher.setBackground(new java.awt.Color(240, 240, 240));
        txtrechercher.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtrechercher.setForeground(new java.awt.Color(0, 153, 153));
        txtrechercher.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtrechercher.setBorder(null);
        txtrechercher.setDoubleBuffered(true);
        txtrechercher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtrechercherMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtrechercherMouseEntered(evt);
            }
        });
        txtrechercher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtrechercherActionPerformed(evt);
            }
        });
        txtrechercher.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtrechercherKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtrechercherKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtrechercherKeyTyped(evt);
            }
        });
        getContentPane().add(txtrechercher);
        txtrechercher.setBounds(4, 89, 213, 14);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Photo  Utilisateur:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 3, 12))); // NOI18N

        image.setBackground(new java.awt.Color(255, 255, 255));
        image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GPB/images/file_image_1.png"))); // NOI18N
        jScrollPane2.setViewportView(image);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 13, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel3);
        jPanel3.setBounds(500, 130, 210, 250);

        btnpath.setText("Images");
        btnpath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpathActionPerformed(evt);
            }
        });
        getContentPane().add(btnpath);
        btnpath.setBounds(550, 410, 110, 23);

        txtpath.setEditable(false);
        getContentPane().add(txtpath);
        txtpath.setBounds(530, 380, 140, 20);

        jPanel6.setBackground(new java.awt.Color(0, 153, 153));

        jLabel11.setFont(new java.awt.Font("Advent Pro", 0, 40)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("GESTION DES UTILISATEURS");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 643, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(507, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel6);
        jPanel6.setBounds(0, 0, 1160, 50);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtrechercherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtrechercherMouseClicked
        Affichage();
        clear();

        ImageIcon img = new ImageIcon(getClass().getResource("txt1.png"));
//        txtbachground.setIcon(img);
        txtrechercher.setText("");
        ImageIcon img2 = new ImageIcon(getClass().getResource("txt2.png"));
//        txtbackground1.setIcon(img2);
//        txtrechercher1.setText("Taper Nom Utilisateur");

        ImageIcon img202 = new ImageIcon(getClass().getResource("file_image_1.png"));
        image.setIcon(img202);
        // TODO add your handling code here:
    }//GEN-LAST:event_txtrechercherMouseClicked

    private void txtrechercherMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtrechercherMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_txtrechercherMouseEntered

    private void txtrechercherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtrechercherActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtrechercherActionPerformed

    private void txtrechercherKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrechercherKeyPressed

    }//GEN-LAST:event_txtrechercherKeyPressed

    private void txtrechercherKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrechercherKeyReleased

        try {
            String requete = "select cin as 'CIN',nom as 'Nom' ,prenom as 'Prenom' ,sexe As 'Sexe',tel as 'GSM',login as 'Login',password as 'Mot de passe',image as ' Photo' from login_table where cin LIKE ?";
            ps = conn.prepareStatement(requete);
            ps.setString(1, "%"+txtrechercher.getText()+"%");
            rs = ps.executeQuery();
//            Table.setModel(DbUtils.resultSetToTableModel(rs));
            ps.close();
                rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }finally {

            try {
                ps.close();
                rs.close();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "deja inserre"+ex);
            }
        } 
    }//GEN-LAST:event_txtrechercherKeyReleased
public void imagess() {
        ConexionBD v = new ConexionBD();
        v.filen();
        String vpath = v.getp();
         try {
        if (vpath == null) {

        } else {
            image.setIcon(new ImageIcon(vpath));
            txtpath.setText(vpath);

            } 
        }catch (Exception e) {
            }

    }

    private void txtrechercherKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrechercherKeyTyped
       clear();
                    ImageIcon img202 = new ImageIcon(getClass().getResource("file_image_1.png"));
        image.setIcon(img202);

    }//GEN-LAST:event_txtrechercherKeyTyped

    private void RadiofemmeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadiofemmeActionPerformed
        sexee = "Féminin";
    }//GEN-LAST:event_RadiofemmeActionPerformed

    private void RadiohommeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadiohommeActionPerformed
        sexee = "Masculin";
    }//GEN-LAST:event_RadiohommeActionPerformed

    private void btnpathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpathActionPerformed
        imagess();
    }//GEN-LAST:event_btnpathActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
clear();       
    }//GEN-LAST:event_formMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton Radiofemme;
    private javax.swing.JRadioButton Radiohomme;
    private javax.swing.JButton btnpath;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel image;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txtcin;
    private javax.swing.JTextField txtlogin;
    private javax.swing.JTextField txtnom;
    private javax.swing.JTextField txtpasse;
    private javax.swing.JTextField txtpath;
    private javax.swing.JTextField txtprenom;
    private javax.swing.JTextField txtrechercher;
    // End of variables declaration//GEN-END:variables
}
