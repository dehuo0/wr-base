SUMMARY = "Fast samples-based log normalization library"
DESCRIPTION = "Briefly described, liblognorm is a tool to normalize log data."
 
HOMEPAGE = "http://www.liblognorm.com"
SECTION = "base"

LICENSE = "LGPL-2.1+"
LIC_FILES_CHKSUM = "file://COPYING;md5=ab3f9ea4e6bdde4a8c569bd2d31f93b1"

SRC_URI = "http://www.liblognorm.com/files/download/${BPN}-${PV}.tar.gz\
           file://liblognorm-parallel-make.patch \
"

inherit autotools pkgconfig

DEPENDS += "libee libestr"

PACKAGECONFIG[debug] = "--enable-debug,--disable-debug,,"
