package com.example.android.shreygarg_deltatask3;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class LocationActivity extends AppCompatActivity {
    TextView date;
    EditText latitude, longitude;
    int year = -1, month = -1;
    int s=0;
    private DatePickerDialog.OnDateSetListener dateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_activity_layout);
        date = findViewById(R.id.date);
        latitude = findViewById(R.id.lat);
        longitude = findViewById(R.id.lon);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(LocationActivity.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        dateSetListener, year, month, 0);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                ((ViewGroup) datePickerDialog.getDatePicker()).findViewById(Resources.getSystem().getIdentifier("day", "id", "android")).setVisibility(View.GONE);
                datePickerDialog.show();
            }
        });
        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month++;
                if (month / 10 == 1)
                    date.setText(year + "-" + month);
                else
                    date.setText(year + "-0" + month);
                s=1;
            }
        };
    }

    public void showCrimes(View view) {
        if (!latitude.equals("") && !longitude.equals("") && month != -1 && year != -1&&s==1) {
            float lat = Float.valueOf(latitude.getText().toString());
            float lon = Float.valueOf(longitude.getText().toString());
            Intent intent = new Intent(this, LocationResultActivity.class);
            intent.putExtra("latitude", Float.valueOf(latitude.getText().toString()));
            intent.putExtra("longitude", Float.valueOf(longitude.getText().toString()));
            intent.putExtra("date",date.getText().toString());
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Fill all fields to continue!!!", Toast.LENGTH_SHORT).show();
        }
    }
}
