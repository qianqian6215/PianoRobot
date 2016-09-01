package cc.xiaoyuanzi.pianorobot.data;

import java.util.ArrayList;

/**
 * Created by qianqianmao on 16/8/27.
 */
public class Bars {

    private long mDuration;
    private int mCount;
    private Clef mClef;
    private ArrayList<Accidental> mAccidentals;
    private ArrayList<Bar> barList = new ArrayList<Bar>();

    public long getmDuration() {
        return mDuration;
    }

    public void setmDuration(long mDuration) {
        this.mDuration = mDuration;
    }

    public int getmCount() {
        return mCount;
    }

    public void setmCount(int mCount) {
        this.mCount = mCount;
    }

    public Clef getmClef() {
        return mClef;
    }

    public void setmClef(Clef mClef) {
        this.mClef = mClef;
    }

    public ArrayList<Accidental> getmAccidentals() {
        return mAccidentals;
    }

    public void setmAccidentals(ArrayList<Accidental> mAccidentals) {
        this.mAccidentals = mAccidentals;
    }

    public ArrayList<Bar> getBarList() {
        return barList;
    }

    public void setBarList(ArrayList<Bar> barList) {
        this.barList = barList;
    }
}
