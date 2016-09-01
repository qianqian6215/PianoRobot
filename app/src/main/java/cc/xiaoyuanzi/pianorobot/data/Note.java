package cc.xiaoyuanzi.pianorobot.data;

import java.util.ArrayList;

/**
 * Created by qianqianmao on 16/8/21.
 */
public class Note {

    private NoteLinkType mNoteLinkType;
    private ArrayList<Note> mLinkNotes = new ArrayList<Note>();

    private Ornaments mOrnaments;
    private ArrayList<Note> mAppoggiatureNotes = new ArrayList<Note>();

    private boolean isReset = false;
    //the default note is middle C
    private PitchName mPitchName = PitchName.C;
    private Register mRegister = Register.SMARLL_C;
    //duration
    private float mDuration = 0.25f;


    public static Note MIDDE_C = ValueOf(36);
    public static Note BASS_C = ValueOf(24);

    public Note() {
    }

    public static Note ValueOf(int indexValue){

        int group = indexValue / 12;
        int pitch = indexValue%12;
        Note note = new Note();
        note.setmPitchName(PitchName.values()[pitch]);
        note.setmRegister(Register.values()[group]);
        return note;
    }

    public static Note ValueOf(Note note, int deltaValue){

        int indexValue = note.getNoteIndex() + deltaValue;
        return ValueOf(indexValue);
    }

    public static Note getResetNote(){
        Note note = new Note();
        note.setReset(true);
        return note;
    }

    public int getNoteIndex(){
        if(isReset) {
            return -1;
        }
        return mPitchName.getStep()+ mRegister.getIndex()*12;
    }


    public NoteLinkType getmNoteLinkType() {
        return mNoteLinkType;
    }

    public void setmNoteLinkType(NoteLinkType mNoteLinkType) {
        this.mNoteLinkType = mNoteLinkType;
    }

    public ArrayList<Note> getmLinkNotes() {
        return mLinkNotes;
    }

    public void setmLinkNotes(ArrayList<Note> mLinkNotes) {
        this.mLinkNotes = mLinkNotes;
    }

    public Ornaments getmOrnaments() {
        return mOrnaments;
    }

    public void setmOrnaments(Ornaments mOrnaments) {
        this.mOrnaments = mOrnaments;
    }

    public ArrayList<Note> getmAppoggiatureNotes() {
        return mAppoggiatureNotes;
    }

    public void setmAppoggiatureNotes(ArrayList<Note> mAppoggiatureNotes) {
        this.mAppoggiatureNotes = mAppoggiatureNotes;
    }

    public boolean isReset() {
        return isReset;
    }

    public void setReset(boolean reset) {
        isReset = reset;
    }

    public PitchName getmPitchName() {
        return mPitchName;
    }

    public void setmPitchName(PitchName mPitchName) {
        this.mPitchName = mPitchName;
    }

    public Register getmRegister() {
        return mRegister;
    }

    public void setmRegister(Register mRegister) {
        this.mRegister = mRegister;
    }

    public float getmDuration() {
        return mDuration;
    }

    public void setmDuration(float mDuration) {
        this.mDuration = mDuration;
    }
}
