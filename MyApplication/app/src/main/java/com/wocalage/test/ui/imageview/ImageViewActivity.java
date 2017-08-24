package com.wocalage.test.ui.imageview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.wocalage.test.R;

/**
 * Created by jiaojian on 2017/8/22.
 */

public class ImageViewActivity extends Activity{

    private Button mOpenBigPicture;
    private ImageView mBigPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initEvent();
    }

    private void initView(){
        mOpenBigPicture = (Button)findViewById(R.id.bt_open_big_picture);
    }

    private void initEvent(){
        mOpenBigPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBigPicture = (ImageView)findViewById(R.id.iv_big_picture);
            }
        });
    }
}
