package com.airhome.airmemo.lock;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.airhome.airmemo.Preferences;
import com.airhome.airmemo.R;
import com.airhome.airmemo.WriteActivity;

public class GestureLockActivity extends AppCompatActivity implements GestureLockView.OnGestureLockListener {
    private GestureLockView mGestureLock;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_loack);
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
    public void unLockSuccess() {
        startActivity(new Intent(this, WriteActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
