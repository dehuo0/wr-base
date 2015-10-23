#
# Copyright (C) 2012 Wind River Systems, Inc.
#
DEPENDS = "qt-x11-free-native freetype virtual/libx11 libxmu libxft libxext libxrender libxrandr libxcursor virtual/libgl libglu"
PROVIDES = "qt3x11"
LICENSE = "GPLv2 | QPL-1.0 |GPLv3"

LIC_FILES_CHKSUM = "file://LICENSE.GPL2;md5=c36a54c36d7375588c055892acf98844 \
                    file://LICENSE.QPL;md5=fff372435cb41647bc0b3cb940ea5c51 \
                    file://LICENSE.GPL3;md5=4c031e3b6ba394f311764db966fd4da1"

SRC_URI = "http://download.qt.io/archive/qt/3/qt-x11-free-${PV}.tar.gz \
	   file://configure.patch \
	   file://no-examples.patch \
           file://gcc4_1-HACK.patch \
           file://qt3-cstddef.patch"

require qt-x11-free-common.inc

SRC_URI[md5sum] = "9f05b4125cfe477cc52c9742c3c09009"
SRC_URI[sha256sum] = "1b7a1ff62ec5a9cb7a388e2ba28fda6f960b27f27999482ebeceeadb72ac9f6e"

inherit distro_features_check
# depends on virtual/libx11
REQUIRED_DISTRO_FEATURES ?= "x11"
