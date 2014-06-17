#
# Copyright (C) 2012 Wind River Systems, Inc.
#
SUMMARY = "Programs for locking and unlocking files and mailboxes"
DESCRIPTION = "This package includes several programs to safely lock and unlock files and mailboxes from the command line."

HOMEPAGE = "http://packages.debian.org/source/sid/lockfile-progs"
SECTION = "utilities"

S = "${WORKDIR}/main"
LICENSE = "LGPLv2.1 & GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=0636e73ff0215e8d672dc4c32c317bb3"
PR = "r0"

DEPENDS += "liblockfile"

SRC_URI = \
"http://ftp.de.debian.org/debian/pool/main/l/lockfile-progs/${BPN}_${PV}.tar.gz \
 file://lockfile-progs_makefile.patch \
"

SRC_URI[md5sum] = "a7f11bb91e0aad09a7b1f37ee99357be"
SRC_URI[sha256sum] = "f95132d632687af971da830497f06257fa86aedbc4b76874456652f06a0a8a69"

inherit autotools-brokensep

EXTRA_OEMAKE += "ROOT=${D}"
