package com.zhuandian.androidstudy.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.StaggeredGridLayoutHelper;
import com.zhuandian.androidstudy.R;
import com.zhuandian.androidstudy.adapter.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 布局说明：以网格的形式进行布局。与网格布局类似，区别在于：
 * 网格布局每栏的Item高度是相等的；
 * 瀑布流布局每栏的Item高度是可以不相等的。
 */
public class StaggeredGridLayoutActivity extends AppCompatActivity {

    private RecyclerView rvList;
    private DelegateAdapter delegateAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staggered_grid_layout);

        rvList = (RecyclerView) findViewById(R.id.rv_list);
        VirtualLayoutManager virtualLayoutManager = new VirtualLayoutManager(this);
        rvList.setLayoutManager(virtualLayoutManager);
        delegateAdapter = new DelegateAdapter(virtualLayoutManager, true);
        rvList.setAdapter(delegateAdapter);

        initStaggeredGridLayout();
    }

    private void initStaggeredGridLayout() {
        StaggeredGridLayoutHelper staggeredGridLayoutHelper = new StaggeredGridLayoutHelper(3);
// 公有属性
        staggeredGridLayoutHelper.setItemCount(20);// 设置布局里Item个数
        staggeredGridLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        staggeredGridLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        staggeredGridLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        staggeredGridLayoutHelper.setAspectRatio(3);// 设置设置布局内每行布局的宽与高的比

        // 特有属性
        staggeredGridLayoutHelper.setLane(3);// 设置控制瀑布流每行的Item数
        staggeredGridLayoutHelper.setHGap(20);// 设置子元素之间的水平间距
        staggeredGridLayoutHelper.setVGap(15);// 设置子元素之间的垂直间距


        List<String> datas = new ArrayList<>();
        datas.add("哈哈的回复回结款了看见看见接口路径看了第三方刻录机的分散结款了的房间爱上刻录机的发生刻录机的发生 的舒服接口路径看了电风扇东方斯卡拉刻录机的分散接口 困了就睡大都费劲上看了复");
        for (int i = 0; i < 20; i++) {
            datas.add("Item " + i);
        }

        delegateAdapter.addAdapter(new CommonAdapter(staggeredGridLayoutHelper, this, datas));
    }
}
