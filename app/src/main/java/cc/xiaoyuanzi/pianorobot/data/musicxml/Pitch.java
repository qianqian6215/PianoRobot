package cc.xiaoyuanzi.pianorobot.data.musicxml;

/**
 * Created by qianqianmao on 16/9/4.
 */
public abstract class Pitch {

    //middle c is 4
    private int octave;

    private Step step;


    public int getOctave() {
        return octave;
    }

    public void setOctave(int octave) {
        this.octave = octave;
    }

    public Step getStep() {
        return step;
    }

    public void setStep(Step step) {
        this.step = step;
    }

}
