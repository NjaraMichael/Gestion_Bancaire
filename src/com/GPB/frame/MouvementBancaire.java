/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.GPB.frame;

import com.GPB.api.RetraitAPI;
import com.GPB.api.UserAPI;
import com.GPB.api.VersementAPI;
import com.GPB.api.VirementAPI;
import com.GPB.entities.Retrait;
import com.GPB.entities.Versement;
import com.GPB.entities.Virement;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JTable;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *
 * @author anouer
 */
public class MouvementBancaire extends javax.swing.JInternalFrame {

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    Connection conn1 = null;
    ResultSet rs1 = null;
    PreparedStatement ps1 = null;
    public static String v4;
    public static String type;
    public static String parc;
    public static String typepermis;
    public static String test;
    public static String test2;
    public static String test3;
    public static String tes;
    private static final int WIDE6 = 122;
    private static final int WIDE4 = 100;
    private static final int WIDE5 = 200;

    /**
     * Creates new form Formation
     */
    public MouvementBancaire() {
        initComponents();
        remove_title_bar();
        conn = ConexionBD.Conexion();
        conn1 = ConexionBD.Conexion();
//        AffichagePret();
//        affichageBanque();
//        affichageClient();
        ImageIcon img = new ImageIcon(getClass().getResource("txt2.png"));
        txtbachground.setIcon(img);
        txtRechercheVersement.setText("Rercherche N°Compte Versement");
        txtRechercheRetrait.setText("Rercherche N°Compte Retrait");
        txtRechercheVirement.setText("Rercherche N°Compte Virement");
        
        loadData6();
        loadData4();
        loadData5();
    }
    
