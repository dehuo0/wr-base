#
# Copyright (C) 2012 Wind River Systems, Inc.
#
DESCRIPTION = "Tools for performance analysis."
HOMEPAGE = "http://lmbench.sourceforge.net/"
SECTION = "console/utils"
RDEPENDS_${PN} = "perl"

LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"
PR = "r9"

inherit autotools-brokensep

SRC_URI = "${SOURCEFORGE_MIRROR}/lmbench/lmbench-${PV}.tgz \
	   file://lmbench-run \
	   file://lmbench-3.0-a9_wr_integration.patch \
	   file://fix-WIND00185963-lmbench-memory-check-failed.patch \
	   file://lmbench_result_html_report.patch \
	   file://fix-WIND00303833.patch \
	   file://wr-lmbench-test.sh \
           file://upload-rth.sh \
	   file://upload-wrts.sh \
           file://dealt_log.sh \
           file://README \
           file://generate_report.sh \
           file://scripts/sysinfo_lib.sh \
           file://scripts/utility.sh \
           file://config/default_case_conf \
           file://config/default_group_conf \
           file://config/template \
"


SRC_URI[md5sum] = "b3351a3294db66a72e2864a199d37cbf"
SRC_URI[sha256sum] = "cbd5777d15f44eab7666dcac418054c3c09df99826961a397d9acf43d8a2a551"

EXTRA_OEMAKE = 'CC="${CC}" AR="${AR}" RANLIB="${RANLIB}" CFLAGS="${CFLAGS}" \
		LDFLAGS="${LDFLAGS}" LD="${LD}" OS="linux" \
		TARGET="${TARGET_OS}" BASE="/opt/lmbench"'

do_configure() {
	:
}

do_compile () {
	. ${CONFIG_SITE}
	if [ X"$ac_cv_uint" = X"yes" ]; then
		CFLAGS="${CFLAGS} -DHAVE_uint"
	fi
	install -d ${S}/bin/linux
	oe_runmake -C src
}

do_install () {
	install -d ${D}/opt/lmbench/bin ${D}${mandir} \
		   ${D}/opt/lmbench/scripts
	oe_runmake 'BASE=${D}/opt/lmbench' \
		    -C src install
	mv ${D}/opt/lmbench/man/* ${D}${mandir}/
	install -m 0755 ${WORKDIR}/lmbench-run ${D}/opt/lmbench/bin/linux/
	sed -i -e 's,^SHAREDIR=.*$,SHAREDIR=${datadir}/${BPN},;' \
	       -e 's,^BINDIR=.*$,BINDIR=${libdir}/${BPN},;' \
	       -e 's,^CONFIG=.*$,CONFIG=$SHAREDIR/`$SCRIPTSDIR/config`,;' \
	       ${D}/opt/lmbench/bin/linux/lmbench-run
	install -m 0755 ${S}/scripts/lmbench ${D}/opt/lmbench/bin/linux/
	install -m 0755 ${S}/scripts/* ${D}/opt/lmbench/scripts
	install -d ${D}/opt/benchmark/os/wr-lmbench
        install -m 0755 ${WORKDIR}/wr-lmbench-test.sh ${D}/opt/benchmark/os/wr-lmbench
        install -m 0755 ${WORKDIR}/upload-rth.sh ${D}/opt/benchmark/os/wr-lmbench
	install -m 0755 ${WORKDIR}/upload-wrts.sh ${D}/opt/benchmark/os/wr-lmbench
        install -m 0755 ${WORKDIR}/dealt_log.sh ${D}/opt/benchmark/os/wr-lmbench
	install -m 0755 ${WORKDIR}/generate_report.sh ${D}/opt/benchmark/os/wr-lmbench
	install -m 0664 ${WORKDIR}/README ${D}/opt/benchmark/os/wr-lmbench/
       	cp -r ${WORKDIR}/config ${D}/opt/benchmark/os/wr-lmbench/
        cp -r ${WORKDIR}/scripts ${D}/opt/benchmark/os/wr-lmbench/

}

FILES_${PN} += "/opt/lmbench/bin /opt/lmbench/scripts /opt/benchmark"
FILES_${PN}-dbg += "/opt/lmbench/bin/linux/.debug"
FILES_${PN}-staticdev += "/opt/lmbench/lib /opt/lmbench/include /opt/lmbench/man"
