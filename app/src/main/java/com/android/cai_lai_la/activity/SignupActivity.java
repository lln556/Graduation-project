package com.android.cai_lai_la.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.cai_lai_la.R;
import com.android.cai_lai_la.controller.UserController;
import com.android.cai_lai_la.model.User;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";

    @BindView(R.id.input_name)
    EditText nameText;
    @BindView(R.id.input_tel)
    EditText telText;
    @BindView(R.id.input_email)
    EditText emailText;
    @BindView(R.id.input_password)
    EditText passwordText;
    @BindView(R.id.btn_signup)
    Button signupButton;
    @BindView(R.id.link_login)
    TextView loginLink;

    User user;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        initDate();
        initView();
    }



    private void initView() {
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void initDate() {

    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        String name = nameText.getText().toString();
        String tel = telText.getText().toString();
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        user = new User();
        user.setNickname(name);
        user.setUsertel(tel);
        user.setEmail(email);
        user.setPassword(password);

        // 注册用户
        new Thread(){
            @Override
            public void run() {
                // 保存到服务器，保存到本地，设置登录状态
                user = UserController.add(user);
                UserController.setLog(SignupActivity.this, true);
                UserController.saveUser(SignupActivity.this, user);
            }
        };
    }


    public void onSignupSuccess() {
        signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = nameText.getText().toString();
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();
        String tel = telText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            nameText.setError("至少3个字符");
            valid = false;
        } else {
            nameText.setError(null);
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailText.setError("请输入正确的邮箱");
            valid = false;
        } else {
            emailText.setError(null);
        }

        if (tel.isEmpty() || Patterns.PHONE.matcher(tel).matches())
        {

            telText.setError("请输入正确的电话");
            valid = false;
        } else{
            telText.setError(null);
        }
        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            passwordText.setError("长度在4~10之间");
            valid = false;
        } else {
            passwordText.setError(null);
        }

        return valid;
    }
}