package cc.xiaoyuanzi.pianorobot.data;

/**
 * Created by qianqianmao on 16/8/21.
 */
public enum Accidental {

    FLAT(-1), SHARP(1), NATURE(0);

    int step;

    Accidental(int step) {
        this.step = step;
    }

    public static Accidental getValueOf(int step) {
        if(step == -1) {
            return FLAT;
        }else if(step == 1) {
            return SHARP;
        }else {
            return NATURE;
        }
    }

    public float getStep() {
        return step;
    }
}
