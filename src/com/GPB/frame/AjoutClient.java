/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.GPB.frame;

import com.GPB.api.ClientAPI;
import com.GPB.api.UserAPI;
import com.GPB.entities.Client;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class AjoutClient extends javax.swing.JFrame {

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
    public AjoutClient() throws SQLException {
        initComponents();
        //this.setIconImage(new ImageIcon(getClass().getResource("logocar.png")).getImage());
        conn = ConexionBD.Conexion();
        /*btnaddpermis.setVisible(false);*/
        recept();
        buttonGroup1.add(jRadioButton1);
        buttonGroup1.add(jRadioButton2);
        /*buttonGroup2.add(act);
        buttonGroup2.add(arc);*/

//        ImageIcon img202 = new ImageIcon(getClass().getResource("file_image_1.png"));
//        image.setIcon(img202);
    }

    private void recept() throws SQLException {
        Liste_des_Clients ad = new Liste_des_Clients();
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
        txtNumCompte.setText("");
        txtNom.setText("");
        txtSolde.setText("");
        txtdtnaissance.setDate(null);
        txtage.setText("");
        txtgsm.setText("");
        txtadresse.setText("");
        jRadioButton1.setSelected(true);
        sexee = "Masculin";
        etat = "Actif";
        ImageIcon img202 = new ImageIcon(getClass().getResource("file_image_1.png"));
        image.setIcon(img202);
        txtpath.setText("");
    }

    public void Recuper() throws SQLException {
        Liste_des_Clients inf = new Liste_des_Clients();
        try {
            String rec = inf.gettableresult();
            String requet = "select * from client_table where cin = '" + rec + "'";
            ps = conn.prepareStatement(requet);
            rs = ps.executeQuery();
            if (rs.next()) {
                String t1 = rs.getString("cin");
                txtNumCompte.setText(t1);
                cin = t1;
                String t2 = rs.getString("nomc");
                txtNom.setText(t2);
                String t = rs.getString("prenomc");
                txtSolde.setText(t);
                Date t3 = rs.getDate("date_naissance");
                txtdtnaissance.setDate(t3);
                String t4 = rs.getString("age");
                txtage.setText(t4);
                String t5 = rs.getString("sexe");
                String vbn = t5;

                if (vbn.equals("Masculin")) {
                    jRadioButton1.setSelected(true);
                    sexee = "Masculin";
                } else if (vbn.equals("Féminin")) {
                    jRadioButton2.setSelected(true);
                    sexee = "Féminin";
                }

                String t6 = rs.getString("gsm");
                txtgsm.setText(t6);
                String t7 = rs.getString("adresse");
                txtadresse.setText(t7);
                String t8 = rs.getString("image");
                if (t8.equals("")) {
                    ImageIcon img202 = new ImageIcon(getClass().getResource("file_image_1.png"));
                    image.setIcon(img202);
                } else {
                    image.setIcon(new ImageIcon(t8));
                    txtpath.setText(t8);
                }
                ps.close();
                rs.close();
            }
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
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtgsm = new javax.swing.JTextField();
        txtNumCompte = new javax.swing.JTextField();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        txtage = new javax.swing.JTextField();
        txtadresse = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtSolde = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtdtnaissance = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        txtNom = new javax.swing.JTextField();
        modifierok = new javax.swing.JButton();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        image = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        txtpath = new javax.swing.JTextField();
        btnpath = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(859, 578));
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
        getContentPane().setLayout(null);

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
        getContentPane().add(ajoutbtnok);
        ajoutbtnok.setBounds(320, 410, 190, 23);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "Inforamation Candidat :", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 14))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel4.setText("Date de naissance * :");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 3, 12)); // NOI18N
        jLabel3.setText("Solde * :");

        txtgsm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtgsmKeyTyped(evt);
            }
        });

        txtNumCompte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumCompteActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Féminin");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel6.setText("Sexe * :");

        txtage.setEditable(false);
        txtage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtageMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtageMouseEntered(evt);
            }
        });
        txtage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtageActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel2.setText("Nom * :");

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("Masculin");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel7.setText("Tél  * :");

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel1.setText("N°Compte* :");
        jLabel1.setToolTipText("");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel8.setText("Adresse * :");

        txtdtnaissance.setDateFormatString("yyyy-MM-dd");
        txtdtnaissance.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                txtdtnaissanceComponentAdded(evt);
            }
        });
        txtdtnaissance.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                txtdtnaissanceAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        txtdtnaissance.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                txtdtnaissanceMouseReleased(evt);
            }
        });
        txtdtnaissance.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txtdtnaissanceInputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        txtdtnaissance.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtdtnaissanceKeyReleased(evt);
            }
        });
        txtdtnaissance.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                txtdtnaissanceVetoableChange(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel5.setText("Age :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNumCompte, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNom, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSolde, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtdtnaissance, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtage, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80)
                        .addComponent(txtadresse, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80)
                        .addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(txtgsm, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jLabel2)
                        .addGap(23, 23, 23)
                        .addComponent(jLabel3)
                        .addGap(23, 23, 23)
                        .addComponent(jLabel4)
                        .addGap(23, 23, 23)
                        .addComponent(jLabel5))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtNumCompte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(txtNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(txtSolde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(txtdtnaissance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(txtage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(txtadresse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(txtgsm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(90, 40, 400, 350);
        jPanel1.getAccessibleContext().setAccessibleName("Information Candidat :");

        modifierok.setText("update");
        modifierok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifierokActionPerformed(evt);
            }
        });
        getContentPane().add(modifierok);
        modifierok.setBounds(100, 410, 205, 23);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Photo Candidat :", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 12))); // NOI18N

        jScrollPane1.setViewportView(image);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                .addContainerGap())
        );

        jDesktopPane1.setLayer(jPanel3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(jDesktopPane1);
        jDesktopPane1.setBounds(519, 31, 249, 240);

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        txtpath.setEditable(false);

        btnpath.setText("Parcourir");
        btnpath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpathActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtpath, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnpath, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtpath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnpath)
                .addContainerGap())
        );

        getContentPane().add(jPanel4);
        jPanel4.setBounds(571, 289, 158, 87);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtNumCompteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumCompteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumCompteActionPerformed

    private void ajoutbtnokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ajoutbtnokActionPerformed
          try {
               Client client = new Client();
               
               client.setNumCompte(txtNumCompte.getText());
               client.setNom(txtNom.getText());
               client.setSolde(Long.parseLong(txtSolde.getText()));
               ClientAPI clientAPI = UserAPI.getUser().create(ClientAPI.class);
               clientAPI.create(client).enqueue(new Callback<Void>() {
                   @Override
                   public void onResponse(Call<Void> call, Response<Void> response) {
                       if(response.isSuccessful()) {
                           JOptionPane.showMessageDialog(null, "Enregistrement avec succès");
                       }
                   }

                   @Override
                   public void onFailure(Call<Void> call, Throwable t) {
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

        String cin2 = txtNumCompte.getText();
        
            if(!cin2.equals(cin)){
                JOptionPane.showMessageDialog(null, "Le CIN n'est pas modifiable \n Sinon, veillez enregistrer un nouveau client");
            } else {
                
            
        String requete = "update client_table set cin =?,nomc =?,prenomc=?,date_naissance =?,age =?,sexe =?,gsm =?,adresse=?,image =? where  cin ='" + cin2 + "'";
        try {
            ps = conn.prepareStatement(requete);
            ps.setString(1, txtNumCompte.getText());
            ps.setString(2, txtNom.getText());
            ps.setString(3, txtSolde.getText());
            ps.setString(4, ((JTextField) txtdtnaissance.getDateEditor().getUiComponent()).getText());
            ps.setString(5, txtage.getText());
            ps.setString(6, sexee);
            ps.setString(7, txtgsm.getText());
            ps.setString(8, txtadresse.getText());
            ps.setString(9, txtpath.getText());
            if(Integer.parseInt(txtage.getText()) < 18){
                JOptionPane.showMessageDialog(null, "L'Age doit être suppérieur à 18");
            } else {
            ps.execute();
            JOptionPane.showMessageDialog(null, "Modification avec succès");}
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {

            try {
                ps.close();
                rs.close();

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "errer");
            }
        }

}
    }//GEN-LAST:event_modifierokActionPerformed

    private void txtageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtageActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        sexee = "Masculin";
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        sexee = "Féminin";
    }//GEN-LAST:event_jRadioButton2ActionPerformed
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
        } catch (Exception e) {
        }

    }

    private void btnpathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpathActionPerformed
        imagess();

    }//GEN-LAST:event_btnpathActionPerformed

    private void txtageMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtageMouseEntered


    }//GEN-LAST:event_txtageMouseEntered

    private void txtgsmKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtgsmKeyTyped
        char t = evt.getKeyChar();
        if (!(Character.isDigit(t) || (t == KeyEvent.VK_BACK_SPACE) || (t == KeyEvent.VK_DELETE))) {
            evt.consume();
        }


    }//GEN-LAST:event_txtgsmKeyTyped

    private void txtdtnaissanceAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_txtdtnaissanceAncestorAdded

    }//GEN-LAST:event_txtdtnaissanceAncestorAdded

    private void txtdtnaissanceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdtnaissanceKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdtnaissanceKeyReleased

    private void txtdtnaissanceComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_txtdtnaissanceComponentAdded
        try {
            Date actuelle = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy");
            String date = dateFormat.format(actuelle);
            String dc = date;

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy", Locale.getDefault());
            String d = sdf.format(txtdtnaissance.getDate());

            int d1 = Integer.parseInt(dc);
            int d2 = Integer.parseInt(d);

            int r = (d1 - d2);
            String resultat = String.valueOf(r);
            txtage.setText(resultat);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Taper Date de Naissance");
        }
    }//GEN-LAST:event_txtdtnaissanceComponentAdded

    private void txtdtnaissanceMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtdtnaissanceMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdtnaissanceMouseReleased

    private void txtageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtageMouseClicked
        try {
            Date actuelle = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy");
            String date = dateFormat.format(actuelle);
            String dc = date;

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy", Locale.getDefault());
            String d = sdf.format(txtdtnaissance.getDate());

            int d1 = Integer.parseInt(dc);
            int d2 = Integer.parseInt(d);

            int r = (d1 - d2);
            String resultat = String.valueOf(r);
            txtage.setText(resultat);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Taper Date de Naissance");
        }
    }//GEN-LAST:event_txtageMouseClicked

    private void txtdtnaissanceInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtdtnaissanceInputMethodTextChanged

    }//GEN-LAST:event_txtdtnaissanceInputMethodTextChanged

    private void txtdtnaissanceVetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_txtdtnaissanceVetoableChange

    }//GEN-LAST:event_txtdtnaissanceVetoableChange

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
            java.util.logging.Logger.getLogger(AjoutClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new AjoutClient().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(AjoutClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ajoutbtnok;
    private javax.swing.JButton btnpath;
    public javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel image;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton modifierok;
    private javax.swing.JTextField txtNom;
    private javax.swing.JTextField txtNumCompte;
    private javax.swing.JTextField txtSolde;
    private javax.swing.JTextField txtadresse;
    private javax.swing.JTextField txtage;
    private com.toedter.calendar.JDateChooser txtdtnaissance;
    private javax.swing.JTextField txtgsm;
    private javax.swing.JTextField txtpath;
    // End of variables declaration//GEN-END:variables
}
