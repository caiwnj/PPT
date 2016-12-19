package ppt.com.ppt.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import ppt.com.ppt.R;
import ppt.com.ppt.global.Constants;
import ppt.com.ppt.utils.MyUtil;
import ppt.com.ppt.utils.SharedPreferencesUtil;

/**
 * Created by Caiwnj on 2016/12/17.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar tb_login;
    private EditText et_password;
    private AutoCompleteTextView et_account;
    private ProgressBar login_progress;
    private Button btn_login;
    private String account;
    private String passWorld;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tb_login = (Toolbar) findViewById(R.id.tb_login);
        login_progress = (ProgressBar) findViewById(R.id.login_progress);
        et_account = (AutoCompleteTextView) findViewById(R.id.et_account);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_login = (Button) findViewById(R.id.btn_login);
        tb_login.setTitle("登录");
        setSupportActionBar(tb_login);
        btn_login.setOnClickListener(this);
    }

    public void onClick(View v) {
        account = et_account.getText().toString().trim();
        passWorld = et_password.getText().toString();
        SharedPreferencesUtil.saveSharedPreferences(Constants.ACCOUNT, account);
        SharedPreferencesUtil.saveSharedPreferences(Constants.PASSWORLD, passWorld);
        login_progress.setVisibility(View.VISIBLE);
        MyUtil.getHandler().postDelayed(new Runnable() {
            public void run() {
                login_progress.setVisibility(View.GONE);
                Intent intent = new Intent(MyUtil.getContext(), LoginedActivity.class);
                startActivity(intent);
                finish();
                SharedPreferencesUtil.saveSharedPreferences(Constants.HAVELOGIN, true);
            }
        }, 1000);
    }
}
