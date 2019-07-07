package com.cloud.ffmpeg.cmd;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

/**
 * Author : xiaoyunfei
 * Create Time : 2019-07-06 20:57
 * Package Name : com.cloud.ffmpeg.cmd
 * Description :
 */
public class FFmepgCmd {

    private static FFmepgCmd fFmepgCmd;

    static {
        System.loadLibrary("avutil");
        System.loadLibrary("avcodec");
        System.loadLibrary("swresample");
        System.loadLibrary("avformat");
        System.loadLibrary("swscale");
        System.loadLibrary("avfilter");
        System.loadLibrary("avutil");
        System.loadLibrary("native-lib");
    }

    private String TAG = "FFmepgCmd";
    private Handler handler;


    private FFmepgCmd() {
        handler = new Handler(Looper.getMainLooper());
    }

    public static FFmepgCmd getInstance() {
        if (fFmepgCmd == null) {
            fFmepgCmd = new FFmepgCmd();
        }
        return fFmepgCmd;
    }

    public native String stringFromJNI();

    //执行FFmpeg命令
    private native int exec(int cmdLen, String[] cmd);

    //获取命令执行进度
    private native int getProgress();

    public void exec(final Cmd cmd, OnCmdExecute onCmdExecute) {

        CmdRunnable runnable = new CmdRunnable(onCmdExecute);
        new Thread(runnable).start();
        int ret = exec(cmd.getCmdLength(), cmd.getCmdValue());
        if (ret == 0) {
            runnable.setResult(ret);
        }

    }


    public interface OnCmdExecute {
        void onExecute(int progress);
    }

    private class CmdRunnable implements Runnable {

        boolean isRunning = true;
        private OnCmdExecute onCmdExecute;

        CmdRunnable(OnCmdExecute onCmdExecute) {
            this.onCmdExecute = onCmdExecute;
        }

        @Override
        public void run() {


            while (isRunning) {
                int progress = getProgress();

                setProgress(progress);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void setResult(int ret) {
            if (ret == 0) {
                isRunning = false;
            }
        }

        private void setProgress(final int progress) {

            Log.e(TAG, "progress  ::" + progress);
            if (onCmdExecute == null) {
                return;
            }


            handler.post(new Runnable() {
                @Override
                public void run() {
                    onCmdExecute.onExecute(progress);
                }
            });
        }


    }

}
