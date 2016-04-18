package com.example.niehongtao.demo04;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.nineoldandroids.view.ViewHelper;


public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private View header_logo;
    private float moveDistanceY = 25;// logo初始移动距离为10
    private float moveDistanceX = 25;// logo初始移动距离为10

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initAllViews();
    }

    private void initAllViews() {
        header_logo = findViewById(R.id.header_logo);
        findViewById(R.id.btn_moveUp).setOnClickListener(this);
        findViewById(R.id.btn_moveDown).setOnClickListener(this);
        findViewById(R.id.btn_moveLeft).setOnClickListener(this);
        findViewById(R.id.btn_moveRight).setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_moveDown :
                moveDistanceY += 20;
                ViewHelper.setTranslationY(header_logo, moveDistanceY);
                break;

            case R.id.btn_moveUp :
                moveDistanceY -= 20;
                ViewHelper.setTranslationY(header_logo, moveDistanceY);
                break;

            case R.id.btn_moveLeft :
                moveDistanceX -= 20;
                ViewHelper.setTranslationX(header_logo, moveDistanceX);
                break;

            case R.id.btn_moveRight :
                moveDistanceX += 20;
                ViewHelper.setTranslationX(header_logo, moveDistanceX);
                break;

            default :
                break;
        }
    }
}