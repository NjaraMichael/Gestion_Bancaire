/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.GPB.frame;

import com.GPB.api.ClientAPI;
import com.GPB.api.UserAPI;
import com.GPB.api.VirementAPI;
import com.GPB.entities.Client;
import com.GPB.entities.Virement;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *
 * @author anouer
 */
public class AjoutVirement extends javax.swing.JFrame {

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    public String sexee;
    public String etat;
    public ImageIcon Format = null;
    int s = 0;
    public static byte[] photo;
    byte[] imagedata;
    String cin;

    /**
     * Creates new form AjoutCandidat
     * @throws java.sql.SQLException
     */
    public AjoutVirement() throws SQLException {
        initComponents();
        //this.setIconImage(new ImageIcon(getClass().getResource("logocar.png")).getImage());
        conn = ConexionBD.Conexion();
        /*btnaddpermis.setVisible(false);*/
        recept();
        /*buttonGroup2.add(act);
        buttonGroup2.add(arc);*/

//        ImageIcon img202 = new ImageIcon(getClass().getResource("file_image_1.png"));
//        image.setIcon(img202);
//        if(txtdateVe.getDate() == null) {
            Date actuelle = new Date();
//            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            txtdateVi.setDate(actuelle);
//        }
//           try {
//            ClientAPI clientAPI = UserAPI.getUser().create(ClientAPI.class);
//            clientAPI.findall().enqueue(new Callback<List<Client>>() {
//                @Override
//                public void onResponse(Call<List<Client>> call, Response<List<Client>> response) {
//                    if(response.isSuccessful()) {
//                        for(Client client : response.body()) {
//                            
//                        }
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<List<Client>> call, Throwable t) {
//                    JOptionPane.showConfirmDialog(null, t.getMessage()); //To change body of generated methods, choose Tools | Templates.
//                }    
//            });
//        } catch (Exception e) {
//            JOptionPane.showConfirmDialog(null, e.getMessage());
//        }
//        
//        Liste_des_Virements lv;
//        lv = new Liste_des_Virements();
//        if(!"".equals(txtMontant.getText())) {
//            jComboBox1.setSelectedItem(lv.txtNumCompte.getText());
//        }else {
//            jComboBox1.setSelectedItem(null);
//        }
//        jComboBox1.setSelectedItem(lv.txtNumCompte.getText());

           txtId.setVisible(false);
           txtAncienMontant.setVisible(false);
            
    }

    private void recept() throws SQLException {
        Liste_des_Virements ad = new Liste_des_Virements();
        String r = ad.re();
        if ("ah".equals(r)) {
            modifierok.setVisible(true);
        } else if (!"ah".equals(r)) {
            modifierok.setVisible(false);
        }
        if ("a".equals(r)) {
            ajoutbtnok.setVisible(true);
        } else if (!"a".equals(r)) {
            ajoutbtnok.setVisible(false);
        }

    }

    public void cleardata() {
//        txtNumCompte.setText("");
        txtNumCompteE.setText("");
        txtNumCompteR.setText("");
        txtMontant.setText("");
//        txtdateVe.setDate(null);
    }

