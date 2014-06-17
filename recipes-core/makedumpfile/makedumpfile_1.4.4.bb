#
# Copyright (C) 2012 Wind River Systems, Inc.
#
SUMMARY = "VMcore extraction tool"
DESCRIPTION = "\
	This program is used to extract a subset of the memory available either \
	via /dev/mem or /proc/vmcore (for crashdumps). It is used to get memory \
	images without extra uneeded information (zero pages, userspace programs, \
	etc). \
	"
HOMEPAGE = "http://makedumpfile.sourceforge.net"

MDF_ELF_VERSION = "0.137"
MDF_ELF_DIR = "${WORKDIR}/elfutils-${MDF_ELF_VERSION}"

DEPENDS = "bzip2 zlib"
RDEPENDS_${PN}-tools = "perl ${PN}"

PACKAGES =+ "${PN}-tools"
FILES_${PN}-tools = "${bindir}/*.pl"

SRC_URI = "\
	${SOURCEFORGE_MIRROR}/makedumpfile/${BPN}-${PV}.tar.gz \
	http://pkgs.fedoraproject.org/repo/pkgs/elfutils/elfutils-${MDF_ELF_VERSION}.tar.gz;name=elfutils;patchdir=${MDF_ELF_DIR} \
	file://elfutils-${MDF_ELF_VERSION}-files/elfutils-wr-crosscompile_issue.patch;patchdir=${MDF_ELF_DIR} \
	file://elfutils-${MDF_ELF_VERSION}-files/elfutils-remove-unused.patch;patchdir=${MDF_ELF_DIR} \
	file://elfutils-${MDF_ELF_VERSION}-files/elfutils-missing-hdr.patch;patchdir=${MDF_ELF_DIR} \
	file://elfutils-${MDF_ELF_VERSION}-files/elfutils-addr2line.patch;patchdir=${MDF_ELF_DIR} \
	file://elfutils-${MDF_ELF_VERSION}-files/elfutils-fix_for_gcc-4.7.patch;patchdir=${MDF_ELF_DIR} \
	file://elfutils-${MDF_ELF_VERSION}-files/elfutils-add-readelf_no_Werror.patch;patchdir=${MDF_ELF_DIR} \
	file://elfutils-${MDF_ELF_VERSION}-files/elfutils-find-debuginfo.c-initialize-fname.patch;patchdir=${MDF_ELF_DIR} \
	file://elfutils-${MDF_ELF_VERSION}-files/i386_dis.h \
	file://elfutils-${MDF_ELF_VERSION}-files/x86_64_dis.h \
	file://makedumpfile-wr-cross-compile.patch \
	file://makedumpfile-wr-compile-unknown_arch.patch \
	file://elfutils-${MDF_ELF_VERSION}-files/elfutils-ar-fix-memset-usage.patch;patchdir=${MDF_ELF_DIR} \
	"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"
LICENSE = "GPLv2.0"

SRC_URI[md5sum] = "7e26120ccb00c491f39b8eb165602102"
SRC_URI[sha256sum] = "81971278efd31d6189bda6dfbe1c4182d481396522e7514b429a0f77a75d34f4"
SRC_URI[elfutils.md5sum] = "a24690a64268516bd413c4c3fe6c6dd4"
SRC_URI[elfutils.sha256sum] = "f6748374b3052a39138ae4ee4c060123e82b434747740ac5fde469158dabf0a7"

PR = "r1"
SECTION = "base"

# elfutils uses autotools, not makedumpfile!
inherit autotools

EXTRA_OEMAKE = "\
	LINKTYPE=static \
	TARGET=${TARGET_ARCH} \
	ELFUTILS_CFLAGS=-I${WORKDIR}/usr/include \
	ELFUTILS_LDFLAGS=-L${WORKDIR}/${libdir}"

do_configure() {
	cp ${WORKDIR}/elfutils-${MDF_ELF_VERSION}-files/*dis.h ${MDF_ELF_DIR}/libcpu
	cd ${MDF_ELF_DIR} && \
		${CACHED_CONFIGUREVARS} ./configure ${CONFIGUREOPTS} ${EXTRA_OECONF} --enable-dependency-tracking
}

do_compile() {
	cd ${MDF_ELF_DIR} && oe_runmake
	cd ${MDF_ELF_DIR} && make install DESTDIR=${WORKDIR}

	cd ${S} && oe_runmake
}

do_install () {
	mkdir -p ${D}/usr/bin
	install -m 755 ${S}/makedumpfile ${D}/usr/bin
	install -m 755 ${S}/makedumpfile-R.pl ${D}/usr/bin

	mkdir -p ${D}/usr/share/man/man8
	install -m 644 ${S}/makedumpfile.8.gz ${D}/usr/share/man/man8

	mkdir -p ${D}/usr/share/man/man5
	install -m 644 ${S}/makedumpfile.conf.5.gz ${D}/usr/share/man/man5

	mkdir -p ${D}/etc/
	install -m 644 ${S}/makedumpfile.conf ${D}/etc/makedumpfile.conf.sample
}
