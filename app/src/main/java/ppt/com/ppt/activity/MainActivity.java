package ppt.com.ppt.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

import ppt.com.ppt.R;
import ppt.com.ppt.fragment.BaseFragment;
import ppt.com.ppt.utils.MyUtil;

public class MainActivity extends AppCompatActivity {

    private TabLayout tab_main;
    private ViewPager vp_main;
    private String[] mTabs;
    private Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTabs = MyUtil.getStringArray(R.array.tab_name);
        initView();
    }

    private void initView() {
        initToolBar();
        tab_main = (TabLayout) findViewById(R.id.tab_main);
        vp_main = (ViewPager) findViewById(R.id.vp_main);
        FragmentManager fm = getSupportFragmentManager();
        HomeViewPagerAdapter homeAdapter = new HomeViewPagerAdapter(fm);
        vp_main.setAdapter(homeAdapter);
        tab_main.setupWithViewPager(vp_main);
    }

    private void initToolBar() {
        toolBar = (Toolbar) findViewById(R.id.toobal);
        setSupportActionBar(toolBar);
    }

    class HomeViewPagerAdapter extends FragmentPagerAdapter {

        public HomeViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public Fragment getItem(int position) {
            return new BaseFragment();
        }

        public int getCount() {
            return mTabs.length;
        }

        public CharSequence getPageTitle(int position) {
            return mTabs[position];
        }
    }
}
