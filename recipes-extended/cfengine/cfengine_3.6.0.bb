#
# Copyright (C) 2014 Wind River Systems, Inc.
#
SUMMARY = "CFEngine is an IT infrastructure automation framework"

DESCRIPTION = "CFEngine is an IT infrastructure automation framework \
that helps engineers, system administrators and other stakeholders \
in an IT system to manage and understand IT infrastructure throughout \
its lifecycle. CFEngine takes systems from Build to Deploy, Manage and Audit."

HOMEPAGE = "http://cfengine.com"

LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b15863f939fe7fa545a6f43c559cd416"

SRC_URI = "https://s3.amazonaws.com/cfengine.package-repos/tarballs/${BPN}-${PV}.tar.gz \
           file://change-hard-coded-lib-path.patch \
           file://Redmine-5874-Do-not-compile-users-promises-if-pw-fun.patch \
           file://Redmine-5874-Add-without-pam-option-to-turn-off-comp.patch \
          "
SRC_URI[md5sum] = "42b0d3a90a1b60bf25cf63ccd6366f59"
SRC_URI[sha256sum] = "bf1a73ebd9b7ca2cb703cc78bb289a097493e787e1658c23e0a5733c49a72e24"

inherit autotools

PACKAGECONFIG ??= "libpcre openssl tokyocabinet \
                   ${@bb.utils.contains('DISTRO_FEATURES', 'pam', 'pam', '', d)} \
"
PACKAGECONFIG[libxml2] = "--with-libxml2=yes,--with-libxml2=no,libxml2,"
PACKAGECONFIG[mysql] = "--with-mysql=yes,--with-mysql=no,mysql,"
PACKAGECONFIG[postgresql] = "--with-postgresql=yes,--with-postgresql=no,postgresql,"
PACKAGECONFIG[acl] = "--with-libacl=yes,--with-libacl=no,acl,"
PACKAGECONFIG[libvirt] = "--with-libvirt=yes,--with-libvirt=no,libvirt,"
PACKAGECONFIG[libpcre] = "--with-pcre=yes,--with-pcre=no,libpcre,"
PACKAGECONFIG[openssl] = "--with-openssl=yes,--with-openssl=no,openssl,"
PACKAGECONFIG[tokyocabinet] = "--with-tokyocabinet=yes,--with-tokyocabinet=no,tokyocabinet,"
PACKAGECONFIG[qdbm] = "--with-qdbm=yes,--with-qdbm=no,,"
PACKAGECONFIG[lmdb] = "--with-lmdb=yes,--with-lmdb=no,lmdb,"
PACKAGECONFIG[pam] = "--with-pam=yes,--with-pam=no,libpam,"

RDEPENDS_${PN} += "${BPN}-masterfiles"
