package com.example.chattingapp.chat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.chattingapp.R;
import com.example.chattingapp.model.Chat;
import com.example.chattingapp.model.NotificationModel;
import com.example.chattingapp.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MessageActivity extends AppCompatActivity {

    private Button button;
    private EditText editText;

    private String uid;
    private String destinationUid;
    private String chatRoomUid;

    private RecyclerView recyclerView;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm");

    private User destinationUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        recyclerView = findViewById(R.id.messageActivity_recyclerView);

        uid = FirebaseAuth.getInstance().getCurrentUser().getUid(); // 채팅을 요구하는 아이디 (단말기에 로그인된 uid)
        destinationUid = getIntent().getStringExtra("destinationUid"); // 채팅 당하는 아이디
        button = findViewById(R.id.messageActivity_button);
        editText = findViewById(R.id.messageActivity_editText);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Chat chat = new Chat();
                chat.users.put(uid, true);
                chat.users.put(destinationUid, true);

                if (chatRoomUid == null) {
                    button.setEnabled(false);
                    FirebaseDatabase.getInstance().getReference().child("chatrooms").push().setValue(chat)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    checkChatRoom();
                                }
                            });
                } else {
                    Chat.Comment comment = new Chat.Comment();
                    comment.uid = uid;
                    comment.message = editText.getText().toString(); // 메세지 보내기
                    comment.timestmap = ServerValue.TIMESTAMP;
                    FirebaseDatabase.getInstance().getReference().child("chatrooms").child(chatRoomUid)
                            .child("comments").push().setValue(comment).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<Void> task) {
                            sendGcm();
                            editText.setText("");
                        }
                    });

                }
            }
        });
        checkChatRoom();
    }

    void sendGcm() {
        Gson gson = new Gson();

        NotificationModel notificationModel = new NotificationModel();
        notificationModel.to = destinationUser.pushToken;
        notificationModel.notification.title = "보낸이 아이디";
        notificationModel.notification.text = editText.getText().toString();

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf8"),
                gson.toJson(notificationModel));
        Request request = new Request.Builder()
                .header("Content-Type","application/json")
                .addHeader("Authorization","AAAAxu7ei7A:APA91bEzX3MvTl3MNKFJr6Mqep1EoLiPqNAJ6K5NNSXOlFfqqDgU0SgeAv8MMNRBA9p3ZOtVipnSY6gAsqVAVwFuhfEVQeXpd8yFLpLIR0afLVJeD3e8HcrC2rk8WRmAdQEdMDn2_O9U")
                .url("https://fcm.googleapis.com/fcm/send")
                .post(requestBody)
                .build();
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

            }
        });
    }

    void checkChatRoom() {
        FirebaseDatabase.getInstance().getReference().child("chatrooms").orderByChild("users/" + uid)
                .equalTo(true).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot item : snapshot.getChildren()) {
                    Chat chat = item.getValue(Chat.class);
                    if (chat != null && chat.users.containsKey(destinationUid)) {
                        chatRoomUid = item.getKey();
                        button.setEnabled(true);
                        recyclerView.setLayoutManager(new LinearLayoutManager(MessageActivity.this));
                        recyclerView.setAdapter(new RecyclerViewAdapter());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

    class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        List<Chat.Comment> comments;
        User user;

        public RecyclerViewAdapter() {
            comments = new ArrayList<>();

            FirebaseDatabase.getInstance().getReference().child("users").child(destinationUid)
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                            user = snapshot.getValue(User.class);
                            getMessageList();
                        }

                        @Override
                        public void onCancelled(@NonNull @NotNull DatabaseError error) {

                        }
                    });

        }

        void getMessageList() {
            FirebaseDatabase.getInstance().getReference().child("chatrooms").child(chatRoomUid)
                    .child("comments").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                    comments.clear();

                    for (DataSnapshot item : snapshot.getChildren()) {
                        comments.add(item.getValue(Chat.Comment.class));
                    }
                    // 메세지가 갱신
                    notifyDataSetChanged();

                    recyclerView.scrollToPosition(comments.size() - 1);
                }

                @Override
                public void onCancelled(@NonNull @NotNull DatabaseError error) {

                }
            });
        }

        @NonNull
        @NotNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message,
                    parent, false);
            return new MessageViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
            MessageViewHolder messageViewHolder = ((MessageViewHolder) holder);

            // 내가 보낸 매세지
            if (comments.get(position).uid.equals(uid)) {
                messageViewHolder.textView_message.setText(comments.get(position).message);
                messageViewHolder.textView_message.setBackgroundResource(R.drawable.rightbubble);
                messageViewHolder.linearLayout_destination.setVisibility(View.INVISIBLE);
                messageViewHolder.textView_message.setTextSize(25);
                messageViewHolder.linearLayout_main.setGravity(Gravity.RIGHT);

                // 상대방이 보낸 메세지
            } else {
                Glide.with(holder.itemView.getContext())
                        .load(user.profileImageUrl)
                        .apply(new RequestOptions().circleCrop())
                        .into(messageViewHolder.imageView_profile);
                messageViewHolder.textView_name.setText(user.userName);
                messageViewHolder.linearLayout_destination.setVisibility(View.VISIBLE);
                messageViewHolder.textView_message.setBackgroundResource(R.drawable.leftbubble);
                messageViewHolder.textView_message.setText(comments.get(position).message);
                messageViewHolder.textView_message.setTextSize(25);
                messageViewHolder.linearLayout_main.setGravity(Gravity.LEFT);
            }
            long unixTime = (long) comments.get(position).timestmap;
            Date date = new Date(unixTime);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
            String time = simpleDateFormat.format(date);
            messageViewHolder.textView_timeStamp.setText(time);
        }

        @Override
        public int getItemCount() {
            return comments.size();
        }

        private class MessageViewHolder extends RecyclerView.ViewHolder {
            public TextView textView_message;
            public TextView textView_name;
            public ImageView imageView_profile;
            public LinearLayout linearLayout_destination;
            public LinearLayout linearLayout_main;
            public TextView textView_timeStamp;

            public MessageViewHolder(View view) {
                super(view);
                textView_message = view.findViewById(R.id.messageItem_textView_message);
                textView_name = view.findViewById(R.id.messageItem_textView_name);
                imageView_profile = view.findViewById(R.id.messageItem_imageView_profile);
                linearLayout_destination = view.findViewById(R.id.messageItem_linearLayout_destination);
                linearLayout_main = view.findViewById(R.id.messageItem_linearLayout_main);
                textView_timeStamp = view.findViewById(R.id.messageItem_textView_timeStamp);
            }
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.fromleft,R.anim.toright);
    }
}