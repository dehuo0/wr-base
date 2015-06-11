FILESPATH_append := ":${@base_set_filespath(['${THISDIR}'], d)}/${BPN}"

SRC_URI_append_class-native = " file://createrepo-skipsigned.patch"
