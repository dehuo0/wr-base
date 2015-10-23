#
# Copyright (C) 2012 Wind River Systems, Inc.
#
SUMMARY = "an Open Source ODBC sub-system"

DESCRIPTION = "unixODBC is an Open Source ODBC sub-system and an ODBC SDK \
for Linux, Mac OSX, and UNIX."

HOMEPAGE = "http://www.unixodbc.org/"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7b37bf80a3df5a65b355433ae36d206"

DEPENDS = "libtool mysql5"

SRC_URI = "ftp://ftp.unixodbc.org/pub/unixODBC/unixODBC-${PV}.tar.gz \
        file://do-not-use-libltdl-source-directory.patch \
        file://Makefile.am-remove-ACLOCAL_AMFLAGS.patch \
           "
SRC_URI[md5sum] = "5e4528851eda5d3d4aed249b669bd05b"
SRC_URI[sha256sum] = "9c3459742f25df5aa3c10a61429bde51a6d4f11552c03095f1d33d7eb02b5c9a"

inherit autotools-brokensep

S ="${WORKDIR}/unixODBC-${PV}"

EXTRA_OEMAKE += "LIBS=-lltdl"

do_configure_prepend(){
    #old m4 files will cause libtool version don't match
    rm -rf m4/*
    rm -fr libltdl
}

