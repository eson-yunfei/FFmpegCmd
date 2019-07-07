package com.cloud.ffmpeg.utils;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * Author : xiaoyunfei
 * Create Time : 2019-07-07 18:39
 * Package Name : com.cloud.ffmpeg.utils
 * Description :
 */
public class GsonUtils {

    private static Gson gson = new Gson();

    public static <T> T fromJson(String json, Class<T> classOfT) {

        return gson.fromJson(json, classOfT);
    }

    public static <T> T fromJson(String json,
                                 Type classOfT) {

        return gson.fromJson(json, classOfT);
    }
}
