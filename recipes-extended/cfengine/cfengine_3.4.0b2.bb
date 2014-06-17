#
# Copyright (C) 2012 Wind River Systems, Inc.
#
SUMMARY = "CFEngine is an IT infrastructure automation framework"

DESCRIPTION = "CFEngine is an IT infrastructure automation framework \
that helps engineers, system administrators and other stakeholders \
in an IT system to manage and understand IT infrastructure throughout \
its lifecycle. CFEngine takes systems from Build to Deploy, Manage and Audit."

HOMEPAGE = "http://cfengine.com"

LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a924e7b7a263e6787ce19ec015cfffb8"

PR = "r1"

SRC_URI = "http://cfengine.com/source-code/download?file=cfengine-3.4.0b2.tar.gz;downloadfilename=cfengine-3.4.0b2.tar.gz \
           file://change-hard-coded-lib-path.patch \
          "
SRC_URI[md5sum] = "eff6a1fd49ee5f00272b7b8ff4c95ef4"
SRC_URI[sha256sum] = "5150125b56f1235e90b29fbe351a0d09b2148cdcf9ad2c78840d2ce08ff20999"

DEPENDS="libpcre openssl tokyocabinet"

inherit autotools

#disable mysql and postgresql
#if you want to enable them,you can over-ride this EXTRA_OECONF in a bbappend file.
#To enable mysql, please remove '--with-mysql=no' and add 'CFLAGS='-I${STAGING_INCDIR}/mysql' 
EXTRA_OECONF += "--with-libxml2=no \
                --with-mysql=no \
                --with-postgresql=no \
                "

FILES_${PN} += "/usr/share/CoreBase"
