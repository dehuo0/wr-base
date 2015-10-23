#
# Copyright (C) 2012 Wind River Systems, Inc.
#
#From:git.openembedded.org/openembedded/recipes/inotify-tool/inotify-tools_3.13.bb
#Lastest commit: b905c8cb7bb09f9364880cfcee8b8bdb950557c5
DESCRIPTION = "command line utilities for the Linux inotify filesystem change notification system."
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=ac6c26e52aea428ee7f56dc2c56424c6"
AUTHOR = "Rohan McGovern"
HOMEPAGE = "http://inotify-tools.sourceforge.net/"

EXTRA_OECONF = "--disable-doxygen"

SRC_URI = "http://github.com/downloads/rvoicilas/inotify-tools/inotify-tools-${PV}.tar.gz \
           file://fix-system-call-number.patch \
          "

PR = 'r0'

inherit autotools-brokensep


SRC_URI[md5sum] = "b43d95a0fa8c45f8bab3aec9672cf30c"
SRC_URI[sha256sum] = "222bcca8893d7bf8a1ce207fb39ceead5233b5015623d099392e95197676c92f"
