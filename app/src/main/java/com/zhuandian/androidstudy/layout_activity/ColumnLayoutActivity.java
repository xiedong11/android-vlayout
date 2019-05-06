package com.zhuandian.androidstudy.layout_activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.ColumnLayoutHelper;
import com.zhuandian.androidstudy.R;
import com.zhuandian.androidstudy.adapter.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 布局说明：该布局只设有一栏（该栏设置多个Item）
 * <p>
 * 可理解为只有一行的线性布局
 */
public class ColumnLayoutActivity extends AppCompatActivity {

    private RecyclerView rvList;
    private DelegateAdapter delegateAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_column_layout);
        rvList = (RecyclerView) findViewById(R.id.rv_list);

        VirtualLayoutManager virtualLayoutManager = new VirtualLayoutManager(this);
        rvList.setLayoutManager(virtualLayoutManager);

        delegateAdapter = new DelegateAdapter(virtualLayoutManager, true);
        rvList.setAdapter(delegateAdapter);

        initColumnLayout();

    }

    private void initColumnLayout() {
        ColumnLayoutHelper columnLayoutHelper = new ColumnLayoutHelper();

        // 公共属性
        columnLayoutHelper.setItemCount(3);// 设置布局里Item个数
        columnLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        columnLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        columnLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        columnLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比

        // columnLayoutHelper特有属性
        columnLayoutHelper.setWeights(new float[]{20, 60, 20});// 设置该行每个Item占该行总宽度的比例
        // 同上面Weigths属性讲解

        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            datas.add("Item  " + i);
        }

        delegateAdapter.addAdapter(new CommonAdapter(columnLayoutHelper, this, datas));
        rvList.setAdapter(delegateAdapter);
    }
}
