/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.GPB.entities;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/**
 *
 * @author Mendrikaja
 */
public class Versement implements Serializable {
    @SerializedName("idVersement")
    private int idVersement;
    
    @SerializedName("NumCompte")
    private String NumCompte;
    
    @SerializedName("Montant_Versement")
    private int Montant_Versement;
    
    @SerializedName("dateVersement")
    private String dateVersement;

    public int getIdVersement() {
        return idVersement;
    }

    public void setIdVersement(int idVersement) {
        this.idVersement = idVersement;
    }

    public String getNumCompte() {
        return NumCompte;
    }

    public void setNumCompte(String NumCompte) {
        this.NumCompte = NumCompte;
    }

    public int getMontant_Versement() {
        return Montant_Versement;
    }

    public void setMontant_Versement(int Montant_Versement) {
        this.Montant_Versement = Montant_Versement;
    }

    public String getDateVersement() {
        return dateVersement;
    }

    public void setDateVersement(String dateVersement) {
        this.dateVersement = dateVersement;
    }
}
