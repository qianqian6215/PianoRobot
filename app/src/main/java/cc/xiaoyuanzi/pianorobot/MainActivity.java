package cc.xiaoyuanzi.pianorobot;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cc.xiaoyuanzi.pianorobot.data.Staff;
import cc.xiaoyuanzi.pianorobot.generate.file.FileStaffReader;
import cc.xiaoyuanzi.pianorobot.player.PianoPlayer;
import cc.xiaoyuanzi.pianorobot.provider.MusicProvider;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "MainActivity";
    public static final String NAME = "name";
    public static final String CONTENT = "content";
    private List<Staff> mStaffList;
    private List<String> mStaffNames;
    private ListView mListView;
    private MusicProvider.MusicProviderChangeListener mProviderChnangeListener;
    private ArrayAdapter<String> mArrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MusicProvider.saveMusic(MainActivity.this,"little star!",FileStaffReader.getTestStringLittleStar(),false);
        MusicProvider.saveMusic(MainActivity.this,"moscow nights!",FileStaffReader.getMoscowNights(), false);
        setContentView(R.layout.activity_main);
        View inputMusicBtn = findViewById(R.id.input_music);
        inputMusicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, InputPianoActivity.class);
                MainActivity.this.startActivity(intent);

            }
        });
        View inputHelpBtn = findViewById(R.id.input_help);
        inputHelpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage(R.string.help_input_content);
                builder.create().show();
            }
        });
        View stopPlayBtn = findViewById(R.id.stop_play);
        stopPlayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PianoPlayer.getPlayer(MainActivity.this).stopPlay();
            }
        });

        mStaffList = MusicProvider.getStaffList(this);

        mListView = (ListView) findViewById(R.id.piano_list);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PianoPlayer.getPlayer(MainActivity.this).play(
                        mStaffList.get(i));
            }
        });
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, InputPianoActivity.class);
                intent.putExtra(NAME, mStaffList.get(i).getmStaffName());
                intent.putExtra(CONTENT, mStaffList.get(i).getmContent() );
                MainActivity.this.startActivity(intent);
                return true;
            }
        });

        mProviderChnangeListener = new MusicProvider.MusicProviderChangeListener() {
            @Override
            public void onChange() {
                mStaffList = MusicProvider.getStaffList(MainActivity.this);
                mStaffNames = getNameList(mStaffList);
                refresh();
            }
        };
        refresh();
        MusicProvider.addChangeListener(mProviderChnangeListener);

        new Thread(){
            @Override
            public void run() {
                 PianoPlayer.getPlayer(MainActivity.this);
                 Log.d(LOG_TAG, "int finish ");
            }
        }.start();
    }

    private void refresh() {
        mStaffNames = getNameList(mStaffList);
        mArrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, mStaffNames.toArray(new String[0]));
        mListView.setAdapter(mArrayAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MusicProvider.removeChnageListener(mProviderChnangeListener);
    }

    private List<String> getNameList(List<Staff> staffList) {
        List<String> nameList = new ArrayList<String>();
        for(Staff staff: staffList) {
            nameList.add(staff.getmStaffName());
        }

        return nameList;
    }

}
