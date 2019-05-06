package com.zhuandian.androidstudy.layout_activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.FixLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.ScrollFixLayoutHelper;
import com.zhuandian.androidstudy.R;
import com.zhuandian.androidstudy.adapter.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 布局说明：布局里的Item 固定位置
 * <p>
 * 固定在屏幕某个位置，且不可拖拽 & 不随页面滚动而滚动（继承自固定布局（FixLayoutHelper））
 * 唯一不同的是，可以自由设置该Item什么时候显示（到顶部显示 / 到底部显示）
 * 需求场景：到页面底部显示”一键到顶部“的按钮功能
 */
public class ScrollFixLayoutActivity extends AppCompatActivity {
    private RecyclerView rvList;
    private DelegateAdapter adapter;
    private List<DelegateAdapter.Adapter> adapters = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_fix_layout);
        rvList = findViewById(R.id.rv_list);
        VirtualLayoutManager virtualLayoutManager = new VirtualLayoutManager(this);
        rvList.setLayoutManager(virtualLayoutManager);

        adapter = new DelegateAdapter(virtualLayoutManager, true);
        rvList.setAdapter(adapter);




        initScrollFixLayout();
        initLinearLayout();


        adapter.addAdapters(adapters);
    }

    private void initScrollFixLayout() {

        // 参数1:设置吸边时的基准位置(alignType) - 有四个取值:TOP_LEFT(默认), TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT
        // 参数2:基准位置的偏移量x
        // 参数3:基准位置的偏移量y
        ScrollFixLayoutHelper scrollFixLayoutHelper = new ScrollFixLayoutHelper(FixLayoutHelper.TOP_LEFT, 0, 0);
        // 公共属性
        scrollFixLayoutHelper.setItemCount(1);// 设置布局里Item个数
        // 从设置Item数目的源码可以看出，一个FixLayoutHelper只能设置一个//        @Override//        public void setItemCount(int itemCount) {//            if (itemCount > 0) {//                super.setItemCount(1);//            } else {//                super.setItemCount(0);//            }//        }
        scrollFixLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        scrollFixLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        scrollFixLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        scrollFixLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比

        // fixLayoutHelper特有属性
        scrollFixLayoutHelper.setAlignType(FixLayoutHelper.TOP_LEFT);// 设置吸边时的基准位置(alignType)
        scrollFixLayoutHelper.setX(0);// 设置基准位置的横向偏移量X
        scrollFixLayoutHelper.setY(0);// 设置基准位置的纵向偏移量Y
        //上面三个参数同构造方法设置一样

        scrollFixLayoutHelper.setShowType(ScrollFixLayoutHelper.SHOW_ALWAYS);// 设置Item的显示模式   //TODO 设置效果无效
//        共有三种显示模式
//        SHOW_ALWAYS：永远显示(即效果同固定布局)
//        SHOW_ON_ENTER：默认不显示视图，当页面滚动到该视图位置时才显示；
//        SHOW_ON_LEAVE：默认不显示视图，当页面滚出该视图位置时才显示


        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            datas.add("Item  " + i);
        }
        adapters.add(new CommonAdapter(scrollFixLayoutHelper, this, datas));
//        rvList.setAdapter(adapter);
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
        for (int i = 0; i < 20; i++) {
            datas.add("Item  " + i);
        }
        adapters.add(new CommonAdapter(linearHelper, this, datas));
//        rvList.setAdapter(adapter);
    }
}
