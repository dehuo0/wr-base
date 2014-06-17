#
# Copyright (C) 2012 Wind River Systems, Inc.
#
SUMMARY = "fetch and change SCSI mode pages"
DESCRIPTION = "The sdparm utility accesses and optionally modifies \
SCSI devices' mode page and inquiry data."
HOMEPAGE = "http://sg.danny.cz/sg/sdparm.html"
SECTION = "console/utils"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=ecab6c36b7ba82c675581dd0afde36f7 \
                    file://lib/BSD_LICENSE;md5=1d52f4a66f1e0ed96776bf354ab7a2ed"
PR = "r0"

DEPENDS="sg3-utils"
SRC_URI = "http://sg.danny.cz/sg/p/${BPN}-${PV}.tgz \
           file://sdparm-sbin.patch \
           file://sdparm-include.patch \
           "

SRC_URI[md5sum] = "c807f9db3dd7af175214be0d7fece494"
SRC_URI[sha256sum] = "c1d257ba97f37f99a602e889a73e4c62f9b374c5b979e33fc06963462f0c0e41"

inherit autotools