    public void Recuper() throws SQLException {
//        Liste_des_Virements inf = new Liste_des_Virements();
//        try {
//            String rec = inf.gettableresult();
//            String requet = "select * from client_table where cin = '" + rec + "'";
//            ps = conn.prepareStatement(requet);
//            rs = ps.executeQuery();
//            if (rs.next()) {
//                String t1 = rs.getString("cin");
//                txtNumCompte.setText(t1);
//                cin = t1;
//                String t2 = rs.getString("nomc");
//                txtNom.setText(t2);
//                String t = rs.getString("prenomc");
//                txtSolde.setText(t);
//                Date t3 = rs.getDate("date_naissance");
//                txtdtnaissance.setDate(t3);
//                String t4 = rs.getString("age");
//                txtage.setText(t4);
//                String t5 = rs.getString("sexe");
//                String vbn = t5;
//
//                if (vbn.equals("Masculin")) {
//                    jRadioButton1.setSelected(true);
//                    sexee = "Masculin";
//                } else if (vbn.equals("Féminin")) {
//                    jRadioButton2.setSelected(true);
//                    sexee = "Féminin";
//                }
//
//                String t6 = rs.getString("gsm");
//                txtgsm.setText(t6);
//                String t7 = rs.getString("adresse");
//                txtadresse.setText(t7);
//                ps.close();
//                rs.close();
//            }
//        } catch (SQLException e) {
//            System.out.println(e);
//        } finally {
//
//            try {
//                ps.close();
//                rs.close();
//
//            } catch (SQLException e) {
//                JOptionPane.showMessageDialog(null, "erreur BD");
//            }
//        }
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
        ajoutbtnok = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtMontant = new javax.swing.JTextField();
        txtdateVi = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        txtNomR = new javax.swing.JTextField();
        txtNumCompteR = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtNumCompteE = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtNomE = new javax.swing.JTextField();
        modifierok = new javax.swing.JButton();
        txtId = new javax.swing.JTextField();
        txtAncienMontant = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(510, 380));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowDeactivated(java.awt.event.WindowEvent evt) {
                formWindowDeactivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        ajoutbtnok.setText("add");
        ajoutbtnok.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ajoutbtnokMouseClicked(evt);
            }
        });
        ajoutbtnok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ajoutbtnokActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "Virement :", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 14))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel3.setText("Date * :");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel2.setText("Montant * :");

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel1.setText("N°Compte Recepteur * :");
        jLabel1.setToolTipText("");

        txtdateVi.setDateFormatString("dd/MM/yyyy");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel4.setText("Nom Recepteur * :");
        jLabel4.setToolTipText("");

        txtNomR.setEnabled(false);
        txtNomR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomRActionPerformed(evt);
            }
        });
        txtNomR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNomRKeyReleased(evt);
            }
        });

        txtNumCompteR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNumCompteRKeyReleased(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel5.setText("N°Compte Emetteur * :");
        jLabel5.setToolTipText("");

        txtNumCompteE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNumCompteEKeyReleased(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel6.setText("Nom Emetteur * :");
        jLabel6.setToolTipText("");

        txtNomE.setEnabled(false);
        txtNomE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomEActionPerformed(evt);
            }
        });
        txtNomE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNomEKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(76, 76, 76)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNomR)
                            .addComponent(txtMontant, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                            .addComponent(txtdateVi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNumCompteR)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNomE, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(txtNumCompteE, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumCompteE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNomE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumCompteR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNomR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtMontant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel3))
                    .addComponent(txtdateVi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        modifierok.setText("update");
        modifierok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifierokActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(modifierok, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ajoutbtnok, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtId)
                        .addComponent(txtAncienMontant)))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ajoutbtnok)
                    .addComponent(txtAncienMontant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(modifierok)
                .addContainerGap())
        );

        jPanel1.getAccessibleContext().setAccessibleName("Information Candidat :");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void ajoutbtnokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ajoutbtnokActionPerformed
          try {
            ClientAPI clientAPI = UserAPI.getUser().create(ClientAPI.class);
            clientAPI.find(txtNumCompteE.getText()).enqueue(new Callback<Client>(){
                @Override
                public void onResponse(Call<Client> call, Response<Client> response) {
                    Client client = response.body();
                    
                    if(client.getSolde() < Long.parseLong(txtMontant.getText())) {
                        JOptionPane.showMessageDialog(null, "Le solde actuel du client émetteur n°compte "+ client.getNumCompte() +" est de "+ client.getSolde() +" Ar. Veuillez réduire la somme ou annuler l'opération.",
                    "Montant insuffisant", JOptionPane.OK_OPTION);
                    }else{
                        Virement virement = new Virement();
        //               final Liste_des_Virements a = new Liste_des_Virements();
        //                Date actuelle = new Date();
                        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        String date = dateFormat.format(txtdateVi.getDate());

                       virement.setNumCompteE(txtNumCompteE.getText());
                       virement.setNumCompteR(txtNumCompteR.getText());
                       virement.setMontant_Virement(Integer.parseInt(txtMontant.getText()));
                       virement.setDateVirement(date);
                       VirementAPI virementAPI = UserAPI.getUser().create(VirementAPI.class);
                       virementAPI.create(virement).enqueue(new Callback<Void>() {
                           @Override
                           public void onResponse(Call<Void> call, Response<Void> response) {
                               if(response.isSuccessful()) {
        //                           a.loadData();
                                   JOptionPane.showMessageDialog(null, "Enregistrement avec succès");
                                   dispose();
                               }
                           }

                           @Override
                           public void onFailure(Call<Void> call, Throwable t) {
                               JOptionPane.showConfirmDialog(null, t.getMessage()); //To change body of generated methods, choose Tools | Templates.
                           }
                       });
                    }
                }

                @Override
                public void onFailure(Call<Client> call, Throwable t) {
                    JOptionPane.showConfirmDialog(null, t.getMessage()); //To change body of generated methods, choose Tools | Templates.
                }
            });
        
               
               
          } catch (Exception e) {
              JOptionPane.showConfirmDialog(null, e.getMessage());
          }
