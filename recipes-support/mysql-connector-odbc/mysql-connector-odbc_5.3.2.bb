#
# Copyright (C) 2014 Wind River Systems, Inc.
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
LIC_FILES_CHKSUM = "file://Licenses_for_Third-Party_Components.txt;md5=b2cee022e19f3b50d086e8570b50376a"

DEPENDS = "unixodbc mysql5"

RDEPENDS_${PN} = "unixodbc libmysqlclient-r mysql5"

SRC_URI = "http://downloads.mysql.com/archives/get/file/${BP}-src.tar.gz \
           file://run-ptest \
          "
SRC_URI[md5sum] = "389010eb70df759e6adb50983a31c9a3"
SRC_URI[sha256sum] = "529bce82d308220379f658f1415bc88b35e9d084ddecd6bebe5fbb3b1fd48651"
S="${WORKDIR}/${BP}-src"

inherit cmake ptest

EXTRA_OECMAKE += " -DDISABLE_GUI=yes \
                 -DWITH_UNIXODBC=yes \
                 -DODBC_INCLUDES='${STAGING_INCDIR}' \
                 -DODBC_LIB_DIR='${STAGING_LIBDIR}' \
                 -DRPM_BUILD=1 \
                 -DCMAKE_INSTALL_PREFIX='${datadir}/doc/mysql-connector-odbc' \
                 -DMYSQL_LINK_FLAGS='${LDFLAGS}' \
"

do_compile_prepend(){
    export MYSQL_CFLAGS='${STAGING_DIR_HOST}/usr/include/mysql'
    export MYSQL_LIB='${STAGING_LIBDIR}'
}

do_install_append(){
    install -d ${D}${libdir}
    mv ${D}${datadir}/doc/mysql-connector-odbc/${base_libdir}/*.so  ${D}${libdir}
    rm -rf ${D}${datadir}/doc/mysql-connector-odbc/${base_libdir}/

    install -d ${D}${bindir}
    mv ${D}${datadir}/doc/mysql-connector-odbc/bin/*  ${D}${bindir}
    rm -rf ${D}${datadir}/doc/mysql-connector-odbc/bin/

    if ${@bb.utils.contains('DISTRO_FEATURES','ptest','true','false',d)}; then
        install -d ${D}${PTEST_PATH}/test
        install ${WORKDIR}/run-ptest ${D}${PTEST_PATH}
        mv ${D}${datadir}/doc/mysql-connector-odbc/test/* ${D}${PTEST_PATH}/test
        sed -i "s:${B}/lib/:${libdir}/:g" ${D}${PTEST_PATH}/test/*.ini
    fi
    rm -rf ${D}${datadir}/doc/mysql-connector-odbc/test
}
do_install_ptest_base(){
}
FILES_${PN} += "${libdir}/libmyodbc5*"
FILES_${PN}-dev = ""
FILES_${PN}-dbg += "${libdir}/${BPN}/test/.debug"

