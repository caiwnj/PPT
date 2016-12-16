package ppt.com.ppt.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import ppt.com.ppt.R;
import ppt.com.ppt.utils.MyUtil;

/**
 * Created by Caiwnj on 2016/12/15.
 * 主界面的基本布局包括：1.正在加载2.加载失败3.加载成功
 */

public abstract class BaseHomeView extends FrameLayout {

    private View mRootView;
    public static final int STATE_FIRSTIN = 1;
    public static final int STATE_LOAD_UNDO = 1;
    public static final int STATE_LOAD_ING = 2;
    public static final int STATE_LOAD_ERR = 3;
    public static final int STATE_LOAD_SUCCESS = 4;
    private int mCurrentState;//////注意更改
    private View view_erro;
    private View view_ing;
    private ImageView iv_erro;
    private View view_success;


    public BaseHomeView(Context context, String className) {
        super(context);
        mRootView = intiView();
        Log.d("ClassName", className + "");
    }


    public BaseHomeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mRootView = intiView();
    }

    public BaseHomeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mRootView = intiView();
    }

    protected View intiView() {//初始化布局
        //错误的布局
        if (view_erro == null) {
            view_erro = MyUtil.inFlate(R.layout.view_load_erro);
            iv_erro = (ImageView) view_erro.findViewById(R.id.iv_erro);
            Animation anim_err = AnimationUtils.loadAnimation(MyUtil.getContext(), R.anim.anim_erro);
            iv_erro.startAnimation(anim_err);
            addView(view_erro);
        }
        if (view_ing == null) {
            view_ing = MyUtil.inFlate(R.layout.view_load_ing);
            addView(view_ing);
        }
        showRightView(STATE_FIRSTIN);//第一次进入

        return this;
    }

    public void showRightView(int state) {
        if (state == STATE_LOAD_UNDO || state == STATE_LOAD_ING) {
            view_erro.setVisibility(View.GONE);
            view_ing.setVisibility(View.VISIBLE);
        }
        if (state == STATE_LOAD_ERR) {
            view_erro.setVisibility(View.VISIBLE);
            view_ing.setVisibility(View.GONE);
        }
        if (state == STATE_LOAD_SUCCESS && view_success == null) {
            Log.d("AddSuccesView","Success");
            view_ing.setVisibility(View.GONE);
            view_erro.setVisibility(View.GONE);
            view_success = initSuccessView();
            if (view_success != null) {
                Log.d("AddSuccesView","SuccessADDD");
                addView(view_success);
            }
        }
        mCurrentState = state;
    }


    public int getCurrentState() {
        return mCurrentState;
    }

    public void loadData() {//暴露给外界的加载数据的方法
        if (mCurrentState != STATE_LOAD_ING) {//不在加载中才加载
            mCurrentState = STATE_LOAD_ING;
            new Thread(new Runnable() {
                private int state;

                public void run() {
                    ResultState resultState = initData();
                    if (resultState != null) {
                        state = resultState.getState();
                        MyUtil.runOnUiThread(new Runnable() {
                            public void run() {
                                showRightView(state);
                            }
                        });
                    }

                }
            }).start();
        }

    }

    /////枚举类型
    public enum ResultState {
        STATE_SUCCESS(BaseHomeView.STATE_LOAD_SUCCESS),
        STATE_ERRO(BaseHomeView.STATE_LOAD_ERR);
        private int state;

        public int getState() {
            return state;
        }

        private ResultState(int state) {
            this.state = state;
        }
    }

    public abstract ResultState initData();//给外界实现的用来加载数据的方法，已封装到子线程

    public abstract View initSuccessView();//给外接实现用来加载布局的方法

}
