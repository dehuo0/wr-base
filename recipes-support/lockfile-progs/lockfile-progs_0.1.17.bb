#
# Copyright (C) 2012 Wind River Systems, Inc.
#
SUMMARY = "Programs for locking and unlocking files and mailboxes"
DESCRIPTION = "This package includes several programs to safely lock and unlock files and mailboxes from the command line."

HOMEPAGE = "http://packages.debian.org/source/sid/lockfile-progs"
SECTION = "utilities"

LICENSE = "LGPLv2.1 & GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=0636e73ff0215e8d672dc4c32c317bb3"

DEPENDS += "liblockfile"

CLEANBROKEN = "1"

SRC_URI = "\
    http://ftp.de.debian.org/debian/pool/main/l/lockfile-progs/${BPN}_${PV}.tar.gz \
    file://lockfile-progs_makefile.patch \
"

SRC_URI[md5sum] = "64424a766fbc8cf6d613fcc14a096e14"
SRC_URI[sha256sum] = "03fb05d25499532f497775b1747b61fa6beebf12d3bcc951e125349ae166c511"

inherit autotools-brokensep

EXTRA_OEMAKE += "ROOT=${D}"
