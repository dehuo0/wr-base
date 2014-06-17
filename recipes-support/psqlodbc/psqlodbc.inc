#
# Copyright (C) 2012 Wind River Systems, Inc.
#
SUMMARY = "ODBC driver for PostgreSQL"
DESCRIPTION = "This package provides a driver that allows ODBC-enabled applications to \
 access PostgreSQL databases.  ODBC is an abstraction layer that allows \
 applications written for that layer to access databases in a manner \
 that is relatively independent of the particular database management \
 system. \
 . \
 You need to install this package if you want to use an application that \
 provides database access through ODBC and you want that application to \
 access a PostgreSQL database.  This package would need to be installed \
 on the same machine as that client application; the PostgreSQL database \
 server can be on a different machine and does not need any additional \
 software to accept ODBC clients."
SECTION = "libs"
HOMEPAGE = "http://psqlodbc.projects.postgresql.org/"

DEPENDS += "postgresql unixodbc"

EXTRA_OECONF = "\
	ac_cv_lib_ltdl_lt_dlopen=no \
	ac_cv_lib_pq_PQconnectdb=yes \
	--with-unixodbc=yes \
	--with-libpq=${STAGING_LIBDIR}/.. \
	--enable-pthreads \
	--enable-openssl \
	--disable-unicode \
	"

inherit autotools pkgconfig

FILES_${PN} += "${libdir}"
