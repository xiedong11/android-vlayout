package com.zhuandian.androidstudy.view_activity.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * desc :
 * author：xiedong
 * date：2019/5/6
 */
public class MyView extends View {
    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(measureWidth(widthMeasureSpec), heightMeasureSpec);
    }

    private int measureWidth(int widthMeasureSpec) {

        int result = 0;
        int spaceMode = MeasureSpec.getMode(widthMeasureSpec);
        int spaceSize = MeasureSpec.getSize(widthMeasureSpec);
        if (spaceMode == MeasureSpec.EXACTLY) {
            result = spaceSize;
        } else {
            result = 200;
            if (spaceMode == MeasureSpec.AT_MOST) {
                result = Math.max(result, spaceSize);
            }
        }
        return result;
    }
}

/**
 *
 *View测量的时候，默认是EXACTLY模式，也许你会感到纳闷，TextView，EditText这些控件，他怎么就支持wrap_content属性了，难道他重写OnMeasure方法，是的
 * ，他们都重写OnMeasure方法。这就是为什么我们在自定义控件的时候，如果要布局支持wrap_content属性，就需要重写onMeasure方法，来指定wrap_content为确切的大小
 *
 * View测量的时候，默认是EXACTLY模式，你不重写OnMeasure方法，即使设置wrap_content属性，他也是填充父容器
 *
 *
 */
