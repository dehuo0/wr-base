#
# Copyright (C) 2013 Wind River Systems, Inc.
#
# LOCAL REV: the replacement of WR busybox-oe-min
# 
# The cases to use busybox for WR images are:
#   1. For glibc-core/-std/-std-sato/-cgl, we use the minimal subset of
#      utilities which inlcudes:
#      run-parts, start-stop-daemon, rfkill, ifup/ifdown, ash
#   2. For glibc-tiny, we use a selective subset as the one of poky-tiny
#   3. For glitb-small, we use the typical oe-core's large set of busybox
#
# so here we have different defconfig and *.cfg based on different DISTROs
# DISTRO 'wrlinux' and 'wrlinux-std-sato' for case 1, 'wrlinux-tiny' for
# case 2, 'wrlinux-small' for case 3 but currently it includes nothing.
# 
# The users can use the config fragment (*.cfg) mechanism to configure
# busybox as they like based on each case.
#

# For glibc-tiny
FILESEXTRAPATHS_prepend_wrlinux-tiny := "${THISDIR}/${P}/wrlinux-tiny:"

# For glibc-core/-std/-std-sato/-cgl
FILESEXTRAPATHS_prepend_wrlinux := "${THISDIR}/${P}/wrlinux:"
FILESEXTRAPATHS_prepend_wrlinux-std-sato := "${THISDIR}/${P}/wrlinux:"

SRC_URI_WRLINUX = " \
    ${@base_contains('MACHINE_FEATURES', 'rtc', 'file://busybox-hwclock.cfg', '', d)} \
"
SRC_URI_append_wrlinux = "${SRC_URI_WRLINUX}"
SRC_URI_append_wrlinux-std-sato = "${SRC_URI_WRLINUX}"

# The minimal subset doesn't include syslog, clear the
# ALTERNATIVE_busybox-syslog to avoid QA warnings.
# As CONFIG_SYSLOGD is disabled, busybox-syslog should not contain any file.
python () {
    distrooverrides = (d.getVar('DISTROOVERRIDES', True) or '').split(':')
    if 'wrlinux' in distrooverrides or 'wrlinux-std-sato' in distrooverrides:
        d.setVar('ALTERNATIVE_%s-syslog' % (d.getVar('PN', True)), '')
        d.setVar('FILES_%s-syslog' % (d.getVar('PN', True)), '')
        d.setVar('SYSTEMD_SERVICE_%s-syslog' % (d.getVar('PN', True)), '')
}
