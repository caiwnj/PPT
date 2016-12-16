package ppt.com.ppt.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashMap;

import ppt.com.ppt.R;
import ppt.com.ppt.fragment.BaseFragment;
import ppt.com.ppt.fragment.FragmentFactory;
import ppt.com.ppt.fragment.YoumaFragment;
import ppt.com.ppt.utils.MyUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private Toolbar toolBar;
    private LinearLayout ll_collection;
    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawer;
    private LinearLayout ll_comment;
    private ListView lv_nav;
    private int[] iconList = {R.mipmap.youma, R.mipmap.youji, R.mipmap.message, R.mipmap.search, R.mipmap.setting};
    private String[] itemnameList = MyUtil.getStringArray(R.array.item_name);
    private int mCurrentSelecterItem = 0;
    private NavAdapter navAdapter;
    private String mCurrentFragmentName = FragmentFactory.YOUMA;
    private FragmentManager supportFragmentManager;
    private String[] mitemnameList = MyUtil.getStringArray(R.array.item_stringname);
    private RelativeLayout fl_login;
    private LinearLayout ll_read;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        supportFragmentManager = getSupportFragmentManager();
        initView();


    }

    private void initView() {
        initToolBar();
        initDrawable();
        setContentFragment(FragmentFactory.YOUMA);
    }

    private void initDrawable() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ll_collection = (LinearLayout) findViewById(R.id.ll_collection);
        ll_comment = (LinearLayout) findViewById(R.id.ll_comment);
        ll_read = (LinearLayout) findViewById(R.id.ll_read);
        lv_nav = (ListView) findViewById(R.id.lv_nav);
        fl_login = (RelativeLayout) findViewById(R.id.rl_login);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolBar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navAdapter = new NavAdapter();
        lv_nav.setAdapter(navAdapter);
        ll_collection.setOnClickListener(this);
        fl_login.setOnClickListener(this);
        ll_read.setOnClickListener(this);
        ll_comment.setOnClickListener(this);
        lv_nav.setOnItemClickListener(this);
    }

    private void initToolBar() {//初始化ToolBar
        toolBar = (Toolbar) findViewById(R.id.toobal);
        setSupportActionBar(toolBar);

    }

    public void setContentFragment(String fragmentName) {
        BaseFragment fragmentToReplace = (BaseFragment) supportFragmentManager.findFragmentByTag(fragmentName);
        BaseFragment fragmentOld = (BaseFragment) supportFragmentManager.findFragmentByTag(mCurrentFragmentName);
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        if (fragmentName == mCurrentFragmentName) {//是同一个条目
            if (fragmentOld == null && fragmentToReplace == null) {//这是第一次
                fragmentToReplace = FragmentFactory.getFragment(fragmentName);
                if (fragmentToReplace != null) {
                    fragmentTransaction.add(R.id.fl_content, fragmentToReplace, fragmentName);
                    Log.d("第一次", "第一次");
                }

            } else {
                Log.d("后次", "同一个");
                return;
            }
        } else {//不同條目
            if (fragmentToReplace == null) { //第一次点击
                fragmentToReplace = FragmentFactory.getFragment(fragmentName);
                Log.d("第一次", "不同个");
                if (fragmentOld != null) {
                    fragmentTransaction.hide(fragmentOld).
                            add(R.id.fl_content, fragmentToReplace, fragmentName);

                }

            } else {//以后点击
                fragmentTransaction.hide(fragmentOld).show(fragmentToReplace);
                Log.d("后次", "不同个");
            }

        }
        fragmentToReplace.loadData();
        fragmentTransaction.commit();
        mCurrentFragmentName = fragmentName;
        Log.d("mCurrentFragmentName", mCurrentFragmentName + "");
    }

    public void onClick(View v) {//侧边小item的点击事件
        switch (v.getId()) {
            case R.id.ll_collection:
                goToActivity(CollectionActivity.class);
                break;
            case R.id.ll_comment:

                break;
            case R.id.ll_read:

                break;
            case R.id.rl_login:
                goToActivity(LoginActivity.class);
                break;
            default:
                break;
        }

    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {//侧边栏ListItem点击事件
        mCurrentSelecterItem = position;
        navAdapter.notifyDataSetChanged();
        setContentFragment(mitemnameList[position]);
        drawer.closeDrawers();
    }

    class NavAdapter extends BaseAdapter {

        public int getCount() {
            return itemnameList.length;
        }

        public String getItem(int position) {
            return itemnameList[position];
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View view = MyUtil.inFlate(R.layout.itme_nav);
            ImageView iv_itemicon = (ImageView) view.findViewById(R.id.iv_itemicon);
            TextView tv_itemtext = (TextView) view.findViewById(R.id.tv_itemtext);
            RelativeLayout rl_item = (RelativeLayout) view.findViewById(R.id.rl_item);
            tv_itemtext.setText(getItem(position));
            iv_itemicon.setImageResource(iconList[position]);
            if (mCurrentSelecterItem == position) {
                rl_item.setBackgroundColor(MyUtil.getColor(R.color.colorItemBackground));
                toolBar.setTitle(itemnameList[position]);
            }
            return view;
        }
    }

    public void goToActivity(Class c) {
        Intent intent = new Intent(MyUtil.getContext(), c);
        startActivity(intent);
    }
}
