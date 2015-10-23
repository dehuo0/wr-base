#
# Copyright (C) 2012-2013 Wind River Systems, Inc.
#
# LOCAL REV: WR specific simics related patches
#
# In openembedded upstream recipe hwclock's priority is set to 10
# problem happens on nslu2
# we don't support this board, change it back to normal
# to support full features of hwclock from util-linux
ALTERNATIVE_PRIORITY[hwclock] = "100"

#include util-linux-agetty too
RRECOMMENDS_${PN} += " util-linux-agetty"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append_virtclass-native = "file://exit_status.patch \
                                file://strip_slash_r_feature.patch \
                                file://non_blocking_read.patch \
                                file://socket_command_extension.patch \
                                file://restart_on_eintr.patch \
                                file://no_switch_on_enter_env.patch \
                                file://remove_control_m_opost.patch \
                                file://no_kill_parrent.patch \
                                file://exit_clean_simics.patch"
SRC_URI_append_virtclass-nativesdk = "file://exit_status.patch \
                                file://strip_slash_r_feature.patch \
                                file://non_blocking_read.patch \
                                file://socket_command_extension.patch \
                                file://restart_on_eintr.patch \
                                file://no_switch_on_enter_env.patch \
                                file://remove_control_m_opost.patch \
                                file://no_kill_parrent.patch \
                                file://exit_clean_simics.patch"

BBCLASSEXTEND = "native nativesdk"
