package com.example.kimutai.bakingapp.Networking.api;

import com.example.kimutai.bakingapp.Networking.route;
import com.google.gson.JsonArray;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {
    @GET(route.END_POINT)
    Call<JsonArray> fetchBakingData();
}
