package com.example.android.shreygarg_deltatask3;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class NoLocationActivity extends AppCompatActivity {
    TextView date;
    int year = -1, month = -1;
    private DatePickerDialog.OnDateSetListener dateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crime_no_location_layout);
        date = findViewById(R.id.datesel);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(NoLocationActivity.this,
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
int s=0;
    public void showForces(View view) {
        if (month != -1 && year != -1&&s==1) {
            Intent intent = new Intent(this, ForceSelecterActivity.class);
            intent.putExtra("date",date.getText().toString());
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Fill all fields to continue!!!", Toast.LENGTH_SHORT).show();
        }
    }
}
