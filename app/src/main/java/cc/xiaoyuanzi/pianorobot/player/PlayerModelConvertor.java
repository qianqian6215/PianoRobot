package cc.xiaoyuanzi.pianorobot.player;

import java.util.ArrayList;
import java.util.List;

import cc.xiaoyuanzi.pianorobot.data.Bar;
import cc.xiaoyuanzi.pianorobot.data.Bars;
import cc.xiaoyuanzi.pianorobot.data.Note;
import cc.xiaoyuanzi.pianorobot.data.Staff;

/**
 * Created by qianqianmao on 16/8/22.
 */
public class PlayerModelConvertor {

    public static final int TIMEBASIC = 200;

    public static PlayStaffModel getPlayStaffModel(Staff model){
       PlayStaffModel playStaffModel = new PlayStaffModel();
       ArrayList<Bars[]> barses = model.getmBarsList();
        Long timebasic = Float.valueOf(TIMEBASIC * model.getmSpeed()).longValue();
        for(int i=0 ;i<barses.size();i++) {
           Bars[] barsesGroup = barses.get(i);
           ArrayList<PlayNoteModel> noteModels = new ArrayList<>();
           for(Bars bars:barsesGroup) {
               fillPlayNoteModel(noteModels, bars, timebasic);
           }
           addToStaffModel(noteModels, playStaffModel);
       }


       return playStaffModel;
   }

    private static void fillPlayNoteModel(ArrayList<PlayNoteModel> noteModels, Bars bars, long timebasic) {

        for(Bar bar:bars.getBarList()) {

            for(Note note:bar.getmNotes()) {
                noteModels.add(getPlayNoteModel(note,timebasic));
            }
        }

    }

    private static PlayNoteModel getPlayNoteModel(Note note, long timeBasic) {
        PlayNoteModel playNoteModel = new PlayNoteModel();

        if(note.isReset()) {
            playNoteModel.addNoteValue(-1);
        }else{
            playNoteModel.addNoteValue(note.getNoteIndex());
        }
        int time = (int) (note.getmDuration() * timeBasic);
        playNoteModel.setTimeDurations(
                time);
        ArrayList<Note> notes = note.getmLinkNotes();
        if(notes != null) {
            for(Note linkedNote: notes){
                playNoteModel.addNoteValue(linkedNote.getNoteIndex());
            }
        }

        return playNoteModel;
    }

    static int c = 0;
   static int d = 2;
   static int e = 4;
   static int f = 5;
   static int g = 7;
   static int a = 9;
   static int b = 11;


   public static PlayStaffModel getTestModel2(){

       PlayStaffModel staffModel = new PlayStaffModel();

       int modle1BackIndex = 36;
       long t = 400;
       ArrayList<PlayNoteModel> modles1 = new ArrayList<PlayNoteModel>();
       int c1 = c + 12;
       int e1 = e + 12;
       int g1 = g + 12;
       int a1 = a + 12;
       int f1 = f + 12;
       int d1 = d + 12;
       long ht = t/2;
       int g_1 = g - 12;
       int a_1 = a - 12;
       int e_1 = e - 12;
       int b_1 = b - 12;
       getModel(36,
               new int[]{
               -100,e,g,c1,   -100,d,g,b,    -100,c,e,a,   -100, b_1,e,g,  -100, a_1,c,a,   -100, g_1,c,e,
               -100, a_1,c,f, -100, b_1,d,g, e1,         d1,           c1,             b,
               a,           g,           a,          b,            e+12+(c1)*100,  d+12+b*100,
               (c+12)*100+a, b*100+g, a*100+f, g*100+e, f*100+a, b*100+g,
               c1,b,c1,c, b_1,g,d,e,   c,c1,b,a,    b,e1,g1,a1,  f1,e1,d1,f1,    e1,d1,c1,b,
               a,g,f,e,    d,a,g,d,      c,d,e,f, b_1,d,g,f,  e,a,g,f,        g,f,e,d,
               c, a_1,c1,d1,  c1,b,a,g,  f,e,d,a, g,a,g,-100,  g1,e1,f1, g1,e1,f1, g1,g,a,b,c1,d1,e1,f1,
               e1,c1,d1,     e1,e,f,     g,a,g,f, g,e,f,g,  f,a,g, f,e,d, g,e,c,d,e,f,g,a,
                f,a,g,a,b,c1, g,a,b,c1,d1,e1,f1,g1, e+12+(c1)*100,  d+12+b*100, (c+12)*100+a, b*100+g,
                a*100+f, g+e*100, a,d1,b,e1, c1

                         },
               new long[]{
               t,t,t,t,    t,t,t,t,      t,t,t,t,    t,t,t,t,      t,t,t,t,        t,t,t,t,
               t,t,t,t,    t,t,t,t,      t*4,        t*4,          t*4,            t*4,
               t*4,        t*4,          t*4,        t*4,          t*4,            t*4,
               t*4,        t*4,          t*4,        t*4,          t*4,            t*4,
               t,t,t,t,    t,t,t,t,      t,t,t,t,    t,t,t,t,      t,t,t,t,        t,t,t,t,
               t,t,t,t,    t,t,t,t,      t,t,t,t,    t,t,t,t,      t,t,t,t,        t,t,t,t,
               t,t,t,t,    t,t,t,t,      t,t,t,t,   t,t,t,t,  t,ht,ht,t,ht,ht, ht, ht, ht, ht, ht, ht, ht, ht,
               t,ht,ht,t,ht,ht, ht,ht,ht,ht,ht,ht,ht,ht, t,ht,ht,t,ht,ht,ht,ht,ht,ht,ht,ht,ht,ht,
               t,ht,ht,t,ht,ht,ht,ht,ht,ht,ht,ht,ht,ht,t*4, t*4, t*4, t*4,
               t*4, t*4, t*2,t*2,t*2,t*2, t*4

               },
               modles1
       ,-1);
       ArrayList<PlayNoteModel> modles2 = new ArrayList<PlayNoteModel>();

       int f_1 = f - 12;
       int c_1 = c - 12;
       getModel(36,
               new int[]{
                       c,      g_1,     a_1,        e_1,            f_1,         c_1,
                       f_1,    g_1,     c,e,g,c1,   g_1, b_1,d,g,   a_1,c,e,a,   e_1,g,b,e,
                       f_1,a_1,c,f, c,e,g,c1, f_1,a_1,c,f, g_1,b_1,d,g, c_1,e_1,g_1,c, g_1,b_1,d,g,
                       g_1,c,e,c,   e_1,g_1,b_1,e, f_1, a_1,c,a_1, c_1,e_1,g_1,c,f_1, a_1,c,a_1,g_1,b_1,d,g_1,
                       c,      g_1,      a_1,      e_1,             f_1,         c_1,
                       f_1,    g_1,      c_1,      g_1,             a_1,        e_1,
                       f_1,    g_1,      f_1,      g_1,             e*100+c,    d*100+g1,
                       a_1,    b_1,      a_1,      g_1,
                       a_1,    g_1,     c_1,e_1,g_1, g_1-12,b_1-12,d-12,g_1, a_1-12,c_1,e_1,a_1, e_1-12,g_1-12,b_1-12,e_1,
                       f_1-12,a_1-12,c_1,f-12, c_1,e_1,g_1,c,f_1-12,a_1-12,c_1,f-12,g_1-12,b_1-12,d-12,g_1,c_1,e_1,g_1,c


               },
               new long[]{
                       t*4,    t*4,     t*4,    t*4,      t*4,        t*4,
                       t*4,    t*4,     t,t,t,t,    t,t,t,t,      t,t,t,t,    t,t,t,t,
                       t,t,t,t,    t,t,t,t,      t,t,t,t,    t,t,t,t,      t,t,t,t,        t,t,t,t,
                       t,t,t,t,    t,t,t,t,      t,t,t,t,    t,t,t,t,      t,t,t,t,        t,t,t,t,
                       t*4,    t*4,     t*4,    t*4,      t*4,        t*4,
                       t*4,    t*4,     t*4,    t*4,      t*4,        t*4,
                       t*4,    t*4,     t*4,    t*4,      t*4,        t*4,
                       t*4,    t*4,     t*4,    t*4,
                       t*4,    t*4,     t,t,t,t,    t,t,t,t,      t,t,t,t,        t,t,t,t,
                       t,t,t,t,    t,t,t,t,      t,t,t,t,    t,t,t,t,  t,t,t, t*3

               },
               modles2
               ,-1);

       addToStaffModel(modles1, staffModel);
       addToStaffModel(modles2, staffModel);
       staffModel.setmName("canon");

       return staffModel;
   }

