#
# Copyright (C) 2014 Wind River Systems, Inc.
#
require ${BPN}.inc

SRC_URI += "git://git.fedorahosted.org/git/microcode_ctl;protocol=git \
	    file://0001-add-the-WRLinux-release-support.patch \
	   "
SRCREV = "2336d8e14545b7dab7c851a740743217728b19e9"
S = "${WORKDIR}/git"
PR = "${INC_PR}.0"
