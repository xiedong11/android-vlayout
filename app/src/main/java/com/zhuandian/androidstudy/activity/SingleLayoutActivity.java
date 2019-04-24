package com.zhuandian.androidstudy.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.zhuandian.androidstudy.R;
import com.zhuandian.androidstudy.adapter.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 布局说明：布局只有一栏，该栏只有一个Item
 */
public class SingleLayoutActivity extends AppCompatActivity {

    private RecyclerView rvList;
    private DelegateAdapter delegateAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_layout);

        rvList = (RecyclerView) findViewById(R.id.rv_list);
        VirtualLayoutManager virtualLayoutManager = new VirtualLayoutManager(this);
        rvList.setLayoutManager(virtualLayoutManager);
        delegateAdapter = new DelegateAdapter(virtualLayoutManager, true);
        initSingleLayout();

        rvList.setAdapter(delegateAdapter);


    }

    private void initSingleLayout() {


        SingleLayoutHelper singleLayoutHelper = new SingleLayoutHelper();

        // 公共属性
        singleLayoutHelper.setItemCount(3);// 设置布局里Item个数
        singleLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        singleLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        singleLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        singleLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比



        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            datas.add("Item");
        }

        delegateAdapter.addAdapter(new CommonAdapter(singleLayoutHelper, this, datas));
    }
}
