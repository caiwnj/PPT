package ppt.com.ppt.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ppt.com.ppt.utils.MyUtil;
import ppt.com.ppt.view.BaseHomeView;

/**
 * Created by Caiwnj on 2016/12/15.
 */

public class BaseFragment extends Fragment {

    private View mRootView;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {//这里要返回一个VIew，这个是一个基本布局
        return new BaseHomeView(MyUtil.getContext());
    }
}
