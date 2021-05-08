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
public class Virement implements Serializable {
    @SerializedName("idVirement")
    private int idVirement;
    
    @SerializedName("NumCompteE")
    private String NumCompteE;
    
    @SerializedName("NumCompteR")
    private String NumCompteR;
    
    @SerializedName("Montant_Virement")
    private int Montant_Virement;
    
    @SerializedName("DateVirement")
    private String DateVirement;

    public int getIdVirement() {
        return idVirement;
    }

    public void setIdVirement(int idVirement) {
        this.idVirement = idVirement;
    }

    public String getNumCompteE() {
        return NumCompteE;
    }

    public void setNumCompteE(String NumCompteE) {
        this.NumCompteE = NumCompteE;
    }

    public String getNumCompteR() {
        return NumCompteR;
    }

    public void setNumCompteR(String NumCompteR) {
        this.NumCompteR = NumCompteR;
    }

    public int getMontant_Virement() {
        return Montant_Virement;
    }

    public void setMontant_Virement(int Montant_Virement) {
        this.Montant_Virement = Montant_Virement;
    }

    public String getDateVirement() {
        return DateVirement;
    }

    public void setDateVirement(String DateVirement) {
        this.DateVirement = DateVirement;
    }
}
