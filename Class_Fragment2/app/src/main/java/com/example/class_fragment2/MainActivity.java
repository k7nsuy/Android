package com.example.class_fragment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(findViewById(R.id.fragment_container) != null ) {
            if(savedInstanceState != null) {
                return;
            }
            SongFragment songF = new SongFragment();
            FragmentManager fmg = getSupportFragmentManager();
            FragmentTransaction ft = fmg.beginTransaction();
            ft.add(R.id.fragment_container,songF);
            ft.commit();
        }
    }

    public void onBtnClicked(View view) {
        Fragment fr = null;
        switch (view.getId()) {
            case R.id.btnSong:
                fr = new SongFragment();
                break;

            case R.id.btnArtist:
                fr = new ArtistFragment();
                break;

            case R.id.btnAlbum:
                fr = new AlbumFragment();
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container,fr)
                .addToBackStack(null)
                .commit();
    }
}