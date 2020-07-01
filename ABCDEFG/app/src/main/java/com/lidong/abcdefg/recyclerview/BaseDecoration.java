package com.lidong.abcdefg.recyclerview;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseDecoration extends RecyclerView.ItemDecoration {

    int mGroupHeight = 120;

    //头部数量、默认为0
    int mHeaderCount = 0;

    protected int getRealPosition(int position) {
        return position - mHeaderCount;
    }

    protected boolean isHeader(int realPosition) {
        return realPosition < 0;
    }

    protected boolean isFirstInGroup(int realPosition) {
        if (realPosition < 0) {
            return false;
        } else if (realPosition == 0) {
            return true;
        }

        String preGroupName = getGroupName(realPosition - 1);
        String currentGroupName = getGroupName(realPosition);
        if (currentGroupName == null) {
            return false;
        }
        return !TextUtils.equals(preGroupName, currentGroupName);
    }

    abstract String getGroupName(int realPosition);

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int position = parent.getChildAdapterPosition(view);
        int realPosition = getRealPosition(position);
        RecyclerView.LayoutManager manager = parent.getLayoutManager();
        if (manager instanceof GridLayoutManager) {

        } else {
            //其他的默认为线性布局
            if (!isHeader(realPosition)) {
                if (isFirstInGroup(realPosition)) {
                    outRect.top = mGroupHeight;
                }
            }
        }


    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }
}
