package com.cloud.ffmpeg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;
import android.widget.Toast;

import com.cloud.ffmpeg.cmd.CmdBuilder;
import com.cloud.ffmpeg.cmd.FFmepgCmd;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = findViewById(R.id.sample_text);
        tv.setText(FFmepgCmd.getInstance().stringFromJNI());

        String srcFile = Environment.getExternalStorageDirectory() + "/demo.mp4";
        String outAudioFile = Environment.getExternalStorageDirectory() + "/test.aac";
        String outVideoFile = Environment.getExternalStorageDirectory() + "/test.h264";
//        detachAduio(srcFile, outAudioFile);

        detachVideo(srcFile, outVideoFile);
    }


    private void detachAduio(String srcFile, String outFile) {
        FFmepgCmd.getInstance().exec(new CmdBuilder()
                .setSrcFile(srcFile)
                .detachAudio(outFile)
                .build(), new FFmepgCmd.OnCmdExecute() {
            @Override
            public void onExecute(int progress) {
                Toast.makeText(MainActivity.this, String.valueOf(progress)
                        , Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void detachVideo(String srcFile, String outVideoFile) {

        FFmepgCmd.getInstance()
                .exec(new CmdBuilder()
                                .setSrcFile(srcFile)
                                .detachVideo(outVideoFile)
                                .build(),
                        new FFmepgCmd.OnCmdExecute() {
                            @Override
                            public void onExecute(int progress) {
                                Toast.makeText(MainActivity.this, String.valueOf(progress)
                                        , Toast.LENGTH_SHORT).show();
                            }
                        });
    }

}
