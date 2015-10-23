FILESPATH_append := ":${@base_set_filespath(['${THISDIR}'], d)}/${BPN}"

# Tell smart to only pull validation keys from the local key directory
# /etc/rpm/keys/0x<long key id>
# (this only works for 'rpm-md' repositories)
# Also, add support for using cacerts (and require pycurl support on
# the target).
PACKAGECONFIG[local-hkp] = ",,python-pycurl,python-pycurl"
OVERRIDES .= "${@['', ':RPM-LOCAL-HKP']['local-hkp' in d.getVar('PACKAGECONFIG', True).split()]}"
SRC_URI_append_RPM-LOCAL-HKP = " \
	file://smartpm-local-key.patch \
	file://smartpm-support-cacerts.patch \
"

# Tell smart to only allow feeds with defined fingerprints
PACKAGECONFIG[enforcesig] = ",,,"
OVERRIDES .= "${@['', ':RPM-ENFORCE']['enforcesig' in d.getVar('PACKAGECONFIG', True).split()]}"
SRC_URI_append_RPM-ENFORCE = " file://smartpm-enforce-sig.patch"

GNUPG_DEP ?= ""
GNUPG_DEP_class-target = "gnupg"

RDEPENDS_${PN}-backend-rpm += "${GNUPG_DEP}"

SRC_URI_append = " file://smart-pick-a-same-arch-for-multilib-do_install.patch"
