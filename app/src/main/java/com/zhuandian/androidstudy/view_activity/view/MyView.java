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
