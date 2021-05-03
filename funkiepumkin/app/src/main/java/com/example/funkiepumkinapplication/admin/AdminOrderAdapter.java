package com.example.funkiepumkinapplication.admin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.funkiepumkinapplication.R;
import com.example.funkiepumkinapplication.order.Order;

import java.util.ArrayList;

public class AdminOrderAdapter extends RecyclerView.Adapter<AdminOrderAdapter.ViewHolder>
        implements OnAdminOrderClickListener{ //2.extends  //1.의 내용을 쓰겠다 //4.implements
    ArrayList<Order> items = new ArrayList<Order>();
    OnAdminOrderClickListener listener; //이벤트1.
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { //3. 세개를 override
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.admin_order_item,parent,false);
        return new ViewHolder(itemView, this); //이벤트2. 이벤트listener도 같이 리턴
    }

    public void setOnItemClickListener(OnAdminOrderClickListener listener){ //이벤트3.
        this.listener = listener;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Order item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Order item){ //끝에 붙임
        items.add(item);
    }

    public void setItems(ArrayList<Order> items){ //한꺼번에 바꿀때
        this.items = items;
    }

    public void setItem(int position, Order item){ //내가 원하는 위치에 붙임
        items.set(position,item);
    }

    public Order getItem(int position){
        return items.get(position);
    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position) { //이벤트5.
        if(listener != null){
            listener.onItemClick(holder, view, position);
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder{ //1. //화면 왔다갔다 할 때 뷰홀더 만들면 새로 만들지 않고 기존에 있던 것을 가져옴
        TextView tvAdminOrderId, tvAdminOrderStatus;

        public ViewHolder(@NonNull View itemView, final OnAdminOrderClickListener listener) {
            super(itemView);

            tvAdminOrderId = itemView.findViewById(R.id.tvAdminOrderId);
            tvAdminOrderStatus = itemView.findViewById(R.id.tvAdminOrderStatus);


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
        public void setItem(Order item){
            tvAdminOrderId.setText(item.getOrderId()+"");
            tvAdminOrderStatus.setText(item.getStatus());
        }
    }
}
