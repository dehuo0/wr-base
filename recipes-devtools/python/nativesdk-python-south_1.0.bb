DESCRIPTION = "An intelligent database migrations library for Django"
HOMEPAGE = "https://pypi.python.org/pypi/South"
SECTION = "devel/python"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://PKG-INFO;md5=c64fbb5a2a73a72ee60eaced80559d2e"

lcl_SOURCE = "South-${PV}"

SRC_URI = "https://pypi.python.org/packages/source/S/South/${lcl_SOURCE}.tar.gz"

SRC_URI[md5sum] = "6819bd6299937b08fbb0f3589d8680b7"
SRC_URI[sha256sum] = "017ecc2d66818580e1131af61b8d96901c4a2d05b051186196d9d4f35bdbb901"


S = "${WORKDIR}/${lcl_SOURCE}"

inherit setuptools
inherit nativesdk

RDEPENDS_${PN} += "python-django"

