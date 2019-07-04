package com.example.android.shreygarg_deltatask3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;
import java.util.Vector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LocationResultActivity extends AppCompatActivity {
    List<String> latitude = new Vector<String>();
    List<String> longitude = new Vector<String>();
    List<String> id = new Vector<String>();
    List<String> pid = new Vector<String>();
    List<String> street = new Vector<String>();
    List<String> category = new Vector<String>();
    List<String> ltype = new Vector<String>();
    List<String> date = new Vector<String>();
    List<String> mode = new Vector<String>();
    RecyclerView list;
    String ds;
    float lat, lng;
    LocationAdapter la;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_result_layout);
        list = findViewById(R.id.locationresult);
        list.setHasFixedSize(true);
        list.setLayoutManager(new LinearLayoutManager(this));
        Intent intent = getIntent();
        ds = intent.getStringExtra("date");
        lng = intent.getFloatExtra("longitude", 55f);
        lat = intent.getFloatExtra("latitude", 0f);
        retro();
        la = new LocationAdapter(this, date, latitude, longitude, category, street,id,pid,ltype,mode);
        list.setAdapter(la);
    }
    @Override
    protected void onResume() {

        super.onResume();
        if(la!=null)
        la.notifyDataSetChanged();
    }
    public void retro()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://data.police.uk/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LocationCrimeApi locationCrimeApi = retrofit.create(LocationCrimeApi.class);
        Call<List<crimes>> call = locationCrimeApi.getLocationCrimes(lat, lng,ds);
        call.enqueue(new Callback<List<crimes>>() {
            @Override
            public void onResponse(Call<List<crimes>> call, Response<List<crimes>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Could Not reach the Server", Toast.LENGTH_SHORT).show();
                    return;
                }
                List<crimes> crimelist = response.body();
                for (crimes crimes1 : crimelist) {
                    id.add(crimes1.getId());
                    date.add(crimes1.getMonth());
                    ltype.add(crimes1.getLocation_type());
                    street.add(crimes1.getLocation().getStreet().getName());
                    category.add(crimes1.getCategory());
                    pid.add(crimes1.getPersistent_id());
                    latitude.add(crimes1.getLocation().getLatitude());
                    longitude.add(crimes1.getLocation().getLongitude());
                    mode.add("1");
                }
                la.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<crimes>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Could Not retrieve Data, Try Again Later.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
