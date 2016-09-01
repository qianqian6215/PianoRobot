package cc.xiaoyuanzi.pianorobot.data.musicxml;

/**
 * Created by qianqianmao on 16/9/5.
 */
public enum BeamValue {
    BEGIN("begin"), CONTINUE("continue"),
    END("end") , FORWARD_HOOK("forward hook"),
    BACKWARD_HOOK("backward hhok");

    private String value;

    BeamValue(String value) {
        this.value = value;
    }
}
