package com.android.cai_lai_la;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.cai_lai_la.activity.LoginActivity;
import com.android.cai_lai_la.activity.SearchActivity;
import com.android.cai_lai_la.adapter.ViewPagerAdapter;
import com.android.cai_lai_la.controller.UserController;
import com.android.cai_lai_la.model.User;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;

import java.util.ArrayList;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "activity_main";
    @BindView(R.id.view_pager)
    AHBottomNavigationViewPager viewPager;
    @BindView(R.id.button_navigation)
    AHBottomNavigation bottomNavigation;
    @BindView(R.id.home_head_icon)
    ImageView headImage;
    @BindView(R.id.home_title_address)
    TextView tvTitle;
    @BindView(R.id.home_title_search)
    LinearLayout searchLayout;
    @BindView(R.id.home_title_group)
    Group titleGroup;

    private ArrayList<AHBottomNavigationItem> bottomNavigationItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//         TODO: 直接跳转到登录页面
        Log.i(TAG, "onCreate: " + UserController.isLog(this));
        if (!UserController.isLog(this)) {
            Log.i(TAG, "53: 尚未登录，跳转到登录页面");
            startActivity(new Intent(this, LoginActivity.class));
        }
        Log.i(TAG, "56: 已经登录");
        initData();
        initView();
    }

    @Override
    protected void onResume() {
                // 设置欢迎语
        boolean isLog = UserController.isLog(this);
        if (isLog) {
            User user = UserController.loadUser(this);
            tvTitle.setText(String.format("欢迎%s到来!", user.getNickname()));
        } else {
            tvTitle.setText(R.string.home_welcome_default);
        }
        super.onResume();
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
        // 搜索框设置
        searchLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SearchActivity.class));
            }
        });
//        // 设置欢迎语
//        boolean isLog = UserController.isLog(this);
//        if (isLog) {
//            User user = UserController.loadUser(this);
//            tvTitle.setText(String.format("欢迎%s到来!", user.getNickname()));
//        } else {
//            tvTitle.setText(R.string.home_welcome_default);
//        }
        // 导航栏设置
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
                                                          // 设置是否显示最上方title
                                                          if (position == 0 || position == 1) {
                                                              titleGroup.setVisibility(Group.VISIBLE);
                                                          } else {
                                                              titleGroup.setVisibility(Group.GONE);
                                                          }
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
