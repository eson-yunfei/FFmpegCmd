package com.cloud.ffmpeg.cmd;

/**
 * Author : xiaoyunfei
 * Create Time : 2019-07-07 10:52
 * Package Name : com.cloud.ffmpeg.cmd
 * Description :
 */
 class Cmd {

    private int cmdLength;
    private String[] cmdValue;


    public String[] getCmdValue() {
        return cmdValue;
    }

    public void setCmdValue(String[] cmdValue) {
        this.cmdValue = cmdValue;
    }

    public int getCmdLength() {
        return cmdLength;
    }

    public void setCmdLength(int cmdLength) {
        this.cmdLength = cmdLength;
    }
}
