SUMMARY = "serial graphics adapter bios option rom for x86"
DESCRIPTION = "serial graphics adapter bios option rom for x86"
HOMEPAGE = "http://code.google.com/p/sgabios/"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=3b83ef96387f14655fc854ddc3c6bd57"

S = "${WORKDIR}/trunk/"

SECTION = "tools"
PR = "r0"

# Update SRC_URI and move sgabios tarball to ${LAYERDIR}/downloads
SRC_URI = "svn://sgabios.googlecode.com/svn;module=trunk;rev=8;protocol=http"

inherit native

do_compile() {
	make
}

do_install() {
	mkdir -p ${D}/${STAGING_DATADIR_NATIVE}/qemu/
	cp ${S}/sgabios.bin ${D}/${STAGING_DATADIR_NATIVE}/qemu/
}

