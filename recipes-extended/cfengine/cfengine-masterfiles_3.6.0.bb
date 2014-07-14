#
# Copyright (C) 2014 Wind River Systems, Inc.
#
SUMMARY = "Base policy for CFEngine"

DESCRIPTION = "CFEngine is an IT infrastructure automation framework \
that helps engineers, system administrators and other stakeholders \
in an IT system to manage and understand IT infrastructure throughout \
its lifecycle. CFEngine takes systems from Build to Deploy, Manage and Audit. \
 \
This package is intended to provide a stable base policy for \
installations and upgrades, and is used by CFEngine 3.6 and newer. \
 \
The contents of this packge are intended to live in `/var/cfengine/masterfiles` \
or wherever `$(sys.masterdir)` points. \
"

HOMEPAGE = "http://cfengine.com"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=52cd3d13af93180822888ab0088e9328"

SRC_URI = "https://github.com/cfengine/masterfiles/archive/${PV}.tar.gz;downloadfilename=${BPN}-${PV}.tar.gz"
SRC_URI[md5sum] = "4065dca987702f8cd1e568a66033aff5"
SRC_URI[sha256sum] = "64964e8ed818b60fbebb4024847b13099cc54df5691d7f9942a7217284f35919"

S = "${WORKDIR}/masterfiles-${PV}"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
	install -d ${D}${localstatedir}/cfengine/masterfiles
	oe_runmake 'DESTDIR=${D}${localstatedir}/cfengine/masterfiles' install
}

FILES_${PN} += "${localstatedir}/cfengine"
