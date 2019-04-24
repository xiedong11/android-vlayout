package com.zhuandian.androidstudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.vlayout.layout.FixLayoutHelper;
import com.zhuandian.androidstudy.activity.FixLayoutActivity;
import com.zhuandian.androidstudy.activity.GridLayoutActivity;
import com.zhuandian.androidstudy.activity.LinearLayoutActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_linear).setOnClickListener(this);
        findViewById(R.id.btn_grid).setOnClickListener(this);
        findViewById(R.id.btn_fix).setOnClickListener(this);

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
        }
    }
}
