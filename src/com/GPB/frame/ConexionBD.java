/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.GPB.frame;

import java.awt.HeadlessException;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author anouer
 */
public class ConexionBD {

    String filename = null;
    public static String path;
    Connection conn = null;

    public static Connection Conexion() {
        try {
            //Chargement du pilote JDBC pour MYSQL puis creation de la connection
            //DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            // ou bien 
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/back_java_gb_jax_rs", "root", "");
            if (conn != null) {
                System.out.println("Connexion à la base de données a été établie avec succès");
            } else {
                System.out.println("Problème de connexion à la base");
            }

            return conn;

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("--> SQLException : " + e);

            return null;
        }
    }

    public void filen() {
        try {

            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Choisir une image png");
            chooser.setApproveButtonText("Ajouter une image");
            chooser.showOpenDialog(null);
            File f = chooser.getSelectedFile();
            filename = f.getAbsolutePath();
            ConexionBD.path = (filename);
        } catch (HeadlessException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, " Veuiler choisir une image ");
        }

    }

    public String getp() {

        return path;

    }

}
