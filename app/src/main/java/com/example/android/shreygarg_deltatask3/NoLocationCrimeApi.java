package com.example.android.shreygarg_deltatask3;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NoLocationCrimeApi {
    @GET("api/crimes-no-location")
    Call<List<crimesnolocation>> getnoLocationCrimes(@Query("category") String cat, @Query("force") String id, @Query("date") String date);
}
