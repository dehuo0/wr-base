#
# Copyright (C) 2014 Wind River Systems, Inc.
#
# LOCAL REV: add WR specific scripts
#

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "\
    file://0001-disable-predictable-name-in-udevd-service.patch \
"

PACKAGECONFIG_append = " networkd"

pkg_postinst_systemd_append() {
       systemd-machine-id-setup 2>/dev/null
}
