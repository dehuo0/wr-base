#
# Copyright (C) 2012 Wind River Systems, Inc.
#
SUMMARY = "hostapd is a user space daemon for access point and authentication servers."
DESCRIPTION = "\
	hostapd is designed to be a "daemon" program \
	that runs in the background and acts as the backend component \
	controlling authentication. It implements IEEE 802.11 access \
	point management, IEEE 802.1X/WPA/WPA2/EAP Authenticators, \
	RADIUS client, EAP server, and RADIUS authentication server. \
	The current version supports Linux (Host AP, madwifi, Prism54, \
	mac80211-based drivers) and FreeBSD (net80211). hostapd supports \
	separate frontend programs and an example text-based frontend, \
	hostapd_cli, is included with hostapd. \
	"
HOMEPAGE = "http://hostap.epitest.fi/hostapd/"

DEPENDS = "libnl openssl"

LICENSE = "GPLv2.0"

LIC_FILES_CHKSUM = "file://COPYING;md5=c54ce9345727175ff66d17b67ff51f58"

SECTION = "base"
PR = "r1"

SRC_URI = "\
	http://hostap.epitest.fi/releases/${BPN}-${PV}.tar.gz \
	file://config \
	"

SRC_URI[md5sum] = "236247a7bbd4f60d5fa3e99849d1ffc9"
SRC_URI[sha256sum] = "002e9dcb7e46cf82b5900a2fcf92b30fc8cdfd32a72d7fd4488588f1c013dfcc"

do_configure() {
	cp ${WORKDIR}/config ${S}/hostapd/.config
	# Enable netlink 3 support
	sed 's,CONFIG_LIBNL20=y,CONFIG_LIBNL32=y,' -i ${S}/hostapd/.config
}

EXTRA_OEMAKE = "-C ${S}/hostapd"
# Enable netlink 3 support
EXTRA_OEMAKE += 'EXTRACFLAGS="-I${STAGING_INCDIR}/libnl3"'

do_compile() {
	make ${EXTRA_OEMAKE} CC="${CC}" V=1
}

do_install() {
	install -m 0755 -d ${D}/usr/bin
	cp ${S}/hostapd/hostapd ${D}/usr/bin
	cp ${S}/hostapd/hostapd_cli ${D}/usr/bin
	install -m 0755 -d ${D}/etc
	cp ${S}/hostapd/hostapd.conf ${D}/etc
}

