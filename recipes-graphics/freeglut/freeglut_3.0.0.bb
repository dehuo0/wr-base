#
# Copyright (C) 2012 Wind River Systems, Inc.
#
SUMMARY = "A library for learning about OpenGL features"
DESCRIPTION = "freeglut is a completely OpenSourced alternative to \
               the OpenGL Utility Toolkit (GLUT) library."
HOMEPAGE = "http://freeglut.sourceforge.net/"
SECTION = "x11/libs"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=89c0b58a3e01ce3d8254c9f59e78adfb"

DEPENDS = "virtual/libgl libxi libglu libxrandr"

SRC_URI = "http://sourceforge.net/projects/${BPN}/files/${BPN}/${PV}/${BP}.tar.gz"

SRC_URI[md5sum] = "90c3ca4dd9d51cf32276bc5344ec9754"
SRC_URI[sha256sum] = "2a43be8515b01ea82bcfa17d29ae0d40bd128342f0930cd1f375f1ff999f76a2"

inherit cmake distro_features_check
# depends on virtual/libgl
REQUIRED_DISTRO_FEATURES = "opengl"
