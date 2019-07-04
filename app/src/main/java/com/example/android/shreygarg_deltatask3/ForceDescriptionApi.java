package com.example.android.shreygarg_deltatask3;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PartMap;
import retrofit2.http.Path;

public interface ForceDescriptionApi {
    @GET("api/forces/{id}")
    Call<Description> getdescription(@Path("id") String ID);
}
