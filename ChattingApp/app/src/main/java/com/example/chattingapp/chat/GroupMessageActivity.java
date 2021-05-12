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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class GroupMessageActivity extends AppCompatActivity {
    Map<String, User> users = new HashMap<>();
    String destinationRoom;
    String uid;
    EditText editText;

    int peopleCount = 0;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm");

    private DatabaseReference databaseReference;
    private ValueEventListener valueEventListener;

    private RecyclerView recyclerView;

    List<Chat.Comment> comments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_message);
        destinationRoom = getIntent().getStringExtra(destinationRoom);
        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        editText = findViewById(R.id.groupMessageActivity_editText);

        FirebaseDatabase.getInstance().getReference().child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot item : snapshot.getChildren()) {
                    users.put(item.getKey(),item.getValue(User.class));
                }
                init();
                recyclerView = findViewById(R.id.groupMessageActivity_recyclerView);
                recyclerView.setAdapter(new groupMessageRecyclerViewAdapter());
                recyclerView.setLayoutManager(new LinearLayoutManager(GroupMessageActivity.this));
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
    void init() {
        Button button = findViewById(R.id.groupMessageActivity_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Chat.Comment comment = new Chat.Comment();
                comment.uid = uid;
                comment.message = editText.getText().toString();
                comment.timestmap = ServerValue.TIMESTAMP;
                FirebaseDatabase.getInstance().getReference().child("chatrooms").child(destinationRoom)
                        .child("comments").push().setValue(comment).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<Void> task) {

                        FirebaseDatabase.getInstance().getReference().child("chatrooms").child(destinationRoom)
                                .child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                                Map<String,Boolean> map = (Map<String, Boolean>) snapshot.getValue();

                                for (String item : map.keySet()) {
                                    if (item.equals(uid)) {
                                        continue;
                                    }
                                    sendGcm(users.get(item).pushToken);
                                }
                                editText.setText("");
                            }

                            @Override
                            public void onCancelled(@NonNull @NotNull DatabaseError error) {

                            }
                        });
                    }
                });
            }
        });
    }

    void sendGcm(String pushToken) {
        Gson gson = new Gson();

        NotificationModel notificationModel = new NotificationModel();
        notificationModel.to = pushToken;
        notificationModel.notification.title = "보낸이 아이디";
        notificationModel.notification.text = editText.getText().toString();

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; UTF-8"),
                gson.toJson(notificationModel));
        Request request = new Request.Builder()
                .header("Content-Type","application/json; UTF-8")
                .addHeader("Authorization","AAAAxu7ei7A:APA91bEzX3MvTl3MNKFJr6Mqep1EoLiPqNAJ6K5NNSXOlFfqqDgU0SgeAv8MMNRBA9p3ZOtVipnSY6gAsqVAVwFuhfEVQeXpd8yFLpLIR0afLVJeD3e8HcrC2rk8WRmAdQEdMDn2_O9U")
                .url("https://fcm.googleapis.com/v1/ChattingApp/chattingapp-95354/messages:send")
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

    class groupMessageRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        public groupMessageRecyclerViewAdapter() {
            getMessageList();
        }

        void getMessageList() {
            databaseReference = FirebaseDatabase.getInstance().getReference().child("chatrooms").child(destinationRoom)
                    .child("comments");

            valueEventListener = databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                    comments.clear();
                    Map<String,Object> readUsersMap = new HashMap<>();

                    for (DataSnapshot item : snapshot.getChildren()) {
                        String key = item.getKey();
                        Chat.Comment comment_origin = item.getValue(Chat.Comment.class);
                        Chat.Comment comment_motify = item.getValue(Chat.Comment.class);
                        comment_motify.readUsers.put(uid,true);

                        readUsersMap.put(key,comment_motify);
                        comments.add(comment_origin);
                    }
                    FirebaseDatabase.getInstance().getReference().child("chatrooms")
                            .child(destinationRoom).child("comments").updateChildren(readUsersMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull @NotNull Task<Void> task) {
                                    // 메세지가 갱신
                                    notifyDataSetChanged();

                                    recyclerView.scrollToPosition(comments.size() - 1);
                                }
                            });
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
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message,parent,false);
            
            return new GroupMessageViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {

            GroupMessageViewHolder messageViewHolder = ((GroupMessageViewHolder) holder);

            // 내가 보낸 매세지
            if (comments.get(position).uid.equals(uid)) {
                messageViewHolder.textView_message.setText(comments.get(position).message);
                messageViewHolder.textView_message.setBackgroundResource(R.drawable.rightbubble);
                messageViewHolder.linearLayout_destination.setVisibility(View.INVISIBLE);
                messageViewHolder.textView_message.setTextSize(25);
                messageViewHolder.linearLayout_main.setGravity(Gravity.RIGHT);
                setReadCounter(position, messageViewHolder.textView_readCount_left);

                // 상대방이 보낸 메세지
            } else {
                Glide.with(holder.itemView.getContext())
                        .load(users.get(comments.get(position).uid).profileImageUrl)
                        .apply(new RequestOptions().circleCrop())
                        .into(messageViewHolder.imageView_profile);
                messageViewHolder.textView_name.setText(users.get(comments.get(position).uid).userName);
                messageViewHolder.linearLayout_destination.setVisibility(View.VISIBLE);
                messageViewHolder.textView_message.setBackgroundResource(R.drawable.leftbubble);
                messageViewHolder.textView_message.setText(comments.get(position).message);
                messageViewHolder.textView_message.setTextSize(25);
                messageViewHolder.linearLayout_main.setGravity(Gravity.LEFT);
                setReadCounter(position, messageViewHolder.textView_readCount_right);
            }
            long unixTime = (long) comments.get(position).timestmap;
            Date date = new Date(unixTime);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
            String time = simpleDateFormat.format(date);
            messageViewHolder.textView_timeStamp.setText(time);

        }

        void setReadCounter(final int position,final TextView textView) {
            if (peopleCount == 0) {
                FirebaseDatabase.getInstance().getReference().child("chatrooms").child(destinationRoom)
                        .child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        Map<String, Boolean> users = (Map<String, Boolean>) snapshot.getValue();
                        peopleCount = users.size();
                        int count = peopleCount - comments.get(position).readUsers.size();
                        if (count > 0) {
                            textView.setVisibility(View.VISIBLE);
                            textView.setText(String.valueOf(count));
                        } else {
                            textView.setVisibility(View.INVISIBLE);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                    }
                });
            } else {
                int count = peopleCount - comments.get(position).readUsers.size();
                if (count > 0) {
                    textView.setVisibility(View.VISIBLE);
                    textView.setText(String.valueOf(count));
                } else {
                    textView.setVisibility(View.INVISIBLE);
                }
            }
        }

        @Override
        public int getItemCount() {
            return comments.size();
        }
    }

    private class GroupMessageViewHolder extends RecyclerView.ViewHolder {

        public TextView textView_message;
        public TextView textView_name;
        public ImageView imageView_profile;
        public LinearLayout linearLayout_destination;
        public LinearLayout linearLayout_main;
        public TextView textView_timeStamp;
        public TextView textView_readCount_left;
        public TextView textView_readCount_right;

        public GroupMessageViewHolder(View view) {
            super(view);
            textView_message = view.findViewById(R.id.messageItem_textView_message);
            textView_name = view.findViewById(R.id.messageItem_textView_name);
            imageView_profile = view.findViewById(R.id.messageItem_imageView_profile);
            linearLayout_destination = view.findViewById(R.id.messageItem_linearLayout_destination);
            linearLayout_main = view.findViewById(R.id.messageItem_linearLayout_main);
            textView_timeStamp = view.findViewById(R.id.messageItem_textView_timeStamp);
            textView_readCount_left = view.findViewById(R.id.messageItem_textView_readCount_left);
            textView_readCount_right = view.findViewById(R.id.messageItem_textView_readCount_right);
        }
    }
}