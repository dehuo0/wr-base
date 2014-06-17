# debug template conf file
#
IMAGE_FEATURES += "ssh-sftp-servers"

IMAGE_INSTALL += "packagegroup-core-tools-debug"
IMAGE_INSTALL += "memstat"

# workbench runtime debug tools
#
require templates/feature/debug-wb/image.inc

