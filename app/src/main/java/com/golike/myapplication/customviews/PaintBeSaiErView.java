package com.golike.myapplication.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

/**
 * Created by admin on 2017/9/7.
 */

public class PaintBeSaiErView extends View {

    private  Paint controlPaint;

    private Path mCurrentPath;

    private float startPointX;
    private float startPointY;
    private float offset = ViewConfiguration.get(getContext()).getScaledTouchSlop();

    public PaintBeSaiErView(Context context) {
        super(context);
    }

    public PaintBeSaiErView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mCurrentPath = new Path();
        controlPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        controlPaint.setStyle(Paint.Style.STROKE);
        controlPaint.setStrokeWidth(5);
        controlPaint.setColor(Color.RED);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mCurrentPath,controlPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mCurrentPath.reset();
                float x = event.getX();
                float y = event.getY();
                startPointX = x;
                startPointY = y;
                mCurrentPath.moveTo(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                float curX= event.getX();
                float curY= event.getY();
                float preX= startPointX;
                float preY= startPointY;
                if(Math.abs(preX-curX)>=offset||Math.abs(preX-curY)>=offset) {
                    mCurrentPath.quadTo((curX + preX) / 2, (curY + preY) / 2, curX, curY);
                    //mCurrentPath.lineTo(curX, curY);
                    startPointX = curX;
                    startPointY = curY;
                }
                invalidate();
                break;
        }
        return  true;
    }
}
