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

public interface IEventService {
    @GET("api/EventInfo")
    Call<List<String>> eventInfo();

    @GET("api/EventInfo/{id}")
    Call<List<String>> eventInfo(int id);

    @POST("api/EventInfo")
    Call<List<String>> eventInfoPost();

    @PUT("api/EventInfo/{id}")
    Call<List<String>> eventPut(int id);

    @DELETE("api/Values/{id}")
    Call<List<String>> eventDelete(int id);
}
