#
# Copyright (C) 2013 Wind River Systems Inc.
#
# This package matches a FEATURE_PACKAGES_packagegroup-wr-core-mail definition in
# wrlinux-image.bbclass that may be used to customize an image by
# adding "wr-core-mail" to IMAGE_FEATURES.
#

DESCRIPTION = "Wind River Linux core package group: mail"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

PR = "r0"

ALLOW_EMPTY_${PN} = "1"

# It doesn't work with the newer db_6.0.30, but rpm requires this
# version, so disable atm.
# RDEPENDS_${PN} = " \
#    postfix \
#    "
