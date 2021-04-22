package com.example.a21_04_22_recyclerview01;

import android.view.View;

public interface OnPersonItemClickListener {

    public void onItemClick(PersonAdapter.ViewHolder holder, View view,int position);

}
