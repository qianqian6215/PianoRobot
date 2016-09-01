package cc.xiaoyuanzi.pianorobot.data.musicxml;

/**
 * Created by qianqianmao on 16/9/4.
 */
public  class FullNote {

    private boolean isChord;

    private Pitch pitch;

    private Grace grace;

    private StartStop tie;

    private boolean cue;

    private int duration;

    public boolean isChord() {
        return isChord;
    }

    public void setChord(boolean chord) {
        isChord = chord;
    }

    public Pitch getPitch() {
        return pitch;
    }

    public void setPitch(Pitch pitch) {
        this.pitch = pitch;
    }

    public Grace getGrace() {
        return grace;
    }

    public void setGrace(Grace grace) {
        this.grace = grace;
    }

    public StartStop getTie() {
        return tie;
    }

    public void setTie(StartStop tie) {
        this.tie = tie;
    }

    public boolean isCue() {
        return cue;
    }

    public void setCue(boolean cue) {
        this.cue = cue;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
