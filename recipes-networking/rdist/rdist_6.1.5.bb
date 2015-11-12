#
# Copyright (C) 2012 Wind River Systems, Inc.
#
SUMMARY = "Remote file distribution client and server"
DESCRIPTION = "\
Rdist is a program to maintain identical copies of files over multiple \
hosts. It preserves the owner, group, mode, and mtime of files if \
possible and can update programs that are executing. \
"
SECTION = "console/network"
LICENSE = "BSD-4-Clause"
LIC_FILES_CHKSUM = "file://Copyright;md5=3f47ec9f64b11c8192ee05a66b5c2755"

SRC_URI = "http://www.magnicomp.com/download/${BPN}/${BPN}-${PV}.tar.gz"
SRC_URI[md5sum] = "546779700af70aa5f9103e08782cdcac"
SRC_URI[sha256sum] = "2bb0d0f5904eadc9e7fe3d60c15389d6897fcf884211070e289a6c710ff37f96"

SRC_URI += "file://rdist-6.1.5-linux.patch"
SRC_URI += "file://rdist-6.1.5-links.patch"
SRC_URI += "file://rdist-6.1.5-oldpath.patch"
SRC_URI += "file://rdist-6.1.5-hardlink.patch"
SRC_URI += "file://rdist-6.1.5-bison.patch"
SRC_URI += "file://rdist-6.1.5-varargs.patch"
SRC_URI += "file://rdist-6.1.5-maxargs.patch"
SRC_URI += "file://rdist-6.1.5-lfs.patch"
SRC_URI += "file://rdist-6.1.5-cleanup.patch"
SRC_URI += "file://rdist-6.1.5-svr4.patch"
SRC_URI += "file://rdist-6.1.5-ssh.patch"
SRC_URI += "file://rdist-6.1.5-mkstemp.patch"
SRC_URI += "file://rdist-6.1.5-stat64.patch"
SRC_URI += "file://rdist-6.1.5-fix-msgsndnotify-loop.patch"
SRC_URI += "file://rdist-6.1.5-bb-build.patch"

DEPENDS = "bison-native"

inherit autotools-brokensep

inherit useradd

# You must set USERADD_PACKAGES when you inherit useradd. This
# lists which output packages will include the user/group
# creation code.
USERADD_PACKAGES = "${PN}"

# You must also set USERADD_PARAM and/or GROUPADD_PARAM when
# you inherit useradd.

GROUPADD_PARAM_${PN} = "-g 2 bin"

