# Force the name, we don't want ?= or our SDK naming will be used.
TOOLCHAIN_OUTPUTNAME = "${SDK_ARCH}-buildtools-nativesdk-standalone-${DISTRO_VERSION}"

# for toaster
#
TOOLCHAIN_HOST_TASK += \
     "nativesdk-python-django nativesdk-python-south \
      nativesdk-python-numbers nativesdk-python-email \
      nativesdk-python-html nativesdk-python-resource \
      nativesdk-python-debugger \
     "

# The buildtools is special, avoid custom SDK tasks.
WRS_CREATE_SDK_FILES = ""

SDK_POSTPROCESS_COMMAND_prepend = "gen_buildtools_delete_target ;"

gen_buildtools_delete_target() {
	rm -rf ${SDK_OUTPUT}/${SDKTARGETSYSROOT}
}
