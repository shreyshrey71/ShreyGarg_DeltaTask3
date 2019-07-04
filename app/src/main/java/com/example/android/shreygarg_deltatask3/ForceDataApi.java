package com.example.android.shreygarg_deltatask3;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ForceDataApi {
    @GET("api/forces")
    Call<List<Forces>> getForces();
}
