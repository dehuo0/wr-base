#
# Copyright (C) 2014 Wind River Systems, Inc.
#
SUMMARY = "Power management tools (ACPI tools, really)"
DESCRIPTION = "This is a small collection of power management test and \
investigation tools."
HOMEPAGE = "http://www.lesswatts.org/projects/acpi/utilities.php"
SECTION = "utils"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=0636e73ff0215e8d672dc4c32c317bb3"

SRC_URI="http://pkgs.fedoraproject.org/lookaside/pkgs/pmtools/pmtools-${PV}.tar.gz/d5ac3abf71b517ab059bfdc066891d35/pmtools-${PV}.tar.gz \
         file://fix-the-incorrect-include-path-for-acpixtract-Makefi.patch \
         file://do-not-strip-the-acpidump-executable.patch \
         file://pmtools-switch-to-dynamic-buffer-for-huge-ACPI-table.patch \
         file://Makefile-fix-to-build-madt.patch \
"

SRC_URI[md5sum] = "d5ac3abf71b517ab059bfdc066891d35"
SRC_URI[sha256sum] = "2fd79b0954ee5c28c4facb8d161116850a903454c400c77a9355318e2aba8070"

S = "${WORKDIR}/${BPN}"

COMPATIBLE_HOST = "(x86_64.*|i.86.*)-linux"

do_compile() {
	oe_runmake
}

do_install() {
	install -d ${D}${bindir}
	install -m 0755 ${S}/acpidump/acpidump ${D}${bindir}
	install -m 0755 ${S}/acpixtract/acpixtract ${D}${bindir}
	install -m 0755 ${S}/madt/madt ${D}${bindir}
}
