package com.example.funkiepumkinapplication.Map;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.funkiepumkinapplication.R;

import java.util.ArrayList;

public class MapAdapter extends RecyclerView.Adapter<MapAdapter.ViewHolder>
        implements OnMapItemClickListener{
    ArrayList<Shop> items = new ArrayList<Shop>();
    OnMapItemClickListener listener; //이벤트1.
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { //3. 세개를 override
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.map_dynamic_rv_item,parent,false);
        return new ViewHolder(itemView, this); //이벤트2. 이벤트listener도 같이 리턴
    }

    public void setOnItemClickListener(OnMapItemClickListener listener){ //이벤트3.
        this.listener = listener;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Shop item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Shop item){ //끝에 붙임
        items.add(item);
    }

    public void setItems(ArrayList<Shop> items){ //한꺼번에 바꿀때
        this.items = items;
    }

    public void setItem(int position, Shop item){ //내가 원하는 위치에 붙임
        items.set(position,item);
    }

    public Shop getItem(int position){
        return items.get(position);
    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position) { //이벤트5.
        if(listener != null){
            listener.onItemClick(holder, view, position);
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder{ //1. //화면 왔다갔다 할 때 뷰홀더 만들면 새로 만들지 않고 기존에 있던 것을 가져옴
        TextView name,address,tel;

        public ViewHolder(@NonNull View itemView, final OnMapItemClickListener listener) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            address = itemView.findViewById(R.id.address);
            tel = itemView.findViewById(R.id.tel);
            String shopLat;
            String shopLng;

            itemView.setOnClickListener(new View.OnClickListener() { //이벤트4.
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(listener != null) {
                        listener.onItemClick(ViewHolder.this, view, position);
                    }
                }
            });

        }
        public void setItem(Shop item){
            name.setText(item.getShopName());
            address.setText(item.getShopAddress());
            tel.setText(item.getShopTel());
        }
    }
}

