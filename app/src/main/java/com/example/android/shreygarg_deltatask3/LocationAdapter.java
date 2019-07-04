package com.example.android.shreygarg_deltatask3;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Vector;
import java.util.zip.Inflater;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationViewHolder> {
    private List<String> longitude = new Vector<String>();
    private List<String> latitude = new Vector<String>();
    private List<String> date = new Vector<String>();
    private List<String> category = new Vector<String>();
    private List<String> street = new Vector<String>();
    private List<String> id = new Vector<String>();
    private List<String> pid = new Vector<String>();
    private List<String> ltype = new Vector<String>();
    private List<String> mode = new Vector<String>();
    private Context context;
    public LocationAdapter(Context context, List<String> date, List<String> latitude, List<String> longitude, List<String> category, List<String> street, List<String> id, List<String> pid, List<String> ltype, List<String> mode) {
        this.context = context;
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
        this.category = category;
        this.street = street;
        this.id = id;
        this.pid = pid;
        this.ltype = ltype;
        this.mode=mode;
    }

    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.lacoationblocks, viewGroup, false);
        return new LocationViewHolder(view);
    }

    public static int position;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(@NonNull LocationViewHolder locationViewHolder, int i) {
        String s = date.get(i);
        locationViewHolder.textViewdate.setText("Date : " + s);
        if(mode.get(i).equals("2"))
        {
            locationViewHolder.textViewlatitude.setVisibility(View.GONE);
            s = longitude.get(i);
            locationViewHolder.textViewlongitude.setText("Force ID :-\n" + s);
        }
        else {
            s = latitude.get(i);
            locationViewHolder.textViewlatitude.setText("Latitude : " + s);
            s = longitude.get(i);
            locationViewHolder.textViewlongitude.setText("Longitude : " + s);
        }
        s = category.get(i);
        locationViewHolder.textViewcategory.setText(s);
        s = street.get(i);
        locationViewHolder.textViewpid.setText(s);
        final int pos = i;
        position = i;
        Database mydb = new Database(context);
        Cursor res = mydb.check(id.get(pos));
        if(res.getCount()==0)
        {
            locationViewHolder.favicon.setBackgroundColor(Color.parseColor("#aaaaaa"));
        }
        else
        {
            locationViewHolder.favicon.setBackgroundColor(Color.parseColor("#00b0e0"));
        }
        locationViewHolder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CrimeDetails.class);
                intent.putExtra("latitude", latitude.get(pos));
                intent.putExtra("longitude", longitude.get(pos));
                intent.putExtra("date", date.get(pos));
                intent.putExtra("category", category.get(pos));
                intent.putExtra("street", street.get(pos));
                intent.putExtra("id", id.get(pos));
                intent.putExtra("pid", pid.get(pos));
                intent.putExtra("ltype", ltype.get(pos));
                intent.putExtra("mode", mode.get(pos));
                context.startActivity(intent);
            }
        });
        final LocationViewHolder locationViewHolder1 = locationViewHolder;
        locationViewHolder.favicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database mydb = new Database(context);
                    ColorDrawable colorDrawable = (ColorDrawable) locationViewHolder1.favicon.getBackground();
                    if (colorDrawable.getColor() == Color.parseColor("#aaaaaa")) {
                        locationViewHolder1.favicon.setBackgroundColor(Color.parseColor("#00b0e0"));
                        long l = mydb.insertData(id.get(pos),category.get(pos),date.get(pos),latitude.get(pos),
                                longitude.get(pos),street.get(pos),ltype.get(pos),pid.get(pos),mode.get(pos));
                    }
                    else {
                        locationViewHolder1.favicon.setBackgroundColor(Color.parseColor("#aaaaaa"));
                        long l = mydb.Deleterow(id.get(pos));
                    }

            }
        });
    }

    public static int getPosition()
    {
        return position;
    }
    @Override
    public int getItemCount() {
        return latitude.size();
    }

    public class LocationViewHolder extends RecyclerView.ViewHolder {
        TextView textViewlatitude;
        TextView textViewlongitude;
        TextView textViewdate;
        TextView textViewcategory;
        TextView textViewpid;
        LinearLayout parent;
        ImageView favicon;

        public LocationViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewlatitude = itemView.findViewById(R.id.locationcrimelat);
            textViewlongitude = itemView.findViewById(R.id.locationcrimelong);
            textViewdate = itemView.findViewById(R.id.locationcrimedate);
            textViewcategory = itemView.findViewById(R.id.locationcrimecategory);
            textViewpid = itemView.findViewById(R.id.locationcrimestreet);
            parent = itemView.findViewById(R.id.locblock);
            favicon = itemView.findViewById(R.id.favstarblock);
        }
    }
}
