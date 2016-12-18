package ppt.com.ppt.view;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import ppt.com.ppt.R;
import ppt.com.ppt.bean.HeadData;
import ppt.com.ppt.bean.YoumaJavaBean;
import ppt.com.ppt.holder.ViewHolder;
import ppt.com.ppt.utils.MyUtil;

/**
 * Created by Caiwnj on 2016/12/17.
 */

public class HeadViewDelegate implements ItemViewDelegate<YoumaJavaBean>, ViewPager.OnPageChangeListener, View.OnTouchListener {

    private ViewPager vp_head;
    private ArrayList<HeadData> headDatas;
    private HeadPagerAdapter headPagerAdapter;
    private ImageView iv_indicator;
    private ImageView iv_indicator_1;
    private ImageView iv_indicator_2;
    private LinearLayout ll_indicators;
    private int distance;
    private int mCurrentItem = 0;
    private Handler mHandler;

    public int getItemViewLayoutId() {
        return R.layout.view_head_youma;
    }

    public boolean isForViewType(YoumaJavaBean item, int position) {
        if (position == 0) {
            return true;
        } else {
            return false;
        }
    }

    public void convert(ViewHolder holder, YoumaJavaBean youmaJavaBean, int position) {
        vp_head = holder.getView(R.id.vp_head);
        iv_indicator = holder.getView(R.id.iv_indicator);
        iv_indicator_1 = holder.getView(R.id.iv_indicator_1);
        iv_indicator_2 = holder.getView(R.id.iv_indicator_2);
        ll_indicators = holder.getView(R.id.ll_indicators);
        getDistanceOfIndicator();
        headDatas = youmaJavaBean.getHeadDatas();
        if (headDatas != null) {
            headPagerAdapter = new HeadPagerAdapter();
            vp_head.setAdapter(headPagerAdapter);
        }
        vp_head.addOnPageChangeListener(this);
        initHandler();
        mHandler.sendEmptyMessageDelayed(0, 2000);

    }

    private void initHandler() {
        if (mHandler == null) {
            mHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    switch (msg.what) {
                        case 0:
                            int currentItem = vp_head.getCurrentItem();
                            if (currentItem < 3) {
                                currentItem++;
                            } else {
                                currentItem = 0;
                            }
                            vp_head.setCurrentItem(currentItem, true);
                            mHandler.sendEmptyMessageDelayed(0, 2000);
                            break;
                    }

                }
            };

        }
        vp_head.setOnTouchListener(this);
    }

    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //滑动的回调
        int distanceToMove = (int) ((distance * positionOffset) + 0.5) + distance * position;
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) iv_indicator.getLayoutParams();
        params.leftMargin = distanceToMove;
        iv_indicator.setLayoutParams(params);
    }

    public void onPageSelected(int position) {
        //页面被选中的回调
    }

    public void onPageScrollStateChanged(int state) {
        //页面状态的变化
    }

    public int getDistanceOfIndicator() {
        iv_indicator.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                iv_indicator.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int left_0 = ll_indicators.getChildAt(0).getLeft();
                int left_1 = ll_indicators.getChildAt(1).getLeft();
                distance = left_1 - left_0;
            }
        });

        return distance;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mHandler.removeCallbacksAndMessages(null);
                break;
            case MotionEvent.ACTION_CANCEL:
                mHandler.sendEmptyMessageDelayed(0, 1500);
                break;
            case MotionEvent.ACTION_UP:
                mHandler.sendEmptyMessageDelayed(0, 1500);
                break;
            default:
                break;
        }
        return false;
    }

    class HeadPagerAdapter extends PagerAdapter {

        public int getCount() {
            return headDatas.size();
        }

        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        public Object instantiateItem(ViewGroup container, int position) {
            View view = MyUtil.inFlate(R.layout.item_viewpager);
            ImageView iv_head = (ImageView) view.findViewById(R.id.iv_head);
            iv_head.setImageResource(headDatas.get(position).getImage());
            TextView tv_headtitle = (TextView) view.findViewById(R.id.tv_headtitle);
            tv_headtitle.setText(headDatas.get(position).getTitle());
            container.addView(view);
            return view;
        }

        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
