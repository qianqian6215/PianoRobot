package cc.xiaoyuanzi.pianorobot.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import cc.xiaoyuanzi.pianorobot.data.musicxml.Pitch;

/**
 * Created by qianqianmao on 16/8/21.
 */
public enum PitchName {

    C(0), C_SHARP(1),
    D(2), D_SHARP(3),
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

    public static PitchName getPicthName(int step) {
        PitchName[] values = values();
        for(PitchName cur: values) {
            if(cur.step == step) {
                return cur;
            }
        }
        return null;
    }

    public static ArrayList<PitchName> WHTE_PITCHS = new ArrayList<PitchName>();
    public static HashMap<PitchName, SharpAndFlatList> SCALE_MAP
            = new HashMap<PitchName, SharpAndFlatList>();

    static {
        WHTE_PITCHS.add(C);
        WHTE_PITCHS.add(D);
        WHTE_PITCHS.add(E);
        WHTE_PITCHS.add(F);
        WHTE_PITCHS.add(G);
        WHTE_PITCHS.add(A);
        WHTE_PITCHS.add(B);

        //TODO fill the left scales
        SCALE_MAP.put(F, new SharpAndFlatList(null, new PitchName[]{B}));

    }

    static class SharpAndFlatList {

        ArrayList<PitchName> sharpList = new ArrayList<PitchName>();
        ArrayList<PitchName> flatList = new ArrayList<PitchName>();

        public SharpAndFlatList(PitchName[] sharps, PitchName[] flats) {
            if (sharps != null) {
                sharpList.addAll(Arrays.asList(sharps));

            }
            if (flats != null) {
                flatList.addAll(Arrays.asList(flats));
            }
        }
    }

    public static int getStepFromWhiteGroup(int index) {
        PitchName pitchName = WHTE_PITCHS.get(index - 1);
        int step = pitchName.getStep();
        return step;
    }

    public static int getStepFromWhiteGroup(PitchName scale, int index) {

        PitchName pitchName = WHTE_PITCHS.get(index - 1);
        if (scale != null && scale != C ) {

            SharpAndFlatList sharpAndFlatList = SCALE_MAP.get(scale);
            if (sharpAndFlatList != null) {
                if (sharpAndFlatList.flatList.contains(pitchName)) {
                    return -1;
                } else if (sharpAndFlatList.sharpList.contains(pitchName)) {
                    return 1;
                }
            }
        }
        return 0;
    }
}
