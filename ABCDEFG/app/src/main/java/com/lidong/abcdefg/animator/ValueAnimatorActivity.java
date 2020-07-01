package com.lidong.abcdefg.animator;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.lidong.abcdefg.R;

public class ValueAnimatorActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mImageBall;
    private Point mCurrentPoint;

    private BallImageView mImageBall2;

    private View[] mMenuItemView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_animator);

        mImageBall = findViewById(R.id.img_rea_ball);

        mImageBall2 = findViewById(R.id.img_rea_ball2);

        mMenuItemView = new View[]{
                findViewById(R.id.menu_item1),
                findViewById(R.id.menu_item2),
                findViewById(R.id.menu_item3),
                findViewById(R.id.menu_item4),
                findViewById(R.id.menu_item5)
        };

        for (View view : mMenuItemView) {
            view.setOnClickListener(this);
        }
    }

    private boolean mOpenMenu = false;

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_begin_animator) {
//        startBallFall();
//        startBall2Fall();
            if (!mOpenMenu) {
                openMenu();
                mOpenMenu = true;
            } else {
                closeMenu();
                mOpenMenu = false;
            }
        } else if (view.getId() == R.id.menu_item1) {
            Toast.makeText(this, "Clicked menu1", Toast.LENGTH_SHORT).show();
            closeMenu();
        } else if (view.getId() == R.id.menu_item2) {
            Toast.makeText(this, "Clicked menu2", Toast.LENGTH_SHORT).show();
            closeMenu();
        } else if (view.getId() == R.id.menu_item3) {
            Toast.makeText(this, "Clicked menu3", Toast.LENGTH_SHORT).show();
            closeMenu();
        } else if (view.getId() == R.id.menu_item4) {
            Toast.makeText(this, "Clicked menu4", Toast.LENGTH_SHORT).show();
            closeMenu();
        } else if (view.getId() == R.id.menu_item5) {
            Toast.makeText(this, "Clicked menu5", Toast.LENGTH_SHORT).show();
            closeMenu();
        }

    }

    private void startBall2Fall() {
        ObjectAnimator animator = ObjectAnimator.ofObject(mImageBall2,
                "fallingPos", new BallFallDownEvaluator(),
                new Point(0, 0), new Point(500, 500));
        animator.setDuration(2000);
        animator.start();
    }

    private void startBallFall() {
        ValueAnimator animator = ValueAnimator.ofObject(
                new BallFallDownEvaluator(),
                new Point(0, 0),
                new Point(500, 500));
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mCurrentPoint = (Point) valueAnimator.getAnimatedValue();
                mImageBall.layout(mCurrentPoint.x, mCurrentPoint.y,
                        mCurrentPoint.x + mImageBall.getWidth(),
                        mCurrentPoint.y + mImageBall.getHeight());
            }
        });
        animator.setDuration(2000);
        animator.start();
    }

    private class BallFallDownEvaluator implements TypeEvaluator<Point> {

        Point point = new Point();

        @Override
        public Point evaluate(float v, Point start, Point end) {
            point.x = (int) (start.x + (end.x - start.x) * v);
            if (v * 2 < 1) {
                point.y = (int) (start.y + (end.y - start.y) * 2 * v);
            } else {
                point.y = end.y;
            }
            return point;
        }
    }


    private void openMenu() {

        for (int i = 0; i < mMenuItemView.length; i++) {
            doAnimatorOpen(mMenuItemView[i], i, 500);
        }

    }

    private void doAnimatorOpen(View view, int index, int radius) {
        view.setVisibility(View.VISIBLE);

        //根据度数得到弧度值
        double degree = Math.toRadians(90) / (mMenuItemView.length - 1) * index;

        //sin cos中的参数并非角度，而是角度对应的弧度值
        int translationX = -(int) (radius * Math.sin(degree));
        int translationY = -(int) (radius * Math.cos(degree));

        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(view, "translationX", 0, translationX),
                ObjectAnimator.ofFloat(view, "translationY", 0, translationY),
                ObjectAnimator.ofFloat(view, "scaleX", 0f, 1f),
                ObjectAnimator.ofFloat(view, "scaleY", 0f, 1f),
                ObjectAnimator.ofFloat(view, "alpha", 0f, 1f)
        );
        set.setDuration(500).start();
    }

    private void closeMenu() {
        for (int i = 0; i < mMenuItemView.length; i++) {
            doAnimatorClose(mMenuItemView[i], i, 500);
        }
    }

    private void doAnimatorClose(View view, int index, int radius) {

        //根据度数得到弧度值
        double degree = Math.toRadians(90) / (mMenuItemView.length - 1) * index;

        //sin cos中的参数并非角度，而是角度对应的弧度值
        int translationX = -(int) (radius * Math.sin(degree));
        int translationY = -(int) (radius * Math.cos(degree));

        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(view, "translationX", translationX, 0),
                ObjectAnimator.ofFloat(view, "translationY", translationY, 0),
                ObjectAnimator.ofFloat(view, "scaleX", 1f, 0f),
                ObjectAnimator.ofFloat(view, "scaleY", 1f, 0f),
                ObjectAnimator.ofFloat(view, "alpha", 1f, 0f)
        );
        set.setDuration(5000).start();
    }

}
