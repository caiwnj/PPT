package ppt.com.ppt.fragment;

import java.util.HashMap;

import ppt.com.ppt.R;
import ppt.com.ppt.utils.MyUtil;

/**
 * Created by Caiwnj on 2016/12/15.
 */

public class FragmentFactory {
    private static HashMap<String, BaseFragment> fragmentHashMap = new HashMap<String, BaseFragment>();

    public static BaseFragment getFragment(String name) {
        BaseFragment fragment;
        fragment = fragmentHashMap.get(name);
        if (fragment == null) {

        }
        return fragment;
    }


    public enum FragmentName {//用枚举类来代表所有的Fragment的类别

        Youma(MyUtil.getString(R.string.YOUMA)),
        Find(MyUtil.getString(R.string.FIND)),
        Message(MyUtil.getString(R.string.MESSAGE)),
        Me(MyUtil.getString(R.string.ME));
        private String name;

        private FragmentName(String name) {
            this.name = name;
        }

        public String getname() {

            return name;
        }
    }
}
