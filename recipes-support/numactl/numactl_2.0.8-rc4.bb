#
# Copyright (C) 2012 Wind River Systems, Inc.
#
PR = "${INC_PR}.0"
LICENSE = "GPL-2.0 & LGPL-2.1"

LIC_FILES_CHKSUM = "file://README;beginline=19;endline=32;md5=5644cc3851cb2499f6c48e52fe198bd9"

SRC_URI = "ftp://oss.sgi.com/www/projects/libnuma/download/${BPN}-${PV}.tar.gz \
	  "
SRC_URI[md5sum] = "7d4fbd9e65782363c9bb3c2ead75d8dc"
SRC_URI[sha256sum] = "f4df6387cd4203887e99d00221ecf390d1402887bb81026c4ffcd289448f6a77"

require ${PN}.inc
