package cc.xiaoyuanzi.pianorobot.data;

import java.util.ArrayList;

/**
 * Created by qianqianmao on 16/8/21.
 */
public class Staff {

    private String mStaffName;
    private String mAuthor;
    private ArrayList<Bars[]> mBarsList = new ArrayList<Bars[]>();
    private float mDuration;
    private int count;
    private float mSpeed = 1.0f;

    public float getmSpeed() {
        return mSpeed;
    }

    public void setmSpeed(float mSpeed) {
        this.mSpeed = mSpeed;
    }

    //TODO should consider the struct
    private String mContent;

    public String getmContent() {
        return mContent;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent;
    }

    public String getmStaffName() {
        return mStaffName;
    }

    public void setmStaffName(String mStaffName) {
        this.mStaffName = mStaffName;
    }

    public String getmAuthor() {
        return mAuthor;
    }

    public void setmAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }

    public ArrayList<Bars[]> getmBarsList() {
        return mBarsList;
    }

    public void setmBarsList(ArrayList<Bars[]> mBarsList) {
        this.mBarsList = mBarsList;
    }

    public float getmDuration() {
        return mDuration;
    }

    public void setmDuration(float mDuration) {
        this.mDuration = mDuration;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
