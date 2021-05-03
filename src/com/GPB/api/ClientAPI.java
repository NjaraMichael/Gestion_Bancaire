/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.GPB.api;

import com.GPB.entities.Client;
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
public interface ClientAPI {
    @GET("list")
    Call<List<Client>> findAll();
    
    @GET("get/{NumCompte}")
    Call<Client> find(@Path("NumCompte") String NumCompte);
    
    @POST("add")
    Call<Void> create(@Body Client client);
    
    @PUT("update")
    Call<Void> update(@Body Client client);
    
    @DELETE("delete/{NumCompte}")
    Call<Void> delete(@Path("NumCompte") String NumCompte);
}
