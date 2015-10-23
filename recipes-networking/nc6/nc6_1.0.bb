#
# Copyright (C) 2012 Wind River Systems, Inc.
#
SUMMARY         = "TCP/IP swiss army knife with IPv6 support"
DESCRIPTION     = "A simple Unix utility which reads and writes data across network \
connections using TCP or UDP protocol.  It is designed to be a reliable \
'back-end' tool that can be used directly or easily driven by other \
programs and scripts. At the same time it is a feature-rich network \
debugging and exploration tool, since it can create almost any kind of \
connection you would need and has several interesting built-in \
capabilities."

HOMEPAGE        = "http://www.deepspace6.net/projects/netcat6.html"
SECTION         = "console/network"
LICENSE         = "GPLv2"
PR              = "r1"
SRC_URI         = "http://ftp.deepspace6.net/pub/ds6/sources/nc6/nc6-1.0.tar.bz2 \
    file://01_quiet_socket_announce.diff \
    file://02_multicast.diff \
    file://03_send_crlf.diff \
    file://04_minus_to_hyphen.diff \
    file://05_source_service_and_source_address.diff \
    file://06_idle_timeout_parsing.diff \
    file://07_z_argument.diff \
    file://nc6_locale.patch \
    file://nc6-fix-a-configure.ac-to-avoid-syntax-error-in-gene.patch"


LIC_FILES_CHKSUM = "file://COPYING;md5=133409beb2c017d3b0f406f22c5439e7"
SRC_URI[md5sum] = "5074bc51989420a1f68716f93322030f"
SRC_URI[sha256sum] = "eb35e7a05a60bf94496075a3a246d7bf63fcdba2ca7ceb6844852a1ff6478206"

inherit autotools gettext bluetooth

PACKAGECONFIG ??= "${@bb.utils.contains('DISTRO_FEATURES', 'bluetooth', 'bluez', '', d)}"
PACKAGECONFIG[bluez] = "--enable-bluez=yes,--enable-bluez=no,${BLUEZ},"
