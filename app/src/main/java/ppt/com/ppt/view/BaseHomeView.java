package ppt.com.ppt.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import ppt.com.ppt.R;
import ppt.com.ppt.utils.MyUtil;

/**
 * Created by Caiwnj on 2016/12/15.
 * 主界面的基本布局包括：1.正在加载2.加载失败3.加载成功
 */

public class BaseHomeView extends FrameLayout {

    private View mRootView;
    private int mCurrentState;
    private final int STATE_LOAD_ING = 1;
    private final int STATE_LOAD_ERR = 2;
    private final int STATE_LOAD_SUCCESS = 3;

    public BaseHomeView(Context context) {
        super(context);
        mRootView = intiView();
    }


    public BaseHomeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mRootView = intiView();
    }

    public BaseHomeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mRootView = intiView();
    }

    private View intiView() {//初始化布局
        //错误的布局
        View view_erro = MyUtil.inFlate(R.layout.view_erro);
        addView(view_erro);
        return this;
    }

}
