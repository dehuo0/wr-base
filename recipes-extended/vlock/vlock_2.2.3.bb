DESCRIPTION = "Linux _V_irtual Console locking program"
SECTION = "utils"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=a17cb0a873d252440acfdf9b3d0e7fbf"

DEPENDS += "${@base_contains('DISTRO_FEATURES', 'pam', 'libpam', '', d)}"

SRC_URI = "${GENTOO_MIRROR}/${BPN}-${PV}.tar.gz \
	   file://vlock_pam_tally2_reset.patch \
	   file://disable_vlockrc.patch \
	   file://vlock-wr-no_tally.patch \
	   file://vlock_pam"

SRC_URI[md5sum] = "378175c7692a8f288e65fd4dbf8a38eb"
SRC_URI[sha256sum] = "85aa5aed1ae49351378a0bd527a013078f0f969372a63164b1944174ae1a5e39"

inherit autotools-brokensep update-alternatives

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

ALTERNATIVE_${PN} = "vlock"
ALTERNATIVE_PRIORITY = "60"
ALTERNATIVE_LINK_NAME[vlock] = "${bindir}/vlock"
