SUMMARY = "RPM build macros to add missing Fedora compatible macros"
DESCRIPTION = "Add an RPM build macros file that includes various Fedora \
specific macros.  The macros are derived from the version of RPM included \
with Fedora 22."

# We choose to match the license declared in the Fedora RPM spec file
LICENSE = "GPLv2+"

SRC_URI = "file://macros.fedora"

inherit allarch

do_configure[noexec] = '1'
do_compile[noexec] = '1'

FILES_${PN} = "${sysconfdir}/rpm"
RRECOMMENDS_${PN} = "rpm-build"

do_install() {
	install -m 0755 -d ${D}${sysconfdir}/rpm
	install -m 0644 ${WORKDIR}/macros.fedora ${D}${sysconfdir}/rpm/.
}

