#
# Copyright (C) 2014 Wind River Systems, Inc.
#
SUMMARY = "CFEngine is an IT infrastructure automation framework"

DESCRIPTION = "CFEngine is an IT infrastructure automation framework \
that helps engineers, system administrators and other stakeholders \
in an IT system to manage and understand IT infrastructure throughout \
its lifecycle. CFEngine takes systems from Build to Deploy, Manage and Audit."

HOMEPAGE = "http://cfengine.com"

LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://LICENSE;md5=cd59c3650c8b7ff36bab1cc6587b097a"

DEPENDS = "attr"

SRC_URI = "https://cfengine-package-repos.s3.amazonaws.com/tarballs/${BPN}-${PV}.tar.gz \
           file://change-hard-coded-lib-path.patch \
           file://initscript-for-wrlinux.patch \
           file://cfengine3.default \
          "

SRC_URI[md5sum] = "6d7ce5cb72b4cf35cc297d556900ad9d"
SRC_URI[sha256sum] = "284101a2de6ed97550f7f2bd4ae632e68a166051cdab36f7c1b98de7144e26da"

inherit autotools systemd

SYSTEMD_SERVICE_${PN} = "cfengine3.service cfengine3-web.service"
SYSTEMD_AUTO_ENABLE_${PN} = "disable"

PACKAGECONFIG ??= "libpcre openssl tokyocabinet \
                   ${@bb.utils.contains('DISTRO_FEATURES', 'pam', 'pam', '', d)} \
                   ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'systemd', '', d)} \
"
PACKAGECONFIG[libxml2] = "--with-libxml2=yes,--with-libxml2=no,libxml2,"
PACKAGECONFIG[mysql] = "--with-mysql=yes,--with-mysql=no,mysql,"
PACKAGECONFIG[postgresql] = "--with-postgresql=yes,--with-postgresql=no,postgresql,"
PACKAGECONFIG[acl] = "--with-libacl=yes,--with-libacl=no,acl,"
PACKAGECONFIG[libvirt] = "--with-libvirt=yes,--with-libvirt=no,libvirt,"
PACKAGECONFIG[libpcre] = "--with-pcre=yes,--with-pcre=no,libpcre,"
PACKAGECONFIG[openssl] = "--with-openssl=yes,--with-openssl=no,openssl,"
PACKAGECONFIG[tokyocabinet] = "--with-tokyocabinet=yes,--with-tokyocabinet=no,tokyocabinet,"
PACKAGECONFIG[qdbm] = "--with-qdbm=yes,--with-qdbm=no,,"
PACKAGECONFIG[lmdb] = "--with-lmdb=yes,--with-lmdb=no,lmdb,"
PACKAGECONFIG[pam] = "--with-pam=yes,--with-pam=no,libpam,"
PACKAGECONFIG[libyaml] = "--with-libyaml,--without-libyaml,libyaml,"
PACKAGECONFIG[systemd] = "--with-systemd-service=${systemd_system_unitdir},--without-systemd-service"

EXTRA_OECONF = "hw_cv_func_va_copy=yes --with-init-script=${sysconfdir}/init.d"

do_install_append() {
	install -d ${D}${localstatedir}/${BPN}/bin
	for f in `ls ${D}${bindir}`; do
	    ln -s ${bindir}/`basename $f` ${D}${localstatedir}/${BPN}/bin/
	done

	install -d ${D}${sysconfdir}/default
	install -m 0644 ${WORKDIR}/cfengine3.default ${D}${sysconfdir}/default/cfengine3

	if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
		install -m 0755 -D ${D}${sysconfdir}/init.d/cfengine3 ${D}${datadir}/${BPN}/scripts/cfengine3
		sed -i -e 's#/etc/init.d#${datadir}/${BPN}/scripts#' ${D}${systemd_system_unitdir}/*.service
	fi
}

RDEPENDS_${PN} += "${BPN}-masterfiles"
