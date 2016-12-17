package ppt.com.ppt.fragment;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ppt.com.ppt.R;
import ppt.com.ppt.adapter.HeaderAndFooterWrapper;
import ppt.com.ppt.adapter.LoadMoreWrapper;
import ppt.com.ppt.adapter.YoumaAdapter;
import ppt.com.ppt.bean.YoumaJavaBean;
import ppt.com.ppt.utils.MyUtil;
import ppt.com.ppt.view.BaseHomeView;

/**
 * Created by Caiwnj on 2016/12/16.
 */

public class YoumaFragment extends BaseFragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener, LoadMoreWrapper.OnLoadMoreListener {

    //    private FloatingActionButton flab_add;
    private RecyclerView rv_youma;
    private SwipeRefreshLayout srl_refresh;
    private List<YoumaJavaBean> youmaList;
    private List<YoumaJavaBean> youmaList2;

    private LoadMoreWrapper<YoumaJavaBean> loadMoreWrapper;
    private YoumaAdapter youmaAdapter;
    private HeaderAndFooterWrapper<YoumaJavaBean> headerAndFooterWrapper;

    public BaseHomeView.ResultState initData() {
        if (youmaList == null) {
            youmaList = new ArrayList<YoumaJavaBean>();
        }
        for (int i = 0; i < 10; i++) {
            YoumaJavaBean youmaJavaBean = new YoumaJavaBean();
            youmaJavaBean.setDescription("description" + i);
            youmaJavaBean.setTitle("Title" + i);
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
                    youmaJavaBean.setDescription("description" + i);
                    youmaJavaBean.setTitle("Title" + i);
                    youmaList2.add(youmaJavaBean);
                }
                youmaList.addAll(youmaList2);
                loadMoreWrapper.notifyDataSetChanged();
            }
        }, 1500);

    }
}
