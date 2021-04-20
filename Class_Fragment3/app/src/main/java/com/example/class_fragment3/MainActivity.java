package com.example.class_fragment3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

    // position을 통해서 WordFragment.OnWordSelectedListener를 implements
public class MainActivity extends AppCompatActivity
        implements WordFragment.OnWordSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.words_container) != null) {
            if (savedInstanceState != null) {
                return;
            }
            WordFragment wf = new WordFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.words_container,wf)
                    .commit();
        }
    }

    @Override
    public void onWordSelected(int position) {

        DefinitionFragment df = (DefinitionFragment) getSupportFragmentManager()
                .findFragmentById(R.id.definition_container);
        if (df != null) {
            df.updateDefinitionView(position);
        } else {
            DefinitionFragment df2 = new DefinitionFragment();
            Bundle args = new Bundle();
            // Bundle => 데이터를 추가할 수 있는 객체
            // Fragment는 Bundle객체를 통해서 데이터를 전달한다.
            args.putInt("position",position);
            df2.setArguments(args);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.definition_container,df2)
                    .addToBackStack(null) //백스택에 남아있는 데이터를 메모리에서 지우기 위해
                    .commit();
        }
    }
}