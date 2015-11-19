#
# Copyright (C) 2012 Wind River Systems, Inc.
#
include crash.inc

PR = "r3"

SRC_URI[md5sum] = "ef154b618e19d968956e4010068e916a"
SRC_URI[sha256sum] = "e9275d4225b414a1ddf63336cff7160997660d7d382b5ec6c47af993bafbb5a2"

inherit autotools-brokensep

DEPENDS += "ncurses zlib"
RDEPENDS_${PN} += "binutils"

# crash is only supported for the following arches
COMPATIBLE_HOST = "(x86_64.*|i.86.*|powerpc.*|arm.*)-linux"

# we need this because with mlib, there would be mixed arch
# binaries in the package
INSANE_SKIP_${PN} = "arch build-deps file-rdeps"
INSANE_SKIP_${PN}-dbg = "arch"

python __anonymous () {
    #
    # Crash needs talk to kernel with the same wordsize, so one of the
    # multilib outputs is invalid for use, we disable all useless dependencies
    # and tasks for it, and the files need be copied from ${D} of the other.
    #
    crash_pn = ""
    crash_mlprefix = ""
    target_arch = d.getVar('TRANSLATED_TARGET_ARCH', True)
    kernel_arch = (d.getVar('KARCH', True) or d.getVar('TRANSLATED_TARGET_ARCH', True)).replace("_", "-")
    pn = d.getVar('PN', True)
    bpn = d.getVar('BPN', True)

    # obtain the multilib prefix
    multilibs = d.getVar('MULTILIBS', True) or ""
    for ext in multilibs.split():
        eext = ext.split(':')
        if len(eext) > 1 and eext[0] == 'multilib':
            crash_mlprefix = eext[1] + '-'

    if target_arch != kernel_arch:
        # STRIP is needed by do_package
        d.setVar('DEPENDS', "virtual/%sbinutils" % d.getVar('TARGET_PREFIX', True))
        d.setVar('RDEPENDS_%s' % d.getVar('PN', True), "")
        d.setVarFlag('do_patch', 'noexec', "1")
        d.setVarFlag('do_configure', 'noexec', "1")
        d.setVarFlag('do_compile', 'noexec', "1")

        if pn != bpn:
            crash_pn = bpn
        else:
            crash_pn = crash_mlprefix + bpn

        d.appendVarFlag('do_install', 'depends', ' %s:do_install' % crash_pn)
        d.setVar('CRASH_PN', crash_pn)
        d.setVar('CRASH_ARCH_DISACCORD', "1")
    elif crash_mlprefix:
        #
        # while multilib is supported, the main recipe must be rm_work
        # exclusive for it's depended by the other.
        #
        rm_excludes = (d.getVar('RM_WORK_EXCLUDE', True) or "").split()
        rm_excludes.append(d.getVar('PN', True))
        d.setVar('RM_WORK_EXCLUDE', " ".join(rm_excludes))
}

do_configure() {
	make configure CC="${BUILD_CC}" CONF_FLAGS="${BUILD_CFLAGS}"
}

do_compile() {
	export GDB_EXTRA_CONF_FLAGS="${CONFIGUREOPTS}"
	export GDB_MAKE_JOBS="${PARALLEL_MAKE}"

	set_crash_arch ${TARGET_ARCH}
	if [ "$CRASH_ARCH" != "unknown" ]; then
		make target=$CRASH_ARCH AR="${AR}" ${PARALLEL_MAKE}
		if [ ! -e crash ]; then
			bbnote "crash executable does not exist after do_compile. Exiting."
			exit 1
		fi
	fi
}

do_install() {
	if [ "${CRASH_ARCH_DISACCORD}" = "1" ]; then
		src=`ls -d ${BASE_WORKDIR}/*/${CRASH_PN}/${EXTENDPE}${PV}-${PR}/image | head -1`
		if [ -d $src ]; then
			tar -cf - -C $src . | tar -xf - -C ${D}
		fi
	else
		mkdir -p ${D}${bindir}
		if [ -e crash ]; then
			install crash ${D}${bindir}
		else
			cat > ${D}${bindir}/crash <<EOT;
#!/bin/sh

echo "Crash is unsupported on this architecture"
EOT
			chmod 755 ${D}${bindir}/crash
		fi
	fi
}
