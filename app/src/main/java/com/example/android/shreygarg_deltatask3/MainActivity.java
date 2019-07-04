package com.example.android.shreygarg_deltatask3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void settodetailactivity(View view) {
        Intent set = new Intent(this, Detail_Activity.class);
        startActivity(set);

    }

    public void settolocationactivity(View view) {
        Intent set = new Intent(this, LocationActivity.class);
        startActivity(set);

    }
    public void settonolocationactivity(View view) {
        Intent set = new Intent(this, NoLocationActivity.class);
        startActivity(set);

    }

    public void settofavoriteactivity(View view) {
        Intent set = new Intent(this, FavoriteActivity.class);
        startActivity(set);

    }
}
