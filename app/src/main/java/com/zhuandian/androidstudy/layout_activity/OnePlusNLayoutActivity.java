package com.zhuandian.androidstudy.layout_activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.OnePlusNLayoutHelper;
import com.zhuandian.androidstudy.R;
import com.zhuandian.androidstudy.adapter.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 一拖N布局 （OnePlusNLayoutHelper）
 * 布局说明：将布局分为不同比例，最多是1拖4。
 * <p>
 * 样例效果
 * |-------|-------|
 * |      |   2    |
 * |   1  |-------|
 * |      |  3| 4 |
 * |------|-------|
 */
public class OnePlusNLayoutActivity extends AppCompatActivity {
    private RecyclerView rvList;
    private DelegateAdapter delegateAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_plus_nlayout);
        rvList = (RecyclerView) findViewById(R.id.rv_list);

        VirtualLayoutManager virtualLayoutManager = new VirtualLayoutManager(this);
        rvList.setLayoutManager(virtualLayoutManager);

        delegateAdapter = new DelegateAdapter(virtualLayoutManager, true);
        rvList.setAdapter(delegateAdapter);

        initOnePlusNLayout();

    }

    private void initOnePlusNLayout() {

        OnePlusNLayoutHelper onePlusNLayoutHelper = new OnePlusNLayoutHelper(5);        // 在构造函数里传入显示的Item数
        // 最多是1拖4,即5个

        // 公共属性
//        onePlusNLayoutHelper.setItemCount(3);// 设置布局里Item个数
        onePlusNLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        onePlusNLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        onePlusNLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        onePlusNLayoutHelper.setAspectRatio(3);// 设置布局内每行布局的宽与高的比

//        OnePlusNLayoutHelper only supports maximum 7 children now
        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            datas.add("Item  " + i);
        }

        delegateAdapter.addAdapter(new CommonAdapter(onePlusNLayoutHelper, this, datas));
    }
}
