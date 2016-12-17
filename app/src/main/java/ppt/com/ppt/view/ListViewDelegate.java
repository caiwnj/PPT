package ppt.com.ppt.view;

import ppt.com.ppt.R;
import ppt.com.ppt.bean.YoumaJavaBean;
import ppt.com.ppt.holder.ViewHolder;

/**
 * Created by Caiwnj on 2016/12/17.
 */

public class ListViewDelegate implements ItemViewDelegate<YoumaJavaBean> {
    @Override
    public int getItemViewLayoutId() {
        return R.layout.view_item_youma;
    }

    @Override
    public boolean isForViewType(YoumaJavaBean item, int position) {
       if (position != 0) {
            return true;
        } else {
            return false;

        }

    }

    public void convert(ViewHolder holder, YoumaJavaBean youmaJavaBean, int position) {
        holder.setText(R.id.tv_title, youmaJavaBean.getTitle());
        holder.setText(R.id.tv_desc, youmaJavaBean.getDescription());
    }
}