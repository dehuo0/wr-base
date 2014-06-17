#
# Copyright (C) 2012 Wind River Systems, Inc.
#
SUMMARY = "Net-Telnet Perl module"
DESCRIPTION = "Net::Telnet allows you to make client connections to a TCP port and do \
network I/O, especially to a port using the TELNET protocol. Simple I/O \
methods such as print, get, and getline are provided. More sophisticated \
interactive features are provided because connecting to a TELNET port \
ultimately means communicating with a program designed for human interaction. \
These interactive features include the ability to specify a time-out and to \
wait for patterns to appear in the input stream, such as the prompt from a \
shell."

HOMEPAGE = "http://search.cpan.org/dist/Net-Telnet/"
SECTION = "Development/Libraries"
LICENSE = "Artistic-1.0|GPLv1+"

RDEPENDS_${PN} = "perl"

PR = "r0"

SRC_URI = "http://search.cpan.org/CPAN/authors/id/J/JR/JROGERS/Net-Telnet-${PV}.tar.gz"

inherit cpan

S = "${WORKDIR}/Net-Telnet-${PV}"
LIC_FILES_CHKSUM = "file://README;beginline=4;endline=6;md5=0102c35b427a5d151d41178355d0d994"
SRC_URI[md5sum] = "2f7d34b09d6117baefe89d44cff9d5fc"
SRC_URI[sha256sum] = "1604911947a283a8df2bde57a9adfe71e2717f6e0f915fcc73fc47a37130a16b"
