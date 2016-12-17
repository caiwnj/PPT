package ppt.com.ppt.view;

import ppt.com.ppt.R;
import ppt.com.ppt.bean.YoumaJavaBean;
import ppt.com.ppt.holder.ViewHolder;

/**
 * Created by Caiwnj on 2016/12/17.
 */

public class HeadViewDelegate implements ItemViewDelegate<YoumaJavaBean> {
    public int getItemViewLayoutId() {
        return R.layout.view_head_youma;
    }

    public boolean isForViewType(YoumaJavaBean item, int position) {
        if (position == 0) {
            return true;
        } else {
            return false;
        }
    }

    public void convert(ViewHolder holder, YoumaJavaBean youmaJavaBean, int position) {

    }
}
