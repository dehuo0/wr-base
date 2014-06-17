#
# Copyright (C) 2012 Wind River Systems, Inc.
#
HOMEPAGE = "http://people.redhat.com/~anderson/"
SUMMARY = "kernel debugging utility, allowing gdb like syntax"
DESCRIPTION = "\
kernel debugging utility, allowing gdb like syntax \
 The core analysis suite is a self-contained tool that can be used to \
 investigate either live systems, or multiple different core dump formats \
 including kdump, LKCD, netdump and diskdump. \
 . \
 o  The tool is loosely based on the SVR4 crash command, but has been \
    completely integrated with gdb in order to be able to display \
    formatted kernel data structures, disassemble source code, etc. \
 . \
 o  The current set of available commands consist of common kernel core \
    analysis tools such as a context-specific stack traces, source code \
    disassembly, kernel variable displays, memory display, dumps of \
    linked-lists, etc.  In addition, any gdb command may be entered, \
    which in turn will be passed onto the gdb module for execution. \
 . \
 o  There are several commands that delve deeper into specific kernel \
    subsystems, which also serve as templates for kernel developers \
    to create new commands for analysis of a specific area of interest. \
    Adding a new command is a simple affair, and a quick recompile \
    adds it to the command menu. \
 . \
 o  The intent is to make the tool independent of Linux version dependencies, \
    building in recognition of major kernel code changes so as to adapt to \
    new kernel versions, while maintaining backwards compatibility. \
	"

SRC_URI = "http://people.redhat.com/~anderson/crash-${PV}.tar.gz"
SRC_URI += "file://make_lib.patch"
SRC_URI += "file://crosscompile.patch"
SRC_URI += "file://gdb_build_jobs.patch"
SRC_URI += "file://support-GRSecurity-in-x86.patch"
SRC_URI += "file://fix-error-about-unused-value.patch"
SRC_URI += "file://gdb-fix-to-use-correct-variable-in-elf32-arm.c.patch"

LIC_FILES_CHKSUM = "file://COPYING3;md5=d32239bcb673463ab874e80d47fae504"
LICENSE = "GPL-3.0"

inherit gettext

SECTION = "base"

set_crash_arch() {
	CRASH_ARCH="unknown"
	case $1 in
	    x86|i*86*)
	    	CRASH_ARCH="X86"
		;;
	    powerpc)
	    	CRASH_ARCH="PPC"
		;;
	    powerpc64)
	    	CRASH_ARCH="PPC64"
		;;
	    x86_64*)
	    	CRASH_ARCH="X86_64"
		;;
	    arm*)
	    	CRASH_ARCH="ARM"
		;;
	esac
}
