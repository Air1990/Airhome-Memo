package com.airhome.airmemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.airhome.airmemo.db.MemoSQLite;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class WriteActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mMemoEt;
    private TextView mTimeTv;
    private TextView mHistoryTv;
    private Button mSaveBt;
    private Button mDropBt;
    private MemoSQLite mSQLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        initView();
        mSQLite = new MemoSQLite(this);
    }

    private void initView() {
        mMemoEt = (EditText) findViewById(R.id.memo_et);
        mTimeTv = (TextView) findViewById(R.id.left_tv);
        mHistoryTv = (TextView) findViewById(R.id.right_tv);
        mSaveBt = (Button) findViewById(R.id.save_bt);
        mDropBt = (Button) findViewById(R.id.drop_bt);
        mHistoryTv.setText(getResources().getString(R.string.go_history));
        mTimeTv.setText(CommonUtils.formatTime("MM.dd"));
        mHistoryTv.setOnClickListener(this);
        mSaveBt.setOnClickListener(this);
        mDropBt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.right_tv:
                Intent intent = new Intent(this, HistoryActivity.class);
                startActivity(intent);
                break;
            case R.id.drop_bt:
                mMemoEt.setText("");
                break;
            case R.id.save_bt:
                saveMemo();
                mMemoEt.setText("");
                break;
        }
    }

    private void saveMemo() {
        if (mMemoEt.getText().toString().trim().length() == 0) {
            CommonUtils.showShortToast(this, "Memo is empty!");
            return;
        }
        long result = mSQLite.insert(CommonUtils.formatTime("yy年MM月dd日  hh:mm"), mMemoEt.getText().toString());
        if (result > 0) {
            CommonUtils.showShortToast(this, "Save success!");
        } else {
            CommonUtils.showShortToast(this, "Sorry,save failed!");
        }
    }
}
