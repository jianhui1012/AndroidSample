package com.golike.myapplication.customviews.hencoder;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by admin on 2018/2/6.
 */
public class PropetyAnimationView extends View {

    Paint paint, textPaint;

    float progress = 0.0f;

    public PropetyAnimationView(Context context) {
        super(context);
    }

    {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.parseColor("#8B1A1A"));
        paint.setStrokeWidth(20.0f);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(20.0f);
        textPaint.setColor(Color.parseColor("#8B1A1A"));
    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawArc(100, 100, 300, 300, 180, this.progress, false, paint);
        canvas.drawText(Math.round(100 * this.progress / 360) + "%", 180, 210, textPaint);
    }
}
