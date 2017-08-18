package com.wocalage.test.main;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.wocalage.test.R;
import com.wocalage.test.aidl.AIDLActivity;
import com.wocalage.test.cache.CacheActivity;
import com.wocalage.test.dynamicload.DynamicLoadActivity;
import com.wocalage.test.framework.FrameworkActivity;
import com.wocalage.test.jni.JNIActivity;
import com.wocalage.test.net.NetActivity;
import com.wocalage.test.ui.UIActivity;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView mListView;
    private String[] mListName;
    private MainAdapter mMainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initView() {
        mListView = (RecyclerView) findViewById(R.id.main_recyclerview);
        mListView.setLayoutManager(new LinearLayoutManager(this));
        mMainAdapter = new MainAdapter();
        mListView.setAdapter(mMainAdapter);
        mListView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
    }

    private void initData() {
        mListName = getResources().getStringArray(R.array.app_model);
    }

    class MainAdapter extends RecyclerView.Adapter<MainViewHolder> {

        @Override
        public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MainViewHolder holder = new MainViewHolder(View.inflate(MainActivity.this,R.layout.main_item_view,parent));
            return  holder;
        }

        @Override
        public void onBindViewHolder(MainViewHolder holder, final int position) {
            holder.tvName.setText(mListName[position]);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    jumpToOtherActivity(position);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mListName.length;
        }
    }

    class MainViewHolder extends RecyclerView.ViewHolder {
        public MainViewHolder(View view) {
            super(view);
            tvName = (TextView) view.findViewById(R.id.main_item_view_name);
        }

        TextView tvName;
    }

    private void jumpToOtherActivity(int index){
        Intent intent;
        switch (index){
            case 0:
                intent = new Intent(this, UIActivity.class);
                break;
            case 1:
                intent = new Intent(this, NetActivity.class);
                break;
            case 2:
                intent = new Intent(this, DynamicLoadActivity.class);
                break;
            case 3:
                intent = new Intent(this, CacheActivity.class);
                break;
            case 4:
                intent = new Intent(this, FrameworkActivity.class);
                break;
            case 5:
                intent = new Intent(this, AIDLActivity.class);
                break;
            case 6:
                intent = new Intent(this, JNIActivity.class);
                break;
            default:
                intent = new Intent();
        }
        startActivity(intent);
    }
}
