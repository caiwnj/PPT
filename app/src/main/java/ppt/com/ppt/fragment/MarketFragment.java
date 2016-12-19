package ppt.com.ppt.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import ppt.com.ppt.R;
import ppt.com.ppt.activity.MainActivity;
import ppt.com.ppt.utils.MyUtil;
import ppt.com.ppt.view.BaseHomeView;

/**
 * Created by Caiwnj on 2016/12/16.
 */

public class MarketFragment extends BaseFragment {

    private WebView wb_market;


    public BaseHomeView.ResultState initData() {
        return BaseHomeView.ResultState.STATE_SUCCESS;
    }

    public View initView() {
        View view = MyUtil.inFlate(R.layout.fragment_market);
        wb_market = (WebView) view.findViewById(R.id.wb_market);
        wb_market.loadUrl("http://m.jd.com/?cu=true&utm_source=h5.oupeng.com&utm_medium=tuiguang&utm_campaign=t_1000037096_&utm_term=39608c067ca9450795b41e45f5e8f833&abt=3");
        wb_market.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        return view;
    }
}
