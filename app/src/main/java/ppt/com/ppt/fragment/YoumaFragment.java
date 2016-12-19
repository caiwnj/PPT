package ppt.com.ppt.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import ppt.com.ppt.R;
import ppt.com.ppt.activity.ItemActivity;
import ppt.com.ppt.adapter.HeaderAndFooterWrapper;
import ppt.com.ppt.adapter.LoadMoreWrapper;
import ppt.com.ppt.adapter.MultiItemTypeAdapter;
import ppt.com.ppt.adapter.YoumaAdapter;
import ppt.com.ppt.bean.HeadData;
import ppt.com.ppt.bean.YoumaJavaBean;
import ppt.com.ppt.global.Constants;
import ppt.com.ppt.utils.MyUtil;
import ppt.com.ppt.view.BaseHomeView;

/**
 * Created by Caiwnj on 2016/12/16.
 */

public class YoumaFragment extends BaseFragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener, LoadMoreWrapper.OnLoadMoreListener, MultiItemTypeAdapter.OnItemClickListener {

    //    private FloatingActionButton flab_add;
    private RecyclerView rv_youma;
    private SwipeRefreshLayout srl_refresh;
    private List<YoumaJavaBean> youmaList;
    private List<YoumaJavaBean> youmaList2;
    private List<HeadData> headDataList;
    private LoadMoreWrapper<YoumaJavaBean> loadMoreWrapper;
    private YoumaAdapter youmaAdapter;

    private HeaderAndFooterWrapper<YoumaJavaBean> headerAndFooterWrapper;

    public BaseHomeView.ResultState initData() {
        if (youmaList == null) {
            youmaList = new ArrayList<YoumaJavaBean>();
        }
        if (headDataList == null) {
            headDataList = new ArrayList<HeadData>();
        }
        for (int i = 0; i < 10; i++) {
            YoumaJavaBean youmaJavaBean = new YoumaJavaBean();
            youmaJavaBean.setDescription("这个是游玩消息" + i);
            youmaJavaBean.setTitle("游玩消息" + i);
            if (i < 4) {
                HeadData headData = new HeadData();
                switch (i) {
                    case 0:
                        headData.setImage(R.mipmap.image);
                        break;
                    case 1:
                        headData.setImage(R.mipmap.image1);
                        break;
                    case 2:
                        headData.setImage(R.mipmap.image2);
                        break;
                    case 3:
                        headData.setImage(R.mipmap.image3);
                        break;
                    default:
                        break;
                }
                headData.setTitle("head" + i);
                if (headDataList.size() == 4) {
                    headDataList.clear();
                }
                headDataList.add(headData);
            }
            youmaJavaBean.setHeadDatas((ArrayList<HeadData>) headDataList);
            youmaList.add(youmaJavaBean);
        }
        return BaseHomeView.ResultState.STATE_SUCCESS;
    }

    public View initView() {

        View view = MyUtil.inFlate(R.layout.fragment_youma);
        rv_youma = (RecyclerView) view.findViewById(R.id.rv_youma);
        rv_youma.setLayoutManager(new LinearLayoutManager(MyUtil.getContext()));//设置布局管理者
        youmaAdapter = new YoumaAdapter(MyUtil.getContext(), youmaList);
        loadMoreWrapper = new LoadMoreWrapper<YoumaJavaBean>(youmaAdapter);
        loadMoreWrapper.setLoadMoreView(R.layout.view_loadmor);
        loadMoreWrapper.setOnLoadMoreListener(this);
        rv_youma.setAdapter(loadMoreWrapper);
        srl_refresh = (SwipeRefreshLayout) view.findViewById(R.id.srl_refresh);
        srl_refresh.setOnRefreshListener(this);//设置滑动刷新
        /*srl_refresh.post(new Runnable() {
            @Override
            public void run() {
            srl_refresh.setRefreshing(true);
            }
        });*/
        youmaAdapter.setOnItemClickListener(this);
        return view;
    }

    public void onClick(View v) {

    }

    public void onRefresh() {
        MyUtil.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                srl_refresh.setRefreshing(false);
            }
        }, 1000);

    }

    public void onLoadMoreRequested() {
        MyUtil.getHandler().postDelayed(new Runnable() {
            public void run() {
                if (youmaList2 == null) {
                    youmaList2 = new ArrayList<YoumaJavaBean>();
                }
                for (int i = 0; i < 10; i++) {
                    YoumaJavaBean youmaJavaBean = new YoumaJavaBean();
                    youmaJavaBean.setDescription("这个是游玩消息" + i);
                    youmaJavaBean.setTitle("游玩消息" + i);
                    youmaList2.add(youmaJavaBean);
                }
                youmaList.addAll(youmaList2);
                loadMoreWrapper.notifyDataSetChanged();
            }
        }, 1500);

    }

    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
        Constants.isClicked.put(position, true);
        CardView cardView = (CardView) view.findViewById(R.id.cardview);
        TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
        TextView tv_desc = (TextView) view.findViewById(R.id.tv_desc);
        tv_title.setTextColor(MyUtil.getColor(R.color.colorText_pressed));
        tv_desc.setTextColor(MyUtil.getColor(R.color.colorText_pressed));
        cardView.setCardBackgroundColor(MyUtil.getColor(R.color.colorHalfGrey));
        Intent intent = new Intent(MyUtil.getContext(), ItemActivity.class);
        intent.putExtra("item", youmaList.get(position));
        startActivity(intent);
    }

    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
        return false;
    }
}
