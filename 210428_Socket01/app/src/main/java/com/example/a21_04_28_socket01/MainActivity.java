package com.example.a21_04_28_socket01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    TextView tvClient,tvServer;
    EditText ed;
    Handler handler;
    Button btnSend,btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler();

        tvClient = findViewById(R.id.tvClient);
        tvServer = findViewById(R.id.tvServer);
        ed = findViewById(R.id.ed);
        btnSend = findViewById(R.id.btnSend);
        btnStart = findViewById(R.id.btnStart);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        setTvServer();
                    }
                }).start();

            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String data = ed.getText().toString();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        send(data);
                    }
                }).start();
            }
        });
    }

    private void send(String data) {
        try {
            int port = 5001;
            Socket socket = new Socket("localhost",port);
            printClientLog("소켓 연결함");

            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(data);
            outputStream.flush();
            printClientLog("데이터 전송 함");

            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            printClientLog("서버로 부터 받음" + inputStream.readObject());
            socket.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setTvServer() {
        try {
            int port = 5001;
            ServerSocket serverSocket = new ServerSocket(port);
            printServerLog("서버 시작함 : " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                int clientPort = socket.getPort();
                InetAddress clientHost = socket.getLocalAddress();
                printServerLog("클라이언트 연결됨 : " + clientHost +
                        " : " + clientPort);

                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                Object obj = inputStream.readObject();
                printServerLog("데이터 받음" + obj);

                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                outputStream.writeObject(obj + " from Server");
                outputStream.flush();
                printServerLog("데이터 보냄");
                socket.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void printClientLog(final String data) {
        Log.d("MainActivity",data);
        handler.post(new Runnable() {
            @Override
            public void run() {
                tvClient.append(data + "\n");
            }
        });
    }

    private void printServerLog(final String data) {
        Log.d("MainActivity",data);
        handler.post(new Runnable() {
            @Override
            public void run() {
                tvServer.append(data + "\n");
            }
        });
    }

}