package com.zhuandian.androidstudy.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.FixLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.zhuandian.androidstudy.R;
import com.zhuandian.androidstudy.adapter.LinearAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * 布局说明：布局里的Item 固定位置
 *
 * 固定在屏幕某个位置，且不可拖拽 & 不随页面滚动而滚动
 *
 */
public class FixLayoutActivity extends AppCompatActivity {

    private RecyclerView rvList;
    private DelegateAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fix_layout);
        rvList = findViewById(R.id.rv_list);
        VirtualLayoutManager virtualLayoutManager = new VirtualLayoutManager(this);
        rvList.setLayoutManager(virtualLayoutManager);

         adapter = new DelegateAdapter(virtualLayoutManager, true);
        initFixLayout();
        initLinearLayout();
    }

    private void initFixLayout() {

        // FixLayoutHelper 参数说明:
        // 参数1:设置吸边时的基准位置(alignType) - 有四个取值:TOP_LEFT(默认), TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT
        // 参数2:基准位置的偏移量x
        // 参数3:基准位置的偏移量y
        FixLayoutHelper fixLayoutHelper = new FixLayoutHelper(FixLayoutHelper.TOP_LEFT, 0, 0);

        // 公共属性
        fixLayoutHelper.setItemCount(1);// 设置布局里Item个数
        // 从设置Item数目的源码可以看出，一个FixLayoutHelper只能设置一个//       @Override//   public void setItemCount(int itemCount) {//            if (itemCount > 0) {//                super.setItemCount(1);//            } else {//                super.setItemCount(0);//            }//        }
        fixLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        fixLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        fixLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        fixLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比


//        // fixLayoutHelper特有属性
        fixLayoutHelper.setAlignType(FixLayoutHelper.TOP_LEFT);// 设置吸边时的基准位置(alignType)
        fixLayoutHelper.setX(30);// 设置基准位置的横向偏移量X
        fixLayoutHelper.setY(50);// 设置基准位置的纵向偏移量Y


        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            datas.add("Item  " + i);
        }
        adapter.addAdapter(new LinearAdapter(fixLayoutHelper, this, datas));
        rvList.setAdapter(adapter);

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
        adapter.addAdapter(new LinearAdapter(linearHelper, this, datas));
        rvList.setAdapter(adapter);
    }
}
