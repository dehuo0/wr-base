#
# Copyright (C) 2012 Wind River Systems, Inc.
#
SUMMARY = "XML::SAX::Writer - SAX2 Writer"
DESCRIPTION = "\
XML::SAX::Writer helps to serialize SAX2 representations of XML documents to \
strings, files, and other flat representations. It handles charset encodings, \
XML escaping conventions, and so forth. It is still considered alpha, \
although it has been put to limited use in settings such as XML::LibXML and \
the AxKit XML Application Server. \
"
SECTION = "libs"
LICENSE = "Artistic-1.0 | GPLv1+"
HOMEPAGE = "http://search.cpan.org/dist/XML-SAX-Writer/"
DEPENDS += "libxml-filter-buffertext-perl-native"
RDEPENDS_${PN} += "libxml-filter-buffertext-perl"
PR = "r0"

SRC_URI = "http://search.cpan.org/CPAN/authors/id/P/PE/PERIGRIN/XML-SAX-Writer-${PV}.tar.gz"
SRC_URI[md5sum] = "afc83cdc49ccc8a7ad72911b2a0bcfbe"
SRC_URI[sha256sum] = "8250e36a061e2ed05a9935c556bb0874a7de5b6d61a94d43407ae78bde2c89ac"

LIC_FILES_CHKSUM = "file://README;beginline=45;endline=46;md5=3a125c1fc1ef9418c0090ee3ebd785b6"

S = "${WORKDIR}/XML-SAX-Writer-${PV}"

inherit cpan

BBCLASSEXTEND = "native"

PACKAGE_ARCH = "all"
