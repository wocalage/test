package com.wocalage.test.ui.imageview.bigimage;

import android.content.Context;
import android.graphics.PointF;
import android.text.style.UpdateAppearance;
import android.view.MotionEvent;

/**
 * Created by jiaojian on 2017/8/23.
 */

public class MoveGestureDetector extends BaseGestureDetector {

    private PointF mCurrentPoint;
    private PointF mPrePoint;

    private PointF mDeltaPoint = new PointF();//为了减少创建内存
    private PointF mExtenalPointPoint = new PointF();//用于记录最终结果并返回
    private OnMoveGestureListener mListener;

    private MoveGestureDetector(Context context,OnMoveGestureListener listener) {
        super(context);
        mListener = listener;
    }

    @Override
    protected void handleInProgressEvent(MotionEvent event) {
        int actionCode = event.getAction() & MotionEvent.ACTION_MASK;
        switch (actionCode)
        {
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                mListener.onMoveEnd(this);
                resetState();
                break;
            case MotionEvent.ACTION_MOVE:
                updataStateByEvent(event);
                boolean update = mListener.onMove(this);
                if (update)
                {
                    mPreMotionEvent.recycle();
                    mPreMotionEvent = MotionEvent.obtain(event);
                }
                break;

        }
    }

    @Override
    protected void handleStartProgressEvent(MotionEvent event) {
        int actionCode = event.getAction() & MotionEvent.ACTION_MASK;
        switch (actionCode)
        {
            case MotionEvent.ACTION_DOWN:
                resetState();//防止没有接收到CANCEL or UP ,保险起见
                mPreMotionEvent = MotionEvent.obtain(event);
                updataStateByEvent(event);
                break;
            case MotionEvent.ACTION_MOVE:
                mGestureInProgress = mListener.onMoveBegin(this);
                break;
        }
    }

    @Override
    protected void updataStateByEvent(MotionEvent event) {
        final MotionEvent prev = mPreMotionEvent;

        mPrePoint = caculateFocalPointer(prev);
        mCurrentPoint = caculateFocalPointer(event);

        //Log.e("TAG", mPrePointer.toString() + " ,  " + mCurrentPointer);

        boolean mSkipThisMoveEvent = prev.getPointerCount() != event.getPointerCount();

        //Log.e("TAG", "mSkipThisMoveEvent = " + mSkipThisMoveEvent);
        mExtenalPointPoint.x = mSkipThisMoveEvent ? 0 : mCurrentPoint.x - mPrePoint.x;
        mExtenalPointPoint.y = mSkipThisMoveEvent ? 0 : mCurrentPoint.y - mPrePoint.y;
    }

    public float getMoveX()
    {
        return mExtenalPointPoint.x;

    }

    public float getMoveY()
    {
        return mExtenalPointPoint.y;
    }

    /**
     * 根据event计算多指中心点
     *
     * @param event
     * @return
     */
    private PointF caculateFocalPointer(MotionEvent event)
    {
        final int count = event.getPointerCount();
        float x = 0, y = 0;
        for (int i = 0; i < count; i++)
        {
            x += event.getX(i);
            y += event.getY(i);
        }

        x /= count;
        y /= count;

        return new PointF(x, y);
    }

    public interface OnMoveGestureListener{
        public boolean onMoveBegin(MoveGestureDetector detector);
        public boolean onMove(MoveGestureDetector detector);
        public void onMoveEnd(MoveGestureDetector detector);
    }

}
