#
# Copyright (C) 2014 Wind River Systems, Inc.
#
SUMMARY = "Intel ASL compiler/decompiler"
DESCRIPTION = "iasl compiles ASL (ACPI Source Language) into AML (ACPI Machine \
Language). This AML is suitable for inclusion as a DSDT in system \
firmware. It also can disassemble AML, for debugging purposes. \
"
HOMEPAGE = "https://www.acpica.org/"
SECTION = "devel"
LICENSE = "intel-acpi"
LIC_FILES_CHKSUM = "file://source/compiler/aslcompiler.h;beginline=7;endline=114;md5=db853cc7b812206d2dca07ab0b893478"

DEPENDS = "bison-native flex-native"

SRC_URI = "https://www.acpica.org/download/acpica-unix-${PV}.tar.gz \
           file://iasl.1 \
           file://Make-CC-definition-conditional.patch \
"

SRC_URI[md5sum] = "ae8e83b353510a73f24dc43840f6a662"
SRC_URI[sha256sum] = "9afbaa84cf3e36e41c4746ed892aee5094dbdc8cc719d89583a81308c284ce84"

S = "${WORKDIR}/acpica-unix-${PV}/"

PARALLEL_MAKE = ""
CFLAGS += "-D_LINUX -DACPI_ASL_COMPILER -I../include -I../compiler"

COMPATIBLE_HOST = "(x86_64.*|i.86.*)-linux"

# By setting NOOPT we suppress forcing -O2 and setting _FORTIFY_SOURCE=2.  Let the
# optimization and security cflags set them.
#
do_compile() {
        oe_runmake iasl NOOPT=TRUE
}

do_install() {
	install -d ${D}${bindir} ${D}${mandir}/man1
	install -m 0755 ${S}/generate/unix/bin/iasl ${D}${bindir}
	install -m 0644 ${WORKDIR}/iasl.1 ${D}${mandir}/man1
}
