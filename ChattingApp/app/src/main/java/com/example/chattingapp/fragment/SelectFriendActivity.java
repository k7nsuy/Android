package com.example.chattingapp.fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.chattingapp.R;
import com.example.chattingapp.chat.MessageActivity;
import com.example.chattingapp.model.Chat;
import com.example.chattingapp.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SelectFriendActivity extends AppCompatActivity {

    Chat chat = new Chat();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_friend);

        RecyclerView recyclerView = findViewById(R.id.selectFriendActivity_recyclerView);
        recyclerView.setAdapter(new SelectFriendRecyclerViewAdapter());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Button button = findViewById(R.id.selectFriendActivity_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String myUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                chat.users.put(myUid,true);

                FirebaseDatabase.getInstance().getReference().child("chatrooms").push()
                        .setValue(chat);
            }
        });
    }

    class SelectFriendRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        List<User> user;

        public SelectFriendRecyclerViewAdapter() {
            user = new ArrayList<>();
            final String myUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
            FirebaseDatabase.getInstance().getReference().child("users").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull @NotNull DataSnapshot dataSnapshot) {
                    user.clear();

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                        User user2 = snapshot.getValue(User.class);

                        if (user2.uid.equals(myUid)) {
                            continue;
                        }
                        user.add(user2);
                    }
                    notifyDataSetChanged();
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
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_friend_select,parent,false);
            return new CustomerViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
            Glide.with
                    (holder.itemView.getContext())
                    .load(user.get(position).profileImageUrl)
                    .apply(new RequestOptions().circleCrop())
                    .into(((CustomerViewHolder)holder).imageView);

            ((CustomerViewHolder)holder).textView.setText(user.get(position).userName);

            // 유저 클릭 시 messageActivity 실행
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), MessageActivity.class);
                    intent.putExtra("destinationUid",user.get(position).uid);
                    ActivityOptions activityOptions = ActivityOptions.makeCustomAnimation(view.getContext(),
                            R.anim.fromright,R.anim.toleft);
                    startActivity(intent,activityOptions.toBundle());
                }
            });
            if (user.get(position).comment != null) {
                ((CustomerViewHolder) holder).textView_comment.setText(user.get(position).comment);
            }
            ((CustomerViewHolder)holder).checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean b) {
                    // 체크 된 상태
                    if (b) {
                        chat.users.put(user.get(position).uid,true);
                        // 체크 취소 상태
                    } else {
                        chat.users.remove(user.get(position));
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return user.size();
        }

        private class CustomerViewHolder extends RecyclerView.ViewHolder {
            public ImageView imageView;
            public TextView textView;
            public TextView textView_comment;
            public CheckBox checkBox;

            public CustomerViewHolder(View view) {
                super(view);
                imageView = view.findViewById(R.id.friendItem_imageView);
                textView = view.findViewById(R.id.friendItem_textView);
                textView_comment = view.findViewById(R.id.friendItem_textView_comment);
                checkBox = view.findViewById(R.id.friendItem_checkBox);
            }
        }
    }
}