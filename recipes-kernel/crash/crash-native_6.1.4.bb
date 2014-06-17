#
# Copyright (C) 2012 Wind River Systems, Inc.
#
include crash.inc

PR = "r0"
BPN = "crash"

SRC_URI[md5sum] = "ef154b618e19d968956e4010068e916a"
SRC_URI[sha256sum] = "e9275d4225b414a1ddf63336cff7160997660d7d382b5ec6c47af993bafbb5a2"

DEPENDS += "ncurses-native zlib-native gettext-native"

# Determine the target arch for the grub modules before the native class
# clobbers TARGET_ARCH.
ORIG_TARGET_ARCH := "${TARGET_ARCH}"

python __anonymous () {
    target = d.getVar('ORIG_TARGET_ARCH', True)
    d.setVar("CRASH_TARGET", target)
}

inherit native

do_configure() {
	set_crash_arch ${CRASH_TARGET}
	if [ "${CRASH_ARCH}" != "unknown" ]; then
		make make_configure
		configure_ok=1
		./configure -t${CRASH_ARCH} -b || configure_ok=0
		if [ $configure_ok -eq 0 ]; then
			touch ${S}/.unsupported_build
		fi
	else
		touch ${S}/.unsupported_build
	fi
}

do_compile() {
	export GDB_EXTRA_CONF_FLAGS="CFLAGS='${BUILD_CFLAGS}' LDFLAGS='${BUILD_LDFLAGS}'"
	export GDB_MAKE_JOBS="${PARALLEL_MAKE}"

	set_crash_arch ${CRASH_TARGET}
	if [ ! -e ${S}/.unsupported_build ]; then
		make target=${CRASH_ARCH} AR=${BUILD_AR} ${PARALLEL_MAKE}
	fi
}

do_install() {
	# note: if crash isn't supported on the host/target combination,
	# nothing is built/installed, but to satisfy the sysroot install
	# option, we create an empty directory
	mkdir -p ${D}${bindir}
	if [ -e crash ]; then
		set_crash_arch ${CRASH_TARGET}
		install crash ${D}${bindir}/crash-${CRASH_ARCH}
	fi
}
