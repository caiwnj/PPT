package ppt.com.ppt.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.ArrayList;

import ppt.com.ppt.R;
import ppt.com.ppt.fragment.BaseFragment;
import ppt.com.ppt.fragment.FragmentFactory;
import ppt.com.ppt.utils.MyUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String[] mTabs;
    private Toolbar toolBar;
    private FrameLayout fl_content;
    private FragmentManager supportFm;
    private LinearLayout ll_collection;
    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawer;
    private LinearLayout ll_comment;
    private BaseFragment baseFragment;
    private String mCurrentFragment = MyUtil.getString(R.string.YOUMA);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTabs = MyUtil.getStringArray(R.array.tab_name);
        initView();
    }

    private void initView() {
        ll_collection = (LinearLayout) findViewById(R.id.ll_collection);
        ll_comment = (LinearLayout) findViewById(R.id.ll_comment);
        fl_content = (FrameLayout) findViewById(R.id.fl_content);
        initToolBarAndDrawble();
        supportFm = getSupportFragmentManager();
        supportFm.beginTransaction().add(R.id.fl_content, FragmentFactory.getFragment(mCurrentFragment), mCurrentFragment).commit();
        ll_collection.setOnClickListener(this);
        ll_comment.setOnClickListener(this);
    }

    private void initToolBarAndDrawble() {//初始化ToolBar
        toolBar = (Toolbar) findViewById(R.id.toobal);
        setSupportActionBar(toolBar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolBar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }

    public void onClick(View v) {//点击侧边栏的条目，切换fragment
        switch (v.getId()) {
            case R.id.ll_collection:
                baseFragment = new BaseFragment();
                supportFm.beginTransaction().replace(R.id.fl_content, baseFragment, MyUtil.getString(R.string.YOUMA)).commit();
                drawer.closeDrawers();
                toolBar.setTitle("收藏");
                break;
            case R.id.ll_comment:
                supportFm.beginTransaction().hide(baseFragment).commit();
                drawer.closeDrawers();
                toolBar.setTitle("评论");
                break;
            default:
                break;
        }

    }

    public void ChangeContent(String fragmentName) {
        if (mCurrentFragment == fragmentName) {
            return;
        } else {
            Fragment fragment = supportFm.findFragmentByTag(mCurrentFragment);
            Fragment fragment2Replace = supportFm.findFragmentByTag(fragmentName);
            if (fragment2Replace == null) {
                supportFm.beginTransaction().hide(fragment).add(FragmentFactory.getFragment(fragmentName), fragmentName).commit();
            } else {
                supportFm.beginTransaction().hide(fragment).show(fragment2Replace).commit();
            }
            mCurrentFragment = fragmentName;
        }
    }
}
