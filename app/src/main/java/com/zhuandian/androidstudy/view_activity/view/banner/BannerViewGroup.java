package com.zhuandian.androidstudy.view_activity.view.banner;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * desc :我们在自定义viewGroup中，必须实现的方法有：测量-》布局-》绘制
 * author：xiedong
 * date：2019/5/22
 */
public class BannerViewGroup extends ViewGroup {
    private int childrenCount;
    private int childrenWidth;
    private int childernHeight;

    public BannerViewGroup(Context context) {
        this(context, null);
    }

    public BannerViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BannerViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        /**
         * 由于我们要实现的是一个ViewGroup的容器，那么我们就应该需要知道该容器中的所有子视图
         * 我们要想测量我们的ViewGroup的宽度跟高度，那么我们就必须先去测量子视图的宽度跟高度之和，
         * 才能计算得出我们ViewGroup的宽度跟高度是多少
         */

        // 1.求出子视图的个数
        childrenCount = getChildCount();
        if (0 == childrenCount) {
            setMeasuredDimension(0, 0);
        } else {
            //2 .测量子视图的宽度跟高度
            measureChildren(widthMeasureSpec, heightMeasureSpec);
            //此时我们以第一个子视图为基准，那么ViewGroup的高度就是第一个子视图的高度，宽度就是我们第一个
            //子视图的宽度 * 子视图的个数
            View view = getChildAt(0);

            //3.根据子视图的宽度跟高度，计算得出该ViewGroup的宽度跟高度
            childernHeight = view.getMeasuredHeight();
            childrenWidth = view.getMeasuredWidth();
            setMeasuredDimension(childrenWidth * childrenCount, childernHeight);   //到此已经确定该ViewGroup的宽高为固定值，下面的onLayout过程只能在此范围内布局
        }
    }

    /**
     * @param changed 当ViewGroup布局位置发生改变时候返回 true  否则为 false
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            int leftMargin = 0;
            for (int i = 0; i < childrenCount; i++) {
                View view = getChildAt(i);
                view.layout(leftMargin, 0, leftMargin + childrenWidth, childernHeight);
                leftMargin += childrenWidth;
            }
        }
    }






    /**
     * 事件传递： 事件传递过程中，我们需要复写父类的onInterceptTouchEvent方法，来确定当前事件是否被该ViewGroup拦截处理
     * 如果返回true，则当前ViewGoup拦截事件，事件将不会继续传递，会交由当前ViewGroup的onTouch方法处理
     * 如果返回false 则 当前ViewGroup不对事件做处理，事件继续传递
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        return super.onInterceptTouchEvent(ev);
        return true; //消费事件，在当前ViewGroup处理
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_CANCEL:
                break;

        }
//        return super.onTouchEvent(event);
        return true;  //返回 true 目的是告诉 当前VIewGroup的父view 我们已经消费了该事件
    }
}
