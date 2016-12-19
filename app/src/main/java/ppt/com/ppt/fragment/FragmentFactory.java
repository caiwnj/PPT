package ppt.com.ppt.fragment;

import java.util.HashMap;

/**
 * Created by Caiwnj on 2016/12/15.
 */

public class FragmentFactory {
    public static final String YOUMA = "youma";
    public static final String YOUJI = "youji";
    public static final String MESSAGE = "message";
    public static final String SEARCH = "search";
    public static final String SETTING = "setting";
    private static HashMap<String, BaseFragment> fragmentHashMap = new HashMap<String, BaseFragment>();

    public static BaseFragment getFragment(String name) {
        BaseFragment fragment = fragmentHashMap.get(name);
        if (fragment == null) {
            switch (name) {
                case YOUMA:
                    fragment = new YoumaFragment();
                    break;
                case YOUJI:
                    fragment = new YoujiFragment();
                    break;
                case MESSAGE:
                    fragment = new MessageFragment();
                    break;
                case SEARCH:
                    fragment = new MarketFragment();
                    break;
                case SETTING:
                    fragment = new SettingFragment();
                    break;
                default:
                    break;
            }
            fragmentHashMap.put(name, fragment);
        }
        return fragment;
    }

}