    public void loadData6() {
        try {
            VersementAPI versementAPI = UserAPI.getUser().create(VersementAPI.class);
            
            versementAPI.findall().enqueue(new Callback<List<Versement>>() {
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
                            TableColumn col6 = Table6.getColumnModel().getColumn(i);
                            col6.setPreferredWidth(WIDE6);
                            col6.setMaxWidth(WIDE6 * 5);
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
            retraitAPI.findall().enqueue(new Callback<List<Retrait>>() {
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
            virementAPI.findall().enqueue(new Callback<List<Virement>>() {
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
    
    public void deplacePret() {
//        try {
//            int row = Table4.getSelectedRow();
//            MouvementBancaire.test3 = (Table4.getModel().getValueAt(row, 0).toString());
//            String requet = " select * from  pret where idPret= '" + test3 + "' ";
//            ps = conn.prepareStatement(requet);
//            rs = ps.executeQuery();
//            System.out.println(test3);
//            if (rs.next()) {
//                String t1 = rs.getString("Montant");
//                txtMontant.setText(t1);
//                String t2 = rs.getString("DatePret");
//
//                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//                Date date = dateFormat.parse(t2);
//
//                DatePret.setDate(date);
//
//                String t3 = rs.getString("idPret");
//                idPret.setText(t3);
//            }
//
//        } catch (SQLException | ParseException e) {
//            System.out.println(e);
//        }
    }

    public void suppPret() {
        try {
            if (JOptionPane.showConfirmDialog(null, "Attention vous devez suprimer un Pret, est-ce que tu es sûr?",
                    "Supprimer Pret", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {

                String requete = "delete from pret where idPret = '" + test3 + "'";
                ps = conn.prepareStatement(requete);

                ps.execute();

            }
        } catch (HeadlessException | SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Rrreur de suppression\n" + e.getMessage());
        }
        refreshPret();

    }

    public void deplaceBanque() {
//        try {
//            int row = Table3.getSelectedRow();
//            MouvementBancaire.test2 = (Table3.getModel().getValueAt(row, 0).toString());
//            String requet = " select * from  banque where NumBanque = '" + test2 + "' ";
//            ps = conn.prepareStatement(requet);
//            rs = ps.executeQuery();
//
//            if (rs.next()) {
//                String t1 = rs.getString("NumBanque");
//                txtidBanque.setText(t1);
//                String t2 = rs.getString("NomBanque");
//                txtNomBanque.setText(t2);
//                String t3 = rs.getString("PretBanque");
//                Tauxlbl.setText(t3);
//            }
//        } catch (SQLException e) {
//            System.out.println(e);
//        }

    }

    public void deplacePretBanque() {
//        try {
//            int row = Table4.getSelectedRow();
//            MouvementBancaire.test2 = (Table4.getModel().getValueAt(row, 2).toString());
//            String requet = " select * from  banque where NumBanque = '" + test2 + "' ";
//            ps = conn.prepareStatement(requet);
//            rs = ps.executeQuery();
//
//            if (rs.next()) {
//                String t1 = rs.getString("NumBanque");
//                txtidBanque.setText(t1);
////                String t2 = rs.getString("Categoriepermis");
////                txtper.setText(t2);
//                String t2 = rs.getString("NomBanque");
//                txtNomBanque.setText(t2);
//                String t3 = rs.getString("PretBanque");
//                Tauxlbl.setText(t3);
//            }
//        } catch (SQLException e) {
//            System.out.println(e);
//        }

    }

    public void rechercheclient() {

//        try {
//
//            String requete = "select cin as 'CIN' ,nomc as 'Nom' ,prenomc as 'Prenom' ,date_naissance as 'Date de Naissance',sexe As 'Sexe',gsm as 'GSM',adresse as 'Adresse',date_inscription as 'Date dinscription' from  client_table where nomc LIKE ? OR prenomc LIKE ?";
//            ps = conn.prepareStatement(requete);
//            ps.setString(1, "%" + txtRechercheVersement.getText() + "%");
//            ps.setString(2, "%" + txtRechercheVersement.getText() + "%");
//            rs = ps.executeQuery();
//            Table2.setModel(DbUtils.resultSetToTableModel(rs));
//
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

    }

    public void refreshPret() {
//        try {
//
//            String requete6 = "select idPret as 'IdPret' ,cincand as 'Cin_Client' ,NumBanque as 'Numero Banque',Montant as 'Montant' ,MontantAPayer as 'Montant à Payer' ,datePret as 'Date du Pret' from  pret where cincand='" + test + "'";
//            ps = conn.prepareStatement(requete6);
//            rs = ps.executeQuery();
//            Table4.setModel(DbUtils.resultSetToTableModel(rs));
//
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
    }

    public void deplaceClient() {
//        try {
//            int row = Table2.getSelectedRow();
//            MouvementBancaire.test = (Table2.getModel().getValueAt(row, 0).toString());
//            String requet = " select * from  client_table where cin = '" + test + "' ";
//            ps = conn.prepareStatement(requet);
//            rs = ps.executeQuery();
//
//            if (rs.next()) {
//                String t1 = rs.getString("cin");
//                txtcin.setText(t1);
//                String t2 = rs.getString("nomc");
//                String t3 = rs.getString("prenomc");
//                txtnomprenom.setText(t2 + "  " + " " + " " + t3);
//            }
//
//        } catch (SQLException e) {
//            System.out.println(e);
//        }

    }

    public void deplacePretClient() {
//        try {
//            int row = Table4.getSelectedRow();
//            MouvementBancaire.test = (Table4.getModel().getValueAt(row, 1).toString());
//            String requet = " select * from  client_table where cin = '" + test + "' ";
//            ps = conn.prepareStatement(requet);
//            rs = ps.executeQuery();
//
//            if (rs.next()) {
//                String t1 = rs.getString("cin");
//                txtcin.setText(t1);
//                String t2 = rs.getString("nomc");
//                String t3 = rs.getString("prenomc");
//                txtnomprenom.setText(t2 + "  " + " " + " " + t3);
//            }
//
//        } catch (SQLException e) {
//            System.out.println(e);
//        }

    }

    private void remove_title_bar() {
        putClientProperty("Formation.isPalette", Boolean.TRUE);
        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        ((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        this.setBorder(null);
    }

    private void AffichagePret() {
        try {

            String requete6 = "select idPret as 'IdPret' ,cincand as 'CIN Client' ,NumBanque as 'Numero Banque',Montant as 'Montant' ,MontantAPayer as 'Montant à Payer' ,datePret as 'Date du Pret' from  pret";
            ps = conn.prepareStatement(requete6);
            rs = ps.executeQuery();
            Table4.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    private void affichageBanque() {
//        try {
//            String requete2 = "select NumBanque as 'NumBanque' ,NomBanque as 'NomBanque' ,ContactBanque as 'ContactBanque' ,PretBanque as 'PretBanque' from banque ";
//            ps = conn.prepareStatement(requete2);
//            rs = ps.executeQuery();
//            Table3.setModel(DbUtils.resultSetToTableModel(rs));
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
    }

    private void affichageClient() {
//        try {
//            String requete = "select cin as 'CIN' ,nomc as 'Nom',prenomc as 'Prenom',date_naissance as 'Date de Naissance',adresse as 'Adresse',sexe As 'Sexe',gsm as 'GSM',date_inscription as 'Date dinscription' from client_table";
//            ps = conn.prepareStatement(requete);
//            rs = ps.executeQuery();
//            Table2.setModel(DbUtils.resultSetToTableModel(rs));
//        } catch (SQLException e) {
//            System.out.println(e);
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
        txtRechercheVersement = new javax.swing.JTextField();
        txtbachground = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        Table4 = new javax.swing.JTable();
        jLabel21 = new javax.swing.JLabel();
        txtRechercheRetrait = new javax.swing.JTextField();
        txtbackground1 = new javax.swing.JLabel();
        txtRechercheVirement = new javax.swing.JTextField();
        txtbackground2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        TotalVersement = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        EffectifVersement = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        Table5 = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        Table6 = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        TotalRetrait = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        EffectifRetrait = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        TotalVirement = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        EffectifVirement = new javax.swing.JLabel();
        btnrefresh = new javax.swing.JButton();

        setBorder(null);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        getContentPane().setLayout(null);

        txtRechercheVersement.setBackground(new java.awt.Color(240, 240, 240));
        txtRechercheVersement.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtRechercheVersement.setForeground(new java.awt.Color(0, 153, 153));
        txtRechercheVersement.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtRechercheVersement.setToolTipText("");
        txtRechercheVersement.setBorder(null);
        txtRechercheVersement.setDoubleBuffered(true);
        txtRechercheVersement.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtRechercheVersementMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtRechercheVersementMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtRechercheVersementMouseExited(evt);
            }
        });
        txtRechercheVersement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRechercheVersementActionPerformed(evt);
            }
        });
        txtRechercheVersement.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRechercheVersementKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRechercheVersementKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRechercheVersementKeyTyped(evt);
            }
        });
        getContentPane().add(txtRechercheVersement);
        txtRechercheVersement.setBounds(52, 58, 213, 14);

        txtbachground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GPB/frame/txt2.png"))); // NOI18N
        getContentPane().add(txtbachground);
        txtbachground.setBounds(50, 50, 220, 30);

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
        Table4.getAccessibleContext().setAccessibleName("\n");

        getContentPane().add(jScrollPane4);
        jScrollPane4.setBounds(530, 80, 500, 250);
        getContentPane().add(jLabel21);
        jLabel21.setBounds(1040, 180, 40, 20);

        txtRechercheRetrait.setBackground(new java.awt.Color(240, 240, 240));
        txtRechercheRetrait.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtRechercheRetrait.setForeground(new java.awt.Color(0, 153, 153));
        txtRechercheRetrait.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtRechercheRetrait.setBorder(null);
        txtRechercheRetrait.setDoubleBuffered(true);
        txtRechercheRetrait.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtRechercheRetraitMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtRechercheRetraitMouseEntered(evt);
            }
        });
        txtRechercheRetrait.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRechercheRetraitActionPerformed(evt);
            }
        });
        txtRechercheRetrait.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRechercheRetraitKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRechercheRetraitKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRechercheRetraitKeyTyped(evt);
            }
        });
        getContentPane().add(txtRechercheRetrait);
        txtRechercheRetrait.setBounds(780, 60, 200, 10);

        txtbackground1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GPB/frame/txt2.png"))); // NOI18N
        getContentPane().add(txtbackground1);
        txtbackground1.setBounds(770, 50, 220, 30);

        txtRechercheVirement.setBackground(new java.awt.Color(240, 240, 240));
        txtRechercheVirement.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtRechercheVirement.setForeground(new java.awt.Color(0, 153, 153));
        txtRechercheVirement.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtRechercheVirement.setBorder(null);
        txtRechercheVirement.setDoubleBuffered(true);
        txtRechercheVirement.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtRechercheVirementMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtRechercheVirementMouseEntered(evt);
            }
        });
        txtRechercheVirement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRechercheVirementActionPerformed(evt);
            }
        });
        txtRechercheVirement.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRechercheVirementKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRechercheVirementKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRechercheVirementKeyTyped(evt);
            }
        });
        getContentPane().add(txtRechercheVirement);
        txtRechercheVirement.setBounds(780, 410, 200, 10);

        txtbackground2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/GPB/frame/txt2.png"))); // NOI18N
        getContentPane().add(txtbackground2);
        txtbackground2.setBounds(770, 400, 220, 30);

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
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel4);
        jPanel4.setBounds(0, 0, 1160, 50);

        jLabel15.setFont(new java.awt.Font("Bahnschrift", 1, 11)); // NOI18N
        jLabel15.setText("SOMME DU VERSEMENT : ");
        getContentPane().add(jLabel15);
        jLabel15.setBounds(190, 340, 180, 14);

        TotalVersement.setText("        ");
        getContentPane().add(TotalVersement);
        TotalVersement.setBounds(340, 340, 160, 14);

        jLabel2.setFont(new java.awt.Font("Bahnschrift", 1, 11)); // NOI18N
        jLabel2.setText("NB VERS. : ");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(30, 340, 70, 14);

        EffectifVersement.setText("           ");
        getContentPane().add(EffectifVersement);
        EffectifVersement.setBounds(100, 340, 34, 14);

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

        getContentPane().add(jScrollPane5);
        jScrollPane5.setBounds(20, 430, 1010, 220);

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

        getContentPane().add(jScrollPane6);
        jScrollPane6.setBounds(20, 80, 500, 250);

        jLabel16.setFont(new java.awt.Font("Bahnschrift", 1, 11)); // NOI18N
        jLabel16.setText("SOMME DU RETRAIT : ");
        getContentPane().add(jLabel16);
        jLabel16.setBounds(670, 340, 150, 14);

        TotalRetrait.setText("        ");
        getContentPane().add(TotalRetrait);
        TotalRetrait.setBounds(800, 340, 160, 14);

        jLabel3.setFont(new java.awt.Font("Bahnschrift", 1, 11)); // NOI18N
        jLabel3.setText("NB RET. : ");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(540, 340, 70, 14);

        EffectifRetrait.setText("           ");
        getContentPane().add(EffectifRetrait);
        EffectifRetrait.setBounds(600, 340, 34, 14);

        jLabel17.setFont(new java.awt.Font("Bahnschrift", 1, 11)); // NOI18N
        jLabel17.setText("SOMME DU VIREMENT : ");
        getContentPane().add(jLabel17);
        jLabel17.setBounds(190, 410, 150, 14);

        TotalVirement.setText("        ");
        getContentPane().add(TotalVirement);
        TotalVirement.setBounds(340, 410, 160, 14);

        jLabel4.setFont(new java.awt.Font("Bahnschrift", 1, 11)); // NOI18N
        jLabel4.setText("NB VIR. : ");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(30, 410, 70, 14);

        EffectifVirement.setText("           ");
        getContentPane().add(EffectifVirement);
        EffectifVirement.setBounds(100, 410, 34, 14);

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
        btnrefresh.setBounds(490, 390, 70, 40);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Table4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table4MouseReleased
