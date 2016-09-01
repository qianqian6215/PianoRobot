package cc.xiaoyuanzi.pianorobot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cc.xiaoyuanzi.pianorobot.data.Staff;
import cc.xiaoyuanzi.pianorobot.generate.file.FileStaffReader;
import cc.xiaoyuanzi.pianorobot.player.PianoPlayer;
import cc.xiaoyuanzi.pianorobot.player.PlayerModelConvertor;
import cc.xiaoyuanzi.pianorobot.provider.MusicProvider;

public class MainActivity extends AppCompatActivity {

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
        MusicProvider.saveMusic(MainActivity.this,"little star!",FileStaffReader.getTestString());
        setContentView(R.layout.activity_main);
        View btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, InputPianoActivity.class);
                MainActivity.this.startActivity(intent);

            }
        });
//        View btn2 = findViewById(R.id.btn2);
//        btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                PianoPlayer.getPlayer().play(MainActivity.this, PlayerModelConvertor.getTestModel2());
//            }
//        });
        View btn0 = findViewById(R.id.btn0);
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PianoPlayer.getPlayer().stopPlay();
            }
        });

        mStaffList = MusicProvider.getStaffList(this);

        mListView = (ListView) findViewById(R.id.piano_list);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PianoPlayer.getPlayer().play(MainActivity.this,
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
                mStaffNames = getNameList(mStaffList);
                refresh();
            }
        };
        refresh();
        MusicProvider.addChangeListener(mProviderChnangeListener);

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
