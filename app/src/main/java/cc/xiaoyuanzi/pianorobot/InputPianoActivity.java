package cc.xiaoyuanzi.pianorobot;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cc.xiaoyuanzi.pianorobot.data.Staff;
import cc.xiaoyuanzi.pianorobot.generate.file.FileStaffReader;
import cc.xiaoyuanzi.pianorobot.player.PianoPlayer;
import cc.xiaoyuanzi.pianorobot.provider.MusicProvider;

public class InputPianoActivity extends AppCompatActivity {

    private Button mSaveBtn;
    private Button mCancelBtn;
    private EditText mMusicContentTextView;
    private EditText mNameTextView;
    private String mOriginalName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_piano);
        mOriginalName = getIntent().getStringExtra(MainActivity.NAME);
        findViewById(R.id.play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Staff staff = FileStaffReader.getStaff(mNameTextView.getText().toString(),
                            mMusicContentTextView.getText().toString());
                    PianoPlayer.getPlayer().play(InputPianoActivity.this, staff);
                }catch (Exception e) {

                    new AlertDialog.Builder(InputPianoActivity.this).setMessage(e.getMessage()).show();

                }

            }

        });

        mSaveBtn = (Button) findViewById(R.id.save);
        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOriginalName != null) {
                    MusicProvider.deleteMusic(InputPianoActivity.this, mOriginalName);
                }
                MusicProvider.saveMusic(InputPianoActivity.this,
                        mNameTextView.getText().toString(),
                        mMusicContentTextView.getText().toString());
                Toast.makeText(InputPianoActivity.this, "Save success", Toast.LENGTH_SHORT);
                InputPianoActivity.this.finish();

            }
        });

        mCancelBtn = (Button) findViewById(R.id.cancel);
        mCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //TODO confirm again
                InputPianoActivity.this.finish();
            }
        });

        mMusicContentTextView = (EditText) findViewById(R.id.music_content);
        mNameTextView = (EditText) findViewById(R.id.input_name);

        if (mOriginalName != null) {
            mNameTextView.setText(mOriginalName);

            mMusicContentTextView.setText(getIntent().getStringExtra(MainActivity.CONTENT));
        }
    }

}
