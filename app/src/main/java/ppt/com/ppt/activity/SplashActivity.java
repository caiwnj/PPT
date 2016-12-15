package ppt.com.ppt.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import ppt.com.ppt.R;

/**
 * Created by Caiwnj on 2016/12/15.
 */

public class SplashActivity extends Activity {

    private ImageView iv_splash;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        iv_splash = (ImageView) findViewById(R.id.iv_splash);
        // 渐变动画 AlphaAnimation
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(2000);
        alphaAnimation.setFillAfter(true);
        iv_splash.setAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationStart(Animation animation) {

            }

            public void onAnimationEnd(Animation animation) {
                enterHome();
            }

            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void enterHome() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
