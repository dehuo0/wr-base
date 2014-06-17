SUMMARY = "tcpslice"
DESCRIPTION = "A tool for extracting parts of a tcpdump packet trace."
HOMEPAGE = "http://ee.lbl.gov"
SECTION = "console/network"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD;md5=3775480a712fc46a69647678acb234cb" 

# The patches have been copied from tcpdump-4.1.1-1.fc14.src.rpm.
#
SRC_URI = "ftp://ftp.ee.lbl.gov/tcpslice-1.2a3.tar.gz \
           file://tcpslice-1.2a3-time.patch \
           file://tcpslice-CVS.20010207-bpf.patch \
           "
SRC_URI[md5sum] = "e329cbeb7e589f132d92c3447c477190"
SRC_URI[sha256sum] = "4096e8debc898cfaa16b5306f1c42f8d18b19e30e60da8d4deb781c8f684c840"

inherit autotools-brokensep

DEPENDS += "libpcap"

# We do not want to autoreconf.  We must specify srcdir as ".".
# We have to set the ac_cv_* cache variables as well as pass the normal
# cross-compilation options to configure!
#
do_configure () {
	oe_runconf \
            --srcdir="." \
            ac_cv_build=${BUILD_SYS} \
            ac_cv_host=${HOST_SYS} \
            ac_cv_target=${HOST_SYS}
}

do_install_prepend () {
	mkdir -p ${D}/usr/sbin
}

