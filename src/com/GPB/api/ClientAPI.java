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
    @GET("clients/list")
    Call<List<Client>> getClients_JSON();
    
    @GET("clients/get/{NumCompte}")
    Call<Client> find(@Path("NumCompte") String NumCompte);
    
    @POST("clients/add")
    Call<Void> create(@Body Client client);
    
    @PUT("clients/update")
    Call<Void> update(@Body Client client);
    
    @DELETE("clients/delete/{NumCompte}")
    Call<Void> delete(@Path("NumCompte") String NumCompte);
}
