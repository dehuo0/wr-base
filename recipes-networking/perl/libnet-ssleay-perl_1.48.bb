#
# Copyright (C) 2012 Wind River Systems, Inc.
#
DESCRIPTION = "This module offers some high level convenience functions for accessing \
web pages on SSL servers (for symmetry, same API is offered for \
accessing http servers, too), a sslcat() function for writing your own \
clients, and finally access to the SSL api of SSLeay/OpenSSL package \
so you can write servers or clients for more complicated applications."

SUMMARY = "Net::SSLeay - Perl extension for using OpenSSL"
HOMEPAGE = "http://search.cpan.org/dist/Net-SSLeay/"
LIC_FILES_CHKSUM = "file://README;beginline=274;endline=294;md5=8c04e66a0f1c70e2922ad38f94d9e765"

SECTION = "libs"
LICENSE = "OpenSSL"
DEPENDS = "openssl zlib"
RDEPENDS_${PN} += " \
	perl-module-carp \
	perl-module-errno \
	perl-module-extutils-makemaker \
	perl-module-mime-base64 \
	perl-module-socket \
	"

BBCLASSEXTEND = "native"
PR = "r2"

SRC_URI = "http://search.cpan.org/CPAN/authors/id/M/MI/MIKEM/Net-SSLeay-${PV}.tar.gz;name=net-ssleay-${PV}"
SRC_URI[net-ssleay-1.48.md5sum] = "4e10d9da28f26732e37807820bf72af5"
SRC_URI[net-ssleay-1.48.sha256sum] = "630d857e797a11dd67433198b0458ad810d5f265d9ba0be5b93e73b629e4be6f"

S = "${WORKDIR}/Net-SSLeay-${PV}"

EXTRA_CPANFLAGS = "LIBS='-L=${libdir} -L=${base_libdir}' \
              INC=-I=${includedir} \
              'EXTRALIBS=-lssl -lcrypto -lz' \
              'LDLOADLIBS=-lssl -lcrypto -lz'"

inherit cpan

FILES_${PN}-dbg =+ "${libdir}/perl/vendor_perl/*/auto/Net/SSLeay/.debug/"
