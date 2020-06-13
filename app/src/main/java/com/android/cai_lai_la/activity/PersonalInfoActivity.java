package com.android.cai_lai_la.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.cai_lai_la.MainActivity;
import com.android.cai_lai_la.R;
import com.android.cai_lai_la.controller.UserController;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;


public class PersonalInfoActivity extends AppCompatActivity {
    @BindView(R.id.bt_logout)
    Button logoutButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_info);
        ButterKnife.bind(this);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
        initDate();
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

    }
    private void initDate(){

    }
}
