#
# Copyright (C) 2014 Wind River Systems, Inc.
#
require ${BPN}.inc

SRC_URI += "git://git.fedorahosted.org/git/microcode_ctl;protocol=git \
	    file://0001-add-the-WRLinux-release-support.patch \
            file://microcode_ctl.service \
            file://0001-fix-the-help-return-code.patch \
	   "

SRCREV = "b80c8ad6625a2c200f2a9c1f99d0417f275e9845"

PV = "1.28+git${SRCREV}"

S = "${WORKDIR}/git"
