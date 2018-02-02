package com.golike.myapplication.customviews.hencoder;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 基本图形练习
 *
 * @author golike
 */
public class BasicShape extends View {

    private Paint mPaint;
    private Paint mSecondPaint;

    public BasicShape(Context context) {
        super(context);
        init(null, 0);
    }

    public BasicShape(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public BasicShape(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.BLUE);

        mSecondPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mSecondPaint.setStrokeCap(Paint.Cap.ROUND);
        mSecondPaint.setStrokeWidth(20);
        mSecondPaint.setColor(Color.DKGRAY);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //半透明的红色遮罩
        canvas.drawColor(Color.parseColor("#88880000"));
        //小人
        canvas.drawCircle(200, 200, 50, mPaint);
        canvas.drawRect(150, 250, 250, 350, mPaint);
        canvas.drawArc(100,270,300,350,-200,90,true, mPaint);
        canvas.drawArc(100,270,300,350,-90,110,true, mPaint);
        canvas.drawLine(300,310,400,200,mPaint);
        canvas.drawPoint(400,200, mSecondPaint);
        //椭圆
        canvas.drawOval(100,450,300,550,mPaint);
        //工口
        canvas.drawLines(new float[]{400,450,500,450,450,450,450,550,400,550,500,550, 520,450,620,450,520,450,520,550,620,450,620,550,520,550,620,550},mPaint);
        //圆角矩形
        canvas.drawRoundRect(100,580,300,630,20,20,mPaint);
    }


}
