#
# Copyright (C) 2012 Wind River Systems, Inc.
#
SUMMARY = "Power management tools (ACPI tools, really)"
DESCRIPTION = "This is a small collection of power management test and \
investigation tools."
HOMEPAGE = "http://www.lesswatts.org/projects/acpi/utilities.php"
SECTION = "utils"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=0636e73ff0215e8d672dc4c32c317bb3"

PR = "r1"

SRC_URI="http://www.lesswatts.org/patches/linux_acpi/pmtools-${PV}.tar.gz \
         file://fix-the-incorrect-include-path-for-acpixtract-Makefi.patch \
         file://do-not-strip-the-acpidump-executable.patch \
         file://pmtools-switch-to-dynamic-buffer-for-huge-ACPI-table.patch \
"

SRC_URI[md5sum] = "10aa00d2ae9f1b4653e3995589a394a3"
SRC_URI[sha256sum] = "91751774976e39f6237efd0326eb35196a9346220b92ad35894a33283e872748"

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
