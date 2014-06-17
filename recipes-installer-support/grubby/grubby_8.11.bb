
LICENSE = "GPLv2+"

LIC_FILES_CHKSUM = "file://COPYING;md5=892f569a555ba9c07a568a7c0c4fa63a"

DEPENDS = "popt util-linux"

SRC_URI = "http://git.fedorahosted.org/cgit/grubby.git/snapshot/${BPN}-${PV}-1.tar.bz2"

SRC_URI[md5sum] = "b3241a14901d27a520b50ebc7398948c"
SRC_URI[sha256sum] = "2c3b9bd302edbaa2e24492511435054d7ed731d6417e3654592449aa82e1db9c"

S = "${WORKDIR}/${BPN}-${PV}-1"

RDEPENDS_${PN} += "dracut"

inherit autotools-brokensep

EXTRA_OEMAKE = "'CC=${CC}'"

COMPATIBLE_HOST = '(x86_64.*|i.86.*)-(linux|freebsd.*)'
