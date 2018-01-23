package com.golike.myapplication.customviews.hencoder;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.golike.myapplication.R;

/**
 * 基本图形练习
 *
 * @author golike
 */
public class BasicShape extends View {

    private Paint paint;

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
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLUE);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(200, 200, 50, paint);
        canvas.drawRect(150, 250, 250, 350, paint);
        canvas.drawArc(100,270,300,350,-200,90,true,paint);
        canvas.drawArc(100,270,300,350,-90,110,true,paint);
    }


}
