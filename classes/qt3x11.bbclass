#
# Copyright (C) 2012 Wind River Systems, Inc.
#
QT3DEPENDS ?=  "qt3x11"
DEPENDS_prepend = "${QT3DEPENDS}"
EXTRA_QMAKEVARS_POST += "CONFIG+=thread"
#
# override variables set by qmake_base to compile Qt/X11 apps
#
export QTDIR = "${STAGING_DIR_HOST}/qt3"
export OE_QMAKE_QMAKE = "${STAGING_BINDIR_NATIVE}/qmake-qt3"
export OE_QMAKE_UIC = "${STAGING_BINDIR_NATIVE}/uic-qt3"
export OE_QMAKE_MOC = "${STAGING_BINDIR_NATIVE}/moc-qt3"
export OE_QMAKE_CXXFLAGS = "${CXXFLAGS} -DQT_NO_XIM"
export OE_QMAKE_INCDIR_QT = "${QTDIR}/include"
export OE_QMAKE_LIBDIR_QT = "${QTDIR}/lib"
export OE_QMAKE_LIBS_QT = "qt"
export OE_QMAKE_LIBS_X11 = "-lXext -lX11 -lm"
export OE_QMAKE_LIBS_OPENGL = "-lGLU -lGL -lXmu"
export OE_QMAKE_LIBS_OPENGL_QT = "-lGL -lXmu"
