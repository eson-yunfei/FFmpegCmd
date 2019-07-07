#include <jni.h>
#include <string>

#include "LogUtil.h"
#include "utils/VersionUtils.h"

extern "C" {
#include <libavcodec/jni.h>
#include "fftools/ffmpeg.h"
#include <libpostproc/postprocess.h>

}

static VersionUtils *versionUtils;

extern "C"
JNIEXPORT jstring JNICALL
Java_com_cloud_ffmpeg_cmd_FFmepgCmd_stringFromJNI(JNIEnv *env, jobject /*instance*/) {

    std::string hello = "Hello from C++";

    const char *versionName = av_version_info();


    return env->NewStringUTF(versionName);
}


extern "C"
JNIEXPORT jint JNICALL
Java_com_cloud_ffmpeg_cmd_FFmepgCmd_exec(JNIEnv *env, jobject instance, jint cmdLen,
                                         jobjectArray cmd) {

    LOGE("================>>>>> Java_com_cloud_ffmpeg_cmd_FFmepgCmd_exec ");
    LOGE("argCmd=%d", cmdLen);

    JavaVM *jvm = NULL;
    env->GetJavaVM(&jvm);
    av_jni_set_java_vm(jvm, NULL);

    char *argCmd[cmdLen];

    jstring buf[cmdLen];

    for (int i = 0; i < cmdLen; ++i) {
        buf[i] = static_cast<jstring>(env->GetObjectArrayElement(cmd, i));
        char *string = const_cast<char *>(env->GetStringUTFChars(buf[i], JNI_FALSE));
        argCmd[i] = string;
        LOGE("argCmd=%s", argCmd[i]);
    }

    int retCode = ffmpeg_exec(cmdLen, argCmd);
    LOGE("ffmpeg-invoke: retCode=%d", retCode);


    return retCode;
}


extern "C"
JNIEXPORT jint JNICALL
Java_com_cloud_ffmpeg_cmd_FFmepgCmd_getProgress(JNIEnv *env, jobject instance) {

    return get_progress();

}



extern "C"
JNIEXPORT jstring JNICALL
Java_com_cloud_ffmpeg_cmd_FFmepgCmd_readNativeVersion(JNIEnv *env, jobject instance) {

    if (versionUtils == NULL) {
        versionUtils = new VersionUtils();
    }
    std::string version_string = versionUtils->getAllVersion();

    LOGE("version_string  : %s", version_string.c_str());
    return env->NewStringUTF(version_string.c_str());
}