package com.example.android.shreygarg_deltatask3;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.nio.file.Path;

public class MapMarker extends AppCompatActivity {
    float lat,lng;
    float t,b,l,r;
    float x,y;
    RelativeLayout parent;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_layout);
        parent=findViewById(R.id.mapparent);
        assert  getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        imageView = findViewById(R.id.map);
        Intent intent = getIntent();
        lat=intent.getFloatExtra("latitude",52.6f);
        lng=intent.getFloatExtra("longitude",-1.2f);
        y=58.67f-lat;
        x=lng+8.25f;
        y/=8.75;
        x/=10;
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                t=imageView.getTop();
                b=imageView.getBottom();
                l=imageView.getLeft();
                r=imageView.getRight();
                x*=(r-l);
                y*=(b-t);
                marker();
            }
        },200);
    }
    public void marker()
    {
        coordinates c=new coordinates(this);
        RelativeLayout.LayoutParams rp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        c.setLayoutParams(rp);
        parent.addView(c);
    }
    public class coordinates extends View{
        Paint paint;

        public coordinates(Context context) {
            super(context);
            paint=new Paint();
            paint.setColor(Color.parseColor("#00b0e0"));
            paint.setStrokeWidth(20);
        }
        @Override
        public void onDraw(Canvas canvas)
        {
            canvas.drawLine(l,y+t,r,y+t,paint);
            canvas.drawLine(x+l,t,x+l,b,paint);
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
}
