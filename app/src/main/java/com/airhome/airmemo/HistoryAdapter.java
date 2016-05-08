package com.airhome.airmemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.airhome.airmemo.db.MemoSQLite;
import com.airhome.airmemo.model.Memo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by airhome on 2016/5/8.
 */
public class HistoryAdapter extends BaseAdapter {
    private List<Memo> mMemoData = new ArrayList<>();
    private Context mContext;
    private MemoSQLite mSQLite;

    public HistoryAdapter(Context context) {
        mContext = context;
        mSQLite = new MemoSQLite(context);
    }

    public void setData(List<Memo> data) {
        mMemoData = data;
    }

    @Override
    public int getCount() {
        return mMemoData.size();
    }

    @Override
    public Object getItem(int position) {
        return mMemoData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        ItemClickListener listener;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_memo_history, parent, false);
            viewHolder = new ViewHolder();
            listener = new ItemClickListener(position);
            viewHolder.timeTv = (TextView) convertView.findViewById(R.id.item_time);
            viewHolder.dropTv = (TextView) convertView.findViewById(R.id.item_drop);
            viewHolder.dropTv.setOnClickListener(listener);
            viewHolder.memoContentTv = (TextView) convertView.findViewById(R.id.item_memo_content);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Memo memo = mMemoData.get(position);
        if (memo != null) {
            viewHolder.timeTv.setText(memo.getTime());
            viewHolder.memoContentTv.setText(memo.getContent());
        }
        return convertView;
    }

    private static class ViewHolder {
        TextView timeTv;
        TextView dropTv;
        TextView memoContentTv;
    }

    private class ItemClickListener implements View.OnClickListener {
        private int position;

        public ItemClickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.item_drop:
                    mSQLite.delete(mMemoData.get(position).getId());
                    mMemoData.remove(position);
                    notifyDataSetChanged();
                    break;
            }
        }
    }

}
