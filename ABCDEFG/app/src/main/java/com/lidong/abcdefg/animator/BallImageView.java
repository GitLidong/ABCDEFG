package com.lidong.abcdefg.animator;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.Nullable;

public class BallImageView extends ImageView {

    private int mLeft = -1;

    public BallImageView(Context context) {
        super(context);
    }

    public BallImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BallImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (mLeft == -1) {
            mLeft = left;
        }
    }

    public void setFallingPos(Point pos) {
        layout(pos.x, pos.y, pos.x + getWidth(), pos.y + getHeight());
    }
}