//        Date actuelle = new Date();
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String date = dateFormat.format(actuelle);
//        String dc = date;
//        
//            int i = txtNumCompte.getText().length();
//            System.out.println(i);
//            if(i!=12){
//                JOptionPane.showMessageDialog(null, "Le CIN doit être à 12 caractères");
//            } else {
//                try {
//            String requete = "insert into client_table (cin,nomc ,prenomc,date_naissance,age,sexe,gsm,adresse,date_inscription,image) values (?,?,?,?,?,?,?,?,'" + dc + "',?)";
//            ps = conn.prepareStatement(requete);
//            ps.setString(1, txtNumCompte.getText());
//            ps.setString(2, txtNom.getText());
//            ps.setString(3, txtSolde.getText());
//            ps.setString(4, ((JTextField) txtdtnaissance.getDateEditor().getUiComponent()).getText());
//            ps.setString(5, txtage.getText());
//            
//            
//            
//            ps.setString(6, sexee);
//            ps.setString(7, txtgsm.getText());
//            ps.setString(8, txtadresse.getText());
//            /*ps.setString(9, etat);*/
//
//            if (txtpath.getText().equalsIgnoreCase("")) {
//                ps.setString(9, "");
//            } else {
//                ps.setString(9, txtpath.getText());
//            }
//            
//            if(Integer.parseInt(txtage.getText()) < 18){
//                JOptionPane.showMessageDialog(null, "L'Age doit être suppérieur à 18");
//            } else {
//            ps.execute();
//            JOptionPane.showMessageDialog(null, "Enregistrement avec succès");
//            }
//            ps.close();
//            rs.close();
//            
//            } catch (HeadlessException | SQLException e) {
//            System.out.println("--> SQLException : " + e);
//            JOptionPane.showMessageDialog(null, "Tout est Obligatoire");
//        } finally {
//
//            try {
//                ps.close();
//                rs.close();
//
//            } catch (SQLException e) {
//                JOptionPane.showMessageDialog(null, "deja inserre");
//            }
//            }
//        
//        }

    }//GEN-LAST:event_ajoutbtnokActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed

    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

    }//GEN-LAST:event_formWindowClosing

    private void ajoutbtnokMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ajoutbtnokMouseClicked

    }//GEN-LAST:event_ajoutbtnokMouseClicked

    private void formWindowDeactivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowDeactivated

    }//GEN-LAST:event_formWindowDeactivated

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated

    }//GEN-LAST:event_formWindowActivated

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        //modifok.setEnabled(false);  

    }//GEN-LAST:event_formWindowOpened

    private void modifierokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifierokActionPerformed
        try {
            ClientAPI clientAPI = UserAPI.getUser().create(ClientAPI.class);
            clientAPI.find(txtNumCompteE.getText()).enqueue(new Callback<Client>(){
                @Override
                public void onResponse(Call<Client> call, Response<Client> response) {
                    Client client = response.body();
                    
                    if((client.getSolde() - Long.parseLong(txtMontant.getText()) + Long.parseLong(txtAncienMontant.getText())) < 0) {
                        JOptionPane.showMessageDialog(null, "Le solde actuel du client n°compte "+ client.getNumCompte() +" est de "+ client.getSolde() +" Ar.\nVeuillez réduire la somme ou annuler l'opération.","Montant insuffisant",JOptionPane.OK_OPTION);
                    }else{
                        Virement virement = new Virement();
//               final Liste_des_Clients a = new Liste_des_Clients();
               
               DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
               String date = dateFormat.format(txtdateVi.getDate());
               
               virement.setIdVirement(Integer.parseInt(txtId.getText()));
               virement.setNumCompteE(txtNumCompteE.getText());
               virement.setNumCompteR(txtNumCompteR.getText());
               virement.setMontant_Virement(Integer.parseInt(txtMontant.getText()));
               virement.setDateVirement(date);
               VirementAPI virementAPI = UserAPI.getUser().create(VirementAPI.class);
               virementAPI.update(virement).enqueue(new Callback<Void>() {
                   @Override
                   public void onResponse(Call<Void> call, Response<Void> response) {
                       if(response.isSuccessful()) {
//                           a.loadData();
                           JOptionPane.showMessageDialog(null, "Modification avec succès");
                           dispose();
                       }
                   }

                   @Override
                   public void onFailure(Call<Void> call, Throwable t) {
                       JOptionPane.showConfirmDialog(null, t.getMessage()); //To change body of generated methods, choose Tools | Templates.
                   }
               });
                    }
                }

                @Override
                public void onFailure(Call<Client> call, Throwable t) {
                    JOptionPane.showConfirmDialog(null, t.getMessage()); //To change body of generated methods, choose Tools | Templates.
                }
            });
        
               
               
          } catch (Exception e) {
              JOptionPane.showConfirmDialog(null, e.getMessage());
          }
