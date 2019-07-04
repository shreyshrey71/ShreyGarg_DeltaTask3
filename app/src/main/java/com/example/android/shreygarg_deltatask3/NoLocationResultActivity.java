package com.example.android.shreygarg_deltatask3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;
import java.util.Vector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NoLocationResultActivity extends AppCompatActivity {
    List<String> id = new Vector<String>();
    List<String> pid = new Vector<String>();
    List<String> category = new Vector<String>();
    List<String> date = new Vector<String>();
    List<String> outcome_status = new Vector<String>();
    List<String> name = new Vector<String>();
    List<String> fid = new Vector<String>();
    List<String> a = new Vector<String>();
    List<String> mode = new Vector<String>();
    RecyclerView list;
    String ds,ids,names;
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
        ids=intent.getStringExtra("id");
        names=intent.getStringExtra("name");
        retro();
        la = new LocationAdapter(this, date, a, fid, category, name ,id,pid,outcome_status,mode);
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
        NoLocationCrimeApi noLocationCrimeApi = retrofit.create(NoLocationCrimeApi.class);
        Call<List<crimesnolocation>> call = noLocationCrimeApi.getnoLocationCrimes("all-crime",ids,ds);
        call.enqueue(new Callback<List<crimesnolocation>>() {
            @Override
            public void onResponse(Call<List<crimesnolocation>> call, Response<List<crimesnolocation>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Could Not reach the Server", Toast.LENGTH_SHORT).show();
                    return;
                }
                List<crimesnolocation> crimelist = response.body();
                for (crimesnolocation crimes1 : crimelist) {
                    id.add(crimes1.getId());
                    date.add(crimes1.getMonth());
                    category.add(crimes1.getCategory());
                    pid.add(crimes1.getPersistent_id());
                    outcome_status.add("");
                    name.add(names);
                    fid.add(ids);
                    a.add("");
                    mode.add("2");
                }
                la.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<crimesnolocation>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Could Not retrieve Data, Try Again Later.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
