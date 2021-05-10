package com.example.chattingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.chattingapp.fragment.ChatFragment;
import com.example.chattingapp.fragment.PeopleFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.mainActivity_bottom_navigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_people:
                        getFragmentManager().beginTransaction().replace(R.id.mainActivity_frameLayout,
                                new PeopleFragment()).commit();
                        return true;
                    case R.id.action_chat:
                        getFragmentManager().beginTransaction().replace(R.id.mainActivity_frameLayout,
                                new ChatFragment()).commit();
                        return true;
                }
                return false;
            }
        });
        passPushTokenToServer();
    }
    void passPushTokenToServer() {
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String token = FirebaseInstanceId.getInstance().getToken();
        Map<String,Object> map = new HashMap<>();
        map.put("pushToken",token);

        FirebaseDatabase.getInstance().getReference().child("users").child(uid).updateChildren(map);
    }
}