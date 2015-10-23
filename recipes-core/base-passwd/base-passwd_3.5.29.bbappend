#
# Copyright (C) 2012 Wind River Systems, Inc.
#
# LOCAL REV: WR specific home directory and passwd for root
#
# optionally set root password to root
#

PR .= ".1"

FILESEXTRAPATHS_prepend := "${THISDIR}:"

SRC_URI += "${@base_contains('DISTRO_FEATURES', 'empty-root-password', '', \
              'file://passwd_master.patch',d)}"
