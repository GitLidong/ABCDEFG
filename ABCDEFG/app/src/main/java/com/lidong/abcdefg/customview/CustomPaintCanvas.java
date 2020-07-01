package com.lidong.abcdefg.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.lidong.abcdefg.utils.LogUtils;

public class CustomPaintCanvas extends View {

    private Paint mPaint1;
    private Paint mPaintPoint;
    private Paint mPaintPath;
    private Paint mPaintCircle;

    private Path mPath;
    private RectF mRect;

    private int mWidth;
    private int mHeight;

    public CustomPaintCanvas(Context context) {
        super(context);
        init();
    }

    public CustomPaintCanvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomPaintCanvas(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint1 = new Paint();
        mPaint1.setColor(Color.BLACK);
        mPaint1.setStrokeWidth(1);
        mPaint1.setStyle(Paint.Style.STROKE);

        mPaintPoint = new Paint();
        mPaintPoint.setColor(Color.RED);
        mPaintPoint.setStrokeWidth(10);
        mPaintPoint.setStyle(Paint.Style.STROKE);

        mPaintCircle = new Paint();
        mPaintCircle.setColor(Color.RED);
        mPaintCircle.setStrokeWidth(5);
        mPaintCircle.setAntiAlias(true);
        mPaintCircle.setStyle(Paint.Style.FILL);

        mPaintPath = new Paint();
        mPaintPath.setColor(Color.RED);
        mPaintPath.setStrokeWidth(1);
        mPaintPath.setAntiAlias(true);
        mPaintPath.setStyle(Paint.Style.STROKE);

        mPath = new Path();
        mRect = new RectF(100, 10, 200, 100);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        LogUtils.printInfo("width " + mWidth + " height " + mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBg(canvas);


        canvas.drawCircle(200, 300, 100, mPaintCircle);

        mPath.moveTo(10, 10);
        mPath.arcTo(mRect, 0, 90);
        canvas.drawRect(mRect, mPaint1);
        canvas.drawPath(mPath, mPaintPath);
    }

    private void drawBg(Canvas canvas) {
        int gapSize = 100;
        for (int i = 0; i <= 8; i++) {
            canvas.drawLine(0, gapSize * i, mWidth, gapSize * i, mPaint1);
            canvas.drawLine(gapSize * i, 0, gapSize * i, mHeight, mPaint1);
        }

    }
}
