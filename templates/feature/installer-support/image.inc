IMAGE_INSTALL += "kernel-image"
IMAGE_INSTALL += "grub"
IMAGE_INSTALL += "grubby"
IMAGE_INSTALL += "shadow"
IMAGE_INSTALL += "efibootmgr"

# Need run depmod on the target
IMAGE_INSTALL += "kmod"

# Busybox's mount doesn't support some options,
# that caused fs mount failed on glibc-small image,
# use util-linux-mount to instead.
IMAGE_INSTALL += "util-linux-mount"

IMAGE_INSTALL += "init-ifupdown"
