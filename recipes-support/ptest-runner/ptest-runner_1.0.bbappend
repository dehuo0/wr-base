#
# Copyright (C) 2012-2013 Wind River Systems, Inc.
#
# LOCAL REV: WR specific simics related patches
#
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append = " file://ptest-summary.sh "

do_install_append () {
        install -m 0755 ${WORKDIR}/ptest-summary.sh ${D}${bindir}/
}
