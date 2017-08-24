package com.wocalage.test.ui.imageview.bigimage;

import android.content.Context;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by jiaojian on 2017/8/23.
 */

public class BigImageView extends View{

    private BitmapRegionDecoder mBitmapRegionDecoder;
    private int mWidth,mHeight;//image的宽与高
    private volatile Rect mRect = new Rect();//绘制的区域


    public BigImageView(Context context) {
        super(context);
        init();
    }

    public BigImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BigImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){

    }
}
