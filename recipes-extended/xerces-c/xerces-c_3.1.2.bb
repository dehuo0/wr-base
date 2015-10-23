#
# Copyright (C) 2012 Wind River Systems, Inc.
#
SUMMARY = "Xerces-C is a validating XML parser written in C++"
DESCRIPTION = "Xerces-C is a validating XML parser written in a portable subset \
of C++. Xerces-C makes it easy to give your application the ability to read \
and write XML data. A shared library is provided for parsing, generating, \
manipulating, and validating XML documents."
HOMEPAGE = "http://xerces.apache.org/xerces-c/"
SECTION =  "libs"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "${APACHE_MIRROR}/xerces/c/3/sources/${BP}.tar.gz \
           file://fix-icu-include-dir.patch \
	"
SRC_URI[md5sum] = "9eb1048939e88d6a7232c67569b23985"
SRC_URI[sha256sum] = "743bd0a029bf8de56a587c270d97031e0099fe2b7142cef03e0da16e282655a0"

DEPENDS = "icu"

inherit autotools pkgconfig

PACKAGECONFIG ??= ""
PACKAGECONFIG[curl] = "--with-curl, --without-curl, curl"
PACKAGES =+ "lib${BPN} ${PN}-samples"
FILES_lib${BPN} = "${libdir}/libxerces-c-3.1.so"
FILES_${PN}-samples = "${bindir}/*"

ALLOW_EMPTY_${PN} = "1"
