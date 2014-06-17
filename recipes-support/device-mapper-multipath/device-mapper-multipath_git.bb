#
# Copyright (C) 2012 Wind River Systems, Inc.
#
SUMMARY = "A set of tools to manage multipath devices using device-mapper"

DESCRIPTION = "\
Device-mapper-multipath provides tools to manage multipath devices \
by instructing the device-mapper kernel module what to do. These \
tools include: \
1. multipath - Scan the system for multipath devices and assemble them.\
2. multipathd - Detects when paths fail and execs multipath to update \
things.\
3. mpathpersist - Persistent reservation management feature allows \
cluster management software to manage persistent reservation through \
mpath device. It processes management requests from callers and hides \
the management task details. It also handles persistent reservation \
management of data path life cycle and state changes.\
4. kpartx - This tool, derived from util-linux's partx, reads partition \
tables on specified device and create device maps over partitions \
segments detected. It is called from hotplug upon device maps creation \
and deletion"

HOMEPAGE = "http://christophe.varoqui.free.fr/"

DEPENDS = "ncurses readline libaio lvm2 sysfsutils udev"

LICENSE = "GPLv2.0"

LIC_FILES_CHKSUM = "file://COPYING;md5=7be2873b6270e45abacc503abbe2aa3d"

## Fetch the upstream source for the multipath-tools from git.opensvc.com
## and patch it with relevant patches from it corresponding fedora repo:
## http://pkgs.fedoraproject.org/cgit/device-mapper-multipath.git/tree/?h=master

SRC_URI = "git://git.opensvc.com/multipath-tools/.git;protocol=http;branch=master"

# There exists multiple sources for the multipath-tools source code. They are
# listed below for future reference. The one used in this recipe (see above)
# is considered the most up-to-date one:
#SRC_URI = "git://git.kernel.org/pub/scm/linux/storage/multipath/hare/multipath-tools.git;protocol=git;branch=master"
#SRCREV = "63704387009443bdb37d9deaaafa9ab121d45bfb" git.kernel.org
#SRC_URI = "http://christophe.varoqui.free.fr/multipath-tools/multipath-tools-${PV}.tar.bz2"

SRC_URI += "file://0001-RH-dont_start_with_no_config.patch \
            file://0002-RH-multipath.rules.patch \
            file://0003-RH-Make-build-system-RH-Fedora-friendly.patch \
            file://0004-RH-multipathd-blacklist-all-by-default.patch \
            file://0005-RH-add-mpathconf.patch \
            file://0006-RH-add-find-multipaths.patch \
            file://0007-RH-add-hp_tur-checker.patch \
            file://0008-RH-RHEL5-style-partitions.patch \
            file://0009-RH-dont-remove-map-on-enomem.patch \
            file://0010-RH-deprecate-uid-gid-mode.patch \
            file://0011-RH-use-sync-support.patch \
            file://0012-RH-change-configs.patch \
            file://0013-RH-kpartx-msg.patch \
            file://0014-RH-dm_reassign.patch \
            file://0015-RH-selector_change.patch \
            file://0016-RH-retain_hwhandler.patch \
            file://0017-RH-netapp_config.patch \
            file://0018-RH-remove-config-dups.patch \
            file://wrs-always-use-libdevmapper.patch \
            file://wrs-always-use-libdevmapper-kpartx.patch \
            file://device-mapper-multipath-wr-src.patch \
            file://fix_global_var_instantiation_problems.patch \
            file://wrs-fix-initscripts.patch \
            file://multipath.conf.example"

SRCREV = "050b24b33d3c60e29f7820d2fb75e84a9edde528"
PV = "0.4.9+git${SRCPV}"
PR = "r1"

## multipath-tools.git, by default, expands directly to ${S}/git so let's
## set a git subdirectory for the source
S = "${WORKDIR}/git/"

SRC_URI[md5sum] = "ec167b9f21edd94c8863c3e077e8ef02"
SRC_URI[sha256sum] = "27865fa50f3fb14c0f4eaae6bed9d7c5abd6bc1ac4efebe346e70fe8f572bc67"

do_compile () {
    oe_runmake DESTDIR=${D} LIB=${libdir} \
    rcdir=${sysconfdir}/init.d/ OPTFLAGS="${CFLAGS}" all
}

do_install () {
    make DESTDIR=${D} \
    LIB=${libdir} rcdir=${sysconfdir}/init.d \
    bindir=${bindir} install

    install -d ${D}${sysconfdir}
    install -m 0644 ${WORKDIR}/multipath.conf.example \
    ${D}${sysconfdir}/multipath.conf.example
    install -m 0644 ${S}/multipath.conf.annotated \
    ${D}${sysconfdir}/multipath.conf.annotated
}

PACKAGES =+ "${PN}-libs"

FILES_${PN}-libs = "${libdir}/lib*.so ${libdir}/multipath/lib*.so"

FILES_${PN} += "/lib/udev/rules.d/62-multipath.rules \
                /lib/systemd/system/multipathd.service"
FILES_${PN}-dbg += "${libdir}/.debug/* ${libdir}/multipath/.debug/* "
FILES_${PN}-dev += "${libdir}/lib*.la"

RDEPENDS_${PN} += "${PN}-libs"
