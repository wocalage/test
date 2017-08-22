package com.wocalage.test.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.wocalage.test.R;
import com.wocalage.test.ui.animation.AnimationActivity;
import com.wocalage.test.ui.customview.CustomViewActivity;
import com.wocalage.test.ui.dialog.DialogActivity;
import com.wocalage.test.ui.imageview.ImageViewActivity;
import com.wocalage.test.ui.listview.ListViewActivity;
import com.wocalage.test.ui.materialdesign.MaterialDesignActivity;
import com.wocalage.test.ui.surfaceview.SurfaceViewActivity;
import com.wocalage.test.ui.webview.WebViewActivity;

import java.util.List;

/**
 * Created by jiaojian on 2017/8/18.
 */

public class UIActivity extends Activity {

    private ListView mListView;
    private MyAdapter mAdapter;
    private String[] itemName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.model_ui);
        initData();
        initView();
    }

    private void initData(){
        itemName = getResources().getStringArray(R.array.model_ui_name);
    }

    private void initView(){
        mListView = (ListView)findViewById(R.id.model_ui_listview);
        mAdapter = new MyAdapter();
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                jumpToSubActivity(i);
            }
        });
    }

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return itemName.length;
        }

        @Override
        public Object getItem(int i) {
            return itemName[i];
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            MyViewHolder myViewHolder;
            if (view == null){
                myViewHolder = new MyViewHolder();
                view = View.inflate(UIActivity.this,R.layout.model_ui_item,viewGroup);
                myViewHolder.tvName = (TextView) view.findViewById(R.id.model_ui_item_name);
                view.setTag(myViewHolder);
            }else{
                myViewHolder = (MyViewHolder)view.getTag();
            }
            myViewHolder.tvName.setText(itemName[i]);
            return view;
        }

        class MyViewHolder{
            TextView tvName;
        }
    }

    private void jumpToSubActivity(final int position){
        Intent intent ;
        switch (position){
            case 0:
                intent = new Intent(this, AnimationActivity.class);
                break;
            case 1:
                intent = new Intent(this, CustomViewActivity.class);
                break;
            case 2:
                intent = new Intent(this, DialogActivity.class);
                break;
            case 3:
                intent = new Intent(this, FragmentActivity.class);
                break;
            case 4:
                intent = new Intent(this, ImageViewActivity.class);
                break;
            case 5:
                intent = new Intent(this, ListViewActivity.class);
                break;
            case 6:
                intent = new Intent(this, MaterialDesignActivity.class);
                break;
            case 7:
                intent = new Intent(this, SurfaceViewActivity.class);
                break;
            case 8:
                intent = new Intent(this, WebViewActivity.class);
                break;
            default:
                intent = new Intent();
        }
        if (intent != null){
            startActivity(intent);
        }
    }
}
