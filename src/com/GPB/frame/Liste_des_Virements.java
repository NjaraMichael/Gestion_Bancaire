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
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JTable;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import net.proteanit.sql.DbUtils;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *
 * @author anouer
 */
public final class Liste_des_Virements extends javax.swing.JInternalFrame {

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    static String test;
    static String te;
    public static String nbrh;
    public static String nbrh2;
    public static String datfin;
    public static String mont;
    public static String catp;
    public static String datfin2;
    public static String mont2;
    public static String catp2;
    public ImageIcon Format = null;
    private static final int WIDE = 150;

    /**
     * Creates new form Liste_des_Virements
     * @throws java.sql.SQLException
     */
    public Liste_des_Virements() throws SQLException {
        initComponents();
        conn = ConexionBD.Conexion();
//        loadData();
        remove_title_bar();
        txtrechercher.setText("Tapez ID Virement");
        txtrechercher1.setText("Tapez N°Compte");
        nncontart.setVisible(false);
        ccode.setVisible(false);
        cconduite.setVisible(false);

//        String requete4 = "delete from effectif";
//            ps = conn.prepareStatement(requete4);
//            ps.execute();

        loadData();
    }
    
    public void loadData() {
        try {
            VirementAPI virementAPI = UserAPI.getUser().create(VirementAPI.class);
            virementAPI.findall().enqueue(new Callback<List<Virement>>() {
                @Override
                public void onResponse(Call<List<Virement>> call, Response<List<Virement>> response) {
                    if(response.isSuccessful()) {
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
                        }
                        Table.setModel(defaultTableModel);
//                        Table.getColumnModel().getColumn(0).setPreferredWidth(5);
//                        Table.getColumnModel().getColumn(2).setPreferredWidth(100);
                        Table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                        for (int i = 0; i < Table.getColumnCount(); i++) {
                            TableColumn col = Table.getColumnModel().getColumn(i);
                            col.setPreferredWidth(WIDE);
                            col.setMaxWidth(WIDE * 5);
                        }
                        Table.setPreferredScrollableViewportSize(new Dimension(
                            Table.getColumnCount() * WIDE, Table.getRowHeight() * 16));
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

 
    public String re() {
        return te;
    }

    void remove_title_bar() {
        putClientProperty("Liste_des_Virements.isPalette", Boolean.TRUE);
        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        ((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        this.setBorder(null);

    }

    public void recuperutlisateur() {
        AcceuilGui los = new AcceuilGui();

        try {
            String recp = los.utilisateurs();
            System.out.println(recp);
            llogin.setText(recp);
            if (recp.equals("Adminstrateur")) {
                Panelaction.setVisible(false);
            } else {
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void clear() {
        try {
            txtNumCompteE.setText("");
            txtNumVi.setText("");
            txtNumCompteR.setText("");
            txtMontantVi.setText("");
            txtNomR.setText("");
            txtNomE.setText("");
            txtDateVi.setText("");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //display table
    /*public void loadData() {
        try {
            String requete = "select cin as 'CIN' ,nomc as 'Nom',prenomc as 'Prenom',date_naissance as 'Date de Naissance',adresse as 'Adresse',sexe As 'Sexe',gsm as 'GSM',date_inscription as 'Date dinscription' from client_table ";
            System.out.println(requete);
            ps = conn.prepareStatement(requete);
            rs = ps.executeQuery();
            Table.setModel(DbUtils.resultSetToTableModel(rs));
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

    }*/

    public void Deplace() {
        int row = Table.getSelectedRow();
        String id = Table.getValueAt(row, 0).toString();
        VirementAPI virementAPI = UserAPI.getUser().create(VirementAPI.class);
        virementAPI.find(Integer.parseInt(id)).enqueue(new Callback<Virement>(){
            @Override
            public void onResponse(Call<Virement> call, Response<Virement> response) {
                Virement virement = response.body();
                txtNumVi.setText(String.valueOf(virement.getIdVirement()));
                txtNumCompteE.setText(virement.getNumCompteE());
                txtNumCompteR.setText(virement.getNumCompteR());
                txtMontantVi.setText(String.valueOf(virement.getMontant_Virement()));
                txtDateVi.setText(virement.getDateVirement());
            }

            @Override
            public void onFailure(Call<Virement> call, Throwable t) {
                JOptionPane.showConfirmDialog(null, t.getMessage()); //To change body of generated methods, choose Tools | Templates.
            }
            
        });
        String numcompte = Table.getValueAt(row, 2).toString();
        ClientAPI clientAPI = UserAPI.getUser().create(ClientAPI.class);
        clientAPI.find(numcompte).enqueue(new Callback<Client>(){
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
        String numcomptee = Table.getValueAt(row, 1).toString();
        ClientAPI clientAPIE = UserAPI.getUser().create(ClientAPI.class);
        clientAPIE.find(numcomptee).enqueue(new Callback<Client>(){
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
//        try {
//
//            String numcompte = Table.getValueAt(row, 1).toString();
//            String requet = " select * from  client where NumCompte = '" + numcompte + "' ";
//            ps = conn.prepareStatement(requet);
//            rs = ps.executeQuery();
//
//            if (rs.next()) {
//                String t1 = rs.getString("Nom");
//                txtNom.setText(t1);
//            }
//            ps.close();
//            rs.close();
//        } catch (SQLException e) {
//            System.out.println(e);
//
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

    public String gettableresult() {
        return test;
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
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtMontantVi = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNomR = new javax.swing.JLabel();
        txtNumCompteR = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtNumVi = new javax.swing.JLabel();
        txtDateVi = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtNumCompteE = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNomE = new javax.swing.JLabel();
        nncontart = new javax.swing.JLabel();
        ccode = new javax.swing.JLabel();
        cconduite = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable(){
            public boolean isCellEditable(int d , int c){
                return false;

            }
        };
        txtrechercher = new javax.swing.JTextField();
        btnrefresh = new javax.swing.JButton();
        Panelaction = new javax.swing.JPanel();
        btnsupprimer = new javax.swing.JButton();
        btnaj = new javax.swing.JButton();
        modifierbtn = new javax.swing.JButton();
        txtbachground = new javax.swing.JLabel();
        txtrechercher1 = new javax.swing.JTextField();
        txtbackground1 = new javax.swing.JLabel();
        llogin = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        printbtn = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();

        setBorder(null);
        setTitle("Listes");
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        getContentPane().setLayout(null);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true), "Information sur le Virement :", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 3, 12))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(0, 0, 204));

        jLabel2.setText("N°Compte Récepteur :");

        jLabel4.setText("Montant :");

        txtMontantVi.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtMontantVi.setForeground(new java.awt.Color(0, 0, 153));

        jLabel3.setText("Nom Récepteur :");

        txtNomR.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtNomR.setForeground(new java.awt.Color(0, 0, 153));

        txtNumCompteR.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtNumCompteR.setForeground(new java.awt.Color(0, 0, 153));

        jLabel1.setText("N°Virement :");

        txtNumVi.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtNumVi.setForeground(new java.awt.Color(0, 0, 153));

        txtDateVi.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtDateVi.setForeground(new java.awt.Color(0, 0, 153));

        jLabel5.setText("Date de Virement :");

        jLabel6.setText("N°Compte Emetteur :");

        txtNumCompteE.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtNumCompteE.setForeground(new java.awt.Color(0, 0, 153));

        jLabel7.setText("Nom Emetteur :");

        txtNomE.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtNomE.setForeground(new java.awt.Color(0, 0, 153));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(txtNumVi, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(196, 196, 196))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(txtNumCompteE, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtMontantVi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtDateVi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNomR, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(190, 190, 190))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNomE, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNumCompteR, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(158, 158, 158))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumVi, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtNumCompteE, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNomE, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumCompteR, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNomR, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtMontantVi, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtDateVi, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(810, 130, 330, 270);

        nncontart.setToolTipText("Pas du Contrat");
        nncontart.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        nncontart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nncontartMouseClicked(evt);
            }
        });
        getContentPane().add(nncontart);
        nncontart.setBounds(820, 6, 40, 40);

        ccode.setToolTipText("Contrat du code de la route");
        ccode.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ccode.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ccodeMouseClicked(evt);
            }
        });
        getContentPane().add(ccode);
        ccode.setBounds(880, 6, 40, 40);

        cconduite.setToolTipText("Contrat du Conduite");
        cconduite.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cconduite.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cconduiteMouseClicked(evt);
            }
        });
        getContentPane().add(cconduite);
        cconduite.setBounds(940, 6, 40, 40);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153)), "Liste de Virements :", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 3, 12))); // NOI18N
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        Table.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        Table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        Table.setAutoscrolls(false);
        Table.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        Table.setRowHeight(20);
        Table.setSelectionBackground(new java.awt.Color(0, 153, 153));
        Table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TableMouseEntered(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                TableMouseReleased(evt);
            }
        });
        Table.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TableKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(Table);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(0, 120, 790, 350);

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
        txtrechercher.setBounds(613, 92, 213, 15);

        btnrefresh.setBackground(new java.awt.Color(153, 0, 0));
        btnrefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GPB/images/interface.png"))); // NOI18N
        btnrefresh.setBorder(null);
        btnrefresh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnrefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrefreshActionPerformed(evt);
            }
        });
        getContentPane().add(btnrefresh);
        btnrefresh.setBounds(840, 70, 70, 40);

        Panelaction.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153)), "Action :", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Verdana", 1, 12))); // NOI18N

        btnsupprimer.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnsupprimer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GPB/images/file (1).png"))); // NOI18N
        btnsupprimer.setText("Supprimer");
        btnsupprimer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnsupprimer.setContentAreaFilled(false);
        btnsupprimer.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnsupprimer.setOpaque(true);
        btnsupprimer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnsupprimerMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnsupprimerMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnsupprimerMousePressed(evt);
            }
        });
        btnsupprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsupprimerActionPerformed(evt);
            }
        });

        btnaj.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnaj.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GPB/images/file.png"))); // NOI18N
        btnaj.setText("Nouveau");
        btnaj.setToolTipText("");
        btnaj.setAutoscrolls(true);
        btnaj.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnaj.setContentAreaFilled(false);
        btnaj.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnaj.setOpaque(true);
        btnaj.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnajMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnajMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnajMousePressed(evt);
            }
        });
        btnaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnajActionPerformed(evt);
            }
        });

        modifierbtn.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        modifierbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GPB/images/file (2).png"))); // NOI18N
        modifierbtn.setText("Modifier");
        modifierbtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        modifierbtn.setContentAreaFilled(false);
        modifierbtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        modifierbtn.setOpaque(true);
        modifierbtn.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                modifierbtnMouseMoved(evt);
            }
        });
        modifierbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                modifierbtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                modifierbtnMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                modifierbtnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                modifierbtnMouseReleased(evt);
            }
        });
        modifierbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifierbtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelactionLayout = new javax.swing.GroupLayout(Panelaction);
        Panelaction.setLayout(PanelactionLayout);
        PanelactionLayout.setHorizontalGroup(
            PanelactionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelactionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnaj, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnsupprimer, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(modifierbtn, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                .addContainerGap())
        );
        PanelactionLayout.setVerticalGroup(
            PanelactionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelactionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelactionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnaj, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsupprimer, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modifierbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        getContentPane().add(Panelaction);
        Panelaction.setBounds(0, 480, 540, 100);

        txtbachground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GPB/frame/txt2.png"))); // NOI18N
        getContentPane().add(txtbachground);
        txtbachground.setBounds(610, 90, 220, 20);

        txtrechercher1.setBackground(new java.awt.Color(240, 240, 240));
        txtrechercher1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtrechercher1.setForeground(new java.awt.Color(0, 153, 153));
        txtrechercher1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtrechercher1.setBorder(null);
        txtrechercher1.setDoubleBuffered(true);
        txtrechercher1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtrechercher1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtrechercher1MouseEntered(evt);
            }
        });
        txtrechercher1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtrechercher1ActionPerformed(evt);
            }
        });
        txtrechercher1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtrechercher1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtrechercher1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtrechercher1KeyTyped(evt);
            }
        });
        getContentPane().add(txtrechercher1);
        txtrechercher1.setBounds(922, 87, 213, 15);

        txtbackground1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GPB/frame/txt2.png"))); // NOI18N
        getContentPane().add(txtbackground1);
        txtbackground1.setBounds(920, 80, 220, 30);
        getContentPane().add(llogin);
        llogin.setBounds(340, 60, 160, 0);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Impression :", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Verdana", 1, 12))); // NOI18N

        printbtn.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        printbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GPB/images/printer.png"))); // NOI18N
        printbtn.setText("Imprimer");
        printbtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        printbtn.setContentAreaFilled(false);
        printbtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        printbtn.setOpaque(true);
        printbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                printbtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                printbtnMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                printbtnMousePressed(evt);
            }
        });
        printbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printbtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(printbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(printbtn, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2);
        jPanel2.setBounds(960, 490, 174, 90);

        jPanel4.setBackground(new java.awt.Color(0, 153, 153));

        jLabel10.setFont(new java.awt.Font("Advent Pro", 0, 40)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("GESTION DES VIREMENTS");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 1150, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel4);
        jPanel4.setBounds(0, 0, 1160, 50);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnajActionPerformed
        Liste_des_Virements.te = "a";

        AjoutVirement act = null;
        try {
            act = new AjoutVirement();
            
//            act.txtMontant.setText("");
//            act.txtdateVe.setDate(new Date());
        } catch (SQLException ex) {
            Logger.getLogger(Liste_des_Virements.class.getName()).log(Level.SEVERE, null, ex);
        }
        act.setVisible(true);
        act.cleardata();
        btnsupprimer.setEnabled(false);
        modifierbtn.setEnabled(false);
        loadData();
        clear();
        //ImageIcon img202 = new ImageIcon(getClass().getResource("file_image_1.png"));
        //image.setIcon(img202);
    }//GEN-LAST:event_btnajActionPerformed

    private void TableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableMouseClicked
        Deplace();


    }//GEN-LAST:event_TableMouseClicked

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        btnsupprimer.setEnabled(false);
        modifierbtn.setEnabled(false);
    }//GEN-LAST:event_formInternalFrameActivated

    private void TableMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableMouseEntered
        //loadData();
    }//GEN-LAST:event_TableMouseEntered

    private void btnsupprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsupprimerActionPerformed
        try {
            int result = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer ce virement?",
                    "Suppression Virement", JOptionPane.YES_NO_OPTION);
            
            if(result == JOptionPane.YES_NO_OPTION) {
                    VirementAPI virementAPI = UserAPI.getUser().create(VirementAPI.class);
                    virementAPI.delete(Integer.parseInt(txtNumVi.getText())).enqueue(new Callback<Void>(){
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if(response.isSuccessful()) {
                                loadData();
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            JOptionPane.showConfirmDialog(null, t.getMessage()); //To change body of generated methods, choose Tools | Templates.
                        }
                    });
            }
            
