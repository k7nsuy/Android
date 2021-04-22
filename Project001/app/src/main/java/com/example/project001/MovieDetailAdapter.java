package com.example.project001;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MovieDetailAdapter extends RecyclerView.Adapter<MovieDetailAdapter.ViewHolder>
implements OnPosterClickListener {

    OnPosterClickListener listener;
    ArrayList<MovieDetail> items = new ArrayList<MovieDetail>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View movieView = inflater.inflate(R.layout.layout,parent,false);
        return new ViewHolder(movieView,this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MovieDetail item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(MovieDetail item) {
        items.add(item);
    }

    public void setItems(ArrayList<MovieDetail> items) {
        this.items = items;
    }

    public MovieDetail getItem(int position) {
        return items.get(position);
    }

    public void item(int position, MovieDetail item) {
        items.set(position,item);
    }

    public void setOnItemClickListener(OnPosterClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        if (listener != null) {
            listener.onItemClick(holder,view,position);
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv1;
        ImageView iv1;

        public ViewHolder(@NonNull View itemView, final OnPosterClickListener listener) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.tv1);
            iv1 = itemView.findViewById(R.id.iv1);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null) {
                        listener.onItemClick(ViewHolder.this,v,position);
                    }
                }
            });

        }
        public void setItem(MovieDetail item) {
            tv1.setText(item.title);
            tv1.setText(item.actor);
            tv1.setText(item.director);

            iv1.setImageResource(Integer.parseInt(String.valueOf(item.imgIds)));
        }
    }

}
