package cc.xiaoyuanzi.pianorobot.data.musicxml;

/**
 * Created by qianqianmao on 16/9/4.
 */
public enum NoteTypeValue {
    T256TH("256th"), T128TH("128th"), T64TH("64th"),
    T32ND("32nd"), T16TH("16th"), EIGHTH("eighth"),
    QUARTER("quarter"), HALF("half"), WHOLE("whole"),
    BREVE("breve"), LONG("long");

    String value;

    NoteTypeValue(String value) {
        this.value = value;
    }
}
