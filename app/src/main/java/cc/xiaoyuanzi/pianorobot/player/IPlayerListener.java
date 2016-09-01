package cc.xiaoyuanzi.pianorobot.player;

/**
 * Created by qianqianmao on 16/8/23.
 */
public interface IPlayerListener {

    public void onStart();

    public void onFinish();

    public void onPlay(int time);
}
