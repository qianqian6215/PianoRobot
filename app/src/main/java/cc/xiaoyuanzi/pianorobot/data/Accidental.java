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

    public float getStep() {
        return step;
    }
}
