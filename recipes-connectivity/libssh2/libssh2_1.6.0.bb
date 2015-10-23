#
# Copyright (C) 2012 Wind River Systems, Inc.
#
DESCRIPTION = "libssh2 is a client-side C library implementing the SSH2 protocol"
HOMEPAGE = "http://www.libssh2.org/"
BUGTRACKER = "http://trac.libssh2.org/"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYING;md5=c5cf34fc0acb44b082ef50ef5e4354ca"

SECTION = "connectivity"
DEPENDS = "libgcrypt openssl"

SRC_URI = "http://www.libssh2.org/download/libssh2-${PV}.tar.gz \
           file://sshdwrap \
           file://host \
           file://host.pub \
           file://user \
           file://user.pub \
           "

SRC_URI[md5sum] = "00aabd6e714a5f42a4fb82ace20db1dd"
SRC_URI[sha256sum] = "5a202943a34a1d82a1c31f74094f2453c207bf9936093867f41414968c8e8215"

inherit autotools

EXTRA_OECONF = "--without-openssl --with-libgcrypt"

do_install_append() {
    mkdir -p ${D}/opt/${BPN}-test/etc/
    install -m 0755 ${WORKDIR}/sshdwrap ${D}/opt/${BPN}-test/
    install -m 0755 ${WORKDIR}/host.pub ${D}/opt/${BPN}-test/etc/
    install -m 0755 ${WORKDIR}/user.pub ${D}/opt/${BPN}-test/etc/
    install -m 0600 ${WORKDIR}/host ${D}/opt/${BPN}-test/etc/
    install -m 0600 ${WORKDIR}/user ${D}/opt/${BPN}-test/etc/

}

PACKAGES += "${PN}-testing"
FILES_${PN}-testing += "/opt/${BPN}-test/*"

