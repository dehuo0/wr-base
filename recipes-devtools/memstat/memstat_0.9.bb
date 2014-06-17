#
# Copyright (C) 2012 Wind River Systems, Inc.
#
SUMMARY = "Display virtual memory allocation"
DESCRIPTION = "Lists all the processes, executables, and shared libraries \
that are using up virtual memory. It's helpful to see how the shared memory \
is used and which 'old' libs are loaded. "
HOMEPAGE = "http://memstattool.sourceforge.net/"
SECTION = "devtool"

LICENSE = "GPLv2"

S = "${WORKDIR}/memstattool"

LIC_FILES_CHKSUM = "file://debian/copyright;md5=2ea132500d2f0f2939b5cea19cbaa9b9"

PR = "r0"

SRC_URI = "http://sourceforge.net/projects/memstattool/files/memstat_0.9.tar.gz \
          "

SRC_URI[md5sum] = "b4ee74125d9da23d64646f5feee4b149"
SRC_URI[sha256sum] = "7213f07020ab603323da4b99ba2aba422c2a43dcb0c1cacaad87992ec1fb3fea"

do_install_append(){
    install -d ${D}${bindir}
    install -m 0755 memstat ${D}${bindir}
    install -d ${D}/etc
    install -m 0755 memstat.conf ${D}/etc
}
