package com.example.funkiepumkinapplication.member;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.funkiepumkinapplication.MainActivity;
import com.example.funkiepumkinapplication.R;
import com.google.gson.Gson;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.User;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;

public class LoginActivity extends AppCompatActivity {
    EditText editid, editpass;
    TextView nickName;
    ImageView profileImage;
    Button btnKakao;
    View dialogView;
    static RequestQueue requestQueue;
    private String TAG = "JoinActivity";
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        


        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Login");
        actionBar.setDisplayHomeAsUpEnabled(true);
        
        editid=findViewById(R.id.editid);
        editpass=findViewById(R.id.editpass);

        Function2<OAuthToken, Throwable, Unit> callback = new Function2<OAuthToken, Throwable, Unit>() {
            @Override
            public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {
                if(oAuthToken!=null){
                    //로그인
                }
                if(throwable!=null){
                    //TBD
                }
                updateKakaoLoginUI();
                return null;
            }
        };

        btnKakao=findViewById(R.id.btnKakao);
        btnKakao.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(UserApiClient.getInstance().isKakaoTalkLoginAvailable(LoginActivity.this)){
                    UserApiClient.getInstance().loginWithKakaoTalk(LoginActivity.this, callback);
                }else{
                    UserApiClient.getInstance().loginWithKakaoAccount(LoginActivity.this, callback);
                }
            }
        });


        TextView btnJoin = (TextView)findViewById(R.id.btnJoin);
        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent joinIntent = new Intent(LoginActivity.this, JoinActivity.class);
                LoginActivity.this.startActivity(joinIntent);
            }
        });


        if(requestQueue==null){
            requestQueue= Volley.newRequestQueue(getApplicationContext());
        }
    }

    private void updateKakaoLoginUI() {
        UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
            @Override
            public Unit invoke(User user, Throwable throwable) {
                if(user != null){
                    Log.i(TAG, "invoke: id="+user.getId());
                    Log.i(TAG, "invoke: email="+user.getKakaoAccount().getEmail());
                    Log.i(TAG, "invoke: nickname="+user.getKakaoAccount().getProfile().getNickname());
                    Log.i(TAG, "invoke: gender="+user.getKakaoAccount().getGender());
                    Log.i(TAG, "invoke: age="+user.getKakaoAccount().getAgeRange());

                    dialogView = (View) View.inflate(LoginActivity.this, R.layout.kakao_login, null);
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    dialog = builder.setView(dialogView)
                            .setPositiveButton("확인", (dialog, which) -> {
                                //로그인정보 저장
                                SharedPreferences sharedPreferences = getSharedPreferences("member",MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("memberNickname", user.getKakaoAccount().getProfile().getNickname());
                                editor.commit();

                                Intent memberIntent = new Intent(LoginActivity.this, MainActivity.class);
                                memberIntent.putExtra("memberNickname",user.getKakaoAccount().getProfile().getNickname());
                                LoginActivity.this.startActivity(memberIntent);
                            })

                            .create();
                    dialog.show();
                    //닉네임 출력
                    nickName = dialog.findViewById(R.id.nickName);
                    nickName.setText(user.getKakaoAccount().getProfile().getNickname());
                    //프로필사진 출력
                    profileImage = dialog.findViewById(R.id.profileImage);
                    Glide.with(profileImage).load(user.getKakaoAccount().getProfile().getThumbnailImageUrl()).circleCrop().into(profileImage);
                    //user.getKakaoAccount().getProfile().getThumbnailImageUrl()

                }else{
                    //nickName.setText(null);
                    //profileImage.setImageBitmap(null);

                }
                return null;
            }
        });
    }

    public void mOnClicked(View view){
        switch(view.getId()){
            case R.id.btnLogin:
                LoginRequest();
                break;


        }
    }

    public void LoginRequest(){
        String url="http://175.215.100.167:8899/member/androidLogin";
        StringRequest request=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "응답->"+response);
                        processResponse(response);
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "에러->"+error.getMessage());
                    }
                }
        ){
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params=new HashMap<String, String>();
                params.put("userId", editid.getText().toString());
                params.put("userPassword", editpass.getText().toString());
                return params;
            }
        };
        request.setShouldCache(false);
        requestQueue.add(request);
    }

    private void processResponse(String response){

        Gson gson=new Gson();
        Member member=gson.fromJson(response, Member.class);
        if(member!=null){
            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
            dialog = builder.setMessage(member.getUserName()+"님이 로그인 했습니다.")
                    .setPositiveButton("확인", null)
                    .create();
            dialog.show();

            //로그인정보 저장
            SharedPreferences sharedPreferences = getSharedPreferences("member",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("memberId", member.getMemberId());
            editor.commit();

            Intent memberIntent = new Intent(LoginActivity.this, MainActivity.class);
            memberIntent.putExtra("memberId",member.getMemberId());
            LoginActivity.this.startActivity(memberIntent);
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
            dialog = builder.setMessage("아이디, 비밀번호를 다시 확인하세요")
                    .setNegativeButton("다시 시도", null)
                    .create();
            dialog.show();
        }
//        for(int i=0; i<boards.length;i++){
//            println(boards[i].toString());
//        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(dialog!=null){
            dialog.dismiss();
            dialog = null;
        }
    }
}