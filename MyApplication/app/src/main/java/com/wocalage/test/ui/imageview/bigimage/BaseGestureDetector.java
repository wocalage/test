package com.wocalage.test.ui.imageview.bigimage;

import android.content.Context;
import android.view.MotionEvent;

/**
 * Created by jiaojian on 2017/8/23.
 */

public abstract class BaseGestureDetector {
    protected boolean mGestureInProgress;
    protected MotionEvent mCurrentMotionEvent;
    protected MotionEvent mPreMotionEvent;
    protected Context mContext;

    public BaseGestureDetector(Context context){
        mContext = context;
    }

    public boolean onTouchEvent(MotionEvent e){
        if (!mGestureInProgress){
            handleStartProgressEvent(e);
        }else{
            handleInProgressEvent(e);
        }
        return true;
    }

    protected abstract void handleInProgressEvent(MotionEvent event);
    protected abstract void handleStartProgressEvent(MotionEvent event);
    protected abstract void updataStateByEvent(MotionEvent event);

    protected void resetState(){
        if (mPreMotionEvent !=null){
            mPreMotionEvent.recycle();
            mPreMotionEvent = null;
        }

        if (mCurrentMotionEvent != null){
            mCurrentMotionEvent.recycle();
            mCurrentMotionEvent = null;
        }
        mGestureInProgress = false;
    }

}
