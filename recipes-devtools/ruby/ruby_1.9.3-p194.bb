#
# Copyright (C) 2012 Wind River Systems, Inc.
#
require ruby.inc
PR = "${INC_PR}.5"

DEPENDS += "libyaml"
DEPENDS_virtclass-native += "libyaml-native"

SRC_URI += "\
	file://ruby-1.9.3-0001-socket-extconf-hardcode-wide-getaddr-info-test-outco.patch \
	file://ruby-1.9.3-always-use-i386.patch \
	file://ruby-1.9.3-disable-versioned-paths.patch \
	file://ruby-1.9.3-fix-s390x-build.patch \
	file://ruby-1.9.3-rubygems-1.8.11-uninstaller.patch \
	file://ruby-1.9.3-webrick-test-fix.patch \
	file://ruby-1.9.3-bignum-test-fix.patch \
	file://ruby-1.9.3-custom-rubygems-location.patch \
	file://rubygems-1.8.11-binary-extensions.patch \
	file://ruby-1.9.3-mkmf-verbose.patch \
	file://ruby-1.9.3-install-cross.patch \
	file://ruby-1.9.3-mkmf-fix-race-conditions-at-install-ext.patch \
	file://ruby-1.9.3-CVE-2012-4522.patch \
        file://ruby-1.9.3-fix-CVE-2012-5371.patch \
	file://ruby-1.9.3-fix-CVE-2013-1821.patch \
	file://ruby-1.9.3-fix-CVE-2012-4464.patch \
	file://ruby-1.9.3-fix-CVE-2013-2065.patch \
	file://ruby-1.9.3-fix-CVE-2013-4164.patch \
	file://0001-Fix-CVE-2013-0256-an-XSS-exploit-in-RDoc.patch \
"

SRC_URI[md5sum] = "bc0c715c69da4d1d8bd57069c19f6c0e"
SRC_URI[sha256sum] = "46e2fa80be7efed51bd9cdc529d1fe22ebc7567ee0f91db4ab855438cf4bd8bb"

EXTRA_OECONF_GEMS = "--with-rubygemsdir=${datadir}/rubygems"
EXTRA_OECONF_GEMS_virtclass-native = "--enable-load-relative"

EXTRA_OECONF = "\
	--enable-wide-getaddrinfo \
	${EXTRA_OECONF_GEMS} \
	--disable-versioned-paths \
	--disable-rpath \
	--enable-shared \
"

EXTRA_OEMAKE = " \
	LIBRUBYARG='-lruby-static' \
"

do_install() {
	if [ ${PN} = "ruby" ]; then
		oe_runmake 'DESTDIR=${D}' install install-cross
	else
		oe_runmake 'DESTDIR=${D}' install
	fi
}

FILES_${PN} += "${datadir}/rubygems \
		${datadir}/ri"

FILES_${PN}-dbg += "${libdir}/ruby/*/.debug \
                    ${libdir}/ruby/*/*/.debug \
                    ${libdir}/ruby/*/*/*/.debug"

BBCLASSEXTEND = "native"

LIC_FILES_CHKSUM = "\
	file://COPYING;md5=837b32593517ae48b9c3b5c87a5d288c \
	file://BSDL;md5=3949e007205deef714bd225e1ee4a8ea \
	file://GPL;md5=393a5ca445f6965873eca0259a17f833 \
	file://LEGAL;md5=e88686821918c0b6d2b1b8328116cec5 \
"
