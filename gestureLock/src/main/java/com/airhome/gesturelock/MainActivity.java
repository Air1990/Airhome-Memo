package com.airhome.gesturelock;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements GestureLockView.OnGestureLockListener {
    private GestureLockView mGestureLock;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGestureLock = (GestureLockView) findViewById(R.id.lock);
        mGestureLock.setGestureListener(this);
        mTextView = (TextView) findViewById(R.id.msg);
    }

    @Override
    public void updateLockState(String message, int color) {
        mTextView.setText(message);
        mTextView.setTextColor(getResources().getColor(color));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Preferences.setLockState(MainActivity.this, false);
    }
}
