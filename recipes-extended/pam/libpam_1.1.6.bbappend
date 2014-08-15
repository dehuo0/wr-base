#
# Add a few "redhat" modules to pam.  Some have been accepted upstream and
# are part of the distribution, but not the three we add here.
#

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

# Note how we "name" the checksums.
#
SRC_URI += "http://pkgs.fedoraproject.org/repo/pkgs/pam/pam-redhat-0.99.11.tar.bz2/29eab110f57e8d60471081a6278a5a92/pam-redhat-0.99.11.tar.bz2;name=redhat \
            file://pam-1.0.90-redhat-modules.patch \
            file://pam-1.1.0-console-nochmod.patch \
            file://pam_console_deps.patch \
           "

SRC_URI[redhat.md5sum] = "29eab110f57e8d60471081a6278a5a92"
SRC_URI[redhat.sha256sum] = "82bd4dc8c79d980bfc8421908e7562a63f0c65cc61152e2b73dcfb97dbf1681b"


# If necessary, move pam-redhat modules to where they will be built.
# Because do_unpack and do_patch are python (probably), we create
# a separate task and use sh.
#
do_lcl_rh_move () {
	SAVED_PWD=`pwd`; cd ${S}
	if [ ! -e modules/pam_console ] ; then
		mv ${WORKDIR}/pam-redhat-0.99.11/* modules
	fi
	cd ${SAVED_PWD}
}

addtask lcl_rh_move after do_unpack before do_patch

# The module itself is put in the package by populate_packages_prepend()
# in the recipe, but we need to put pam_console_apply in manually.
#
FILES_pam-plugin-console = "${sbindir}/pam_console_apply"

