package cc.xiaoyuanzi.pianorobot.data;

import java.util.ArrayList;

/**
 * Created by qianqianmao on 16/8/21.
 */
public class Bar {

    private ArrayList<Note> mNotes = new ArrayList<Note>();

    public ArrayList<Note> getmNotes() {
        return mNotes;
    }

    public void setmNotes(ArrayList<Note> mNotes) {
        this.mNotes = mNotes;
    }
}
