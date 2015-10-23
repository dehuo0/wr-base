#
# Copyright (C) 2012 Wind River Systems, Inc.
#
SUMMARY = "Construct and optionally mail MIME messages"
DESCRIPTION = "mime-construct constructs and (by default) mails MIME messages. \
It is entirely driven from the command line, it is designed to be used by \
other programs, or people who act like programs."

HOMEPAGE = "http://search.cpan.org/~rosch/mime-construct/mime-construct"
SECTION = "utilities"

#it is under GPLv2+
LICENSE = "Artistic-1.0|GPLv2+"

#use sendmail which is in msmtp package
RDEPENDS_${PN} += "perl perl-module-mime-base64 perl-module-mime-quotedprint"
RDEPENDS_${PN} += "libproc-waitstat-perl libmime-types-perl msmtp \
                   perl-module-filehandle perl-module-posix"

PR = "r1"

SRC_URI = "http://search.cpan.org/CPAN/authors/id/R/RO/ROSCH/${BPN}-${PV}.tar.gz \
           file://0001-fix-mime-construct-help-return-value.patch \
"

inherit cpan

LIC_FILES_CHKSUM = \
"file://README;beginline=17;endline=28;md5=048c9f21ce4514f61f364caa096b79b7"

SRC_URI[md5sum] = "73834ea780fbea81b89dbd9b2fb54f58"
SRC_URI[sha256sum] = "4cd7bb61b51d41192d1498c1051aa6a4ccd75aeb09b71d2ec706a7084a4a9303"