//            if (JOptionPane.showConfirmDialog(null, "Vous voulez vraiment supprimer ce client?",
//                    "Suppression Client", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
//                if (txtNumCompte.getText().length() != 0) {
//
//                    ClientAPI clientAPI = UserAPI.getUser().create(ClientAPI.class);
//                    clientAPI.delete(txtNumCompte.getText()).enqueue(new Callback<Void>(){
//                        @Override
//                        public void onResponse(Call<Void> call, Response<Void> response) {
//                            if(response.isSuccessful()) {
//                                loadData();
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<Void> call, Throwable t) {
//                            JOptionPane.showConfirmDialog(null, t.getMessage()); //To change body of generated methods, choose Tools | Templates.
//                        }
//                    });
//                } else {
//                    JOptionPane.showMessageDialog(null, "veuillez choisir  un Client !");
//                }
//            }
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "erreur de supprimer peut être qu'il existe des prets en cours pour cette client\n" + e.getMessage());
        }
        btnsupprimer.setEnabled(false);
        modifierbtn.setEnabled(false);
        loadData();
        clear();

    }//GEN-LAST:event_btnsupprimerActionPerformed


    private void TableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableMouseReleased
        btnsupprimer.setEnabled(true);
        modifierbtn.setEnabled(true);
        //Deplace();
    }//GEN-LAST:event_TableMouseReleased

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        // TODO add your handling code here:

    }//GEN-LAST:event_formMouseMoved


    private void modifierbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifierbtnActionPerformed
        Liste_des_Virements.te = "ah";
        try {
            AjoutVirement act = new AjoutVirement();
            if(txtNumVi.getText() != "") {
                act.txtId.setText(txtNumVi.getText());
                act.txtNumCompteE.setText(txtNumCompteE.getText());
                act.txtNumCompteE.setEnabled(false);
                act.txtNumCompteR.setText(txtNumCompteR.getText());
                act.txtNumCompteR.setEnabled(false);
                act.txtMontant.setText(txtMontantVi.getText());
                act.txtAncienMontant.setText(txtMontantVi.getText());
                act.txtNomE.setText(txtNomE.getText());
                act.txtNomR.setText(txtNomR.getText());
//                Calendar ca = new GregorianCalendar();
//                String day = ca.get(Calendar.DAY_OF_MONTH) + "";
//                String month = ca.get(Calendar.MONTH) + 1 + "";
//                String year = ca.get(Calendar.YEAR) + "";
//
//                if (day.length() == 1) {
//                    day = "0" + day;
//                }
//                if (month.length() == 1) {
//                    month = "0" + month;
//                }
//
//                String dd = year + "-" + month + "-" + day;
                
                Date date = new SimpleDateFormat("dd/MM/yyyy").parse(txtDateVi.getText());
                act.txtdateVi.setDate(date);
            }
            act.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Liste_des_Virements.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Liste_des_Virements.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        btnsupprimer.setEnabled(false);
        modifierbtn.setEnabled(false);
        loadData();
        clear();

    }//GEN-LAST:event_modifierbtnActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened

    }//GEN-LAST:event_formInternalFrameOpened

    private void modifierbtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modifierbtnMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_modifierbtnMouseReleased

    private void modifierbtnMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modifierbtnMouseMoved

    }//GEN-LAST:event_modifierbtnMouseMoved

    private void printbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printbtnActionPerformed

        MessageFormat header = new MessageFormat("Liste de Virements:");
        MessageFormat footer = new MessageFormat("Page{0,number,integer}");
        try {
            Table.print(JTable.PrintMode.FIT_WIDTH, header, footer);

        } catch (java.awt.print.PrinterException e) {
            System.err.format("Erreur d'impression ", e.getMessage());
        }


    }//GEN-LAST:event_printbtnActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        loadData();
        clear();
        btnsupprimer.setEnabled(false);
        modifierbtn.setEnabled(false);
        txtrechercher.setText("");
        ImageIcon img = new ImageIcon(getClass().getResource("txt2.png"));
        txtbachground.setIcon(img);
        txtrechercher.setText("Tapez Id Virement");
        ImageIcon img2 = new ImageIcon(getClass().getResource("txt2.png"));
        txtbackground1.setIcon(img2);
        txtrechercher1.setText("Tapez N°Compte");
    }//GEN-LAST:event_formMouseClicked

    private void txtrechercherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtrechercherActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtrechercherActionPerformed

    private void btnrefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrefreshActionPerformed
        loadData();

        clear();
        btnsupprimer.setEnabled(false);
        modifierbtn.setEnabled(false);
        ImageIcon img = new ImageIcon(getClass().getResource("txt2.png"));
        txtbachground.setIcon(img);
        txtrechercher.setText("Tapez Id Virement");
        ImageIcon img2 = new ImageIcon(getClass().getResource("txt2.png"));
        txtbackground1.setIcon(img2);
        txtrechercher1.setText("Tapez N°Compte");
    }//GEN-LAST:event_btnrefreshActionPerformed
    public void search() {
        try {
            String requete = "select cin as 'CIN' ,nomc as 'Nom' ,prenomc as 'Prenom' ,date_naissance as 'Date de Naissance',sexe As 'Sexe',gsm as 'GSM',adresse as 'Adresse',date_inscription as 'Date dinscription' from  client_table where nomc LIKE  ?";
            ps = conn.prepareStatement(requete);
            ps.setString(1, "%" + txtrechercher.getText() + "%");
            rs = ps.executeQuery();
            Table.setModel(DbUtils.resultSetToTableModel(rs));
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
//        
    }
    private void txtrechercherKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrechercherKeyReleased

        VirementAPI virementAPI = UserAPI.getUser().create(VirementAPI.class);
        virementAPI.find(Integer.parseInt(txtrechercher.getText())).enqueue(new Callback<Virement>(){
            @Override
            public void onResponse(Call<Virement> call, Response<Virement> response) {
                Virement virement = response.body();
                txtNumVi.setText(String.valueOf(virement.getIdVirement()));
                txtNumCompteE.setText(virement.getNumCompteE());
                txtNumCompteR.setText(virement.getNumCompteR());
                txtMontantVi.setText(String.valueOf(virement.getMontant_Virement()));
                txtDateVi.setText(virement.getDateVirement());
                
                ClientAPI clientAPIE = UserAPI.getUser().create(ClientAPI.class);
                clientAPIE.find(virement.getNumCompteE()).enqueue(new Callback<Client>(){
                    @Override
                    public void onResponse(Call<Client> call, Response<Client> response) {
                        Client client = response.body();
                        txtNomE.setText(client.getNom());
                    }

                    @Override
                    public void onFailure(Call<Client> call, Throwable t) {
//                        JOptionPane.showConfirmDialog(null, t.getMessage()); //To change body of generated methods, choose Tools | Templates.
                    }
                });
                
                ClientAPI clientAPI = UserAPI.getUser().create(ClientAPI.class);
                clientAPI.find(virement.getNumCompteR()).enqueue(new Callback<Client>(){
                    @Override
                    public void onResponse(Call<Client> call, Response<Client> response) {
                        Client client = response.body();
                        txtNomR.setText(client.getNom());
                    }

                    @Override
                    public void onFailure(Call<Client> call, Throwable t) {
//                        JOptionPane.showConfirmDialog(null, t.getMessage()); //To change body of generated methods, choose Tools | Templates.
                    }
                });
            }

            @Override
            public void onFailure(Call<Virement> call, Throwable t) {
                JOptionPane.showConfirmDialog(null, t.getMessage()); //To change body of generated methods, choose Tools | Templates.
            }
            
        });
        


//        try {
//            String requete = "select cin as 'CIN' ,nomc as 'Nom' ,prenomc as 'Prenom' ,date_naissance as 'Date de Naissance',sexe As 'Sexe',gsm as 'GSM',adresse as 'Adresse',date_inscription as 'Date dinscription' from  client_table where cin LIKE ?";
//            ps = conn.prepareStatement(requete);
//            ps.setString(1, "%" + txtrechercher.getText() + "%");
//            rs = ps.executeQuery();
//            Table.setModel(DbUtils.resultSetToTableModel(rs));
//            ps.close();
//            rs.close();
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
    }//GEN-LAST:event_txtrechercherKeyReleased

    private void txtrechercherKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrechercherKeyTyped
        clear();
        btnsupprimer.setEnabled(false);
        modifierbtn.setEnabled(false);
    }//GEN-LAST:event_txtrechercherKeyTyped

    private void txtrechercherKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrechercherKeyPressed

    }//GEN-LAST:event_txtrechercherKeyPressed

    private void txtrechercherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtrechercherMouseClicked
        loadData();
        clear();

        btnsupprimer.setEnabled(false);
        modifierbtn.setEnabled(false);
        ImageIcon img = new ImageIcon(getClass().getResource("txt1.png"));
        txtbachground.setIcon(img);
        txtrechercher.setText("");
        ImageIcon img2 = new ImageIcon(getClass().getResource("txt2.png"));
        txtbackground1.setIcon(img2);
        txtrechercher1.setText("Tapez N°Compte");
        // TODO add your handling code here:
    }//GEN-LAST:event_txtrechercherMouseClicked

    private void txtrechercherMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtrechercherMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_txtrechercherMouseEntered

    private void txtrechercher1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtrechercher1MouseClicked
        loadData();
        clear();
        btnsupprimer.setEnabled(false);
        modifierbtn.setEnabled(false);
        ImageIcon img2 = new ImageIcon(getClass().getResource("txt1.png"));
        txtbackground1.setIcon(img2);
        txtrechercher1.setText("");
        ImageIcon img = new ImageIcon(getClass().getResource("txt2.png"));
        txtbachground.setIcon(img);
        txtrechercher.setText("Tapez Id Virement");
    }//GEN-LAST:event_txtrechercher1MouseClicked

    private void txtrechercher1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtrechercher1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_txtrechercher1MouseEntered

    private void txtrechercher1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtrechercher1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtrechercher1ActionPerformed

    private void txtrechercher1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrechercher1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtrechercher1KeyPressed

    private void txtrechercher1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrechercher1KeyReleased
        
        try {
            VirementAPI virementAPI = UserAPI.getUser().create(VirementAPI.class);
            virementAPI.findByNumCompteE(txtrechercher1.getText()).enqueue(new Callback<List<Virement>>() {
                @Override
                public void onResponse(Call<List<Virement>> call, Response<List<Virement>> response) {
                    if(response.isSuccessful()) {
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
                        }
                        Table.setModel(defaultTableModel);
//                        Table.getColumnModel().getColumn(0).setPreferredWidth(5);
//                        Table.getColumnModel().getColumn(2).setPreferredWidth(100);
                        Table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                        for (int i = 0; i < Table.getColumnCount(); i++) {
                            TableColumn col = Table.getColumnModel().getColumn(i);
                            col.setPreferredWidth(WIDE);
                            col.setMaxWidth(WIDE * 5);
                        }
                        Table.setPreferredScrollableViewportSize(new Dimension(
                            Table.getColumnCount() * WIDE, Table.getRowHeight() * 16));
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
    }//GEN-LAST:event_txtrechercher1KeyReleased

    private void txtrechercher1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrechercher1KeyTyped
        clear();
        btnsupprimer.setEnabled(false);
        modifierbtn.setEnabled(false);
    }//GEN-LAST:event_txtrechercher1KeyTyped

    private void ccodeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ccodeMouseClicked
        String info = "<html><head></head><body><h3 color ='#2C4CCC'><center>Contrat du Code de la route</center></h3><p>Cin :<b><i>" + test + "</i></b></p>"
                + "<br><p>Nom & Prénom :<b><i>" + txtNumCompteR.getText() + "</i> " + txtMontantVi.getText() + "</b></p>"
                + "<br><p >Nombre D'heures du contrat :<b><i>" + nbrh2 + "</i></b></p>"
                + "<br><p >Montant du Contrat  :<b><i>" + mont2 + "Dt</i></b></p>"
                + "<br><p >Catégorie du Permis :<b><i>" + catp2 + "</i></b></p>"
                + "<br><p >Date Fin du Contrat :<b><i>" + datfin2 + "</i></b></p><br></body></html>";
        JOptionPane p = new JOptionPane();
        p.setMessage(info);
        p.setMessageType(JOptionPane.INFORMATION_MESSAGE);
        JDialog d = p.createDialog(null, "Information sur le contrat ");
        d.setVisible(true);
    }//GEN-LAST:event_ccodeMouseClicked

    private void cconduiteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cconduiteMouseClicked
        String info = "<html><head></head><body><h3 color='#2C4CCC'><center>Contrat du Conduite</center></h3> <p >Cin :<b><i>" + test + "</i></b></p>"
                + "<br><p>Nom & Prénom :<b><i>" + txtNumCompteR.getText() + "</i>  " + txtMontantVi.getText() + "</b></p>"
                + "<br><p >Nombre D'heures du contrat :<b><i>" + nbrh + "</i></b></p>"
                + "<br><p >Montant du Contrat  :<b><i>" + mont + "Dt</i></b></p>"
                + "<br><p >Catégorie du Permis :<b><i>" + catp + "</i></b></p>"
                + "<br><p >Date Fin du Contrat :<b><i>" + datfin + "</i></b></p><br></body></html>";
        JOptionPane p = new JOptionPane();
        p.setMessage(info);
        p.setMessageType(JOptionPane.INFORMATION_MESSAGE);
        JDialog d = p.createDialog(null, "Informations sur le contrat");
        d.setVisible(true);
    }//GEN-LAST:event_cconduiteMouseClicked

    private void nncontartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nncontartMouseClicked

    }//GEN-LAST:event_nncontartMouseClicked

    private void TableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TableKeyReleased
        if ((evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
            Deplace();
        }
    }//GEN-LAST:event_TableKeyReleased

    private void modifierbtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modifierbtnMouseEntered
        modifierbtn.setBackground(new java.awt.Color(0, 153, 153));
    }//GEN-LAST:event_modifierbtnMouseEntered

    private void modifierbtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modifierbtnMouseExited
        modifierbtn.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_modifierbtnMouseExited

    private void btnajMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnajMouseEntered
        btnaj.setBackground(new java.awt.Color(0, 153, 153));
    }//GEN-LAST:event_btnajMouseEntered

    private void btnajMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnajMouseExited
        btnaj.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_btnajMouseExited

    private void btnajMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnajMousePressed
        btnaj.setBackground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_btnajMousePressed

    private void btnsupprimerMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsupprimerMouseExited
        btnsupprimer.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_btnsupprimerMouseExited

    private void btnsupprimerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsupprimerMousePressed
        btnsupprimer.setBackground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_btnsupprimerMousePressed

    private void btnsupprimerMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsupprimerMouseEntered
        btnsupprimer.setBackground(new java.awt.Color(0, 153, 153));
    }//GEN-LAST:event_btnsupprimerMouseEntered

    private void modifierbtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modifierbtnMousePressed
        modifierbtn.setBackground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_modifierbtnMousePressed

    private void printbtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_printbtnMouseExited
        printbtn.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_printbtnMouseExited

    private void printbtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_printbtnMousePressed
        printbtn.setBackground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_printbtnMousePressed

    private void printbtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_printbtnMouseEntered
        printbtn.setBackground(new java.awt.Color(0, 153, 153));
    }//GEN-LAST:event_printbtnMouseEntered


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Panelaction;
    private javax.swing.JTable Table;
    private javax.swing.JButton btnaj;
    private javax.swing.JButton btnrefresh;
    private javax.swing.JButton btnsupprimer;
    private javax.swing.JLabel ccode;
    private javax.swing.JLabel cconduite;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel llogin;
    public javax.swing.JButton modifierbtn;
    private javax.swing.JLabel nncontart;
    private javax.swing.JButton printbtn;
    private javax.swing.JLabel txtDateVi;
    private javax.swing.JLabel txtMontantVi;
    private javax.swing.JLabel txtNomE;
    private javax.swing.JLabel txtNomR;
    private javax.swing.JLabel txtNumCompteE;
    public javax.swing.JLabel txtNumCompteR;
    private javax.swing.JLabel txtNumVi;
    private javax.swing.JLabel txtbachground;
    private javax.swing.JLabel txtbackground1;
    private javax.swing.JTextField txtrechercher;
    private javax.swing.JTextField txtrechercher1;
    // End of variables declaration//GEN-END:variables

    //To change body of generated methods, choose Tools | Templates.
}
