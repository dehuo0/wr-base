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

PR = "r0"

SRC_URI = "ftp://ftp.unixodbc.org/pub/unixODBC/unixODBC-${PV}.tar.gz \
	   file://do-not-use-libltdl-source-directory.patch \
           "
SRC_URI[md5sum] = "86788d4006620fa1f171c13d07fdcaab"
SRC_URI[sha256sum] = "1f5be3edecff9e31072ef738ea1d8019594c4f0c2e3ab427e6eef153491db6a2"

inherit autotools-brokensep

S ="${WORKDIR}/unixODBC-${PV}"

EXTRA_OEMAKE += "LIBS=-lltdl"

do_configure_prepend(){
        #old m4 files will cause libtool version don't match
	rm -rf m4/*
}

