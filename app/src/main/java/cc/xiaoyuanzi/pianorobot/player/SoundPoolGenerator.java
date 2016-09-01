package cc.xiaoyuanzi.pianorobot.player;

import android.content.Context;
import android.media.SoundPool;
import android.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cc.xiaoyuanzi.pianorobot.R;


/**
 * Created by qianqianmao on 16/8/23.
 */
public class SoundPoolGenerator {


    private static List<SoundPool> mSoundPoolList = new ArrayList<SoundPool>();
    
    private static ArrayList<Integer> mSoundResourceList = new ArrayList<Integer>();
    
    static {
        mSoundResourceList.add(R.raw.p1_1);
        mSoundResourceList.add(R.raw.p1_2);
        mSoundResourceList.add(R.raw.p1_3);
        mSoundResourceList.add(R.raw.p1_4);
        mSoundResourceList.add(R.raw.p1_5);
        mSoundResourceList.add(R.raw.p1_6);
        mSoundResourceList.add(R.raw.p1_7);
        mSoundResourceList.add(R.raw.p1_8);
        mSoundResourceList.add(R.raw.p1_9);
        mSoundResourceList.add(R.raw.p1_10);
        mSoundResourceList.add(R.raw.p1_11);
        mSoundResourceList.add(R.raw.p1_12);

        mSoundResourceList.add(R.raw.p2_1);
        mSoundResourceList.add(R.raw.p2_2);
        mSoundResourceList.add(R.raw.p2_3);
        mSoundResourceList.add(R.raw.p2_4);
        mSoundResourceList.add(R.raw.p2_5);
        mSoundResourceList.add(R.raw.p2_6);
        mSoundResourceList.add(R.raw.p2_7);
        mSoundResourceList.add(R.raw.p2_8);
        mSoundResourceList.add(R.raw.p2_9);
        mSoundResourceList.add(R.raw.p2_10);
        mSoundResourceList.add(R.raw.p2_11);
        mSoundResourceList.add(R.raw.p2_12);

        mSoundResourceList.add(R.raw.p3_1);
        mSoundResourceList.add(R.raw.p3_2);
        mSoundResourceList.add(R.raw.p3_3);
        mSoundResourceList.add(R.raw.p3_4);
        mSoundResourceList.add(R.raw.p3_5);
        mSoundResourceList.add(R.raw.p3_6);
        mSoundResourceList.add(R.raw.p3_7);
        mSoundResourceList.add(R.raw.p3_8);
        mSoundResourceList.add(R.raw.p3_9);
        mSoundResourceList.add(R.raw.p3_10);
        mSoundResourceList.add(R.raw.p3_11);
        mSoundResourceList.add(R.raw.p3_12);

        mSoundResourceList.add(R.raw.p4_1);
        mSoundResourceList.add(R.raw.p4_2);
        mSoundResourceList.add(R.raw.p4_3);
        mSoundResourceList.add(R.raw.p4_4);
        mSoundResourceList.add(R.raw.p4_5);
        mSoundResourceList.add(R.raw.p4_6);
        mSoundResourceList.add(R.raw.p4_7);
        mSoundResourceList.add(R.raw.p4_8);
        mSoundResourceList.add(R.raw.p4_9);
        mSoundResourceList.add(R.raw.p4_10);
        mSoundResourceList.add(R.raw.p4_11);
        mSoundResourceList.add(R.raw.p4_12);

        mSoundResourceList.add(R.raw.p5_1);
        mSoundResourceList.add(R.raw.p5_2);
        mSoundResourceList.add(R.raw.p5_3);
        mSoundResourceList.add(R.raw.p5_4);
        mSoundResourceList.add(R.raw.p5_5);
        mSoundResourceList.add(R.raw.p5_6);
        mSoundResourceList.add(R.raw.p5_7);
        mSoundResourceList.add(R.raw.p5_8);
        mSoundResourceList.add(R.raw.p5_9);
        mSoundResourceList.add(R.raw.p5_10);
        mSoundResourceList.add(R.raw.p5_11);
        mSoundResourceList.add(R.raw.p5_12);

        mSoundResourceList.add(R.raw.p6_1);
        mSoundResourceList.add(R.raw.p6_2);
        mSoundResourceList.add(R.raw.p6_3);
        mSoundResourceList.add(R.raw.p6_4);
        mSoundResourceList.add(R.raw.p6_5);
        mSoundResourceList.add(R.raw.p6_6);
        mSoundResourceList.add(R.raw.p6_7);
        mSoundResourceList.add(R.raw.p6_8);
        mSoundResourceList.add(R.raw.p6_9);
        mSoundResourceList.add(R.raw.p6_10);
        mSoundResourceList.add(R.raw.p6_11);
        mSoundResourceList.add(R.raw.p6_12);

        mSoundResourceList.add(R.raw.p7_1);
        mSoundResourceList.add(R.raw.p7_2);
        mSoundResourceList.add(R.raw.p7_3);
        mSoundResourceList.add(R.raw.p7_4);
        mSoundResourceList.add(R.raw.p7_5);
        mSoundResourceList.add(R.raw.p7_6);
        mSoundResourceList.add(R.raw.p7_7);
        mSoundResourceList.add(R.raw.p7_8);
        mSoundResourceList.add(R.raw.p7_9);
        mSoundResourceList.add(R.raw.p7_10);
        mSoundResourceList.add(R.raw.p7_11);
        mSoundResourceList.add(R.raw.p7_12);

        mSoundResourceList.add(R.raw.p8_1);
        
    }

    private static SoundPool mPool;
    //TODO should adapter to size generate , consider like ThreadPool
    public static SoundPool getSoundPoolList(Context context, int size) {
//        ArrayList<SoundPool> result = new ArrayList<SoundPool>();
//        if(mSoundPoolList.size() < size) {
//            for(int i= mSoundPoolList.size(); i< size;i++) {
//                mSoundPoolList.add(generateSoundPool(context, 6));
//            }
//        }
//        result.addAll(mSoundPoolList.subList(0,size));
//        return result;
        if(mPool == null) {
            mPool = generateSoundPool(context,10);
        }
        return mPool;
    }

    private static SoundPool generateSoundPool(Context context ,int maxStream) {
        SoundPool.Builder builder = new SoundPool.Builder();
        builder.setMaxStreams(maxStream);
        SoundPool soundPool = builder.build();
        initSountPool(context ,soundPool);
        return soundPool;
    }

    private static  void initSountPool(Context context , SoundPool soundPool) {
        for(int i=0; i< mSoundResourceList.size(); i++) {
            soundPool.load(context, mSoundResourceList.get(i),1);
        }
    }

}
