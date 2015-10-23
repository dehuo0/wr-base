#
# Copyright (C) 2012 Wind River Systems, Inc.
#
SUMMARY = "scsidev - Lets you access your SCSI devices easily"

DESCRIPTION = "\
scsidev is a utility that is used to guarantee that the same device node can be used \
for the same scsi device, no matter what other scsi devices are added or \
removed from the scsi chain.  The need for this tool arose because device \
numbers are assigned dynamicly at boot time, and if a new disk were added \
to the system (or if some disk didn't spin up), then fixed device nodes would \
cause the wrong filesystems to be mounted, checked, etc.  This can also result \
in security holes, as some device nodes may have permissions that allow \
general users access to the raw device, and if the mappings were to change, \
users would be able to access different devices."

HOMEPAGE = "http://www.garloff.de/kurt/linux/scsidev/"

PR = "r0"

LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://COPYING;md5=15fac19769be7c6091cda2f70757f064"

SRC_URI = "http://www.garloff.de/kurt/linux/scsidev/scsidev-${PV}.tar.gz \
           file://scsidev-disable-stripping.patch \
           file://scsidev-fortify-source-fix.patch \
           file://scsidev-keep-config-on-clean.patch \
           file://static-inline.patch \
           file://scsi.alias"

SRC_URI[md5sum] = "e3f2116f5b069503fda62363634dc4c6"
SRC_URI[sha256sum] = "a10b29867a71f1ce041f0fcbe6bda7a7bc38705cf2059e179e569de8ffcef06f"

S = "${WORKDIR}/scsidev-${PV}"

inherit autotools-brokensep

do_configure () {
    ./configure --prefix=/usr --host=${TARGET_SYS}
}

do_install_append () {
    install -d ${D}${sysconfdir}
    install -m 755 ${WORKDIR}/scsi.alias ${D}/${sysconfdir}/scsi.alias
}

FILES_${PN} += "/dev/scsi"

