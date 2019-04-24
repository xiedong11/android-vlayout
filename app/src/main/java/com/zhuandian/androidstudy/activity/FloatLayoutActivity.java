package com.zhuandian.androidstudy.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.FixLayoutHelper;
import com.alibaba.android.vlayout.layout.FloatLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.zhuandian.androidstudy.R;
import com.zhuandian.androidstudy.adapter.LinearAdapter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FloatLayoutActivity extends AppCompatActivity {
    private RecyclerView rvList;
    private DelegateAdapter adapter;
    List<DelegateAdapter.Adapter> adapters = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_layout);


        rvList = findViewById(R.id.rv_list);
        VirtualLayoutManager virtualLayoutManager = new VirtualLayoutManager(this);
        rvList.setLayoutManager(virtualLayoutManager);

        adapter = new DelegateAdapter(virtualLayoutManager, true);
        rvList.setAdapter(adapter);


        initLinearLayout();
        initFloatLayout();

        adapter.addAdapters(adapters);
    }

    private void initFloatLayout() {
        FloatLayoutHelper floatLayoutHelper = new FloatLayoutHelper();
        // 从设置Item数目的源码可以看出，一个FixLayoutHelper只能设置一个//        @Override//        public void setItemCount(int itemCount) {//            if (itemCount > 0) {//                super.setItemCount(1);//            } else {//                super.setItemCount(0);//            }//        }
        floatLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        floatLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        floatLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        floatLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比

        // floatLayoutHelper特有属性
        floatLayoutHelper.setDefaultLocation(300, 300);// 设置布局里Item的初始位置
        floatLayoutHelper.setAlignType(FixLayoutHelper.TOP_LEFT);

        List<String> datas = new ArrayList<>();

        datas.add("浮动布局View");
        adapters.add(new LinearAdapter(new FloatLayoutHelper(), this, datas));

    }

    private void initLinearLayout() {

        //Linear 布局
        LinearLayoutHelper linearHelper = new LinearLayoutHelper(10, 12);   //构造方法中可以直接指定dividerHeight,itemCount,也可以单独使用
        linearHelper.setItemCount(12); //具体数目以adapter中的数据集合数据为准
        linearHelper.setPadding(0, 10, 10, 10);
        linearHelper.setMargin(0, 10, 10, 10);
        linearHelper.setBgColor(Color.GRAY);// 设置背景颜色
        linearHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比

        // linearLayoutHelper特有属性
        linearHelper.setDividerHeight(10); // 设置每行Item的距离


        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            datas.add("Item  " + i);
        }
        adapters.add(new LinearAdapter(linearHelper, this, datas));



    }
}
