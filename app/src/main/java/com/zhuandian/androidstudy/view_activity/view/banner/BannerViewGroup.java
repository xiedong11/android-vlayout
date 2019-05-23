package com.zhuandian.androidstudy.view_activity.view.banner;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

import java.util.Timer;
import java.util.TimerTask;

/**
 * desc :我们在自定义viewGroup中，必须实现的方法有：测量-》布局-》绘制
 * author：xiedong
 * date：2019/5/22
 */
public class BannerViewGroup extends ViewGroup {
    private int childrenCount;
    private int childrenWidth;
    private int childernHeight;
    private int oldX;
    private int currentIndex;  //当前子view索引
    private Scroller scroller;

    //自动轮播
    private boolean isAutoPlay = true; //默认开启
    private static final int SEND_START_BANNER_MESSAGE = 1;
    private Timer timer = new Timer();
    private TimerTask timerTask;
    private Handler autoHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SEND_START_BANNER_MESSAGE:
                    if (++currentIndex > childrenCount - 1) {
                        currentIndex = 0;
                    }
                    scrollTo(currentIndex * childrenWidth, 0);
                    break;
            }
        }
    };

    private void startAutoPlay() {
        isAutoPlay = true;
    }

    private void stopAutoPlay() {
        isAutoPlay = false;
    }

    public BannerViewGroup(Context context) {
        this(context, null);
    }

    public BannerViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BannerViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initConfig();
    }

    private void initConfig() {
        scroller = new Scroller(getContext());

        timerTask = new TimerTask() {
            @Override
            public void run() {
                if (isAutoPlay) {
                    autoHandler.sendEmptyMessage(SEND_START_BANNER_MESSAGE);
                }
            }
        };
        timer.schedule(timerTask, 500, 2000);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(), 0);
            invalidate();
        }
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


    /**
     * 借助ScrollTo、ScrollBy实现手动轮播
     * <p>
     * 一、我们在滑动屏幕图片的过程中，其实就是我们自定义ViewGroup的子视图移动的过程。所以我们只需要知道滑动之前的横坐标跟滑动之后的横坐标
     * 此时我们就可以通过前后坐标的差值得到此过程的移动的距离， 我们再利用ScrollBy方法实现图片的滑动，所以此时，需要我们求出的两个值为
     * 移动之前、移动之后的横坐标
     * <p>
     * 二、在我们 第一次 按下的那一瞬间，此时的移动之前和移动之后的值是相等的，也就是此时按下的那一瞬间的哪一个点的横坐标的值
     * <p>
     * 三、我们在不断的滑动过程中，是会不断的调用ACTION_MOVE方法，那么此时我们就应该讲 移动之前的值 和移动之后的值保存，以便我们能计算得出滑动的距离
     * <p>
     * 四、在我们 抬起的那一瞬间 ，此次手指拖动完成，我们此时需要计算出我们此时将要 滑动到哪张图片上
     * <p>
     * （我们当前ViewGroup的滑动位置 + 每一张图片的宽度 /2 ）/每一张图片的宽度值
     * <p>
     * 然后再利用ScrollTo方法，滑动到该图片的位置上
     */
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                oldX = (int) event.getX();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                int currentX = (int) event.getX();
//                int distance = currentX - oldX;
//                scrollBy(-distance, 0); //只在X轴上发生偏移，Y轴不变
//                oldX = currentX; //重新赋值oldX
//                break;
//            case MotionEvent.ACTION_UP:
//                int scrollX = getScrollX();
//                currentIndex = (scrollX + childrenWidth / 2) / childrenWidth;
//                if (currentIndex < 0) {
//                    currentIndex = 0;
//                } else if (currentIndex > childrenCount - 1) {
//                    currentIndex = childrenCount - 1;
//                }
//                scrollTo(currentIndex * childrenWidth, 0);
//                break;
//            case MotionEvent.ACTION_CANCEL:
//                break;
//
//        }
////        return super.onTouchEvent(event);
//        return true;  //返回 true 目的是告诉 当前VIewGroup的父view 我们已经消费了该事件
//    }


    /**
     * 借助Scroller完成手动滑动
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (!scroller.isFinished()) {
                    scroller.abortAnimation();
                }
                oldX = (int) event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                int currentX = (int) event.getX();
                int distance = currentX - oldX;
                scrollBy(-distance, 0); //只在X轴上发生偏移，Y轴不变
                oldX = currentX; //重新赋值oldX
                break;
            case MotionEvent.ACTION_UP:
                int scrollX = getScrollX();
                currentIndex = (scrollX + childrenWidth / 2) / childrenWidth;
                if (currentIndex < 0) {
                    currentIndex = 0;
                } else if (currentIndex > childrenCount - 1) {
                    currentIndex = childrenCount - 1;
                }

                int dx = currentIndex * childrenWidth - scrollX;
                scroller.startScroll(scrollX, 0, dx, 0);
                postInvalidate();


//                scrollTo(currentIndex * childrenWidth, 0);
                break;
            case MotionEvent.ACTION_CANCEL:
                break;

        }
//        return super.onTouchEvent(event);
        return true;  //返回 true 目的是告诉 当前VIewGroup的父view 我们已经消费了该事件
    }
}
