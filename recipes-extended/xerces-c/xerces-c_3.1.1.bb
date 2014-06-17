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
           file://fix-icu-include-dir.patch"
SRC_URI[md5sum] = "6a8ec45d83c8cfb1584c5a5345cb51ae"
SRC_URI[sha256sum] = "a42785f71e0b91d5fd273831c87410ce60a73ccfdd207de1b805d26d44968736"

DEPENDS = "icu"
PR = "r2"

inherit autotools pkgconfig

PACKAGES =+ "lib${BPN} ${PN}-samples"
FILES_lib${BPN} = "${libdir}/libxerces-c-3.1.so"
FILES_${PN}-samples = "${bindir}/*"

ALLOW_EMPTY_${PN} = "1"