    public static PlayStaffModel getTestModel(){

        PlayStaffModel staffModel = new PlayStaffModel();

        int modle1BackIndex = 36;
        long t = 400;
        ArrayList<PlayNoteModel> modles1 = new ArrayList<PlayNoteModel>();
        getModel(36,
                new int[]{c,c,g,g,a,a,g,f,f,e,e,d,d,c,g,g,f,f,e,e,d,g,g,f,f,e,e,d,c,c,g,g,a,a,g,f,f,e,e,d,d,c},
                new long[]{t,t,t,t,t,t,t*2,t,t,t,t,t,t,t*2,t,t,t,t,t,t,t*2,t,t,t,t,t,t,t*2,t,t,t,t,t,t,t*2,t,t,t,t,t,t,t},
                modles1
                ,-1);
        ArrayList<PlayNoteModel> modles2 = new ArrayList<PlayNoteModel>();
        getModel(24,
                new int[]{c,e,f,e,d,c,g,c,c,f,c,g,c,f,c,g,c,e,f,e,b,c,g,c},
                new long[]{},
                modles2
                ,t*2);

        addToStaffModel(modles1, staffModel);
        addToStaffModel(modles2, staffModel);
        staffModel.setmName("little star");


        return staffModel;
    }

    private static void addToStaffModel(List<PlayNoteModel> noteModelList, PlayStaffModel modle) {

        int currentTimeDuration = 0;
        for(PlayNoteModel note: noteModelList) {
            modle.putTimeNote(currentTimeDuration, note);
            currentTimeDuration+=note.getTimeDurations();

        }
    }

    private static void getModel(int startIndex, int[] modle1BackIndex, long[] duration,
                                          ArrayList<PlayNoteModel> modles1, long sametime) {
        for(int i=0;i<modle1BackIndex.length;i++) {
            PlayNoteModel model1 = new PlayNoteModel();
            if(modle1BackIndex[i] > 100){
                String s = String.valueOf(modle1BackIndex[i]);
                int value1 = modle1BackIndex[i]/100;
                int value2 = modle1BackIndex[i]%100;
                model1.addNoteValue(startIndex + value1);
                model1.addNoteValue(startIndex + value2);
            }else if(modle1BackIndex[i] != -100){
                model1.addNoteValue(modle1BackIndex[i] + startIndex);
            }else{
                model1.addNoteValue(-1);
            }
            if(sametime == -1) {
                model1.setTimeDurations(duration[i]);
            }else{
                model1.setTimeDurations(sametime);
            }

            modles1.add(model1);
        }


    }
}