//        try {
//               Virement virement = new Virement();
//               final Liste_des_Virements a = new Liste_des_Virements();
//               DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//               String date = dateFormat.format(txtdateVe.getDate());
//               
//               virement.setNumCompte(txtNumCompte.getText());
//               virement.setMontant_Virement(Integer.parseInt(txtMontant.getText()));
//               virement.setDateVirement(date);
//               VirementAPI virementAPI = UserAPI.getUser().create(VirementAPI.class);
//               virementAPI.update(virement).enqueue(new Callback<Void>() {
//                   @Override
//                   public void onResponse(Call<Void> call, Response<Void> response) {
//                       if(response.isSuccessful()) {
//                           a.loadData();
//                           JOptionPane.showMessageDialog(null, "Modification avec succes");
//                           dispose();
//                       }
//                   }
//
//                   @Override
//                   public void onFailure(Call<Void> call, Throwable t) {
//                       JOptionPane.showConfirmDialog(null, t.getMessage()); //To change body of generated methods, choose Tools | Templates.
//                   }
//               });
//               
//          } catch (Exception e) {
//              JOptionPane.showConfirmDialog(null, e.getMessage());
//          }
        
//        String cin2 = txtNumCompte.getText();
//        
//            if(!cin2.equals(cin)){
//                JOptionPane.showMessageDialog(null, "Le CIN n'est pas modifiable \n Sinon, veillez enregistrer un nouveau client");
//            } else {
//                
//            
//        String requete = "update client_table set cin =?,nomc =?,prenomc=?,date_naissance =?,age =?,sexe =?,gsm =?,adresse=?,image =? where  cin ='" + cin2 + "'";
//        try {
//            ps = conn.prepareStatement(requete);
//            ps.setString(1, txtNumCompte.getText());
//            ps.setString(2, txtNom.getText());
//            ps.setString(3, txtSolde.getText());
//            ps.setString(4, ((JTextField) txtdtnaissance.getDateEditor().getUiComponent()).getText());
//            ps.setString(5, txtage.getText());
//            ps.setString(6, sexee);
//            ps.setString(7, txtgsm.getText());
//            ps.setString(8, txtadresse.getText());
//            if(Integer.parseInt(txtage.getText()) < 18){
//                JOptionPane.showMessageDialog(null, "L'Age doit être suppérieur à 18");
//            } else {
//            ps.execute();
//            JOptionPane.showMessageDialog(null, "Modification avec succès");}
//            ps.close();
//            rs.close();
//        } catch (SQLException ex) {
//            System.out.println(ex);
//        } finally {
//
//            try {
//                ps.close();
//                rs.close();
//
//            } catch (SQLException e) {
//                JOptionPane.showMessageDialog(null, "errer");
//            }
//        }
//
//}
    }//GEN-LAST:event_modifierokActionPerformed

    private void txtNomRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomRActionPerformed

    private void txtNumCompteRKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumCompteRKeyReleased
        ClientAPI clientAPI = UserAPI.getUser().create(ClientAPI.class);
        clientAPI.find(txtNumCompteR.getText()).enqueue(new Callback<Client>(){
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                Client client = response.body();
                txtNomR.setText(client.getNom());
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                JOptionPane.showConfirmDialog(null, t.getMessage()); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        if(txtNomR.getText() != "") {
                    ajoutbtnok.setEnabled(true);
                    modifierok.setEnabled(true);
        }else{
            ajoutbtnok.setEnabled(false);
            modifierok.setEnabled(false);
        }
    }//GEN-LAST:event_txtNumCompteRKeyReleased

    private void txtNomRKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomRKeyReleased
        
    }//GEN-LAST:event_txtNomRKeyReleased

    private void txtNumCompteEKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumCompteEKeyReleased
        ClientAPI clientAPI = UserAPI.getUser().create(ClientAPI.class);
        clientAPI.find(txtNumCompteE.getText()).enqueue(new Callback<Client>(){
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                Client client = response.body();
                txtNomE.setText(client.getNom());
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                JOptionPane.showConfirmDialog(null, t.getMessage()); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        if(txtNomE.getText() != "") {
                    ajoutbtnok.setEnabled(true);
                    modifierok.setEnabled(true);
        }else{
            ajoutbtnok.setEnabled(false);
            modifierok.setEnabled(false);
        }
    }//GEN-LAST:event_txtNumCompteEKeyReleased

    private void txtNomEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomEActionPerformed

    private void txtNomEKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomEKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomEKeyReleased


    /**
     * red();
     *
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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AjoutVirement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new AjoutVirement().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(AjoutVirement.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton ajoutbtnok;
    public javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JButton modifierok;
    public javax.swing.JTextField txtAncienMontant;
    public javax.swing.JTextField txtId;
    public javax.swing.JTextField txtMontant;
    public javax.swing.JTextField txtNomE;
    public javax.swing.JTextField txtNomR;
    public javax.swing.JTextField txtNumCompteE;
    public javax.swing.JTextField txtNumCompteR;
    public com.toedter.calendar.JDateChooser txtdateVi;
    // End of variables declaration//GEN-END:variables
}
