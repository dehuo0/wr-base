#
# Copyright (C) 2012 Wind River Systems, Inc.
#
SUMMARY = "Intel ASL compiler/decompiler"
DESCRIPTION = "iasl compiles ASL (ACPI Source Language) into AML (ACPI Machine \
Language). This AML is suitable for inclusion as a DSDT in system \
firmware. It also can disassemble AML, for debugging purposes. \
"
HOMEPAGE = "https://www.acpica.org/"
SECTION = "devel"
LICENSE = "intel-acpi"
LIC_FILES_CHKSUM = "file://aslcompiler.h;beginline=8;endline=115;md5=d7d6186ffb0917e0983f0e72d5e54aeb"

PR = "r0"

DEPENDS = "bison flex"

SRC_URI = "https://www.acpica.org/download/acpica-unix-${PV}.tar.gz \
           file://iasl.1 \
"

SRC_URI[md5sum] = "f80f0b079af06134769a2642b490cd30"
SRC_URI[sha256sum] = "f3af776f968c29daa4f061d594d641297f3838805307d243385785b7326d324c"

S = "${WORKDIR}/acpica-unix-${PV}/compiler"

PARALLEL_MAKE = ""
CFLAGS += "-D_LINUX -DACPI_ASL_COMPILER -I../include -I../compiler"

COMPATIBLE_HOST = "(x86_64.*|i.86.*)-linux"

do_install() {
	install -d ${D}${bindir} ${D}${mandir}/man1
	install -m 0755 ${S}/iasl ${D}${bindir}
	install -m 0644 ${WORKDIR}/iasl.1 ${D}${mandir}/man1
}
