package com.example.android.shreygarg_deltatask3;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LocationCrimeApi {
    @GET("api/crimes-street/all-crime")
    Call<List<crimes>> getLocationCrimes(@Query("lat") float lat, @Query("lng") float lng,@Query("date") String date);
}
