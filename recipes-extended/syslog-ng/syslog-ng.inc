#
# Copyright (C) 2012 Wind River Systems, Inc.
#
SUMMARY = "Alternative system logger daemon"
DESCRIPTION = "syslog-ng, as the name shows, is a syslogd replacement, but with new \
functionality for the new generation. The original syslogd allows \
messages only to be sorted based on priority/facility pairs; syslog-ng \
adds the possibility to filter based on message contents using regular \
expressions. The new configuration scheme is intuitive and powerful. \
Forwarding logs over TCP and remembering all forwarding hops makes it \
ideal for firewalled environments."

SRC_URI = "http://www.balabit.com/downloads/files/${BPN}/open-source-edition/${PV}/source/${BPN}_${PV}.tar.gz \
           file://syslog-ng.conf \
           file://initscript \
           file://volatiles.03_syslog-ng \
           file://fix-invalid-ownership.patch \
           file://fix-config-libnet.patch \
           file://fix-the-framed-server-free-method.patch \
           file://fix-memory-leak-on-reload.patch \
           file://fix-memory-leak-in-persist_state_renam.patch \
           file://free-global-LogTemplateOptions.patch \
           file://afsql-afsql_dd_insert_db-refactor.patch \
           file://deinit-the-new-config-when-reverting-to-the.patch \
           file://fix-a-memory-leak-in-log_driver_free.patch \
           file://logwriter-fixed-a-double-free-issue-in-case-of-a-flu.patch \
           file://logwriter-fixed-an-infinite-loop-in-LogWriter.patch \
           file://logwriter-still-free-the-unconsumed-item.patch \
           file://Fix-the-memory-leak-problem-for-mutex.patch \
           file://Fix-the-memory-leak-problem-when-HAVE_ENVIRON-defined.patch \
           file://LogProtoServer-fixed-a-use-after-free-after-reload.patch \
           file://syslog-ng-verify-the-list-before-del.patch \
          "

DEPENDS = "flex eventlog glib-2.0"

# syslog initscript is handled explicitly because order of update-rc.d and update-alternatives is important
DEPENDS_append = " update-rc.d update-rc.d-native"
RDEPENDS_${PN}_append = " ${@base_conditional("ONLINE_PACKAGE_MANAGEMENT", "none", "", "update-rc.d", d)}"

INC_PR = "r4"

inherit autotools-brokensep pkgconfig update-rc.d update-alternatives

ALTERNATIVE_${PN} = "syslog-init"
ALTERNATIVE_PRIORITY[syslog-init] = "200"
ALTERNATIVE_LINK_NAME[syslog-init] = "${sysconfdir}/init.d/syslog"

INITSCRIPT_NAME = "syslog"
INITSCRIPT_PARAMS = "start 20 2 3 4 5 . stop 90 0 1 6 ."

noipv6 = "${@base_contains('DISTRO_FEATURES', 'ipv6', '', '--disable-ipv6', d)}"

PACKAGECONFIG ??= "openssl"

PACKAGECONFIG[openssl] = "--enable-ssl,--disable-ssl,openssl,"
PACKAGECONFIG[linux-caps] = "--enable-linux-caps,--disable-linux-caps,libcap,"
PACKAGECONFIG[pcre] = "--enable-pcre,--disable-pcre,libpcre,"
PACKAGECONFIG[dbi] = "--enable-sql,--disable-sql,libdbi,"
PACKAGECONFIG[libnet] = "--enable-libnet --with-libnet=${STAGING_BINDIR_CROSS},--disable-libnet,libnet,"
PACKAGECONFIG[smtp] = "--enable-smtp --with-libesmtp=${STAGING_LIBDIR},--disable-smtp,libesmtp,"

EXTRA_OECONF = " \
   --enable-dynamic-linking \
   ${noipv6} \
   --disable-sub-streams \
   --localstatedir=${localstatedir}/run/${BPN} \
   --sysconfdir=${sysconfdir}/${BPN}"

# workaround compile errors on qemuarm when enable-build=debug:
# {standard input}: Assembler messages:
# {standard input}:102: Error: invalid operands (.text and *UND* sections) for `-'
CFLAGS_arm += "-O2"

do_configure_prepend() {

	eval "${@base_contains('DISTRO_FEATURES', 'largefile', '', 'sed -i -e "s/-D_LARGEFILE_SOURCE//" -e "s/-D_FILE_OFFSET_BITS=64//" ${S}/configure.in', d)}"
   
   # Fix the libtool mismatch error
	autoreconf -f -i -v
}

do_install_append() {
        install -d ${D}/${sysconfdir}/${BPN}
        install ${WORKDIR}/syslog-ng.conf ${D}/${sysconfdir}/${BPN}/${BPN}.conf
        install -d ${D}/${sysconfdir}/init.d
        install -m 755 ${WORKDIR}/initscript ${D}/${sysconfdir}/init.d/syslog.${BPN}
        install -d ${D}/${sysconfdir}/default/volatiles/
        install -m 755 ${WORKDIR}/volatiles.03_syslog-ng ${D}/${sysconfdir}/default/volatiles/03_syslog-ng

        # Remove /var/run as it is created on startup
        rm -rf ${D}${localstatedir}/run
}

FILES_${PN} = "${bindir}/* ${sbindir}/* ${libexecdir} ${libexecdir}/* ${libdir}/lib*.so.* \
            /usr/lib/${BPN}/lib*.so.* ${LIBDIR}/${BPN}/*.so.* ${base_libdir}/*.so.* \
            ${sysconfdir} ${sharedstatedir} ${localstatedir} \
            ${base_bindir}/* ${base_sbindir}/* \
            ${datadir}/${BPN} ${datadir}/include/scl/ ${datadir}/xsd ${datadir}/tools/*" 

# This overcomes the syslog-ng rdepends on syslog-ng-dev QA Error
PACKAGES =+ "${PN}-libs ${PN}-libs-dev ${PN}-libs-dbg"
FILES_${PN}-libs = "${libdir}/${BPN}/*.so /usr/lib/${BPN}/*.so ${libdir}/libsyslog-ng-*.so*"
FILES_${PN}-libs-dev = "${libdir}/${BPN}/lib*.la /usr/lib/${BPN}/*.la"
FILES_${PN}-libs-dbg = "/usr/lib/${BPN}/.debug /usr/lib/${BPN}/.debug/lib*${SOLIBS}"
INSANE_SKIP_${PN}-libs = "dev-so libdir"
INSANE_SKIP_${PN}-libs-dbg = "libdir"
RDEPENDS_${PN} += "${PN}-libs"

CONFFILES_${PN} = "${sysconfdir}/${BPN}.conf ${sysconfdir}/scl.conf"
