package com.zhuandian.androidstudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.vlayout.layout.FixLayoutHelper;
import com.alibaba.android.vlayout.layout.ScrollFixLayoutHelper;
import com.zhuandian.androidstudy.activity.ColumnLayoutActivity;
import com.zhuandian.androidstudy.activity.FixLayoutActivity;
import com.zhuandian.androidstudy.activity.FloatLayoutActivity;
import com.zhuandian.androidstudy.activity.GridLayoutActivity;
import com.zhuandian.androidstudy.activity.LinearLayoutActivity;
import com.zhuandian.androidstudy.activity.ScrollFixLayoutActivity;
import com.zhuandian.androidstudy.activity.SingleLayoutActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_linear).setOnClickListener(this);
        findViewById(R.id.btn_grid).setOnClickListener(this);
        findViewById(R.id.btn_fix).setOnClickListener(this);
        findViewById(R.id.btn_scroll_fix).setOnClickListener(this);
        findViewById(R.id.btn_float).setOnClickListener(this);
        findViewById(R.id.btn_column).setOnClickListener(this);
        findViewById(R.id.btn_single).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_linear:
                startActivity(new Intent(MainActivity.this, LinearLayoutActivity.class));
                break;
            case R.id.btn_grid:
                startActivity(new Intent(MainActivity.this, GridLayoutActivity.class));
                break;
            case R.id.btn_fix:
                startActivity(new Intent(MainActivity.this, FixLayoutActivity.class));
                break;
            case R.id.btn_scroll_fix:
                startActivity(new Intent(MainActivity.this, ScrollFixLayoutActivity.class));
                break;
            case R.id.btn_float:
                startActivity(new Intent(MainActivity.this, FloatLayoutActivity.class));
                break;
            case R.id.btn_column:
                startActivity(new Intent(MainActivity.this, ColumnLayoutActivity.class));
                break;
            case R.id.btn_single:
                startActivity(new Intent(MainActivity.this, SingleLayoutActivity.class));
                break;
        }
    }
}
