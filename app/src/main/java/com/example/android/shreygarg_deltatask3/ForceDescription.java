package com.example.android.shreygarg_deltatask3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ForceDescription extends AppCompatActivity {
    String id;
    String name;
    String description;
    String link;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.force_description_layout);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        name = intent.getStringExtra("name");
        textView1 = findViewById(R.id.forceid);
        textView2 = findViewById(R.id.forcename);
        textView3 = findViewById(R.id.forcedescription);
        textView4 = findViewById(R.id.forcelink);
        textView1.setText("ID : " + id);
        textView2.setText("Name : " + name);
        textView3.setVisibility(View.INVISIBLE);
        textView4.setVisibility(View.GONE);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://data.police.uk/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ForceDescriptionApi forceDescriptionApi = retrofit.create(ForceDescriptionApi.class);
        Call<Description> call = forceDescriptionApi.getdescription(id);
        call.enqueue(new Callback<Description>() {
            @Override
            public void onResponse(Call<Description> call, Response<Description> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Could Not reach the Server", Toast.LENGTH_SHORT).show();
                    textView3.setVisibility(View.VISIBLE);
                    return;
                }
                Description forcedescription = response.body();
                if (forcedescription.getDesc() != null) {
                    description = Html.fromHtml(forcedescription.getDesc()).toString();
                    link = Html.fromHtml(forcedescription.getLink()).toString();
                    textView3.setVisibility(View.VISIBLE);
                    textView4.setVisibility(View.VISIBLE);
                    textView3.setText("Description :-\n\n" + description);
                    textView4.setText("Link : " + link);
                    Linkify.addLinks(textView4, Linkify.ALL);
                } else {
                    Toast.makeText(getApplicationContext(), "No Data Found", Toast.LENGTH_SHORT).show();
                    textView3.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onFailure(Call<Description> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Could Not retrieve Data, Try Again Later.", Toast.LENGTH_SHORT).show();
                textView3.setVisibility(View.VISIBLE);

            }
        });

    }
}
