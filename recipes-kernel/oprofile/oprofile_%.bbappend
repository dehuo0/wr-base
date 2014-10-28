# This runtime dependency is something that WRLinux needs but not OE.
# This is because that OE's default busybox provides `pidof' command.
RDEPENDS_${PN}_append_wrlinux = " sysvinit-pidof"
RDEPENDS_${PN}_append_wrlinux-std-sato = " sysvinit-pidof"
