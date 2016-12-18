package ppt.com.ppt.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Caiwnj on 2016/12/17.
 */

public class YoumaJavaBean implements Serializable {
    private String title;
    private String description;
    private ArrayList<HeadData> headDatas;
    public ArrayList<HeadData> getHeadDatas() {
        return headDatas;
    }

    public void setHeadDatas(ArrayList<HeadData> headDatas) {
        this.headDatas = headDatas;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
