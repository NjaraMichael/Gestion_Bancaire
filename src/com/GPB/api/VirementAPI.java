/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.GPB.api;


import com.GPB.entities.Virement;
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
public interface VirementAPI {
    @GET("virements/list")
    Call<List<Virement>> findall();
    
    @GET("virements/get/{idVirement}")
    Call<Virement> find(@Path("idVirement") int idVirement);
    
    @GET("virements/get/NumCompteE/{NumCompteE}")
    Call<List<Virement>> findByNumCompteE(@Path("NumCompteE") String NumCompteE);
    
    @POST("virements/add")
    Call<Void> create(@Body Virement virement);
    
    @PUT("virements/update")
    Call<Void> update(@Body Virement virement);
    
    @DELETE("virements/delete/{idVirement}")
    Call<Void> delete(@Path("idVirement") int idVirement);
}
