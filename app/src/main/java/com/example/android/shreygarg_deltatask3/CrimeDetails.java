package com.example.android.shreygarg_deltatask3;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Vector;

public class CrimeDetails extends AppCompatActivity {
    TextView textView;
    LinearLayout linearLayout;
    List<String> s = new Vector<String>();
    ImageView imageView;
    Button button;
    Database mydb;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crime_detail_layout);
        assert  getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        imageView = findViewById(R.id.favstar);
        mydb = new Database(this);
        linearLayout = findViewById(R.id.crimedetaillay);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = Math.round(10 * getResources().getDisplayMetrics().density);
        lp.topMargin = Math.round(10 * getResources().getDisplayMetrics().density);
        Intent intent = getIntent();
        s.add(intent.getStringExtra("id"));
        s.add(intent.getStringExtra("category"));
        s.add(intent.getStringExtra("date"));
        s.add(intent.getStringExtra("latitude"));
        s.add(intent.getStringExtra("longitude"));
        s.add(intent.getStringExtra("street"));
        s.add(intent.getStringExtra("ltype"));
        s.add(intent.getStringExtra("pid"));
        s.add(intent.getStringExtra("mode"));
        if(s.get(8).equals("1")) {
            for (int i = 0; i < s.size()-1; i++) {
                textView = new TextView(getApplicationContext());
                textView.setLayoutParams(lp);
                textView.setTextColor(Color.WHITE);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
                textView.setTypeface(null, Typeface.BOLD);
                switch (i) {
                    case 0: {
                        textView.setText("ID : ");
                        break;
                    }
                    case 1: {
                        textView.setText("Category : ");
                        break;
                    }
                    case 2: {
                        textView.setText("Date : ");
                        break;
                    }
                    case 3: {
                        textView.setText("Latitude : ");
                        break;
                    }
                    case 4: {
                        textView.setText("Longitude : ");
                        break;
                    }
                    case 5: {
                        textView.setText("Location : ");
                        break;
                    }
                    case 6: {
                        textView.setText("Location Type : ");
                        break;
                    }
                    case 7: {

                        textView.setText("Persistent ID :-\n");
                        break;
                    }
                }
                textView.append(s.get(i));
                linearLayout.addView(textView);
            }
            View view = new View(getApplicationContext());
            LinearLayout.LayoutParams lp3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0);
            lp3.weight = 1;
            view.setLayoutParams(lp3);
            linearLayout.addView(view);
            button = new Button(getApplicationContext());
            button.setLayoutParams(lp2);
            button.setText("View in Map");
            button.setBackgroundColor(Color.parseColor("#b71c1c"));
            linearLayout.addView(button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent1 = new Intent(getApplicationContext(),MapMarker.class);
                    intent1.putExtra("latitude",Float.valueOf(s.get(3)));
                    intent1.putExtra("longitude",Float.valueOf(s.get(4)));
                    startActivity(intent1);
                }
            });
        }
        else {
            for (int i = 0; i < s.size()-1; i++) {
                textView = new TextView(getApplicationContext());
                textView.setLayoutParams(lp);
                textView.setTextColor(Color.WHITE);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
                textView.setTypeface(null, Typeface.BOLD);
                switch (i) {
                    case 0: {
                        textView.setText("ID : ");
                        break;
                    }
                    case 1: {
                        textView.setText("Category : ");
                        break;
                    }
                    case 2: {
                        textView.setText("Date : ");
                        break;
                    }
                    case 3: {
                        break;
                    }
                    case 4: {
                        textView.setText("Force ID : ");
                        break;
                    }
                    case 5: {
                        textView.setText("Force : ");
                        break;
                    }
                    case 6: {
                        break;
                    }
                    case 7: {

                        textView.setText("Persistent ID :-\n");
                        break;
                    }
                }
                if (i != 3 && i != 6) {
                    textView.append(s.get(i));
                    linearLayout.addView(textView);
                }

            }
        }
        Cursor res = mydb.check(s.get(0));
        if(res.getCount()==0)
        {
            imageView.setBackgroundColor(Color.parseColor("#aaaaaa"));
        }
        else
        {
            imageView.setBackgroundColor(Color.parseColor("#00b0e0"));
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
            {
                onBackPressed();
            }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void fav(View view) {
        imageView = findViewById(R.id.favstar);
        ColorDrawable colorDrawable = (ColorDrawable) imageView.getBackground();
        if (colorDrawable.getColor() == Color.parseColor("#aaaaaa")) {
            imageView.setBackgroundColor(Color.parseColor("#00b0e0"));
            long l = mydb.insertData(s.get(0), s.get(1), s.get(2), s.get(3), s.get(4), s.get(5), s.get(6), s.get(7),s.get(8));
        }
        else {
            imageView.setBackgroundColor(Color.parseColor("#aaaaaa"));
            long l = mydb.Deleterow(s.get(0));
        }
    }
}
