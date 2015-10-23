# When configured with --enable-build=debug, option of gcc
# '-fno-omit-frame-pointer' is added into TARGET_CLFAGS. But guile asm code
# need -fomit-frame-pointer option, otherwise there will be error on arm arch.
# So filter out -fno-omit-frame-pointer and add -fomit-frame-pointer.
TARGET_CFLAGS_arm := "${@oe_filter_out('-fno-omit-frame-pointer', '${TARGET_CFLAGS}', d)}"
TARGET_CFLAGS_arm += "-fomit-frame-pointer"
