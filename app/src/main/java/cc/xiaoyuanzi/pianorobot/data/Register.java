package cc.xiaoyuanzi.pianorobot.data;

/**
 * Created by qianqianmao on 16/8/21.
 */
public enum Register {

    CONTRA_C(0),
    GREAT_C(1),
    SMARLL_C(2),
    ONE_LINE_OCTAVE(3),
    TWO_LINE_ONCTAVE(4),
    THREE_LINE_ONCTAVE(5),
    FOUR_LINE_OCTAVE (6);

    int index;

    Register(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
