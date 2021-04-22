package com.example.a21_04_22_recyclerview01;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder>
                            implements OnPersonItemClickListener {

    OnPersonItemClickListener listener;
    ArrayList<Person> items = new ArrayList<Person>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.layout1,parent,false);
        return new ViewHolder(itemView,this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Person item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    // ArrayList 에 데이터를 추가.
    public void addItem(Person item) {
        items.add(item);
    }

    // 외부에서 생성한 아이템 ArrayList 를 쓰기 위해 set 지정
    public void setItems(ArrayList<Person> items) {
        this.items = items;
    }

    // set 한 아이템을 꺼내온다.
    public Person getItem(int position) {
        return items.get(position);
    }

    // item 을 지정.
    public void item(int position, Person item) {
        items.set(position,item);
    }

    public void setOnItemClickListener(OnPersonItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        if (listener != null) {
            listener.onItemClick(holder,view,position);
        }
    }

    // 하나의 view 를 hold 시켜 다음에 새로 생성없이 또 꺼내 쓸 수 있도록 한다.
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName,tvPhone;

        // OnPersonItemClickListener 를 ViewHolder 에 불러준다.
        public ViewHolder(@NonNull View itemView, final OnPersonItemClickListener listener) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvPhone = itemView.findViewById(R.id.tvPhone);
            
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

        public void setItem(Person item) {
            tvName.setText(item.getName());
            tvPhone.setText(item.getPhone());
        }
    }
}

