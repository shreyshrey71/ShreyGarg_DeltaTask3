package com.example.android.shreygarg_deltatask3;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Vector;

public class ForceSelecterAdapter extends RecyclerView.Adapter<ForceSelecterAdapter.DetilViewHolder> {
    private List<String> dept = new Vector<String>();
    private List<String> id = new Vector<String>();
    String date;
    private Context context;

    public ForceSelecterAdapter(Context context, List<String> dataid, List<String> dataname, String date) {
        this.context = context;
        id = dataid;
        dept = dataname;
        this.date = date;
    }

    @NonNull
    @Override
    public DetilViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.detailblocks, viewGroup, false);
        return new DetilViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetilViewHolder detilViewHolder, int i) {
        String title = dept.get(i);
        detilViewHolder.textView.setText(title);
        title = id.get(i);
        detilViewHolder.textView2.setText(title);
        final int pos = i;
        detilViewHolder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NoLocationResultActivity.class);
                intent.putExtra("id", id.get(pos));
                intent.putExtra("name", dept.get(pos));
                intent.putExtra("date",date);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return dept.size();
    }

    public class DetilViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        TextView textView2;
        LinearLayout parent;

        public DetilViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.detailblockimage);
            textView = itemView.findViewById(R.id.detailblocktext);
            textView2 = itemView.findViewById(R.id.detailblockid);
            parent = itemView.findViewById(R.id.parent);
        }
    }
}
