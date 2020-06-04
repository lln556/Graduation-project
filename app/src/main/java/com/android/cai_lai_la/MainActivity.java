package com.android.cai_lai_la;

import android.graphics.Color;
import android.os.Bundle;

import com.android.cai_lai_la.adapter.ViewPagerAdapter;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;

import java.util.ArrayList;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.view_pager)
    AHBottomNavigationViewPager viewPager;
    @BindView(R.id.button_navigation)
    AHBottomNavigation bottomNavigation;
//    @BindView(R.id.toolbar)
//    Toolbar toolbar;

    private ArrayList<AHBottomNavigationItem> bottomNavigationItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    /**
     * 初始化视图
     */
    private void initView() {
        // 隐藏工具条设置
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.hide();
        }
        AHBottomNavigationItem item1 = new AHBottomNavigationItem("首页", R.drawable.home, R.color.bottom_navigation_active);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("分类", R.drawable.category, R.color.bottom_navigation_active);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("购物车", R.drawable.cart, R.color.bottom_navigation_active);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem("我的", R.drawable.me, R.color.bottom_navigation_active);
        bottomNavigationItems.add(item1);
        bottomNavigationItems.add(item2);
        bottomNavigationItems.add(item3);
        bottomNavigationItems.add(item4);

        bottomNavigation.addItems(bottomNavigationItems);
        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#FEFEFE"));  // 背景色
        bottomNavigation.setAccentColor(Color.parseColor("#1DA1F2"));  // 选中时颜色
        bottomNavigation.setForceTint(true);  // 防错措施
        // 图标下文字状态
//        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.SHOW_WHEN_ACTIVE);
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);  // 总是开启
//        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_HIDE);

        // 换页事件处理
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
                                                      @Override
                                                      public boolean onTabSelected(int position, boolean wasSelected) {
                                                          viewPager.setCurrentItem(position, false);
                                                          return true;
                                                      }
                                                  }
        );

        viewPager.setOffscreenPageLimit(4);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));

    }

    /**
     * 初始化数据
     */
    private void initData() {

    }
}
