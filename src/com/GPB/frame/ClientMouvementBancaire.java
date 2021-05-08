/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.GPB.frame;

import com.GPB.api.ClientAPI;
import com.GPB.api.RetraitAPI;
import com.GPB.api.UserAPI;
import com.GPB.api.VersementAPI;
import com.GPB.api.VirementAPI;
import com.GPB.entities.Client;
import com.GPB.entities.Retrait;
import com.GPB.entities.Versement;
import com.GPB.entities.Virement;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *
 * @author anouer
 */
public class ClientMouvementBancaire extends javax.swing.JFrame {

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
    private static final int WIDE6 = 155;
    private static final int WIDE4 = 115;
    private static final int WIDE5 = 120;

    /**
     * Creates new form AjoutCandidat
     * @throws java.sql.SQLException
     */
    public ClientMouvementBancaire() throws SQLException {
        initComponents();
        //this.setIconImage(new ImageIcon(getClass().getResource("logocar.png")).getImage());
        conn = ConexionBD.Conexion();
        /*btnaddpermis.setVisible(false);*/
        recept();
        /*buttonGroup2.add(act);
        buttonGroup2.add(arc);*/

//        ImageIcon img202 = new ImageIcon(getClass().getResource("file_image_1.png"));
//        image.setIcon(img202);
        jTextField1.setVisible(false);
    }
    
