package cc.xiaoyuanzi.pianorobot.data.musicxml;

/**
 * Created by qianqianmao on 16/9/4.
 */
public enum SteamValue {

    //note direction
    DOWN("down"),UP("up"),DOUBLE("double"),NONE("none");

    private String value;

    SteamValue (String steamValue) {
        this.value = steamValue;
    }

    public String getValue() {
        return value;
    }
}
