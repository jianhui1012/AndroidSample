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

public class BeSaiErView extends View {

    //贝塞尔控制点与数据点
    private Point  beSaiStartPoint=new Point();
    private Point  beSaiEndPoint=new Point();
    private Point  beSaiControlPoint=new Point();

    //两种画笔
    private Paint controlPaint;
    private Paint mPaintAuxiliary;
    private Paint dataPaint;

    private Path mPath = new Path();

    public BeSaiErView(Context context) {
        super(context);
    }

    public BeSaiErView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        controlPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        controlPaint.setStyle(Paint.Style.STROKE);
        controlPaint.setColor(Color.BLUE);
        controlPaint.setStrokeWidth(4);

        mPaintAuxiliary = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintAuxiliary.setStyle(Paint.Style.STROKE);
        mPaintAuxiliary.setStrokeWidth(2);

        dataPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        dataPaint.setStyle(Paint.Style.STROKE);
        dataPaint.setTextSize(20);


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        beSaiStartPoint.set(w/4,h/2-200);
        beSaiEndPoint.set(3*w/4,h/2-200);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        mPath.moveTo(beSaiStartPoint.x,beSaiStartPoint.y);
        // 辅助点
        canvas.drawPoint(beSaiControlPoint.x, beSaiControlPoint.y, controlPaint);
        canvas.drawText("控制点",beSaiControlPoint.x, beSaiControlPoint.y, dataPaint);
        canvas.drawText("起始点",beSaiStartPoint.x, beSaiStartPoint.y, dataPaint);
        canvas.drawText("结束点",beSaiEndPoint.x, beSaiEndPoint.y, dataPaint);
        //辅助线
        canvas.drawLine(beSaiStartPoint.x,beSaiStartPoint.y,beSaiControlPoint.x,beSaiControlPoint.y,mPaintAuxiliary);
        canvas.drawLine(beSaiEndPoint.x,beSaiEndPoint.y,beSaiControlPoint.x,beSaiControlPoint.y,mPaintAuxiliary);
        mPath.quadTo(beSaiControlPoint.x,beSaiControlPoint.y,beSaiEndPoint.x,beSaiEndPoint.y);

        canvas.drawPath(mPath,controlPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE:
                beSaiControlPoint.set((int)event.getX(),(int)event.getY());
                invalidate();
        }
        return true;
    }
}
