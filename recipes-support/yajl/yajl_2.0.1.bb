#
# Copyright (C) 2012 Wind River Systems, Inc.
#
SUMMARY = "Yet Another JSON Library."

DESCRIPTION = "YAJL is a small event-driven (SAX-style) JSON parser \
written in ANSI C, and a small validating JSON generator."

HOMEPAGE = "http://lloyd.github.com/yajl/"

PR = "r2"

LICENSE = "ISC"

LIC_FILES_CHKSUM = "file://COPYING;md5=74f541bd9a2b6c8e5d0714bcdc327f32 \
		 file://src/yajl.c;beginline=2;endline=6;md5=a0be7b2518c484f6c0407d0f2f75e6a5 \
		   "

DEPENDS = ""

inherit cmake

SRC_URI = "git://github.com/lloyd/yajl \
           file://CMakeLists.txt-No-RPATH-at-all.patch \
"
SRCREV = "f4b2b1af87483caac60e50e5352fc783d9b2de2d"

S = "${WORKDIR}/git"

# libs install into /usr/libx where x = LIB_SUFFIX
#
EXTRA_OECMAKE = "-DLIB_SUFFIX=${@d.getVar('baselib',True).replace('lib','')}"

