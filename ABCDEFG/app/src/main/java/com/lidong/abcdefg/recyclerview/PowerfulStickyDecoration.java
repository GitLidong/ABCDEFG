package com.lidong.abcdefg.recyclerview;

import android.graphics.Canvas;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lidong.abcdefg.recyclerview.listener.PowerGroupListener;

public class PowerfulStickyDecoration extends BaseDecoration {

    private PowerGroupListener mGroupListener;

    public PowerfulStickyDecoration(PowerGroupListener groupListener) {
        this.mGroupListener = groupListener;
    }

    @Override
    String getGroupName(int realPosition) {
        if (mGroupListener != null) {
            return mGroupListener.getGroupName(realPosition);
        } else {
            return null;
        }
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        int itemCount = state.getItemCount();
        int childCount = parent.getChildCount();
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        for (int i = 0; i < childCount; i++) {
            View childView = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(childView);
            int realPosition = getRealPosition(position);
            if (isFirstInGroup(realPosition)) {
                int viewBottom = childView.getBottom();
                
            }
        }

    }

    public static class Builder {

        PowerfulStickyDecoration mDecoration;

        private Builder(PowerGroupListener listener) {
            mDecoration = new PowerfulStickyDecoration(listener);
        }

        public static Builder init(PowerGroupListener listener) {
            return new Builder(listener);
        }

        public Builder setGroupHeight(int groutHeight) {
            mDecoration.mGroupHeight = groutHeight;
            return this;
        }

        public Builder setHeaderCount(int headerCount) {
            if (headerCount >= 0) {
                mDecoration.mHeaderCount = headerCount;
            }
            return this;
        }

        public PowerfulStickyDecoration build() {
            return mDecoration;
        }

    }


}
