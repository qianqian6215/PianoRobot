package cc.xiaoyuanzi.pianorobot.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by qianqianmao on 16/8/24.
 */
public class PlayStaffModel {

    private TreeMap<Long, List<PlayNoteModel>> time2Notes = new TreeMap<Long, List<PlayNoteModel>>();
    private String mName;

    public void putTimeNote(long time, PlayNoteModel note){

        List<PlayNoteModel> playNoteModels = time2Notes.get(time);
        if(playNoteModels == null) {

            playNoteModels = new ArrayList<PlayNoteModel>();
            time2Notes.put(time,playNoteModels);
        }
        playNoteModels.add(note);

    }

    public ArrayList<Entry<Long, List<PlayNoteModel>>> getTime2Notes() {


        Set<Entry<Long, List<PlayNoteModel>>> entries = time2Notes.entrySet();
        ArrayList<Entry<Long, List<PlayNoteModel>>> result = new ArrayList<Entry<Long, List<PlayNoteModel>>>();
        result.addAll(entries);
        return result;
    }


    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    //TODO ,consider the struct
 }
