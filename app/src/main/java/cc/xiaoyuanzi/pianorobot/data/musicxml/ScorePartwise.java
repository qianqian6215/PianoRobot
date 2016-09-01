package cc.xiaoyuanzi.pianorobot.data.musicxml;

import com.thoughtworks.xstream.annotations.*;

/**
 * Created by qianqianmao on 16/9/2.
 */
@XStreamAlias("score-partwise")
public class ScorePartwise {

    @XStreamAsAttribute
    private String version;

    private Part[] parts;

    private ScoreHeader scoreHeader;
}
