DESCRIPTION = "Linux _V_irtual Console locking program"
SECTION = "utils"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=a17cb0a873d252440acfdf9b3d0e7fbf"

DEPENDS += "${@base_contains('DISTRO_FEATURES', 'pam', 'libpam', '', d)}"

SRC_URI = "http://NO_LONGER_MAINTAINED/vlock-2.2.2.tar.bz2 \
	   file://vlock_pam_tally2_reset.patch \
	   file://disable_vlockrc.patch \
	   file://vlock-wr-no_tally.patch \
	   file://vlock_pam"

SRC_URI[md5sum] = "0b26703c0aa3a9ae67bb90b9373a3b88"
SRC_URI[sha256sum] = "616dc9fffa7cdf7af980f6846d7b9994458fca80252fdb6b70cc5f163a7b97c0"

inherit autotools-brokensep

FILES_${PN}-dbg += "${libdir}/vlock/modules/.debug"

VLOCK_PAM = "\
 ${@base_contains('DISTRO_FEATURES', 'pam', '--enable-pam', '--disable-pam', d)}"

CFLAGS += "-Wall -W -pedantic -std=gnu99"

do_configure () {
	${S}/configure \
	        VLOCK_GROUP=root \
        	CC="${CC}" \
        	CFLAGS="${CFLAGS}" \
        	--prefix=${prefix} \
        	--libdir=${libdir} \
        	--mandir=${mandir} \
        	--with-modules="all.so new.so nosysrq.so ttyblank.so vesablank.so" \
        	--disable-root-password --enable-debug --disable-fail-count ${VLOCK_PAM}
}

do_install_append () {
	if [ ${@base_contains('DISTRO_FEATURES', 'pam', 'yes', '', d)} = yes ]; then
		install -d -m 0755 ${D}/${sysconfdir}/pam.d
		install -m 0644 ${WORKDIR}/vlock_pam ${D}${sysconfdir}/pam.d/vlock
	fi
}
