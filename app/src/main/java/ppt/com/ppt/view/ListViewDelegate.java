package ppt.com.ppt.view;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import ppt.com.ppt.R;
import ppt.com.ppt.bean.YoumaJavaBean;
import ppt.com.ppt.global.Constants;
import ppt.com.ppt.holder.ViewHolder;
import ppt.com.ppt.utils.MyUtil;

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
        TextView tv_title = holder.getView(R.id.tv_title);
        TextView tv_desc = holder.getView(R.id.tv_desc);

        CardView cardView = holder.getView(R.id.cardview);
        if (Constants.isClicked.containsKey(position)) {
            cardView.setCardBackgroundColor(MyUtil.getColor(R.color.colorHalfGrey));
            tv_title.setTextColor(MyUtil.getColor(R.color.colorText_pressed));
            tv_desc.setTextColor(MyUtil.getColor(R.color.colorText_pressed));

        } else {
            cardView.setCardBackgroundColor(MyUtil.getColor(R.color.colorHalfWhite));
            tv_title.setTextColor(MyUtil.getColor(R.color.colorText));
            tv_desc.setTextColor(MyUtil.getColor(R.color.colorText_pressed));

        }

    }
}