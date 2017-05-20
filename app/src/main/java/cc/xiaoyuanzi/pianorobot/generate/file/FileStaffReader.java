package cc.xiaoyuanzi.pianorobot.generate.file;

import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import cc.xiaoyuanzi.pianorobot.data.Bar;
import cc.xiaoyuanzi.pianorobot.data.Bars;
import cc.xiaoyuanzi.pianorobot.data.Clef;
import cc.xiaoyuanzi.pianorobot.data.Note;
import cc.xiaoyuanzi.pianorobot.data.PitchName;
import cc.xiaoyuanzi.pianorobot.data.Staff;
import cc.xiaoyuanzi.pianorobot.data.musicxml.Pitch;

/**
 * Created by qianqianmao on 16/8/27.
 */
public class FileStaffReader {

    public static final String TREBLE_START = "99";
    public static final String BASS_START = "88";

    public static Staff converToStaff(Reader reader, String name) {

        StringBuilder sb = new StringBuilder();
        BufferedReader bufReader = new BufferedReader(reader);
        try {
            String line = null;
            while((line = bufReader.readLine())!=null){
                sb.append(line).append(" ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String line = sb.toString();

        return getStaff(name, line);
    }

    public static Staff getStaff(String name, String line) {
        Staff staff = new Staff();
        staff.setmContent(line);
        staff.setmStaffName(name);

        fillStaff(staff, line);
        return staff;
    }

    public static void fillStaff(Staff staff, String staffLines) {

        String[] words = staffLines.split(" ");

        Bars bars = null;
        Bar bar = new Bar();
        long curBarDuration = 0;
        float barDuration = staff.getmDuration()*staff.getCount();
        PitchName scalePichName = PitchName.C;
        for(int i=0; i<words.length; i++) {
            if(TextUtils.isEmpty(words[i].trim())){
                continue;
            }
            if("999".equals(words[i])){
                i++;
                String[] split = words[i].trim().split("/");
                staff.setmDuration(Integer.valueOf(split[1]));
                staff.setCount(Integer.valueOf(split[0]));

            }else if("000".equals(words[i])) {
                i++;
                staff.setmSpeed(Float.valueOf(words[i]));
            }else if(words[i].startsWith("**") ) {
                String substring = words[i].substring(2, words[i].length());
                char[] chars = substring.toCharArray();
                if(chars.length == 1) {
                    scalePichName = PitchName.valueOf(String.valueOf(chars[0]));
                } else if(chars.length == 2) {
                    //TODO
                }
            }
            else if(TREBLE_START.equals(words[i])){
                bars = new Bars();
                bars.setmClef(Clef.TREBLE);
                staff.getmBarsList().add(new Bars[]{bars});
            }else if(BASS_START.equals(words[i])){
                bars = new Bars();
                bars.setmClef(Clef.BASS);
                staff.getmBarsList().add(new Bars[]{bars});
            }else{
                if(bar == null) {
                    bar = new Bar();
                }
                Note note = getNote(words[i], bars.getmClef(), staff.getmDuration(), scalePichName);
                bar.getmNotes().add(note);
                curBarDuration += note.getmDuration();
                if(curBarDuration >= barDuration) {
                    bars.getBarList().add(bar);
                    bar = null;
                    curBarDuration = 0;
                }
            }
        }

    }



    private static Note getNote(String noteString, Clef clef, float basicDuration, PitchName
            scalePitchName) {

        Note basicNote = clef==Clef.BASS?Note.BASS_C:Note.MIDDE_C;
        Note resulteNote = null;
        int index = 0;
        char[] chars = noteString.trim().toCharArray();

        int delta = 0;
        float durationCount = 1;
        boolean restore = false;
        for(int i=0;i<chars.length;i++){

            if(chars[i] == '-') {
                delta -=12;
            }else if(chars[i] == '+') {
                delta +=12;
            }else if(chars[i] == '9') {
                delta +=1;
            }else if(chars[i] == '8') {
                delta -=1;
            }else if(chars[i] == '*') {
                restore = true;
            }
            else if(chars[i]=='.') {
                durationCount +=1;
            }else if(chars[i]=='/') {
                durationCount /=2;
            }else{

                String s = String.valueOf(chars[i]);
                if("0".equals(s)){
                    resulteNote = Note.getResetNote();
                } else {
                    index = Integer.valueOf(s);
                    int step = PitchName.getStepFromWhiteGroup(index);
                    int stepScale = PitchName.getStepFromWhiteGroup(scalePitchName, index);
                    if(restore) {
                        //delta = 0;
                        //TODO
                    }else{
                        delta += stepScale;
                    }
                    //TODO reset
                    if (resulteNote == null) {
                        resulteNote = Note.ValueOf(basicNote, step, delta);

                    } else {
                        resulteNote.getmLinkNotes().add(Note.ValueOf(basicNote, step , delta));

                    }
                    delta = 0;
                }

                restore = false;
            }
        }
        resulteNote.setmDuration(durationCount*basicDuration);

        return  resulteNote;
    }

    public static Staff getTestStaffModel() {

        String lines = getTestStringLittleStar();
        return getStaff("little star", lines);

    }

    public static String getTestStringLittleStar() {
//        return "999 4/4  99 1 1 5 5 6 6 5. 4 4 3 3 2 2 1. 5 5 4 4 3 3 2. 5 5 4 4 3 3 2. 1 1 5 5 6 6 5. 4 4 3 3 2 2 1." +
//                    " 88 1. 3. 4. 3. 2. 1. 5. 1. 1. 4. 1. 5. 1. 4. 1. 5. 1. 3. 4. 3. 2. 1. 5. 1.";

              return "999 4/4 000 0.6 99 1 1 5 5 6 6 5. 4 4 3 3 2 2 1. 5 5 4 4 3 3 2. 5 5 4 4 3 3 2. 1 1 5 5 6 6 5. 4 4 3 3 2 2 1." +
                      " 88 1. 3. 4. 3. 2. 1. 5. 1. 1. 4. 1. 5. 1. 4. 1. 5. 1. 3. 4. 3. 2. 1. 5. 1.";

    }

    public static String getMoscowNights() {
        return "999 2/4 **F 99 2/ 4/ 6/ 4/ 5 4/ 3/ 6 5 2. 4/ 6/ +1/ +1/ +2 +1/ 7/ 6."+
                " +1 +91 +3/ +2/ 6 0 3 2/ 6/ 5/ 7  0 +1/ 7/ 6 5/ 4/ 6 5 2. " +
                "88 2. 7. 6 -6 2. 4. -7 1 +4 95 6 2. 4. 5. -5. -6.. -6 2.";

    }

}
