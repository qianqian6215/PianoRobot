package cc.xiaoyuanzi.pianorobot.data;

import java.util.ArrayList;

/**
 * Created by qianqianmao on 16/8/21.
 */
public enum PitchName {

    C(0),C_SHARP(1),
    D(2),D_SHARP(3),
    E(4),
    F(5), E_SHARP(6),
    G(7), G_SHARP(8),
    A(9), A_SHARP(10),
    B(11);

    int step;

    PitchName(int value) {
        this.step = value;
    }

    public int getStep() {
        return step;
    }

    public static ArrayList<PitchName> WHTE_PITCHS = new ArrayList<PitchName>();
    static {
        WHTE_PITCHS.add(C);
        WHTE_PITCHS.add(D);
        WHTE_PITCHS.add(E);
        WHTE_PITCHS.add(F);
        WHTE_PITCHS.add(G);
        WHTE_PITCHS.add(A);
        WHTE_PITCHS.add(B);

    }

    public static int getStepFromWhteGroup(int index) {
        return WHTE_PITCHS.get(index-1).getStep();
    }
}
