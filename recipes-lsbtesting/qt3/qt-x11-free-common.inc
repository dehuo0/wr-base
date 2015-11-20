#
# Copyright (C) 2012 Wind River Systems, Inc.
#
DESCRIPTION = "Qt/X11 Version ${PV} is a full fledged cross-platform application framework"
SECTION = "x11/libs"
LICENSE = "GPLv2 | QPL-1.0"
HOMEPAGE = "http://www.trolltech.com"
INC_PR = "r6"

S = "${WORKDIR}/qt-x11-free-${PV}"

QT3DEPENDS = ""

inherit qmake_base qt3x11

export QTDIR = "${S}"
ARCH_i686 = "x86"
EXTRA_OEMAKE = "-e"

QT_CONFIG_FLAGS = "-release -shared -qt-zlib -no-nas-sound -no-sm -qt-libpng -no-gif -no-xinerama \
                   -no-tablet -no-xkb -no-dlopen-opengl -no-nis -no-cups -thread  -verbose"

EXTRA_ENV = 'QMAKE="${OE_QMAKE_QMAKE} -after INCPATH+=${STAGING_INCDIR} \
             INCPATH+=${STAGING_INCDIR}/freetype2 LIBS+=-L${STAGING_LIBDIR}" \
             QMAKESPEC="${QMAKESPEC}" LINK="${CXX} -Wl,-rpath-link,${STAGING_LIBDIR}" \
             AR="${TARGET_PREFIX}ar cqs" \
             MOC="${OE_QMAKE_MOC}" UIC="${OE_QMAKE_UIC}" MAKE="make -e"'

do_configure() {
	if [ ! -L ${QMAKE_MKSPEC_PATH}/${TARGET_OS}-oe-g++ ]; then
		ln -sf ${QMAKE_MKSPEC_PATH}/linux-g++ ${QMAKE_MKSPEC_PATH}/${TARGET_OS}-oe-g++
	fi

	echo "yes" | ./configure -prefix ${prefix} ${QT_CONFIG_FLAGS} -no-fast \
		-L${STAGING_LIBDIR} -I${STAGING_INCDIR} -I${STAGING_INCDIR}/freetype2 -I${STAGING_INCDIR}/mysql

	# force regenerate
	rm -f src/qtmain.pro
	find . -name "Makefile"|xargs rm -f
	(cd src && ${OE_QMAKE_QMAKE} -spec ${QMAKESPEC} )
	(cd plugins/src && ${OE_QMAKE_QMAKE} -spec ${QMAKESPEC} )
	(cd tools && ${OE_QMAKE_QMAKE} -spec ${QMAKESPEC} )
	(cd tools/qvfb && ${OE_QMAKE_QMAKE} -spec ${QMAKESPEC} )
}

do_compile() {
	unset CFLAGS
	unset CXXFLAGS

	install -m 0755 ${OE_QMAKE_MOC} ${S}/bin/moc
	install -m 0755 ${OE_QMAKE_UIC} ${S}/bin/uic

	oe_runmake -C src ${EXTRA_ENV}
	oe_runmake -C plugins/src ${EXTRA_ENV}
	oe_runmake -C tools ${EXTRA_ENV}
}

do_install() {
	install -d ${D}${includedir}
	install -d ${D}${includedir}/qt3
	install -d ${D}${includedir}/qt3/private
	for f in include/*.h
	do
		install -m 0644 $f ${D}${includedir}/qt3
	done
	for f in include/private/*.h
	do
		install -m 0644 $f ${D}${includedir}/qt3/private
	done
	install -d ${D}${libdir}
	install -d ${D}${libdir}/qt3
	for f in lib/*.prl
	do
		install -m 0644 $f ${D}${libdir}/qt3
	done
	oe_libinstall -so -C lib libqt-mt ${D}${libdir}
	install -d ${D}${libdir}/qt3/plugins/
	cp -pPR plugins/imageformats plugins/sqldrivers plugins/designer ${D}${libdir}/qt3/plugins/
	chown -R root:root ${D}${libdir}/qt3/plugins

	# Avoid empty directory QA error
	rmdir ${D}${libdir}/qt3/plugins/sqldrivers 2>/dev/null || true
}

PACKAGES =+ " libqt-mt3 qt-x11-plugins-imageformats qt-x11-plugins-sqldrivers qt-x11-plugins-designer \
             qt-x11-designer qt-x11-assistant qt-x11-qvfb qt-x11-qtconfig"
RPROVIDES_libqt-mt3 = "${PN}"
FILES_libqt-mt3 = "${libdir}/libqt-mt.so.*"
FILES_${PN}-dev += "${libdir}/qt3/*.prl"
FILES_qt-x11-plugins-imageformats = "${libdir}/qt3/plugins/imageformats/*.so"
FILES_qt-x11-plugins-sqldrivers = "${libdir}/qt3/plugins/sqldrivers/*.so"
FILES_qt-x11-plugins-designer = "${libdir}/qt3/plugins/designer/*.so"
FILES_qt-x11-designer = "${bindir}/designer"
FILES_qt-x11-assistant = "${bindir}/assistant"
FILES_qt-x11-qtconfig = "${bindir}/qtconfig"
FILES_${PN}-dbg += "${libdir}/qt3/plugins/*/.debug"
