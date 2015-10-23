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
SRC_URI[md5sum] = "cf90cae797400b91d3c016d6fbcb316b"
SRC_URI[sha256sum] = "58065337fc4123fa16705e43956419d103587e092f2861d396bea288486e487e"

S = "${WORKDIR}/masterfiles-${PV}"

inherit autotools

EXTRA_OECONF = "--prefix=${localstatedir}/cfengine"

FILES_${PN} += "${localstatedir}/cfengine"
