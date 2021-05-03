package com.example.funkiepumkinapplication.admin;

import android.view.View;

import com.example.funkiepumkinapplication.cart.CartAdapter;

public interface OnAdminOrderClickListener {
    public void onItemClick(AdminOrderAdapter.ViewHolder holder, View view, int position);
}
