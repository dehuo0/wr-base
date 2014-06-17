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

PR = "r0"

DEPENDS = "virtual/libgl libxi libglu"

SRC_URI = "http://dfn.dl.sourceforge.net/sourceforge/freeglut/freeglut-${PV}.tar.gz \
	"

SRC_URI[md5sum] = "918ffbddcffbac83c218bc52355b6d5a"
SRC_URI[sha256sum] = "dde46626a62a1cd9cf48a11951cdd592e7067c345cffe193a149dfd47aef999a"

inherit autotools
