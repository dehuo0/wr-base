#
# Copyright (C) 2012-2013 Wind River Systems Inc.
#

DESCRIPTION = "Core packages for Linux/GNU (non-busybox) runtime images"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

PR = "r0"

ALLOW_EMPTY_${PN} = "1"
ALLOW_EMPTY_${PN}-net = "1"

PACKAGES += "${PN}-net ${PN}-net-dbg ${PN}-net-dev"

# Core userspace package list based roughly on oe-core's
# packagegroup-core-initscripts and packagegroup-core-basic-utils:
VIRTUAL-RUNTIME_syslog ?= "sysklogd"
RDEPENDS_${PN} = "\
    acl \
    attr \
    bash \
    bc \
    coreutils \
    cpio \
    e2fsprogs \
    ed \
    findutils \
    gawk \
    grep \
    logrotate \
    mingetty \
    mktemp \
    ncurses \
    procps \
    psmisc \
    sed \
    sudo \
    ${VIRTUAL-RUNTIME_syslog} \
    tar \
    time \
    udev \
    util-linux \
    util-linux-mount \
    util-linux-umount \
    util-linux-fstrim \
    util-linux-hwclock \
    vim \
    kmod \
    which \
    iproute2 \
    "

# Minimal network environment
RDEPENDS_${PN}-net = "\
    dhcp-client \
    ethtool \
    net-tools \
    openssh \
    openssh-scp \
    openssh-ssh \
    openssh-sshd \
    "
