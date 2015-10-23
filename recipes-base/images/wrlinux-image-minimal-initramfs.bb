# Simple initramfs image for wrlinux distro. Mostly used for live images.
DESCRIPTION = "Small image capable of booting a device. The kernel includes \
the Minimal RAM-based Initial Root Filesystem (initramfs), which finds the \
first 'init' program more efficiently."

# As we are using a minimal set of busybox for wrlinux distro, we need to
# provide other necessary utilities to make the live images work correctly.
IMAGE_INSTALL = "initramfs-live-boot initramfs-live-install initramfs-live-install-efi busybox udev base-passwd"
# Don't add util-linux to IMAGE_INSTALL here, because switch_root in init uses
# the semantics of that from busybox. So switch_root from util-linux will cause
# a kernel panic.
IMAGE_INSTALL += "coreutils util-linux-mount util-linux-umount kmod grep sed gawk e2fsprogs-mke2fs psmisc"

# Do not pollute the initrd image with rootfs features
IMAGE_FEATURES = ""

export IMAGE_BASENAME = "wrlinux-image-minimal-initramfs"
IMAGE_LINGUAS = ""

LICENSE = "MIT"

IMAGE_FSTYPES = "${INITRAMFS_FSTYPES}"
inherit wrlinux-image

IMAGE_ROOTFS_SIZE = "8192"
