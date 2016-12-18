package ppt.com.ppt.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.Serializable;

import ppt.com.ppt.R;
import ppt.com.ppt.bean.YoumaJavaBean;

/**
 * Created by Caiwnj on 2016/12/18.
 */

public class ItemActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar tb_item;
    private WebView wv_item;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        Intent intent = getIntent();
        YoumaJavaBean youmaJavaBean = (YoumaJavaBean) intent.getSerializableExtra("item");
        String title = youmaJavaBean.getTitle();
        tb_item = (Toolbar) findViewById(R.id.tb_item);
        tb_item.setTitle(title);
        setSupportActionBar(tb_item);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tb_item.setNavigationOnClickListener(this);
        wv_item = (WebView) findViewById(R.id.wv_item);
        wv_item.loadUrl("http:www.baidu.com");
        wv_item.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

    }

    public void onClick(View v) {
        onBackPressed();
    }

    public void onBackPressed() {
        super.onBackPressed();
    }
}
