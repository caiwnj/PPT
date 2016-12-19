package ppt.com.ppt.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

import ppt.com.ppt.R;
import ppt.com.ppt.activity.MainActivity;
import ppt.com.ppt.utils.MyUtil;
import ppt.com.ppt.view.BaseHomeView;

/**
 * Created by Caiwnj on 2016/12/16.
 */

public class MessageFragment extends BaseFragment {


    @Override
    public BaseHomeView.ResultState initData() {
        return BaseHomeView.ResultState.STATE_SUCCESS;
    }

    @Override
    public View initView() {
        View view = MyUtil.inFlate(R.layout.fragment_message);
        return view;
    }

}
