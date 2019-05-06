package com.zhuandian.androidstudy.layout_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.zhuandian.androidstudy.R;

public class LayoutMainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_main);
        findViewById(R.id.btn_linear).setOnClickListener(this);
        findViewById(R.id.btn_grid).setOnClickListener(this);
        findViewById(R.id.btn_fix).setOnClickListener(this);
        findViewById(R.id.btn_scroll_fix).setOnClickListener(this);
        findViewById(R.id.btn_float).setOnClickListener(this);
        findViewById(R.id.btn_column).setOnClickListener(this);
        findViewById(R.id.btn_single).setOnClickListener(this);
        findViewById(R.id.btn_one_plus_n).setOnClickListener(this);
        findViewById(R.id.btn_stick_layout).setOnClickListener(this);
        findViewById(R.id.btn_staggered_grid).setOnClickListener(this);
        findViewById(R.id.btn_all).setOnClickListener(this);
    }

      @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_linear:
                startActivity(new Intent(LayoutMainActivity.this, LinearLayoutActivity.class));
                break;
            case R.id.btn_grid:
                startActivity(new Intent(LayoutMainActivity.this, GridLayoutActivity.class));
                break;
            case R.id.btn_fix:
                startActivity(new Intent(LayoutMainActivity.this, FixLayoutActivity.class));
                break;
            case R.id.btn_scroll_fix:
                startActivity(new Intent(LayoutMainActivity.this, ScrollFixLayoutActivity.class));
                break;
            case R.id.btn_float:
                startActivity(new Intent(LayoutMainActivity.this, FloatLayoutActivity.class));
                break;
            case R.id.btn_column:
                startActivity(new Intent(LayoutMainActivity.this, ColumnLayoutActivity.class));
                break;
            case R.id.btn_single:
                startActivity(new Intent(LayoutMainActivity.this, SingleLayoutActivity.class));
                break;
            case R.id.btn_one_plus_n:
                startActivity(new Intent(LayoutMainActivity.this, OnePlusNLayoutActivity.class));
                break;
            case R.id.btn_stick_layout:
                startActivity(new Intent(LayoutMainActivity.this, StickLayoutActivity.class));
                break;
            case R.id.btn_staggered_grid:
                startActivity(new Intent(LayoutMainActivity.this, StaggeredGridLayoutActivity.class));
                break;


        }
    }
}
