package com.android.cai_lai_la.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.cai_lai_la.MainActivity;
import com.android.cai_lai_la.R;
import com.android.cai_lai_la.controller.UserController;
import com.android.cai_lai_la.model.User;

import java.util.List;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;


public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    @BindView(R.id.input_tel)
    EditText telText;  // 邮箱
    @BindView(R.id.input_password)
    EditText passwordText;  // 密码
    @BindView(R.id.btn_login)
    Button loginButton;  // 登录按钮
    @BindView(R.id.link_signup)
    TextView signupLink;  // 注册连接
    User currentUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initDate();
        initView();
    }
    private void initView(){
        // 隐藏工具栏
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
        // 登录按钮
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        // 登录的连接
        signupLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });

    }
    private void initDate(){
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("验证中，请稍侯...");
        progressDialog.show();

        String tell = telText.getText().toString();
        String password = passwordText.getText().toString();
        new Thread(){
            @Override
            public void run() {
                List<User> userList = UserController.list();
                for (User u :
                        userList) {
                    if (u.getUsertel().equals(tell) && u.getPassword().equals(password)){
                        currentUser = u;
                        break;
                    }
                }
                LoginActivity.this.runOnUiThread(()->{
                    if (currentUser != null){
                        onLoginSuccess();
                    } else {
                        onLoginFailed();
                    }
                        progressDialog.dismiss();
                });
            }
        }.start();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {
                // TODO: 成功登录
                Log.i(TAG, "onActivityResult: 成功登录");
                UserController.setLog(this, true);
                UserController.saveUser(this, currentUser);
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        }
    }

    public void onLoginSuccess() {
        loginButton.setEnabled(true);
        Toast.makeText(getBaseContext(), "登录成功", Toast.LENGTH_LONG).show();
        UserController.setLog(this, true);
        UserController.saveUser(this, currentUser);
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "登录失败", Toast.LENGTH_LONG).show();
        loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = telText.getText().toString();
        String password = passwordText.getText().toString();

        if (email.isEmpty() || !Patterns.PHONE.matcher(email).matches()) {
            telText.setError("请输入正确的手机号");
            valid = false;
        } else {
            telText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            passwordText.setError("请输入4~10位密码");
            valid = false;
        } else {
            passwordText.setError(null);
        }

        return valid;
    }
}