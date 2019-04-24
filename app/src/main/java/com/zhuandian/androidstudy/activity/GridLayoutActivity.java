package com.zhuandian.androidstudy.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.zhuandian.androidstudy.R;
import com.zhuandian.androidstudy.adapter.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

public class GridLayoutActivity extends AppCompatActivity {

    private RecyclerView rvList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_layout);
        rvList = findViewById(R.id.rv_list);
        initGridLayout();
    }

    private void initGridLayout() {
        VirtualLayoutManager virtualLayoutManager = new VirtualLayoutManager(this);
        rvList.setLayoutManager(virtualLayoutManager);

        DelegateAdapter adapter = new DelegateAdapter(virtualLayoutManager, true);

        //GridLayout
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(3);
        // 公共属性
        gridLayoutHelper.setItemCount(6);// 设置布局里Item个数
        gridLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        gridLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        gridLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        gridLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比


        // gridLayoutHelper特有属性（下面会详细说明）
        gridLayoutHelper.setWeights(new float[]{40, 30, 30});//设置每行中 每个网格宽度 占 每行总宽度 的比例
        gridLayoutHelper.setVGap(20);// 控制子元素之间的垂直间距
        gridLayoutHelper.setHGap(20);// 控制子元素之间的水平间距
        gridLayoutHelper.setAutoExpand(false);//是否自动填充空白区域
        gridLayoutHelper.setSpanCount(3);// 设置每行多少个网格
        // 通过自定义SpanSizeLookup来控制某个Item的占网格个数
        gridLayoutHelper.setSpanSizeLookup(new GridLayoutHelper.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position > 7) {
                    return 3;                    // 第7个位置后,每个Item占3个网格
                } else {
                    return 1;                    // 第7个位置前,每个Item占2个网格
                }
            }
        });


        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            datas.add("Item  " + i);
        }

        adapter.addAdapter(new CommonAdapter(gridLayoutHelper, this, datas));
        rvList.setAdapter(adapter);


    }
}

/**
 * gridLayoutHelper.setWeights(new float[]{40, 30, 30});//设置每行中 每个网格宽度 占 每行总宽度 的比例
 * 作用：设置每行中每个网格宽度占每行总宽度的比例
 * 默认情况下，每行中每个网格中的宽度相等
 * weights属性是一个float数组，每一项代表当个网格占每行总宽度的百分比；
 * 总和是100，否则布局会超出容器宽度；
 * 如果布局中有4列，那么weights的长度也应该是4；长度大于4，多出的部分不参与宽度计算；\
 * 如果小于4，不足的部分默认平分剩余的空间。
 */
