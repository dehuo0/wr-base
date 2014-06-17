#
# Copyright (C) 2012 Wind River Systems, Inc.
#
DESCRIPTION = "This module is a true drop-in replacement for IO::Socket::INET that \
uses SSL to encrypt data before it is transferred to a remote server \
or client. IO::Socket::SSL supports all the extra features that one \
needs to write a full-featured SSL client or server application: \
multiple SSL contexts, cipher selection, certificate verification, and \
SSL version selection. As an extra bonus, it works perfectly with \
mod_perl."

SUMMARY = "Perl library for transparent SSL"
HOMEPAGE = "http://search.cpan.org/dist/IO-Socket-SSL/"
LIC_FILES_CHKSUM = "file://SSL.pm;beginline=2511;endline=2521;md5=b11d724916a421e8e92ad6ad51450bde"
SRC_URI[io-socket-ssl-1.74.md5sum] = "6a9bc800d136af7709b2fb8dd2e4e8a5"
SRC_URI[io-socket-ssl-1.74.sha256sum] = "e080fae93663322a49c2cdf3eeeb5cad7bd9bf7ad9dd9f0147ddb57573fc2800"

SECTION = "libs"
LICENSE = "Artistic-1.0|GPLv1+"
RDEPENDS_${PN} += "perl-module-scalar-util libnet-ssleay-perl"
BBCLASSEXTEND = "native"
PR = "r1"

SRC_URI = "http://search.cpan.org/CPAN/authors/id/S/SU/SULLR/IO-Socket-SSL-${PV}.tar.gz;name=io-socket-ssl-${PV}"

S = "${WORKDIR}/IO-Socket-SSL-${PV}"

inherit cpan

PACKAGE_ARCH = "all"

do_install_append () {
        mkdir -p ${D}${docdir}/${PN}/
        mkdir -p ${D}/opt/${PN}/
        cp ${S}/BUGS ${D}${docdir}/${PN}/
        cp ${S}/Changes ${D}${docdir}/${PN}/
        cp ${S}/README ${D}${docdir}/${PN}/
        cp -pRP ${S}/docs ${D}${docdir}/${PN}/
        cp -pRP ${S}/certs ${D}${docdir}/${PN}/
        cp -pRP ${S}/example ${D}${docdir}/${PN}/
        cp -pRP ${S}/util ${D}${docdir}/${PN}/
        cp -pRP ${S}/t ${D}/opt/${PN}/
}

PACKAGES =+ "${PN}-tests"

FILES_${PN}-tests = "/opt/*"
