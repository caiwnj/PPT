package ppt.com.ppt.fragment;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ppt.com.ppt.R;
import ppt.com.ppt.bean.HeadData;
import ppt.com.ppt.bean.YoumaJavaBean;
import ppt.com.ppt.utils.MyUtil;
import ppt.com.ppt.view.BaseHomeView;

/**
 * Created by Caiwnj on 2016/12/16.
 */

public class YoujiFragment extends BaseFragment {

    private ListView lv_youji;
    List<YoumaJavaBean> list;

    public BaseHomeView.ResultState initData() {
        if (list == null) {
            list = new ArrayList<YoumaJavaBean>();
        }
        for (int i = 0; i < 50; i++) {
            YoumaJavaBean youmaJavaBean = new YoumaJavaBean();
            youmaJavaBean.setDescription("这个是游记" + i);
            youmaJavaBean.setTitle("游记" + i);
            list.add(youmaJavaBean);
        }


        return BaseHomeView.ResultState.STATE_SUCCESS;
    }

    public View initView() {
        View view = MyUtil.inFlate(R.layout.fragment_youji);
        lv_youji = (ListView) view.findViewById(R.id.lv_youji);
        lv_youji.setAdapter(new YoujiAdapter());
        return view;
    }

    class YoujiAdapter extends BaseAdapter {

        public int getCount() {
            return list.size();
        }

        @Override
        public YoumaJavaBean getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = MyUtil.inFlate(R.layout.view_item_youji);
            TextView tv_desc = (TextView) view.findViewById(R.id.tv_desc);
            TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
            tv_desc.setText(getItem(position).getDescription());
            tv_title.setText(getItem(position).getTitle());
            return view;
        }
    }
}
