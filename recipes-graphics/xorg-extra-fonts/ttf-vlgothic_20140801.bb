SUMMARY = "Japanese TrueType fonts from Vine Linux"
AUTHOR = "Contributor: noonov <noonov@gmail.com>"
HOMEPAGE = "http://vlgothic.dicey.org/"
LICENSE = "VLGOTHIC"
LICENSE_FLAGS = "commercial"
LIC_FILES_CHKSUM = "file://LICENSE.en;md5=66ecd0fd7e4da6246fa30317c7b66755"
PR = "r0"

require ttf.inc

_mirror="jaist"
# keihanna, jaist, iij, osdn

SRC_URI = "http://${_mirror}.dl.sourceforge.jp/vlgothic/61731/VLGothic-${PV}.tar.xz"
S = "${WORKDIR}/VLGothic"

do_install_append () {
    install -d ${D}${datadir}/fonts/TTF/
    install -d ${D}${sysconfdir}/fonts/conf.d/
    install -m 0644 *.ttf ${D}${datadir}/fonts/TTF/
    install -D -m644 ${S}/LICENSE_E.mplus ${D}${datadir}/licenses/${PN}/COPYING_MPLUS.txt
    install -D -m644 ${S}/README.sazanami ${D}${datadir}/licenses/${PN}/COPYING_SAZANAMI.txt
    install -D -m644 ${S}/LICENSE.en ${D}${datadir}/licenses/${PN}/COPYING_VLGOTHIC.txt
}

PACKAGES = "${PN}"
FONT_PACKAGES = "${PN}"

FILES_${PN} = "${datadir}/fonts ${sysconfdir} ${datadir}/licenses/${PN}"

SRC_URI[md5sum] = "ab35ef72b6f755c26975633519a5b1aa"
SRC_URI[sha256sum] = "fe986a087f82db8d16cb3360068f004647146a68356ce9f34d8ad3cd0f8c9b64"
