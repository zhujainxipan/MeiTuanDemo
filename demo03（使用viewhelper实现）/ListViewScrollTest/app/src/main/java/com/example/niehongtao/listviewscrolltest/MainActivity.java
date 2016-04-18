package com.example.niehongtao.listviewscrolltest;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.nineoldandroids.view.ViewHelper;
import java.util.ArrayList;

public class MainActivity extends ActionBarActivity implements AbsListView.OnScrollListener {
    ListView listView;
    TextView textview;
    private int mTvOriginalBottom;
    private View headerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listview);
        textview = (TextView) findViewById(R.id.textview);

        //写在这里的话
        //1.要用帧布局
        //2.属性动画虽然让视图移动了，其实并没有移动，但是视觉上是移动了，并且点击事件也移动了。不过还是假的
        //3.加一个header，把listview挤下来
        //4.其实加入headerview还是有点小问题，可能是因为listview被复用的原因吧(不是因为listview滚动高度的测量的问题)

        headerView = getLayoutInflater().inflate(R.layout.head, null);
        listView.addHeaderView(headerView);

        //获取textview的bottom
        initViewObserver();

        ArrayList<String> stringArrayList = new ArrayList<String>();
        for (int i = 0; i < 10000; i++) {
            stringArrayList.add(i + "");
        }

        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stringArrayList));
        listView.setOnScrollListener(this);
    }


    /**
     * 取到textview的底部
     */
    public void  initViewObserver() {
        textview.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mTvOriginalBottom = textview.getBottom();
            }
        });
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    /**
     * listview的滚动监听方法
     * @param view
     * @param firstVisibleItem
     * @param visibleItemCount
     * @param totalItemCount
     */
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        int scrollY = getScrollY(listView);
        int temp = Math.max(-scrollY, -1 * mTvOriginalBottom + 40);
        ViewHelper.setTranslationY(textview, temp);
    }

    /**
     * 这个可以测量出listview有header情况下，listview的高度
     * @param view
     * @return
     */
    public int getScrollY(AbsListView view) {
        View c = view.getChildAt(0);
        if (c == null) {
            return 0;
        }
        int firstVisiblePosition = view.getFirstVisiblePosition();
        int top = c.getTop();
        int headerHeight = 0;
        if (firstVisiblePosition >= 1) {
            headerHeight = headerView.getHeight();
        }
        return -top + firstVisiblePosition * c.getHeight() + headerHeight;
    }

}