package com.example.a21_04_27_movie;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.viewHolder> {

    ArrayList<Movie> items = new ArrayList<Movie>();

    // 1. viewHolder 를 만들어준다.
    static class viewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle,tvPoint;
        ImageView siv;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvPoint = itemView.findViewById(R.id.tvPoint);
            siv = itemView.findViewById(R.id.siv);
        }

        public void setItem(Movie item) {
            tvTitle.setText(item.getTitle());
            tvPoint.setText("평점: " + item.getPoint());
            siv.setImageResource(item.getResId());
        }
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // view layout 을 들고온다.
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.layout,parent,false);
        return new viewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Movie item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Movie item) {
        items.add(item);
    }

    public  void setItems(ArrayList<Movie> items) {
        this.items = items;
    }

    public void setItem(int position,Movie item) {
        items.set(position,item);
    }

    public Movie getItem(int position) {
        return items.get(position);
    }
}
