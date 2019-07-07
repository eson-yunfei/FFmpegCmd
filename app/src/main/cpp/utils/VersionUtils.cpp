//
// Created by 肖云飞 on 2019-07-07.
//

extern "C" {
#include <libavcodec/avcodec.h>
#include <libavfilter/avfilter.h>
#include <libavformat/avformat.h>
#include <libswresample/swresample.h>
#include <libswscale/swscale.h>
#include <libpostproc/postprocess.h>
}

#include "VersionUtils.h"
#include "../LogUtil.h"

std::string VersionUtils::getAllVersion() {


    std::string ffmpeg = get_lib_version("ffmpeng", av_version_info());
    std::string avCodec = get_lib_version("libavcodec", get_version_name(avcodec_version()));
    std::string avFilter = get_lib_version("libavfilter", get_version_name(avfilter_version()));
    std::string avFormat = get_lib_version("libavformat", get_version_name(avformat_version()));
    std::string swResample = get_lib_version("libswresample",
                                             get_version_name(swresample_version()));
    std::string swScale = get_lib_version("libswscale", get_version_name(swscale_version()));
    std::string avUtil = get_lib_version("libavutil", get_version_name(avutil_version()));
    std::string postProc = get_lib_version("libpostproc", get_version_name(postproc_version()));

    std::string all_info;
    all_info = "[" + ffmpeg + "," + avCodec + "," + avFilter + "," + avFormat + "," + swResample +
               "," + swScale + "," + avUtil + "," + postProc + "]";

    LOGE("all_info : %s", all_info.c_str());
    return all_info;

}

std::string VersionUtils::get_version_name(int version) {

    int version_major, version_minor, version_micro;

    version_major = (version >> 16) & 0xff;
    version_minor = (version >> 8) & 0xff;
    version_micro = (version) & 0xff;

    std::string versionName;

    versionName = std::to_string(version_major) + "." + std::to_string(version_minor) + "." +
                  std::to_string(version_micro);

    LOGE("versionName : %s", versionName.c_str());
    return versionName;
}


std::string VersionUtils::get_lib_version(std::string name, std::string version) {
    std::string item;
    item = "{\"name\":\""+name+"\",\"version\":\""+version+"\"}";
    LOGE("item : %s", item.c_str());
    return item;
}
