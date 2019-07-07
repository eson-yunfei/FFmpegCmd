//
// Created by 肖云飞 on 2019-07-07.
//

#ifndef FFMPEGCMD_LOGUTIL_H
#define FFMPEGCMD_LOGUTIL_H

#include <android/log.h>

#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG  , "CPP_LogUtils", __VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR  , "CPP_LogUtils", __VA_ARGS__)


#endif //FFMPEGCMD_LOGUTIL_H