    public void loadData6() {
        try {
            VersementAPI versementAPI = UserAPI.getUser().create(VersementAPI.class);
            versementAPI.findByNumCompte(jTextField1.getText()).enqueue(new Callback<List<Versement>>() {
                @Override
                public void onResponse(Call<List<Versement>> call, Response<List<Versement>> response) {
                    if(response.isSuccessful()) {
                        Integer sommeVers = 0;
                        Integer nbVers = 0;
                        DefaultTableModel defaultTableModel = new DefaultTableModel();
                        defaultTableModel.addColumn("Id");
                        defaultTableModel.addColumn("NumCompte");
                        defaultTableModel.addColumn("Montant Versement");
                        defaultTableModel.addColumn("Date de Depot");
                        for(Versement versement : response.body()) {
                            defaultTableModel.addRow(new Object[] {
                                versement.getIdVersement(),
                                versement.getNumCompte(),
                                versement.getMontant_Versement(),
                                versement.getDateVersement()
                            });
                            sommeVers += versement.getMontant_Versement();
                            nbVers++;
                        }
                        Table6.setModel(defaultTableModel);
//                        Table.getColumnModel().getColumn(0).setPreferredWidth(5);
//                        Table.getColumnModel().getColumn(2).setPreferredWidth(100);
                        Table6.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                        for (int i = 0; i < Table6.getColumnCount(); i++) {
                            TableColumn col = Table6.getColumnModel().getColumn(i);
                            col.setPreferredWidth(WIDE6);
                            col.setMaxWidth(WIDE6 * 5);
                        }
                        Table6.setPreferredScrollableViewportSize(new Dimension(
                            Table6.getColumnCount() * WIDE6, Table6.getRowHeight() * 16));
                        EffectifVersement.setText(String.valueOf(nbVers));
                        TotalVersement.setText(String.valueOf(sommeVers));
                    }
                }

                @Override
                public void onFailure(Call<List<Versement>> call, Throwable t) {
                    JOptionPane.showConfirmDialog(null, t.getMessage()); //To change body of generated methods, choose Tools | Templates.
                }
                   
            });
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e.getMessage());
        }
    }

    public void loadData4() {
        try {
            RetraitAPI retraitAPI = UserAPI.getUser().create(RetraitAPI.class);
            retraitAPI.findByNumCompte(jTextField1.getText()).enqueue(new Callback<List<Retrait>>() {
                @Override
                public void onResponse(Call<List<Retrait>> call, Response<List<Retrait>> response) {
                    if(response.isSuccessful()) {
                        Integer sommeRet = 0;
                        Integer nbRet = 0;
                        DefaultTableModel defaultTableModel = new DefaultTableModel();
                        defaultTableModel.addColumn("Id");
                        defaultTableModel.addColumn("NumCheque");
                        defaultTableModel.addColumn("NumCompte");
                        defaultTableModel.addColumn("Montant Retrait");
                        defaultTableModel.addColumn("Date de Retrait");
                        for(Retrait retrait : response.body()) {
                            defaultTableModel.addRow(new Object[] {
                                retrait.getIdRetrait(),
                                retrait.getNumCheque(),
                                retrait.getNumCompte(),
                                retrait.getMontant_Retrait(),
                                retrait.getDateRetrait()
                            });
                            sommeRet += retrait.getMontant_Retrait();
                            nbRet++;
                        }
                        Table4.setModel(defaultTableModel);
//                        Table.getColumnModel().getColumn(0).setPreferredWidth(5);
//                        Table.getColumnModel().getColumn(2).setPreferredWidth(100);
                        Table4.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                        for (int i = 0; i < Table4.getColumnCount(); i++) {
                            TableColumn col = Table4.getColumnModel().getColumn(i);
                            col.setPreferredWidth(WIDE4);
                            col.setMaxWidth(WIDE4 * 5);
                        }
                        Table4.setPreferredScrollableViewportSize(new Dimension(
                            Table4.getColumnCount() * WIDE4, Table4.getRowHeight() * 16));
                        EffectifRetrait.setText(String.valueOf(nbRet));
                        TotalRetrait.setText(String.valueOf(sommeRet));
                    }
                }

                @Override
                public void onFailure(Call<List<Retrait>> call, Throwable t) {
                    JOptionPane.showConfirmDialog(null, t.getMessage()); //To change body of generated methods, choose Tools | Templates.
                }
                   
            });
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e.getMessage());
        }
    }
    
    public void loadData5() {
        try {
            VirementAPI virementAPI = UserAPI.getUser().create(VirementAPI.class);
            virementAPI.findByNumCompteE(jTextField1.getText()).enqueue(new Callback<List<Virement>>() {
                @Override
                public void onResponse(Call<List<Virement>> call, Response<List<Virement>> response) {
                    if(response.isSuccessful()) {
                        Integer sommeVir = 0;
                        Integer nbVir = 0;
                        DefaultTableModel defaultTableModel = new DefaultTableModel();
                        defaultTableModel.addColumn("Id");
                        defaultTableModel.addColumn("NumCompte Emetteur");
                        defaultTableModel.addColumn("NumCompte Recepteur");
                        defaultTableModel.addColumn("Montant Virement");
                        defaultTableModel.addColumn("Date de Virement");
                        for(Virement virement : response.body()) {
                            defaultTableModel.addRow(new Object[] {
                                virement.getIdVirement(),
                                virement.getNumCompteE(),
                                virement.getNumCompteR(),
                                virement.getMontant_Virement(),
                                virement.getDateVirement()
                            });
                            sommeVir += virement.getMontant_Virement();
                            nbVir++;
                        }
                        Table5.setModel(defaultTableModel);
//                        Table.getColumnModel().getColumn(0).setPreferredWidth(5);
//                        Table.getColumnModel().getColumn(2).setPreferredWidth(100);
                        Table5.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                        for (int i = 0; i < Table5.getColumnCount(); i++) {
                            TableColumn col = Table5.getColumnModel().getColumn(i);
                            col.setPreferredWidth(WIDE5);
                            col.setMaxWidth(WIDE5 * 5);
                        }
                        Table5.setPreferredScrollableViewportSize(new Dimension(
                            Table5.getColumnCount() * WIDE5, Table5.getRowHeight() * 16));
                        EffectifVirement.setText(String.valueOf(nbVir));
                        TotalVirement.setText(String.valueOf(sommeVir));
                    }
                }

                @Override
                public void onFailure(Call<List<Virement>> call, Throwable t) {
                    JOptionPane.showConfirmDialog(null, t.getMessage()); //To change body of generated methods, choose Tools | Templates.
                }
                   
            });
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e.getMessage());
        }
    }

    private void recept() throws SQLException {
//        Liste_des_Clients ad = new Liste_des_Clients();
//        String r = ad.re();
//        if ("ah".equals(r)) {
//            modifierok.setVisible(true);
//        } else if (!"ah".equals(r)) {
//            modifierok.setVisible(false);
//        }
//        if ("a".equals(r)) {
//            ajoutbtnok.setVisible(true);
//        } else if (!"a".equals(r)) {
//            ajoutbtnok.setVisible(false);
//        }

    }

    public void cleardata() {
        txtNumCompte.setText("");
        txtNom.setText("");
        txtSolde.setText("");
        sexee = "Masculin";
        etat = "Actif";
    }

    public void Recuper() throws SQLException {
        Liste_des_Clients inf = new Liste_des_Clients();
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
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtSolde = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNom = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtNumCompte = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        Table5 = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        Table6 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        Table4 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        EffectifVersement = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        TotalVersement = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        EffectifVirement = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        TotalVirement = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        EffectifRetrait = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        TotalRetrait = new javax.swing.JLabel();
        printbtn1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1160, 640));
        setPreferredSize(new java.awt.Dimension(1160, 640));
        setResizable(false);
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

        jPanel4.setBackground(new java.awt.Color(0, 153, 153));

        jLabel10.setFont(new java.awt.Font("Advent Pro", 0, 40)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("MOUVEMENTS BANCAIRES");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 575, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(575, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true), "Information sur le Client :", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 3, 12), new java.awt.Color(0, 153, 153))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(0, 0, 204));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Nom  :");

        txtSolde.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Solde Actuel :");

        txtNom.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("N°Compte :");

        txtNumCompte.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNumCompte, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                    .addComponent(txtNom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSolde, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumCompte, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNom, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSolde, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153)), "Tous les Virements :", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 3, 10))); // NOI18N

        Table5.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        Table5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        Table5.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        Table5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Table5.setSelectionBackground(new java.awt.Color(0, 153, 153));
        Table5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Table5MouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Table5MouseReleased(evt);
            }
        });
        jScrollPane5.setViewportView(Table5);

        jScrollPane6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153)), "Tous les Versements :", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 3, 10))); // NOI18N

        Table6.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        Table6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        Table6.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        Table6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Table6.setSelectionBackground(new java.awt.Color(0, 153, 153));
        Table6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Table6MouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Table6MouseReleased(evt);
            }
        });
        jScrollPane6.setViewportView(Table6);

        jScrollPane4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153)), "Tous les Retraits :", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 3, 10))); // NOI18N

        Table4.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        Table4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        Table4.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        Table4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Table4.setSelectionBackground(new java.awt.Color(0, 153, 153));
        Table4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Table4MouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Table4MouseReleased(evt);
            }
        });
        jScrollPane4.setViewportView(Table4);

        jLabel4.setFont(new java.awt.Font("Bahnschrift", 1, 11)); // NOI18N
        jLabel4.setText("NB VERS. : ");

        EffectifVersement.setText("           ");

        jLabel15.setFont(new java.awt.Font("Bahnschrift", 1, 11)); // NOI18N
        jLabel15.setText("SOMME DU VERSEMENT : ");

        TotalVersement.setText("        ");

        jLabel5.setFont(new java.awt.Font("Bahnschrift", 1, 11)); // NOI18N
        jLabel5.setText("NB VIR. : ");

        EffectifVirement.setText("           ");

        jLabel17.setFont(new java.awt.Font("Bahnschrift", 1, 11)); // NOI18N
        jLabel17.setText("SOMME DU VIREMENT : ");

        TotalVirement.setText("        ");

        jLabel6.setFont(new java.awt.Font("Bahnschrift", 1, 11)); // NOI18N
        jLabel6.setText("NB RET. : ");

        EffectifRetrait.setText("           ");

        jLabel16.setFont(new java.awt.Font("Bahnschrift", 1, 11)); // NOI18N
        jLabel16.setText("SOMME DU RETRAIT : ");

        TotalRetrait.setText("        ");

        printbtn1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        printbtn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GPB/images/printer.png"))); // NOI18N
        printbtn1.setText("Imprimer");
        printbtn1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        printbtn1.setContentAreaFilled(false);
        printbtn1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        printbtn1.setOpaque(true);
        printbtn1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                printbtn1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                printbtn1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                printbtn1MousePressed(evt);
            }
        });
        printbtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printbtn1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 627, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(EffectifVersement, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(56, 56, 56)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(150, 150, 150)
                                                .addComponent(TotalVersement, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, 0)
                                                .addComponent(EffectifVirement, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(60, 60, 60)
                                                .addComponent(EffectifRetrait, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(56, 56, 56)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(130, 130, 130)
                                                .addComponent(TotalRetrait, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, 0)
                                                .addComponent(TotalVirement, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(printbtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(EffectifVersement)
                            .addComponent(jLabel15)
                            .addComponent(TotalVersement))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(EffectifVirement)
                            .addComponent(jLabel17)
                            .addComponent(TotalVirement))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(EffectifRetrait)
                                    .addComponent(jLabel6)))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TotalRetrait)
                                    .addComponent(jLabel16))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(printbtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed

    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

    }//GEN-LAST:event_formWindowClosing

    private void formWindowDeactivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowDeactivated

    }//GEN-LAST:event_formWindowDeactivated

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated

    }//GEN-LAST:event_formWindowActivated

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        //modifok.setEnabled(false);  

    }//GEN-LAST:event_formWindowOpened

    private void Table6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Table6MouseClicked

    private void Table6MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table6MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_Table6MouseReleased

    private void Table4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Table4MouseClicked

    private void Table4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table4MouseReleased
        //        suppPret.setEnabled(true);
        //        AjoutPret.setEnabled(false);
        //        modifierPret.setEnabled(true);
        //        deplacePretClient();
        //        deplacePretBanque();
        //        deplacePret();

        //clearsuivi();
    }//GEN-LAST:event_Table4MouseReleased

    private void Table5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Table5MouseClicked

    private void Table5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table5MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_Table5MouseReleased

    private void printbtn1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_printbtn1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_printbtn1MouseEntered

    private void printbtn1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_printbtn1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_printbtn1MouseExited

    private void printbtn1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_printbtn1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_printbtn1MousePressed

    private void printbtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printbtn1ActionPerformed

        MessageFormat header = new MessageFormat("Mouvement Bancaire du Client N°Compte Bancaire :"+jTextField1.getText());
        MessageFormat footer = new MessageFormat("Page{0,number,integer}");
        try {
            Table6.print(JTable.PrintMode.FIT_WIDTH, header, footer);
            Table4.print(JTable.PrintMode.FIT_WIDTH, header, footer);
            Table5.print(JTable.PrintMode.FIT_WIDTH, header, footer);

        } catch (java.awt.print.PrinterException e) {
            System.err.format("Erreur d'impression ", e.getMessage());
        }
    }//GEN-LAST:event_printbtn1ActionPerformed


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
            java.util.logging.Logger.getLogger(ClientMouvementBancaire.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                    new ClientMouvementBancaire().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(ClientMouvementBancaire.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel EffectifRetrait;
    private javax.swing.JLabel EffectifVersement;
    private javax.swing.JLabel EffectifVirement;
    private javax.swing.JTable Table4;
    private javax.swing.JTable Table5;
    private javax.swing.JTable Table6;
    private javax.swing.JLabel TotalRetrait;
    private javax.swing.JLabel TotalVersement;
    private javax.swing.JLabel TotalVirement;
    public javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    public javax.swing.JTextField jTextField1;
    private javax.swing.JButton printbtn1;
    public javax.swing.JLabel txtNom;
    public javax.swing.JLabel txtNumCompte;
    public javax.swing.JLabel txtSolde;
    // End of variables declaration//GEN-END:variables
}
