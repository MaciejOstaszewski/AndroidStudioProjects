package com.example.start.l13.TMDataManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

/**
 * Created by start on 2018-02-03.
 */

public interface IValueService {
    @GET("api/Values")
    Call<List<String>> listValues();



}
