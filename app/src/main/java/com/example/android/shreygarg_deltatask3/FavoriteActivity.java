package com.example.android.shreygarg_deltatask3;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;
import java.util.Vector;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FavoriteActivity extends AppCompatActivity {
    Database mydb;
    RecyclerView detaillist;
    List<String> name = new Vector<String>();
    List<String> latitude = new Vector<String>();
    List<String> longitude = new Vector<String>();
    List<String> id = new Vector<String>();
    List<String> pid = new Vector<String>();
    List<String> street = new Vector<String>();
    List<String> category = new Vector<String>();
    List<String> ltype = new Vector<String>();
    List<String> date = new Vector<String>();
    List<String> mode = new Vector<String>();
    LocationAdapter detailadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity_layout);
        mydb = new Database(this);
        detaillist = findViewById(R.id.deptdetail);
        detaillist.setHasFixedSize(true);
        detaillist.setLayoutManager(new LinearLayoutManager(this));
        Cursor res = mydb.getAllData();
        if(res.getCount()==0)
        {
            Toast.makeText(this,"No Data Found",Toast.LENGTH_SHORT).show();
        }
        else
        {
            while (res.moveToNext())
            {id.add(res.getString(0));
             category.add(res.getString(1));
             date.add(res.getString(2));
             latitude.add(res.getString(3));
             longitude.add(res.getString(4));
             street.add(res.getString(5));
             ltype.add(res.getString(6));
             pid.add(res.getString(7));
             mode.add(res.getString(8));
            }
            detailadapter = new LocationAdapter(this, date, latitude, longitude, category, street,id,pid,ltype,mode);
            detaillist.setAdapter(detailadapter);
        }
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

    @Override
    protected void onResume() {

        super.onResume();
        if(detailadapter!=null)
        detailadapter.notifyDataSetChanged();
    }

    public void serchaction(String s) {
        List<String> searchlatitude = new Vector<String>();
        List<String> searchlongitude = new Vector<String>();
        List<String> searchid = new Vector<String>();
        List<String> searchpid = new Vector<String>();
        List<String> searchstreet = new Vector<String>();
        List<String> searchcategory = new Vector<String>();
        List<String> searchltype = new Vector<String>();
        List<String> searchdate = new Vector<String>();
        List<String> searchmode = new Vector<String>();
        if (!s.equals("")) {
            for (int i = 0; i < category.size(); i++) {
                if (category.get(i).toLowerCase().contains(s.toLowerCase())) {
                    searchid.add(id.get(i));
                    searchcategory.add(category.get(i));
                    searchdate.add(date.get(i));
                    searchlatitude.add(latitude.get(i));
                    searchlongitude.add(longitude.get(i));
                    searchstreet.add(street.get(i));
                    searchltype.add(ltype.get(i));
                    searchpid.add(pid.get(i));
                    searchmode.add(mode.get(i));
                }
            }

            detailadapter = new LocationAdapter(this, searchdate, searchlatitude, searchlongitude, searchcategory,
                    searchstreet,searchid,searchpid,searchltype,searchmode);
            detailadapter.notifyDataSetChanged();
            detaillist.setAdapter(detailadapter);
        } else {

            detailadapter = new LocationAdapter(this, date, latitude, longitude, category, street,id,pid,ltype,mode);
            detailadapter.notifyDataSetChanged();
            detaillist.setAdapter(detailadapter);

        }
    }
}
