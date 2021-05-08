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
public class Retrait implements Serializable {
    @SerializedName("idRetrait")
    private int idRetrait;
    
    @SerializedName("NumCompte")
    private String NumCompte;
    
    @SerializedName("NumCheque")
    private String NumCheque;
    
    @SerializedName("Montant_Retrait")
    private int Montant_Retrait;
    
    @SerializedName("DateRetrait")
    private String DateRetrait;

    public int getIdRetrait() {
        return idRetrait;
    }

    public void setIdRetrait(int idRetrait) {
        this.idRetrait = idRetrait;
    }

    public String getNumCompte() {
        return NumCompte;
    }

    public void setNumCompte(String NumCompte) {
        this.NumCompte = NumCompte;
    }

    public String getNumCheque() {
        return NumCheque;
    }

    public void setNumCheque(String NumCheque) {
        this.NumCheque = NumCheque;
    }

    public int getMontant_Retrait() {
        return Montant_Retrait;
    }

    public void setMontant_Retrait(int Montant_Retrait) {
        this.Montant_Retrait = Montant_Retrait;
    }

    public String getDateRetrait() {
        return DateRetrait;
    }

    public void setDateRetrait(String DateRetrait) {
        this.DateRetrait = DateRetrait;
    }
}
