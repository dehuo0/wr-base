SUMMARY = "pam-ssh-agent-auth"
DESCRIPTION = "A PAM module which permits authentication via ssh-agent."
HOMEPAGE = "http://sourceforge.net/projects/pamsshagentauth/"
SECTION = "libs"
LICENSE = "openssl BSD"
LIC_FILES_CHKSUM = "file://LICENSE.OpenSSL;md5=8ab01146141ded59b75f8ba7811ed05a \
                    file://OPENSSH_LICENSE;md5=7ae09218173be1643c998a4b71027f9b \
"

SRC_URI = "http://sourceforge.net/projects/pamsshagentauth/files/pam_ssh_agent_auth/v${PV}/pam_ssh_agent_auth-${PV}.tar.bz2 \
           "

SRC_URI[md5sum] = "a212baca7ce11d596bd8dcb222859ace"
SRC_URI[sha256sum] = "99bbbae3494032e17b62ff3b9ff71e2d78e3c6809463649afe7bae79e4f81fcd"

DEPENDS += "libpam openssl"

# This gets us ssh-agent, which we are almost certain to want.
#
RDEPENDS_${PN} += "openssh-misc"

# Kind of unfortunate to have underscores in the name.
#
S = "${WORKDIR}/pam_ssh_agent_auth-${PV}"

inherit autotools-brokensep

# Avoid autoreconf.  Override the --libexec oe_runconf specifies so that
# the module is put with the other pam modules.  Because it cannot, in general,
# do a runtime test, configure wants to use rpl_malloc() and rpl_realloc()
# instead of malloc() and realloc().  We set variables to tell it not to because
# these functions do not exist.
#
do_configure () {
	oe_runconf --without-openssl-header-check  --libexecdir=${base_libdir}/security \
	           ac_cv_func_malloc_0_nonnull=yes ac_cv_func_realloc_0_nonnull=yes
}

# Link with CC.  Configure cannot figure out the correct AR.
#
do_compile () {
	oe_runmake  LD="${CC}" AR="${AR}"
}

# This stuff is not any place looked at by default.
#
FILES_${PN} += "${base_libdir}/security/pam*"
FILES_${PN}-dbg += "${base_libdir}/security/.debug"

