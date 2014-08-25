#
# Copyright (C) 2012 - 2014 Wind River Systems Inc.
#
# Deploy native recipes' sstate-cache files which are in
# ${B}/sstate-cache:
# - populate_lic.tgz
# - populate_lic.tgz.siginfo
# - populate_sysroot.tgz
# - populate_sysroot.tgz.siginfo
# - package_qa.tgz
# - package_qa.tgz.siginfo
#

DESCRIPTION = "A list of commonly used native packages."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

PR = "r2"

inherit native

do_fetch[noexec] = "1"
do_unpack[noexec] = "1"
do_patch[noexec] = "1"
do_configure[noexec] = "1"
do_compile[noexec] = "1"
do_install[noexec] = "1"
do_populate_sysroot[noexec] = "1"
do_populate_lic[noexec] = "1"

# Make do_populate_lic, do_package_qa and do_populate_sysroot done
do_build[deptask] += "do_populate_lic do_package_qa do_populate_sysroot"

NATIVE_SS_OUTPUT_DIR ?= "${DEPLOY_DIR}/sstate-native"
NATIVE_SS_OUTPUT_NAME ?= "host-tools-${BUILD_ARCH}"
NATIVE_SS_OUTPUT ?= "${DEPLOY_DIR}/sstate-native/host-tools-${BUILD_ARCH}.tar.gz"

# The list of common native packages
require wr-common-packages-native.inc

do_deploy_sstate () {
    sstc="${B}/sstate-cache"
    if [ -d "$sstc" ]; then
        # Check the sstate cache files
        found="`find $sstc -name '*_configure.tgz.siginfo' | wc -l`"
        if [ "$found" != "0" ]; then
            bbfatal "Found configure sstate files in $sstc, but should not!"
        fi
        cd ${B} || exit 1
        mkdir -p ${NATIVE_SS_OUTPUT_DIR} || exit 1
        bbnote "Creating ${NATIVE_SS_OUTPUT}"
        # Remove the sub-directories and put the sstate files in the top dir.
        find sstate-cache -type f -o -type l -exec mv {} sstate-cache/ \;
        find sstate-cache -depth -type d -a ! -name 'sstate-cache' -exec rm -fr {} \;
        tar --exclude='*.tgz.done' --exclude='*.siginfo.done' \
            --transform 's#sstate-cache#${NATIVE_SS_OUTPUT_NAME}#' \
            -czhf "${NATIVE_SS_OUTPUT}" sstate-cache
    else
        bbnote "No $sstc, nothing to deploy"
    fi
}

do_deploy_sstate[deptask] += "do_populate_lic do_package_qa do_populate_sysroot"
addtask deploy_sstate

# Create the symlink in the export directory
do_deploy_sstate[postfuncs] += "wrl_host_tools_symlink"

python wrl_host_tools_symlink (){
    target = d.getVar('NATIVE_SS_OUTPUT', True)
    wrlexport = d.getVar('WRL_EXPORT_DIR', True)
    buildarch = d.getVar('BUILD_ARCH', True)
    link = "%s/host-tools-%s.tar.gz" % (wrlexport, buildarch)
    wrl_symlink(target, link, d)
}
