package com.airhome.airmemo;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.airhome.airmemo.db.MemoSQLite;
import com.airhome.airmemo.model.Memo;

import java.util.List;

public class HistoryActivity extends AppCompatActivity implements ListView.OnItemClickListener {
    private TextView mBackTv;
    private TextView mRightTv;
    private ListView mMemoLv;
    private HistoryAdapter mAdapter;
    private MemoSQLite mSQLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        initView();
        new LoadMemoTask().execute();
        mMemoLv.setOnItemClickListener(this);
    }

    private void initView() {
        mBackTv = (TextView) findViewById(R.id.left_tv);
        mRightTv = (TextView) findViewById(R.id.right_tv);
        mMemoLv = (ListView) findViewById(R.id.memo_list);
        mBackTv.setText(getResources().getString(R.string.back));
        mRightTv.setText("");
        mAdapter = new HistoryAdapter(this);
        mSQLite = new MemoSQLite(this);
        mBackTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private class LoadMemoTask extends AsyncTask<Void, Void, List<Memo>> {

        @Override
        protected List<Memo> doInBackground(Void... params) {
            return mSQLite.getAllMemos();
        }

        @Override
        protected void onPostExecute(List<Memo> memos) {
            super.onPostExecute(memos);
            mAdapter.setData(memos);
            mMemoLv.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent();
    }
}
