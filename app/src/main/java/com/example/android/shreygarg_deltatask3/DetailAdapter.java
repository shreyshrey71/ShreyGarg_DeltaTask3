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

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.DetilViewHolder> {
    private List<String> dept = new Vector<String>();
    private List<String> id = new Vector<String>();
    private Context context;

    public DetailAdapter(Context context, List<String> dataid, List<String> dataname) {
        this.context = context;
        id = dataid;
        dept = dataname;

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
                Intent intent = new Intent(context, ForceDescription.class);
                intent.putExtra("id", id.get(pos));
                intent.putExtra("name", dept.get(pos));
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
