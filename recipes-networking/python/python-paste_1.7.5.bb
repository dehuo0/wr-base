#
# Copyright (C) 2012 Wind River Systems, Inc.
#
DESCRIPTION = "Tools for using a Web Server Gateway Interface stack"
SUMMARY = "Tools for using a Web Server Gateway Interface stack"
HOMEPAGE = "http://pythonpaste.org/"
SECTION = "devel/python"
PRIORITY = "optional"
LICENSE = "MIT"
SRCNAME = "Paste"
PR = "r0"

LIC_FILES_CHKSUM = "file://docs/license.txt;md5=1798f29d55080c60365e6283cb49779c"

SRC_URI = "http://pypi.python.org/packages/source/P/${SRCNAME}/${SRCNAME}-${PV}.tar.gz;name=paste"
SRC_URI[paste.md5sum] = "8cf5a47639c4d3ccadd753fc4101be1f"
SRC_URI[paste.sha256sum] = "c60001b71525b1e47a358a2bec832bcae7598b7751036f554b53a106b9ab6839"

S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit setuptools

FILES_${PN} += "/usr/lib/*" 
