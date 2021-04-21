package com.example.class_service;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

public class MainActivity extends AppCompatActivity implements AutoPermissionsListener {

    EditText edit1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit1 = findViewById(R.id.edit1);

        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            // service 로 보낸다.
            @Override
            public void onClick(View v) {
                String name = edit1.getText().toString();
                Intent intent = new Intent(getApplicationContext(),MyService.class);
                intent.putExtra("name",name);
                intent.putExtra("command","show");
                startService(intent);
            }
        });
        AutoPermissions.Companion.loadAllPermissions(this,101);
    }

    // service 에서 돌아온 뒤, 새로운 작업
    @Override
    protected void onNewIntent(Intent intent) {

        processIntent(intent);
//        if ( intent != null) {
//            String command = intent.getStringExtra("command");
//            String name = intent.getStringExtra("name");
//            edit1.setText(command + ", " + name);
////            Toast.makeText(this,command + ", " + name,Toast.LENGTH_SHORT).show();
//        }
        super.onNewIntent(intent);
    }

    private void processIntent(Intent intent) {
        if ( intent != null) {
            String command = intent.getStringExtra("command");
            String name = intent.getStringExtra("name");
            edit1.setText(command + ", " + name);
            Toast.makeText(this,command + ", " + name,Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AutoPermissions.Companion.parsePermissions(this,requestCode,permissions,this);
        Toast.makeText(this, "requestCode : "+requestCode+"  permissions : "+permissions+"  grantResults :"+grantResults, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDenied(int i, String[] strings) {
        Toast.makeText(this,"permissions denied : " + strings.length,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGranted(int i, String[] strings) {
        Toast.makeText(this,"permissions granted : " + strings.length,
                Toast.LENGTH_SHORT).show();
    }
}