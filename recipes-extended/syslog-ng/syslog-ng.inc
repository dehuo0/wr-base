SUMMARY = "Alternative system logger daemon"
DESCRIPTION = "syslog-ng, as the name shows, is a syslogd replacement, \
but with new functionality for the new generation. The original syslogd \
allows messages only to be sorted based on priority/facility pairs; \
syslog-ng adds the possibility to filter based on message contents using \
regular expressions. The new configuration scheme is intuitive and powerful. \
Forwarding logs over TCP and remembering all forwarding hops makes it \
ideal for firewalled environments. \
"
HOMEPAGE = "http://www.balabit.com/network-security/syslog-ng/opensource-logging-system"

LICENSE = "GPLv2 & LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=e0e8658d9be248f01b7933df24dc1408"

DEPENDS = "flex eventlog glib-2.0"

SRC_URI = "http://www.balabit.com/downloads/files/syslog-ng/sources/${PV}/source/${BPN}_${PV}.tar.gz \
           file://syslog-ng.conf \
           file://initscript \
           file://volatiles.03_syslog-ng \
"

inherit autotools systemd pkgconfig update-rc.d update-alternatives

LIBTOOL = "${B}/${HOST_SYS}-libtool"
EXTRA_OEMAKE = "'LIBTOOL=${LIBTOOL}'"

EXTRA_OECONF = " \
    --enable-dynamic-linking \
    --disable-sub-streams \
    --disable-pacct \
    --localstatedir=${localstatedir}/run/${BPN} \
    --sysconfdir=${sysconfdir}/${BPN} \
    --with-module-dir=${libdir}/${BPN} \
"

PACKAGECONFIG ??= "openssl \
    ${@base_contains('DISTRO_FEATURES', 'ipv6', 'ipv6', '', d)} \
    ${@base_contains('DISTRO_FEATURES', 'systemd', 'systemd', '', d)} \
"
PACKAGECONFIG[openssl] = "--enable-ssl,--disable-ssl,openssl,"
PACKAGECONFIG[ipv6] = "--enable-ipv6,--disable-ipv6,,"
PACKAGECONFIG[systemd] = "--enable-systemd --with-systemdsystemunitdir=${systemd_unitdir}/system/,--disable-systemd --without-systemdsystemunitdir,systemd,"
PACKAGECONFIG[linux-caps] = "--enable-linux-caps,--disable-linux-caps,libcap,"
PACKAGECONFIG[pcre] = "--enable-pcre,--disable-pcre,libpcre,"
PACKAGECONFIG[dbi] = "--enable-sql,--disable-sql,libdbi,"
PACKAGECONFIG[libnet] = "--enable-libnet --with-libnet=${STAGING_BINDIR_CROSS},--disable-libnet,libnet,"
PACKAGECONFIG[smtp] = "--enable-smtp --with-libesmtp=${STAGING_LIBDIR},--disable-smtp,libesmtp,"

do_configure_prepend() {
    eval "${@base_contains('DISTRO_FEATURES', 'largefile', '', 'sed -i -e "s/-D_LARGEFILE_SOURCE//" -e "s/-D_FILE_OFFSET_BITS=64//" ${S}/configure.in', d)}"
}

# workaround compile errors on qemuarm when enable-build=debug:
# {standard input}: Assembler messages:
# {standard input}:102: Error: invalid operands (.text and *UND* sections) for `-'
CFLAGS_arm += "-O2"

do_install_append() {
    install -d ${D}/${sysconfdir}/${BPN}
    install ${WORKDIR}/syslog-ng.conf ${D}${sysconfdir}/${BPN}/${BPN}.conf
    install -d ${D}/${sysconfdir}/init.d
    install -m 755 ${WORKDIR}/initscript ${D}/${sysconfdir}/init.d/syslog.${BPN}
    install -d ${D}/${sysconfdir}/default/volatiles/
    install -m 755 ${WORKDIR}/volatiles.03_syslog-ng ${D}/${sysconfdir}/default/volatiles/03_syslog-ng

    # Remove /var/run as it is created on startup
    rm -rf ${D}${localstatedir}/run
}

PACKAGES += "${PN}-tools"

FILES_${PN} = "\
    ${bindir}/* ${sbindir}/* ${libexecdir}/* ${libdir}/lib*${SOLIBS} \
    ${sysconfdir} ${sharedstatedir} ${localstatedir} \
    ${base_bindir}/* ${base_sbindir}/* \
    ${base_libdir}/*${SOLIBS} \
    ${datadir}/${BPN} ${libdir}/${BPN}/*${SOLIBS} \
    ${datadir}/include/scl/ ${datadir}/xsd \
"
FILES_${PN}-dev += "${libdir}/${BPN}/lib*.la ${libdir}/${BPN}/*${SOLIBSDEV}"
FILES_${PN}-tools = "${datadir}/tools"
CONFFILES_${PN} = "${sysconfdir}/${BPN}.conf ${sysconfdir}/scl.conf"

# syslog initscript is handled explicitly because order of
# update-rc.d and update-alternatives is important
RDEPENDS_${PN} += " ${@base_conditional("ONLINE_PACKAGE_MANAGEMENT", "none", "", "update-rc.d", d)}"

RPROVIDES_${PN} += "${PN}-systemd"
RREPLACES_${PN} += "${PN}-systemd"
RCONFLICTS_${PN} += "${PN}-systemd"
SYSTEMD_SERVICE_${PN} = "${PN}.service"

ALTERNATIVE_${PN} = "syslog-init"
ALTERNATIVE_PRIORITY[syslog-init] = "200"
ALTERNATIVE_LINK_NAME[syslog-init] = "${sysconfdir}/init.d/syslog"

INITSCRIPT_NAME = "syslog"
INITSCRIPT_PARAMS = "start 20 2 3 4 5 . stop 90 0 1 6 ."
