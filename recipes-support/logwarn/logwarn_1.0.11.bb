DESCRIPTION = "A utility for finding interesting messages in log files"
HOMEPAGE = "http://code.google.com/p/logwarn"
SECTION = "console/utils"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "http://logwarn.googlecode.com/files/logwarn-${PV}.tar.gz"

SRC_URI[md5sum] = "bfe56260658dd8a69cb1f5286012c963"
SRC_URI[sha256sum] = "fe5d7e5a40908489a243f2d7ccb37af583e74f072e3cb6012e5b3f3e68e0bad8"

inherit autotools-brokensep


# Make sure some files exist for autoreconf.
#
do_configure_prepend () {
        touch ${S}/NEWS
        touch ${S}/ChangeLog
}

# Create a directory for logfile state info under /var/lib.
#
do_install_append () {
        install -d ${D}${sysconfdir}/default/volatiles
        cat > ${D}${sysconfdir}/default/volatiles/99_logwarn << EOF
d root root 0755 /var/lib/logwarn none
EOF
}

# This resets /var/lib/logwarn if the package is installed in a
# running system.
#
pkg_postinst_${PN}() {
    if [ -z "$D" ]; then
	if type systemd-tmpfiles >/dev/null; then
	    systemd-tmpfiles --create
	elif [ -e ${sysconfdir}/init.d/populate-volatile.sh ]; then
	    ${sysconfdir}/init.d/populate-volatile.sh update
	fi
    fi
}

# Make a package for the nagios plug-in (script).
#
PACKAGES += "${PN}-nagios"

FILES_${PN}-nagios = "/usr/lib/nagios"
