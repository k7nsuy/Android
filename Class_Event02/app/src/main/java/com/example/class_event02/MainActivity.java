package com.example.class_event02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    int x, y;

    public class KeyView extends View {
        public KeyView(Context context) {
            super(context);
            setBackgroundColor(Color.CYAN);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            Paint paint = new Paint();
            paint.setColor(Color.MAGENTA);
            canvas.drawRect(x, y, x + 100, y + 100, paint);
        }
    }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            KeyView view = new KeyView(this);
            view.setFocusable(true);
            view.setFocusableInTouchMode(true);
            setContentView(view);
//        setContentView(R.layout.activity_main);
            view.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (event.getAction() == KeyEvent.ACTION_UP) {
//                    int x = view.x;
//                    int y = view.y;
                        switch (keyCode) {
                            case KeyEvent.KEYCODE_DPAD_LEFT:
                                if (x - 30 >= 0)
                                    x -= 30;
                                else
                                    x = 0;
                                break;
                            case KeyEvent.KEYCODE_DPAD_RIGHT:
                                if (x + 30 < view.getWidth() - 100)
                                    x += 30;
                                else
                                    x = view.getWidth() - 100;
                                break;
                            case KeyEvent.KEYCODE_DPAD_UP:
                                if (y - 30 >= 0)
                                    y -= 30;
                                else
                                    y = 0;
                                break;
                            case KeyEvent.KEYCODE_DPAD_DOWN:
                                if (y + 30 < view.getHeight() - 100)
                                    y += 30;
                                else
                                    y = view.getHeight() - 100;
                                break;
                        }
                        view.invalidate();
                        return true;
                    }
                    return false;
                }
            });
        }
}
