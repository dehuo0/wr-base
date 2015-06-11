FILESPATH_append := ":${@base_set_filespath(['${THISDIR}'], d)}/${BPN}"

# Enforce signature checking and validation
PACKAGECONFIG[enforcesig] = ",,,"
OVERRIDES .= "${@['', ':RPM-ENFORCE']['enforcesig' in d.getVar('PACKAGECONFIG', True).split()]}"
CFLAGS_append_RPM-ENFORCE = " -DMANDATORY_KNOWN_SIG"
SRC_URI_append_RPM-ENFORCE = " file://rpm-checksig.patch"

# Add in the macros.hkp file to point to the local directory
# to put keys...
PACKAGECONFIG[local-hkp] = ",,,"
OVERRIDES .= "${@['', ':RPM-LOCAL-HKP']['local-hkp' in d.getVar('PACKAGECONFIG', True).split()]}"
SRC_URI_append_RPM-LOCAL-HKP = " file://macros.hkp"

RDEPENDS_${PN} += "${PN}-common"

do_install_append_RPM-LOCAL-HKP () {
	mkdir -p ${D}${sysconfdir}/rpm/
	install -m 0644 ${WORKDIR}/macros.hkp ${D}${sysconfdir}/rpm/.
}
