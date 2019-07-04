package com.example.android.shreygarg_deltatask3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Vector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Detail_Activity extends AppCompatActivity {

    RecyclerView detaillist;
    List<String> name = new Vector<String>();
    DetailAdapter detailadapter;
    List<String> id = new Vector<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity_layout);
        detaillist = findViewById(R.id.deptdetail);
        detaillist.setHasFixedSize(true);
        detaillist.setLayoutManager(new LinearLayoutManager(this));
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://data.police.uk/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ForceDataApi forceDataApi = retrofit.create(ForceDataApi.class);
        Call<List<Forces>> call = forceDataApi.getForces();
        call.enqueue(new Callback<List<Forces>>() {
            @Override
            public void onResponse(Call<List<Forces>> call, Response<List<Forces>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Could Not reach the Server", Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Forces> forcesList = response.body();
                for (Forces forces1 : forcesList) {
                    id.add(forces1.getId());
                    name.add(forces1.getName());
                }
                detailadapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Forces>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Could Not retrieve Data, Try Again Later.", Toast.LENGTH_SHORT).show();

            }
        });

        detailadapter = new DetailAdapter(this, id, name);
        detaillist.setAdapter(detailadapter);
        EditText search = (EditText) findViewById(R.id.detailsearch);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                serchaction(s.toString());
            }
        });

    }

    public void serchaction(String s) {
        List<String> searchname = new Vector<String>();
        List<String> searchid = new Vector<String>();
        if (!s.equals("")) {
            for (int i = 0; i < name.size(); i++) {
                if (name.get(i).toLowerCase().contains(s.toLowerCase())) {
                    searchname.add(name.get(i));
                    searchid.add(id.get(i));
                }
            }

            detailadapter.notifyDataSetChanged();
            detailadapter = new DetailAdapter(this, searchid, searchname);
            detaillist.setAdapter(detailadapter);
        } else {

            detailadapter.notifyDataSetChanged();
            detailadapter = new DetailAdapter(this, id, name);
            detaillist.setAdapter(detailadapter);

        }
    }
}
