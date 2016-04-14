#
# Copyright (C) 2014 Wind River Systems, Inc.
#
# LOCAL REV: add WR specific scripts
#

do_install_append() {
    ln -sf /dev/null  ${D}${sysconfdir}/udev/rules.d/80-net-setup-link.rules
}

PACKAGECONFIG_append = " networkd"
