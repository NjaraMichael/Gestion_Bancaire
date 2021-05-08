/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.GPB.api;


import com.GPB.entities.Retrait;
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
public interface RetraitAPI {
    @GET("retraits/list")
    Call<List<Retrait>> findall();
    
    @GET("retraits/get/{idRetrait}")
    Call<Retrait> find(@Path("idRetrait") int idRetrait);
    
    @GET("retraits/get/NumCompte/{NumCompte}")
    Call<List<Retrait>> findByNumCompte(@Path("NumCompte") String NumCompte);
    
    @POST("retraits/add")
    Call<Void> create(@Body Retrait retrait);
    
    @PUT("retraits/update")
    Call<Void> update(@Body Retrait retrait);
    
    @DELETE("retraits/delete/{idRetrait}")
    Call<Void> delete(@Path("idRetrait") int idRetrait);
}
