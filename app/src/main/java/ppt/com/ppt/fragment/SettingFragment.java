package ppt.com.ppt.fragment;

import android.view.View;
import android.widget.TextView;

import ppt.com.ppt.R;
import ppt.com.ppt.utils.MyUtil;
import ppt.com.ppt.view.BaseHomeView;

/**
 * Created by Caiwnj on 2016/12/16.
 */

public class SettingFragment extends BaseFragment {
    public BaseHomeView.ResultState initData() {
        return BaseHomeView.ResultState.STATE_SUCCESS;
    }

    public View initView() {
        TextView textView=new TextView(MyUtil.getContext());
        textView.setText(getClass().getSimpleName());
        textView.setTextColor(MyUtil.getColor(R.color.colorText));
        textView.setTextSize(36);
        return textView;
    }
}
