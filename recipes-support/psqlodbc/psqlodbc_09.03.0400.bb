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

SRC_URI[psqlodbc.md5sum] = "33d7020d30978089df3914bcbfa5049f"
SRC_URI[psqlodbc.sha256sum] = "de77dfa89dba0a159afc57b2e312ca6e9075dd92b761c7cc700c0450ba02b56b"
