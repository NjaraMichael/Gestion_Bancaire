/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.GPB.api;

import com.GPB.entities.Client;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 *
 * @author Mendrikaja
 */
public interface ClientAPI {
    @GET("list")
    Call<List<Client>> findAll();
}
