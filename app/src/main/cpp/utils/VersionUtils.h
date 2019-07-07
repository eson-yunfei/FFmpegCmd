//
// Created by 肖云飞 on 2019-07-07.
//

#ifndef FFMPEGCMD_VERSIONUTILS_H
#define FFMPEGCMD_VERSIONUTILS_H


#include <string>

class VersionUtils {

public:
    std::string getAllVersion();
    std::string get_version_name(int version);


private:
    std::string get_lib_version(std::string name, std::string version);

};


#endif //FFMPEGCMD_VERSIONUTILS_H
