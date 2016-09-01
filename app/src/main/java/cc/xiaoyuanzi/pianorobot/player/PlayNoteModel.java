package cc.xiaoyuanzi.pianorobot.player;

import java.util.ArrayList;

/**
 * Created by qianqianmao on 16/8/23.
 */
public class PlayNoteModel {

    private ArrayList<Integer> mNoteValues = new ArrayList<Integer>();
    //secondmillis
    private long mTimeDurations;

    private int mMode;

    public static final int resetValue = -1;

    public int getMode() {
        return mMode;
    }

    public void setMode(int mMode) {
        this.mMode = mMode;
    }

    public long getTimeDurations() {
        return mTimeDurations;
    }

    public void setTimeDurations(long mTimeDurations) {
        this.mTimeDurations = mTimeDurations;
    }

    public ArrayList<Integer> getNoteValues() {
        return mNoteValues;
    }

    public void setNoteValues(ArrayList<Integer> mNoteValues) {
        this.mNoteValues = mNoteValues;
    }

    public void addNoteValue(Integer value) {
        mNoteValues.add(value);
    }
}
