#
# Copyright (C) 2012 Wind River Systems, Inc.
#
SUMMARY = "A powerful tool for network monitoring and data acquisition"
DESCRIPTION = "\
This program allows you to dump the traffic on a network. tcpdump is \
able to examine IPv4, ICMPv4, IPv6, ICMPv6, UDP, TCP, SNMP, AFS BGP, \
RIP, PIM, DVMRP, IGMP, SMB, OSPF, NFS and many other packet types. \
\
It can be used to print out the headers of packets on a network \
interface, filter packets that match a certain expression. You can use \
this tool to track down network problems, to detect ping attacks or \
to monitor network activities. \
"
HOMEPAGE = "http://www.tcpdump.org/"
LICENSE = "BSD"
SECTION = "console/network"
PRIORITY = "optional"
DEPENDS = "libpcap"
PR = "r4"

SRC_URI = "\
	http://www.tcpdump.org/release/tcpdump-${PV}.tar.gz \
	file://tcpdump_configure_no_-O2.patch \
	file://0001-minimal-IEEE802.15.4-allowed.patch \
	file://ipv6-cross.patch \
	file://configure.patch \
	file://tcpdump-configure-dlpi.patch \
	file://tcpdump-cross-getaddrinfo.patch \
	file://check-openssl.diff \
"

LIC_FILES_CHKSUM = "file://LICENSE;md5=1d4b0366557951c84a94fabe3529f867" 

inherit autotools-brokensep
# ac_cv_linux_vers=${ac_cv_linux_vers=2}

CACHED_CONFIGUREVARS += "ac_cv_linux_vers=2"

EXTRA_OECONF = "${@base_contains('DISTRO_FEATURES', 'ipv6', '--enable-ipv6', '--disable-ipv6', d)}"

PACKAGECONFIG ??= "openssl"
PACKAGECONFIG[openssl] = "--with-crypto=yes, --without-openssl --without-crypto, openssl"

do_configure() {
	gnu-configize
	autoconf
	oe_runconf
	sed -i 's:/usr/lib:${STAGING_LIBDIR}:' ./Makefile
	sed -i 's:/usr/include:${STAGING_INCDIR}:' ./Makefile
	sed -i 's:,-rpath,$(libdir)::' ./Makefile
}

do_install_append() {
	# tcpdump ${PV} installs a copy to /usr/sbin/tcpdump.${PV}
	rm -f ${D}${sbindir}/tcpdump.${PV}
}

SRC_URI[md5sum] = "d0dd58bbd6cd36795e05c6f1f74420b0"
SRC_URI[sha256sum] = "e6cd4bbd61ec7adbb61ba8352c4b4734f67b8caaa845d88cb826bc0b9f1e7f0a"
