package com.android.cai_lai_la.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.cai_lai_la.MainActivity;
import com.android.cai_lai_la.R;
import com.android.cai_lai_la.controller.UserController;
import com.android.cai_lai_la.model.User;
import com.android.cai_lai_la.widget.ItemGroup;
import com.android.cai_lai_la.widget.TitleLayout;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;


public class PersonalInfoActivity extends AppCompatActivity {
    @BindView(R.id.bt_logout)
    Button logoutButton;
    @BindView(R.id.tl_title)
    TitleLayout toolbar;
    @BindView(R.id.ig_id)
    ItemGroup id;
    @BindView(R.id.ig_name)
    ItemGroup name;
    @BindView(R.id.ig_gender)
    ItemGroup gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_info);
        ButterKnife.bind(this);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
        initData();
        initView();
    }


    private void initView(){
        // 退出登录按钮
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 退出登录并跳转到主页面
                UserController.logout(PersonalInfoActivity.this);
                Intent intent = new Intent(PersonalInfoActivity.this, MainActivity.class);
                intent.putExtra("position", 0);
                startActivity(intent);
            }
        });

        // 保存按钮
    }

    private void initData(){
        User user = (User)getIntent().getSerializableExtra("User");
        id.setContentEdt(user.getUid() + "");
        name.setContentEdt(user.getNickname());
        gender.setContentEdt("男");
    }
}
