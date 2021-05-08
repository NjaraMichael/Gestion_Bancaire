/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.GPB.api;


import com.GPB.entities.Versement;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 *
 * @author Mendrikaja
 */
public interface VersementAPI {
    @GET("versements/list")
    Call<List<Versement>> findall();
    
    @GET("versements/get/{idVersement}")
    Call<Versement> find(@Path("idVersement") int idVersement);
    
    @GET("versements/get/NumCompte/{NumCompte}")
    Call<List<Versement>> findByNumCompte(@Path("NumCompte") String NumCompte);
    
    @POST("versements/add")
    Call<Void> create(@Body Versement versement);
    
    @PUT("versements/update")
    Call<Void> update(@Body Versement versement);
    
    @DELETE("versements/delete/{idVersement}")
    Call<Void> delete(@Path("idVersement") int idVersement);
}
