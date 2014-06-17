#
# Copyright (C) 2012 Wind River Systems, Inc.
#
PR = "${INC_PR}.0"
LICENSE = "GPL-2.0 & LGPL-2.1"

LIC_FILES_CHKSUM = "file://README;beginline=19;endline=32;md5=5644cc3851cb2499f6c48e52fe198bd9"

SRC_URI = "ftp://oss.sgi.com/www/projects/libnuma/download/${BPN}-${PV}.tar.gz \
	  "
SRC_URI[md5sum] = "dadc81f3623475b14ca5110662ba3b04"
SRC_URI[sha256sum] = "0a1e237a98649c5cd2db7c684afe33bb81d290b406cf00d0469bdba9a11b39ef"

require ${PN}.inc
