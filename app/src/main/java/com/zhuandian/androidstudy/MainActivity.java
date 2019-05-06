package com.zhuandian.androidstudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


import com.zhuandian.androidstudy.layout_activity.ColumnLayoutActivity;
import com.zhuandian.androidstudy.layout_activity.FixLayoutActivity;
import com.zhuandian.androidstudy.layout_activity.FloatLayoutActivity;
import com.zhuandian.androidstudy.layout_activity.GridLayoutActivity;
import com.zhuandian.androidstudy.layout_activity.LayoutMainActivity;
import com.zhuandian.androidstudy.layout_activity.LinearLayoutActivity;
import com.zhuandian.androidstudy.layout_activity.OnePlusNLayoutActivity;
import com.zhuandian.androidstudy.layout_activity.ScrollFixLayoutActivity;
import com.zhuandian.androidstudy.layout_activity.SingleLayoutActivity;
import com.zhuandian.androidstudy.layout_activity.StaggeredGridLayoutActivity;
import com.zhuandian.androidstudy.layout_activity.StickLayoutActivity;
import com.zhuandian.androidstudy.view.ViewMainActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_linear).setOnClickListener(this);
        findViewById(R.id.btn_view).setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_linear:
                startActivity(new Intent(MainActivity.this, LayoutMainActivity.class));
                break;
            case R.id.btn_view:
                startActivity(new Intent(MainActivity.this, ViewMainActivity.class));
                break;
        }
    }
}
