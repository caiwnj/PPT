package ppt.com.ppt.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ppt.com.ppt.R;
import ppt.com.ppt.utils.MyUtil;
import ppt.com.ppt.view.BaseHomeView;

/**
 * Created by Caiwnj on 2016/12/15.
 */

public abstract class BaseFragment extends Fragment {

    private BaseHomeView mRootView;

    public BaseFragment() {
        this.mRootView = new BaseHomeView(MyUtil.getContext(), getClass().getName()) {
            public ResultState initData() {
                return BaseFragment.this.initData();
            }

            public View initSuccessView() {
                return initView();
            }
        };
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        return mRootView;
    }


    public View getRootView() {
        return mRootView;
    }

    public abstract BaseHomeView.ResultState initData();

    public abstract View initView();

    public void loadData() {
        if (mRootView != null) {
            mRootView.loadData();
            Log.d("loadData", "loadData");
        }
    }
}
