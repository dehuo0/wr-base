#
# Copyright (C) 2012 Wind River Systems, Inc.
#
require ${PN}.inc

LICENSE = "LGPLv2"
LIC_FILES_CHKSUM = "file://license.txt;md5=6db3822fc7512e83087ba798da013692"

SRC_URI = "http://ftp.postgresql.org/pub/odbc/versions/src/${BPN}-${PV}.tar.gz;name=psqlodbc \
	   file://remove-some-checks-for-cross-compiling.patch \
	   file://donot-use-the-hardcode-libdir.patch \
	  "

SRC_URI[psqlodbc.md5sum] = "3426173781606386856fcbda128b6c89"
SRC_URI[psqlodbc.sha256sum] = "e8df6bad6d325339ba96bd78c82a68c2c5cddd1be534a220c978912bc0f6e9c4"
