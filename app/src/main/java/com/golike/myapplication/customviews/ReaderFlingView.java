package com.golike.myapplication.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by admin on 2017/9/7.
 */

public class ReaderFlingView extends View {

    //第一段贝塞尔曲线
    private float[]  oneStartPoint=new float[2];
    private float[]  oneControlPoint=new float[2];
    //第一段直线 第二段直线
    private float[]  oneEndPoint=new float[2];
    private float[]  touchPonit=new float[2];
    private float[]  twoStartPoint=new float[2];
    //第二段贝塞尔曲线
    private float[]  twoControlPoint=new float[2];
    private float[]  twoEndPoint=new float[2];
    //第三段直线
    private float[]  finalPoint=new float[2];
    //闭合路径--最后一段曲线

    //三种画笔
    private Paint pagerPaint;
    private Paint inPaint;
    private Paint outsidePaint;

    private Path mPath = new Path();

    public ReaderFlingView(Context context) {
        super(context);
    }

    public ReaderFlingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        outsidePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        outsidePaint.setStyle(Paint.Style.STROKE);
        outsidePaint.setColor(Color.YELLOW);
        outsidePaint.setStrokeWidth(4);

        pagerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        pagerPaint.setStyle(Paint.Style.STROKE);
        pagerPaint.setColor(Color.RED);
        pagerPaint.setStrokeWidth(4);

        inPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        inPaint.setStyle(Paint.Style.STROKE);
        pagerPaint.setColor(Color.BLUE);
        inPaint.setTextSize(4);
    }

    public void calculateALLPoint(){
        finalPoint[0]=this.getWidth();
        finalPoint[1]=this.getHeight();

        float[] pPoint=new float[2];
        pPoint[0]=(finalPoint[0]+touchPonit[0])/2;
        pPoint[1]=(finalPoint[1]+touchPonit[1])/2;


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_DOWN:
                mPath.reset();
                touchPonit[0]=event.getX();
                touchPonit[1]=event.getY();
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }
}
