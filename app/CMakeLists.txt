cmake_minimum_required(VERSION 3.4.1)



#add libavcodec
add_library(libavcodec
        SHARED
        IMPORTED
        )

SET_TARGET_PROPERTIES(
        libavcodec
        PROPERTIES IMPORTED_LOCATION
        ${PROJECT_SOURCE_DIR}/libs/${CMAKE_ANDROID_ARCH_ABI}/libavcodec.so
)

#add libavfilter
add_library(libavfilter
        SHARED
        IMPORTED
        )

SET_TARGET_PROPERTIES(
        libavfilter
        PROPERTIES IMPORTED_LOCATION
        ${PROJECT_SOURCE_DIR}/libs/${CMAKE_ANDROID_ARCH_ABI}/libavfilter.so
)


#add libavformat
add_library(libavformat
        SHARED
        IMPORTED
        )

SET_TARGET_PROPERTIES(
        libavformat
        PROPERTIES IMPORTED_LOCATION
        ${PROJECT_SOURCE_DIR}/libs/${CMAKE_ANDROID_ARCH_ABI}/libavformat.so
)


#add libavutil
add_library(libavutil
        SHARED
        IMPORTED
        )

SET_TARGET_PROPERTIES(
        libavutil
        PROPERTIES IMPORTED_LOCATION
        ${PROJECT_SOURCE_DIR}/libs/${CMAKE_ANDROID_ARCH_ABI}/libavutil.so
)


#add libpostproc
add_library(libpostproc
        SHARED
        IMPORTED
        )

SET_TARGET_PROPERTIES(
        libpostproc
        PROPERTIES IMPORTED_LOCATION
        ${PROJECT_SOURCE_DIR}/libs/${CMAKE_ANDROID_ARCH_ABI}/libpostproc.so
)

#add libswresample
add_library(libswresample
        SHARED
        IMPORTED
        )

SET_TARGET_PROPERTIES(
        libswresample
        PROPERTIES IMPORTED_LOCATION
        ${PROJECT_SOURCE_DIR}/libs/${CMAKE_ANDROID_ARCH_ABI}/libswresample.so
)

#add libswscale
add_library(libswscale
        SHARED
        IMPORTED
        )

SET_TARGET_PROPERTIES(
        libswscale
        PROPERTIES IMPORTED_LOCATION
        ${PROJECT_SOURCE_DIR}/libs/${CMAKE_ANDROID_ARCH_ABI}/libswscale.so
)





file(GLOB SOURCE src/main/cpp/*.cpp)
file(GLOB FFTOOL_SOURCE src/main/cpp/fftools/*.c)

add_library(
        native-lib
        SHARED

        ${SOURCE}

        ${FFTOOL_SOURCE})

find_library(
        log-lib

        log)

include_directories(src/main/cpp/include/)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -L${CMAKE_SOURCE_DIR}/libs/${CMAKE_ANDROID_ARCH_ABI}")





target_link_libraries(
        native-lib

#        avcodec avfilter avformat
#        avutil postproc swresample
#        swscale

        libavcodec
        libswscale
        libswresample
        libpostproc
        libavutil
        libavformat
        libavfilter

        ${log-lib})