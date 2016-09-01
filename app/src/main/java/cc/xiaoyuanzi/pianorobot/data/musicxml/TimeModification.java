package cc.xiaoyuanzi.pianorobot.data.musicxml;

/**
 * Created by qianqianmao on 16/9/4.
 */
public class TimeModification {

    private int actualNote;

    private int normalNotes;

    private NoteTypeValue normalType;

    public int getActualNote() {
        return actualNote;
    }

    public void setActualNote(int actualNote) {
        this.actualNote = actualNote;
    }

    public int getNormalNotes() {
        return normalNotes;
    }

    public void setNormalNotes(int normailNotes) {
        this.normalNotes = normailNotes;
    }

    public NoteTypeValue getNormalType() {
        return normalType;
    }

    public void setNormalType(NoteTypeValue normalType) {
        this.normalType = normalType;
    }
}
