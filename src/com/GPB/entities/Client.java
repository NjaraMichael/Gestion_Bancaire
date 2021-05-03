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
public class Client implements Serializable {
    @SerializedName("NumCompte")
    private String NumCompte;
    
    @SerializedName("Nom")
    private String Nom;
    
    @SerializedName("Solde")
    private long Solde;

    public String getNumCompte() {
        return NumCompte;
    }

    public void setNumCompte(String NumCompte) {
        this.NumCompte = NumCompte;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public long getSolde() {
        return Solde;
    }

    public void setSolde(long Solde) {
        this.Solde = Solde;
    }
}
