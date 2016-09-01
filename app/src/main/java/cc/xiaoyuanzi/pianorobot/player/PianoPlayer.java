package cc.xiaoyuanzi.pianorobot.player;

import android.content.Context;
import android.media.SoundPool;
import android.os.Handler;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cc.xiaoyuanzi.pianorobot.data.Staff;

/**
 * Created by qianqianmao on 16/8/23.
 */
public class PianoPlayer {

    private List<IPlayerListener> mPlayerListeners = new ArrayList<IPlayerListener>();

    public void addPlayerListener(IPlayerListener listener) {
        mPlayerListeners.add(listener);
    }

    public void removePlayerListener(IPlayerListener listener) {
        mPlayerListeners.remove(listener);
    }

    private static PianoPlayer instance;
    private static SoundPool  mSoundPool;
    private static Handler mHandler;

    public static PianoPlayer getPlayer() {
        if(instance == null) {
            instance = new PianoPlayer();
            mHandler = new Handler();
        }
        return  instance;
    }

    private PianoPlayer(){}

    public void play(Context context, Staff staff) {
       play(context,PlayerModelConvertor.getPlayStaffModel(staff));
    }

    private void play(Context context, PlayStaffModel model) {

        //prepare soundpool
        mSoundPool = SoundPoolGenerator.
                getSoundPoolList(context, 1);
        mSoundPool.stop(0);
        //notify start play
        notifyStartPlay();


        mHandler.post(new PlayTask(mHandler, 0,
                 model.getTime2Notes(),
                mSoundPool));

    }
    private static int msg = 1;
    public void stopPlay() {
        if(mSoundPool != null) {
            mSoundPool.stop(0);
            //TODO
            if(mCurrentRunnable != null) {
                mHandler.removeCallbacks(mCurrentRunnable);
            }

        }
    }
    private static Runnable mCurrentRunnable;

    private static class PlayTask implements Runnable {

        private Handler mHandler;
        private PlayStaffModel staffModel;
        private int index;
        private SoundPool mSoundPool;
        private ArrayList<Map.Entry<Long, List<PlayNoteModel>>> entrys;

        public PlayTask(Handler mHandler, int index,
                        ArrayList<Map.Entry<Long, List<PlayNoteModel>>> entrys,
                        SoundPool mSoundPool) {
            this.mHandler = mHandler;
            this.index = index;
            this.mSoundPool = mSoundPool;
            this.entrys = entrys;
        }

        @Override
        public void run() {
            //play
            //TODO MODE may be differ
            Map.Entry<Long, List<PlayNoteModel>> entry = entrys.get(index);
            long delay = 0;
            List<PlayNoteModel> noteValues = entry.getValue();
            for (PlayNoteModel note : noteValues) {
                for (Integer value : note.getNoteValues()) {
                    if (value != -1) {
                        Log.d("eeee", index + "   value size is " + value);
                        mSoundPool.play(value, 1, 1, 0, 0, 1);
                    }
                }
            }

            index++;
            if (index != entrys.size()) {
                Long key2 = entrys.get(index).getKey();
                Long key = entry.getKey();
                delay = key2 - key;
                Log.d("eeee", index + "   delay is " + delay+"  key1:"+key+"  key2:"+key2
                );
                mCurrentRunnable = new PlayTask(mHandler, index, entrys, mSoundPool);
                mHandler.postDelayed(mCurrentRunnable, delay);
            }
        }
    }


    private void notifyStartPlay() {

        for (IPlayerListener listener : mPlayerListeners) {
            listener.onStart();
        }
    }

    private void notifyPlayFinish() {

        for (IPlayerListener listener : mPlayerListeners) {
            listener.onFinish();
        }
    }



}