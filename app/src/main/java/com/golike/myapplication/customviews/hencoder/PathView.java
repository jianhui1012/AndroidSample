package com.golike.myapplication.customviews.hencoder;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

/**
 *
 * @author admin
 * @date 2018/1/24
 */

public class PathView extends View {

    private Paint mPaint;
    private Path mPath;

    public PathView(Context context) {
        super(context);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.parseColor("#88880000"));
        mPath = new Path();
        // 使用 path 对图形进行描述（这段描述代码不必看懂）
        mPath.addArc(200, 200, 400, 400, -225, 225);
        mPath.arcTo(400, 200, 600, 400, -180, 225, false);
        mPath.lineTo(400, 542);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath,mPaint);
    }
}
