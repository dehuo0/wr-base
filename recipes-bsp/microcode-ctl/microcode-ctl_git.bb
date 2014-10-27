#
# Copyright (C) 2014 Wind River Systems, Inc.
#
require ${BPN}.inc

SRC_URI += "git://git.fedorahosted.org/git/microcode_ctl;protocol=git \
	    file://0001-add-the-WRLinux-release-support.patch \
            file://microcode_ctl.service \
	   "
SRCREV = "8e508bb417329cf4386c0f0bef8c79163f5b06ca"
S = "${WORKDIR}/git"
PR = "${INC_PR}.0"
