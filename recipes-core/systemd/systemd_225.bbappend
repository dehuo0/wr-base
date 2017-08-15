#
# Copyright (C) 2014 Wind River Systems, Inc.
#
# LOCAL REV: add WR specific scripts
#

pkg_postinst_udev_append() { 
    if [ x"$D" = "x" ];then
        ln -sf /dev/null ${sysconfdir}/udev/rules.d/80-net-setup-link.rules
    else
        ln -sf /dev/null .${sysconfdir}/udev/rules.d/80-net-setup-link.rules
    fi
}

PACKAGECONFIG_append = " networkd"
