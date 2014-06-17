#
# Copyright (C) 2012 Wind River Systems, Inc.
#
SUMMARY = "Development package for building Applications that use numa"
DESCRIPTION = \
"Simple NUMA policy support. It consists of a numactl program to run \
other programs with a specific NUMA policy and a libnuma to do \
allocations with NUMA policy in applications."

SECTION = "apps"
INC_PR = "r1"

inherit autotools-brokensep

PACKAGES =+ "${PN}-tests"
RDEPENDS_${PN} = "perl"

FILES_${PN} = "${libdir}/libnuma.so.1 \
               ${bindir}/numactl ${bindir}/numademo ${bindir}/numastat \
               ${bindir}/memhog ${bindir}/migspeed ${bindir}/migratepages \
              "

FILES_${PN}-dev = "${libdir}/libnuma.so \
                   ${includedir}/numa.h ${includedir}/numaif.h \
                   ${includedir}/numacompat1.h	\
                  "

FILES_${PN}-staticdev = "${libdir}/libnuma.a"

FILES_${PN}-doc = "${mandir}/man3/*.3* ${mandir}/man8/*.8*"

FILES_${PN}-tests = "/opt/${BPN}-tests/*"
FILES_${PN}-dbg += "/opt/${BPN}-tests/.debug/*"
SECTION_${PN}-tests = "test"
SUMMARY_${PN}-tests = "${SUMMARY} - Regression test files"

# ARM does not currently support NUMA
COMPATIBLE_HOST = "^((?!arm).*)$"

do_install() {
        oe_runmake DESTDIR=${D} prefix=${D}/usr libdir=${D}/${libdir} install
	#remove the empty man2 directory
	rm -r ${D}${mandir}/man2

	#install tests binaries
        local test_binaries="checkaffinity checktopology distance	\
		ftok mbind_mig_pages migrate_pages move_pages mynode	\
		nodemap pagesize prefered printcpu randmap realloc_test	\
		regress regress2 runltp shmtest tbitmap tshared"

	[ ! -d ${D}/opt/${BPN}-tests ] && mkdir -p ${D}/opt/${BPN}-tests
	for i in $test_binaries; do
		install -m 0755 ${S}/test/$i ${D}/opt/${BPN}-tests
	done 
}

