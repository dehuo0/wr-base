#
# Copyright (C) 2012 Wind River Systems, Inc.
#
SUMMARY = "MIME::Types - Definition of MIME types"
DESCRIPTION = "MIME types are used in MIME compliant lines, for instance \
as part of e-mail and HTTP traffic, to indicate the type of content which \
is transmitted. Sometimes real knowledge about a mime-type is need.\
\n\
This module maintains a set of MIME::Type objects, which each describe \
one known mime type."

HOMEPAGE = "http://search.cpan.org/~markov/MIME-Types-1.35/"
SECTION = "libraries"

LICENSE = "Artistic-1.0|GPLv1+"

PR = "r1"

SRC_URI = \
"http://search.cpan.org/CPAN/authors/id/M/MA/MARKOV/MIME-Types-${PV}.tar.gz"

inherit cpan

S = "${WORKDIR}/MIME-Types-${PV}"

LIC_FILES_CHKSUM = \
"file://Makefile.PL;beginline=13;endline=13;md5=03941136f295d32adc19806b99d6370e\
 file://Makefile.PL;beginline=22;endline=22;md5=8bc093a25b2f49e8ad9701e21e4fb1ad"

SRC_URI[md5sum] = "0bba9902b326e03e376fe313b0fb239a"
SRC_URI[sha256sum] = "166c47b795c35a8ccd8dcfda50ad06aa43c32c9bd277db2f1272175538d92daa"
