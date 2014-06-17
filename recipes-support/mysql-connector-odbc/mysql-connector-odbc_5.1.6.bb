#
# Copyright (C) 2012 Wind River Systems, Inc.
#
SUMMARY = "MySQL Connector/ODBC"

DESCRIPTION = "Open Database Connectivity (ODBC) is a widely accepted standard \
for reading/writing data. ODBC is designed in a similar manner \
to the MS Windows printing system in that it has a Driver Manager \
and Drivers. MySQL Connector/ODBC is a driver for the ODBC system \
which allows applications to communicate with the MySQL Server \
using the ODBC standard. ODBC implementations exist on all popular \
platforms and MySQL Connector/ODBC is also available on those \
platforms. \
"

HOMEPAGE = "http://www.mysql.com"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE.gpl;md5=477ab0a4c8ca64b482b3f2a365d0fdfa"

DEPENDS = "unixodbc mysql5"

RDEPENDS_${PN} = "unixodbc libmysqlclient-r mysql5"

PR = "r1"

SRC_URI = "http://downloads.mysql.com/archives/mysql-connector-odbc-5.1/mysql-connector-odbc-5.1.6.tar.gz \
           file://remove-mysql-detective-from-configure.in.patch \
          "
SRC_URI[md5sum] = "ed445092466be030f991b3a093649f45"
SRC_URI[sha256sum] = "1d53c3a20cf68b089535278d3e69c0e49f367a457640c5f8af25c1cbf4ffb02e"

inherit autotools-brokensep

EXTRA_OECONF += "MYSQL_CFLAGS='-I${STAGING_DIR_HOST}/usr/include/mysql' MYSQL_LIB='-L${STAGING_LIBDIR} -lmysqlclient_r' \
                 --enable-gui=no \
                 --enable-dmlink=yes \
                 --with-unixODBC=yes \
                 --with-unixODBC-includes=${STAGING_INCDIR} \
                 --with-unixODBC-libs=${STAGING_LIBDIR} \
"

do_install_append(){
        #test directory is useful for checking how odbc works,so install it.
        install -d ${D}/${datadir}/${BPN}/test/
        install ${S}/test/* ${D}/${datadir}/${BPN}/test/
        rm ${D}/${datadir}/${BPN}/test/*.o
}

PACKAGES =+ "${PN}-test"

FILES_${PN}-test += "${datadir}/${BPN}/test/*"
FILES_${PN} += "${libdir}/libmyodbc5-5.1.6.so"
FILES_${PN}-dev += "${libdir}/libmyodbc5.so"
FILES_${PN}-dbg += "${datadir}/${BPN}/test/.debug"