//        suppPret.setEnabled(true);
//        AjoutPret.setEnabled(false);
//        modifierPret.setEnabled(true);
//        deplacePretClient();
//        deplacePretBanque();
//        deplacePret();

//clearsuivi();

    }//GEN-LAST:event_Table4MouseReleased

    private void txtRechercheVersementMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtRechercheVersementMouseClicked
        txtRechercheVersement.setText("");
    }//GEN-LAST:event_txtRechercheVersementMouseClicked

    private void txtRechercheVersementMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtRechercheVersementMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRechercheVersementMouseEntered

    private void txtRechercheVersementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRechercheVersementActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRechercheVersementActionPerformed

    private void txtRechercheVersementKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRechercheVersementKeyPressed

    }//GEN-LAST:event_txtRechercheVersementKeyPressed

    private void txtRechercheVersementKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRechercheVersementKeyReleased
        try {
            VersementAPI versementAPI = UserAPI.getUser().create(VersementAPI.class);
            versementAPI.findByNumCompte(txtRechercheVersement.getText()).enqueue(new Callback<List<Versement>>() {
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
    }//GEN-LAST:event_txtRechercheVersementKeyReleased

    private void txtRechercheVersementKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRechercheVersementKeyTyped


    }//GEN-LAST:event_txtRechercheVersementKeyTyped

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked

    }//GEN-LAST:event_formMouseClicked

    private void Table4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Table4MouseClicked

    private void txtRechercheRetraitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtRechercheRetraitMouseClicked
        txtRechercheRetrait.setText("");
    }//GEN-LAST:event_txtRechercheRetraitMouseClicked

    private void txtRechercheRetraitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtRechercheRetraitMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRechercheRetraitMouseEntered

    private void txtRechercheRetraitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRechercheRetraitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRechercheRetraitActionPerformed

    private void txtRechercheRetraitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRechercheRetraitKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRechercheRetraitKeyPressed

    private void txtRechercheRetraitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRechercheRetraitKeyReleased
        try {
            RetraitAPI retraitAPI = UserAPI.getUser().create(RetraitAPI.class);
            retraitAPI.findByNumCompte(txtRechercheRetrait.getText()).enqueue(new Callback<List<Retrait>>() {
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

    }//GEN-LAST:event_txtRechercheRetraitKeyReleased

    private void txtRechercheRetraitKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRechercheRetraitKeyTyped

    }//GEN-LAST:event_txtRechercheRetraitKeyTyped

    private void txtRechercheVersementMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtRechercheVersementMouseExited

    }//GEN-LAST:event_txtRechercheVersementMouseExited

    private void txtRechercheVirementMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtRechercheVirementMouseClicked
        txtRechercheVirement.setText("");
    }//GEN-LAST:event_txtRechercheVirementMouseClicked

    private void txtRechercheVirementMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtRechercheVirementMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRechercheVirementMouseEntered

    private void txtRechercheVirementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRechercheVirementActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRechercheVirementActionPerformed

    private void txtRechercheVirementKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRechercheVirementKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRechercheVirementKeyPressed

    private void txtRechercheVirementKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRechercheVirementKeyReleased
        try {
            VirementAPI virementAPI = UserAPI.getUser().create(VirementAPI.class);
            virementAPI.findByNumCompteE(txtRechercheVirement.getText()).enqueue(new Callback<List<Virement>>() {
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
    }//GEN-LAST:event_txtRechercheVirementKeyReleased

    private void txtRechercheVirementKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRechercheVirementKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRechercheVirementKeyTyped

    private void Table5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Table5MouseClicked

    private void Table5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table5MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_Table5MouseReleased

    private void Table6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Table6MouseClicked

    private void Table6MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table6MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_Table6MouseReleased

    private void btnrefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrefreshActionPerformed
        txtRechercheVersement.setText("Rercherche N°Compte Versement");
        txtRechercheRetrait.setText("Rercherche N°Compte Retrait");
        txtRechercheVirement.setText("Rercherche N°Compte Virement");
        
        loadData6();
        loadData4();
        loadData5();
    }//GEN-LAST:event_btnrefreshActionPerformed


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
    private javax.swing.JButton btnrefresh;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTextField txtRechercheRetrait;
    private javax.swing.JTextField txtRechercheVersement;
    private javax.swing.JTextField txtRechercheVirement;
    private javax.swing.JLabel txtbachground;
    private javax.swing.JLabel txtbackground1;
    private javax.swing.JLabel txtbackground2;
    // End of variables declaration//GEN-END:variables
}
