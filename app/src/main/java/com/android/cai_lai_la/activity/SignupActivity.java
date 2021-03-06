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

import androidx.appcompat.app.ActionBar;
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
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null){
            supportActionBar.hide();
        }
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
        progressDialog.setMessage("?????????......");
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
        user.setBalance((float) 0);
        user.setGid(1);

        // ????????????
        new Thread() {
            @Override
            public void run() {
                // ?????????????????????????????????????????????????????????, ???????????????
                user = UserController.add(user);
                Log.i(TAG, "run: ???????????????" + user);
                UserController.setLog(SignupActivity.this, true);
                UserController.saveUser(SignupActivity.this, user);
                runOnUiThread(()->{
                    startActivity(new Intent(SignupActivity.this, MainActivity.class));
                    onSignupSuccess();
                    progressDialog.dismiss();
                });
            }
        }.start();
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
            nameText.setError("??????3?????????");
            valid = false;
        } else {
            nameText.setError(null);
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailText.setError("????????????????????????");
            valid = false;
        } else {
            emailText.setError(null);
        }

        if (tel.isEmpty() || !Patterns.PHONE.matcher(tel).matches())
        {
            telText.setError("????????????????????????");
            valid = false;
        } else{
            telText.setError(null);
        }
        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            passwordText.setError("?????????4~10??????");
            valid = false;
        } else {
            passwordText.setError(null);
        }

        return valid;
    }
}