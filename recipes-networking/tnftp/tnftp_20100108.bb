#
# Copyright (C) 2012 Wind River Systems, Inc.
#
SUMMARY = "Enhanced NetBSD ftp client"
DESCRIPTION = "tnftp (formerly known as lukemftp) is a port of the NetBSD FTP client \
to other systems. It offers many enhancements over the traditional \
BSD FTP client, including command-line editing, command-line fetches \
of FTP and HTTP URLs (including via proxies), command-line uploads of \
FTP URLs, context-sensitive word completion, dynamic progress bar, \
IPv6 support, modification time preservation, paging of local and \
remote files, passive mode support (with fallback to active mode), \
SOCKS support, TIS FWTK gate-ftp server support, and transfer rate \
throttling."

SECTION = "console/network"
LICENSE = "BSD-4-Clause"
PR = "r0"

DEPENDS = "ncurses"

SRC_URI = "ftp://ftp.netbsd.org/pub/NetBSD/misc/tnftp/${BPN}-${PV}.tar.gz \
	   file://tnftp-autotools.patch \
	  "

inherit autotools update-alternatives

ALTERNATIVE_PRIORITY = "100"

ALTERNATIVE_${PN} = "ftp"
ALTERNATIVE_LINK_NAME_${PN} = "${bindir}/ftp"
ALTERNATIVE_TARGET_${PN}  = "${bindir}/tnftp"

FILES_${PN} = "${bindir}/tnftp"

LIC_FILES_CHKSUM = "file://COPYING;md5=6d6796cb166a9bb050958241dad9479e"
SRC_URI[md5sum] = "192aac255abd515d7d4cc9b397dc0cba"
SRC_URI[sha256sum] = "31d9f9ae333cdf99290c30b1f409e4fff79824cd2498464a8cade881c9b5511a"
