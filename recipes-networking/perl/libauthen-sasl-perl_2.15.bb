#
# Copyright (C) 2012 Wind River Systems, Inc.
#
DESCRIPTION = "SASL is a generic mechanism for authentication used by several network \
protocols. Authen::SASL provides an implementation framework that all \
protocols should be able to share."

SUMMARY = "SASL Authentication framework for Perl"
HOMEPAGE = "http://search.cpan.org/dist/Authen-SASL/"

LIC_FILES_CHKSUM = "file://lib/Authen/SASL/Perl.pm;beginline=1;endline=3;md5=17123315bbcda19f484c07227594a609"

SECTION = "libs"
LICENSE = "Artistic-1.0|GPL-1.0+"
DEPENDS = "perl"
RDEPENDS_${PN} = "libdigest-hmac-perl"

BBCLASSEXTEND = "native"
PR = "r1"

SRC_URI = "http://www.cpan.org/authors/id/G/GB/GBARR/Authen-SASL-${PV}.tar.gz;name=libAuthen-SASL-${PV}"
SRC_URI[libAuthen-SASL-2.15.md5sum] = "35929abb2755cf479d548672800aebee"
SRC_URI[libAuthen-SASL-2.15.sha256sum] = "e9c0be516302e75d7ecdf0ef20b89f58e8b43533ae39ded68ebfc72a23b077ee"


S = "${WORKDIR}/Authen-SASL-${PV}"

inherit cpan

PACKAGE_ARCH = "all"
