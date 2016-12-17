package ppt.com.ppt.adapter;

import android.content.Context;

import java.util.List;

import ppt.com.ppt.bean.YoumaJavaBean;
import ppt.com.ppt.view.HeadViewDelegate;
import ppt.com.ppt.view.ListViewDelegate;

/**
 * Created by Caiwnj on 2016/12/17.
 */

public class YoumaAdapter extends MultiItemTypeAdapter<YoumaJavaBean> {
    public YoumaAdapter(Context context, List<YoumaJavaBean> datas) {//加入布局
        super(context, datas);
        addItemViewDelegate(new ListViewDelegate());
        addItemViewDelegate(new HeadViewDelegate());
    }
}
