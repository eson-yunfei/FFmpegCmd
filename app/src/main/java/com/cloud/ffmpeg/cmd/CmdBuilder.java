package com.cloud.ffmpeg.cmd;

import java.util.ArrayList;

/**
 * Author : xiaoyunfei
 * Create Time : 2019-07-07 10:43
 * Package Name : com.cloud.ffmpeg.cmd
 * Description :
 */
public class CmdBuilder {

    private Cmd cmd;
    private ArrayList<String> cmdList;

    public CmdBuilder() {
        cmd = new Cmd();
        cmdList = new ArrayList<>();
        cmdList.add("ffmpeg");
    }


    public CmdBuilder setSrcFile(String srcFile) {
        cmdList.add("-i");
        cmdList.add(srcFile);
        return this;
    }

    /**
     * 分离音频
     *
     * @param outFile 输入的音频文件
     * @return  cmd
     */
    public CmdBuilder detachAudio(String outFile) {
        cmdList.add(Encoder.AUDIO_ENCODER); //指定音频编码
        cmdList.add("copy");    //只做 copy ,不做编码
        cmdList.add("-vn");     //v 代表视频，n 代表no ,意思是无视频
        cmdList.add(outFile);   //输出文件
        return this;
    }

    /**
     * 分离视频
     * @param outFile
     * @return
     */
    public CmdBuilder detachVideo(String outFile) {
        cmdList.add(Encoder.VIDEO_ENCODER); //指定视频编码
        cmdList.add("copy");    //只做 copy ,不做编码
        cmdList.add("-an");     //a 代表音频，n 代表no ,意思是无音频
        cmdList.add(outFile);   //输出文件
        return this;
    }

    public CmdBuilder composed(String srcVideo,String srcAudio,String outVideo) {
        setSrcFile(srcVideo);
        setSrcFile(srcAudio);
        cmdList.add(Encoder.VIDEO_ENCODER);
        cmdList.add("copy");
        cmdList.add(Encoder.AUDIO_ENCODER);
        cmdList.add("copy");
        cmdList.add(outVideo);
        return this;
    }


    public Cmd build() {
        String[] array = new String[cmdList.size()];
        cmd.setCmdLength(cmdList.size());
        cmd.setCmdValue(cmdList.toArray(array));
        return cmd;
    }


}
