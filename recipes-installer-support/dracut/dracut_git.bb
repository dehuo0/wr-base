SUMMARY = "Initramfs generator using udev"
DESCRIPTION = "Dracut is an event driven initramfs infrastructure. dracut (the tool) is used to create an initramfs image by copying tools and files from an installed system and combining it with the dracut framework, usually found in /usr/lib/dracut/modules.d."

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

PE = "1"
PV = "036"

# v036 tag
SRCREV = "d50a99c5ceeb7107f624c5d3238d37509b2217a8"
SRC_URI = "git://git.kernel.org/pub/scm/boot/dracut/dracut.git \
           file://dracut-fix-files-directory-error.patch \
          "

S = "${WORKDIR}/git"

EXTRA_OEMAKE += 'libdir=${prefix}/lib'

do_configure() {
    ./configure --prefix=${prefix} \
                --libdir=${prefix}/lib \
                --datadir=${datadir} \
                --sysconfdir=${sysconfdir} \
                --sbindir=${sbindir} \
                --disable-documentation \
                --bindir=${bindir} \
                --includedir=${includedir} \
                --localstatedir=${localstatedir} \
}

do_install() {
    oe_runmake install DESTDIR=${D}
}

FILES_${PN} += "${datadir}/bash-completion \ 
                ${prefix}/lib/kernel \
                ${prefix}/lib/dracut \
               "

FILES_${PN}-dbg += "${prefix}/lib/dracut/.debug"

RDEPENDS_${PN} = "findutils cpio util-linux-blkid util-linux-partx bash ldd"

PACKAGECONFIG ??= "${@base_contains('DISTRO_FEATURES', 'systemd', 'systemd', '', d)}"
PACKAGECONFIG[systemd] = "--with-systemdsystemunitdir=${systemd_unitdir}/system/,,,systemd"

# This could be optimized a bit, but let's avoid non-booting systems :)
RRECOMMENDS_${PN} = " \
                     kernel-modules \
                     coreutils \ 
                    "
