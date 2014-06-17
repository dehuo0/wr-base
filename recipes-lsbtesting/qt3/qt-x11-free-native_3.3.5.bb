#
# Copyright (C) 2012 Wind River Systems, Inc.
#
DESCRIPTION = "Qt/X11 Version ${PV}"
SECTION = "libs"
LICENSE = "GPLv2 | QPL-1.0"
DEPENDS = "xmu-native"
HOMEPAGE = "http://www.trolltech.com"
PR = "r4"

FILESDIR = "${@os.path.dirname(bb.data.getVar('FILE',d,1))}/qt-x11-free"

LIC_FILES_CHKSUM = "file://LICENSE.GPL;md5=629178675a7d49c9fa19dfe9f43ea256 \
                    file://LICENSE.QPL;md5=fff372435cb41647bc0b3cb940ea5c51"

SRC_URI = "ftp://ftp.trolltech.com/qt/source/qt-x11-free-${PV}.tar.bz2 \
           file://no-examples.patch \
           file://qt3-cstddef.patch \
           file://qmake.conf \
           file://configure-long-string.patch \
           "

S = "${WORKDIR}/qt-x11-free-${PV}"

#
# FIXME - This should be updated to use OE's qmake_base.oeclass
#         or the full qmake.oeclass.
#

export QTDIR = "${S}"
export SYSCONF_CXX = "${CCACHE} g++"
export SYSCONF_CC  = "${CCACHE} gcc"
export SYSCONF_LINK  = "${CCACHE} g++"
THIS_QMAKESPEC = "${STAGING_DATADIR}/qmake/${TARGET_OS}-oe-g++"
export QMAKESPEC = ""
ARCH_i686 = "x86"

QT_CONFIG_FLAGS = "-release -shared -qt-zlib -no-nas-sound -no-sm -qt-libpng -qt-gif -no-xshape -no-xinerama -no-xcursor -no-xrandr \
                   -no-xrender -no-xft -no-tablet -no-xkb -no-freetype -no-nis -no-cups -stl -thread -no-exceptions -disable-opengl"


inherit native

do_configure() {
	echo "yes" | ./configure -prefix ${prefix} ${QT_CONFIG_FLAGS} -fast -L ${STAGING_LIBDIR} \
                -I ${STAGING_INCDIR_NATIVE} 
	sed -i -e '\:obj/release-shared-mt/qpngio.o\ kerne:s:$(CXXFLAGS):-I3rdparty/libpng $(CXXFLAGS):' src/Makefile
}

do_compile() {
	export LD_LIBRARY_PATH=${S}/lib; oe_runmake \
		QMAKE="${STAGING_BINDIR_NATIVE}/qmake -after INCLUDEPATH+=${STAGING_INCDIR} LIBS+=-L${STAGING_LIBDIR}" \
		QMAKESPEC="${THIS_QMAKESPEC}"
}


do_install() {
    install -d ${D}${bindir}/
    install -m 0755 bin/qmake ${D}${bindir}/qmake-qt3
    for i in moc uic  lrelease lupdate; do
        install -m 0755 bin/${i} ${D}${bindir}/${i}-qt3
    done
 
    install -d ${D}${datadir}/qt3/
    cp -PfR mkspecs ${D}${datadir}/qt3/
    install -m 0644 ${WORKDIR}/qmake.conf ${D}${datadir}/qt3/mkspecs/linux-g++/
    ln -sf linux-g++ ${D}${datadir}/qt3/mkspecs/${TARGET_OS}-oe-g++
    ln -sf qt3/mkspecs ${D}${datadir}/qmake
    install -d ${D}${libdir}/
    oe_libinstall -so -C lib libqt-mt ${D}${libdir}
}


SRC_URI[md5sum] = "05d04688c0c0230ed54e89102d689ca4"
SRC_URI[sha256sum] = "aac89e862c74b2f3ead768e50e9fa7ada1e4225fe9d1d9e05723a3279259eb96"
