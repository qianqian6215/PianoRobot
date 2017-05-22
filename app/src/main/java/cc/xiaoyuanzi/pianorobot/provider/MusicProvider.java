package cc.xiaoyuanzi.pianorobot.provider;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import cc.xiaoyuanzi.pianorobot.data.Staff;
import cc.xiaoyuanzi.pianorobot.generate.file.FileStaffReader;

/**
 * Created by qianqianmao on 16/8/29.
 */
public class MusicProvider {

    private static final String MUSIC_PIANO_DIR = "piano_dir";

    private static ArrayList<MusicProviderChangeListener> musicProviderChangeListeners
            = new ArrayList<MusicProviderChangeListener>();


    public static void addChangeListener(MusicProviderChangeListener listener){
        musicProviderChangeListeners.add(listener);
    }

    public static void removeChnageListener(MusicProviderChangeListener listener){
        musicProviderChangeListeners.remove(listener);
    }

    private static void notifyChange() {
        for(MusicProviderChangeListener listener:musicProviderChangeListeners){
            listener.onChange();
        }
    }

    public static interface MusicProviderChangeListener{

        public void onChange();
    }

    public static void deleteMusic(Context context, String name) {
        File pianoDir = getPianoDir(context);
        File[] files = pianoDir.listFiles();
        for(File file : files) {
            if(file.getName().equals(name)) {
                file.delete();
                break;
            }
        }


    }

    public static boolean saveMusic(Context context, String name, String content){
        return saveMusic(context, name, content, true);
    }

    public static boolean saveMusic(Context context, String name, String content, boolean force) {

        FileOutputStream outputStream = null;

        try {
            File dir = getPianoDir(context);
            File file = new File(dir, name);
            if (!force && file.exists()) {

                return false;
            }
            outputStream = new FileOutputStream(file);
            outputStream.write(content.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (outputStream!=null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        notifyChange();
        return true;

    }

    private static File getPianoDir(Context context) {
        File dir = new File(context.getFilesDir(), MUSIC_PIANO_DIR);
        if(!dir.exists()) {
            dir.mkdir();
        }
        return dir;
    }




    public static List<Staff> getStaffList(Context context) {
        List<Staff> staffs = new ArrayList<Staff>();

        File dir = getPianoDir(context);
        for(File file: dir.listFiles()) {
            FileReader reader = null;
            try {
                reader = new FileReader(file);
                Staff staff = FileStaffReader.converToStaff(reader, file.getName());
                staffs.add(staff);
            }catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {

                    reader.close();
                }catch (IOException e){

                }
            }

        }

        return staffs;

    }
}
