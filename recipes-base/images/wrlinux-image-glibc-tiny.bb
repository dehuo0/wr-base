#
# Copyright (C) 2012 Wind River Systems, Inc.
#
DESCRIPTION = "A foundational tiny image that boots to a console."

LICENSE = "MIT"

PR = "r0"

inherit wrlinux-image

# We override what gets set in core-image.bbclass
#
IMAGE_INSTALL = "\
    packagegroup-wr-tiny \
    "
