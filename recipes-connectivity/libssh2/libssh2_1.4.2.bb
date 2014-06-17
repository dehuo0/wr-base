#
# Copyright (C) 2012 Wind River Systems, Inc.
#
DESCRIPTION = "libssh2 is a client-side C library implementing the SSH2 protocol"
HOMEPAGE = "http://www.libssh2.org/"
BUGTRACKER = "http://trac.libssh2.org/"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYING;md5=d00afe44f336a79a2ca7e1681ce14509"

SECTION = "connectivity"
DEPENDS = "libgcrypt openssl"
PR = "r1"

SRC_URI = "http://www.libssh2.org/download/libssh2-${PV}.tar.gz \
           file://sshdwrap \
           file://host \
           file://host.pub \
           file://user \
           file://user.pub"

SRC_URI[md5sum] = "42e2b3796ac07fc1dbafc7abcc002cd3"
SRC_URI[sha256sum] = "418c09061fb9c3c26fec391a35a062780b21b3199885e3b27054619582469bd3"

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

