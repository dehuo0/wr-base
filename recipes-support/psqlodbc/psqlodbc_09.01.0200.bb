#
# Copyright (C) 2012 Wind River Systems, Inc.
#
require ${PN}.inc

PR = "r0"

LICENSE = "LGPLv2"
LIC_FILES_CHKSUM = "file://license.txt;md5=6db3822fc7512e83087ba798da013692"

SRC_URI = "http://ftp.postgresql.org/pub/odbc/versions/src/${BPN}-${PV}.tar.gz;name=psqlodbc \
	   file://remove-some-checks-for-cross-compiling.patch \
	  "

SRC_URI[psqlodbc.md5sum] = "0f4ac0e6b0755af2452764db4cfe4e8f"
SRC_URI[psqlodbc.sha256sum] = "89c230f5068dcd17d5c6dab37d13ed03525040da534cc7f49564dceba00a9c2c"
