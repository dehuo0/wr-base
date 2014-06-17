#
# Copyright (C) 2012 Wind River Systems, Inc.
#
DESCRIPTION = "Dump in hexadecimal the content of a scalar. The result \
is returned in a string. Each line of the result consists of the offset \
in the source in the leftmost column of each line, followed by one or \
more columns of data from the source in hexadecimal. The rightmost column \
of each line shows the printable characters \
(all others are shown as single dots).\
"

SUMMARY = "Data::HexDump - Hexadecial Dumper"
HOMEPAGE = "http://search.cpan.org/~ftassin/Data-HexDump-0.02/lib/Data/HexDump.pm"
SECTION = "libs"

LICENSE = "Artistic-1.0"
LIC_FILES_CHKSUM = "file://lib/Data/HexDump.pm;beginline=215;endline=217;md5=bf1cd9373f8d1f85fe091ee069a480e9"

DEPENDS = "perl"

BBCLASSEXTEND = "native"
PR = "r2"

SRC_URI = "http://search.cpan.org/CPAN/authors/id/F/FT/FTASSIN/Data-HexDump-${PV}.tar.gz"
SRC_URI[md5sum] = "467b7183d1062ab4a502b50c34e7d67f"
SRC_URI[sha256sum] = "1a9d843e7f667c1c6f77c67af5d77e7462ff23b41937cb17454d03535cd9be70"


S = "${WORKDIR}/Data-HexDump-${PV}"

inherit cpan

PACKAGES =+ "${PN}-tests"
TEST_LOC = "/opt"

do_install_append () {
	install -d ${D}${TEST_LOC}/${BPN}
	install ${S}/t/* ${D}${TEST_LOC}/${BPN}
}

FILES_${PN}-tests = "${TEST_LOC}/${BPN}/*"

