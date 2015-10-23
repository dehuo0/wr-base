#!/bin/sh

if [ "$1" = "" ] ; then
	dest=/boot/initramfs
else
	dest=$1
fi
d=`mktemp -d`

cd $d || exit 1

mkdir -p sbin tmp bin mnt dev/pts dev/shm proc sys usr/sbin usr/bin lib lib64
mknod dev/console c 5 1
mknod dev/dsp c 14 3
mknod dev/full c 1 7
mknod dev/kmem c 1 2
mknod dev/mem c 1 1
mknod dev/mixer c 14 0
mknod dev/null c 1 3
mknod dev/port c 1 4
mknod dev/ptmx c 5 2
mknod dev/ram b 1 1
mknod dev/ram0 b 1 0
mknod dev/ram1 b 1 1
mknod dev/random c 1 8
mknod dev/tty c 5 0
mknod dev/urandom c 1 9
mknod dev/zero c 1 5

bins="bin/cat bin/cp bin/echo bin/false bin/ln bin/ls bin/mkdir bin/more bin/mount bin/mv bin/ping bin/rm bin/sh bin/sleep bin/touch bin/true bin/umount sbin/switch_root"
bins="$bins usr/sbin/vgchange usr/sbin/lvchange"
bins="$bins `cd /;ls sbin/fsck*;cd $d`"
bins="$bins usr/bin/cut bin/date"
for b in $bins; do
	cp /$b $b
	libs=`ldd $b |grep "=>" |awk '{print $3}'; ldd $b |grep -v "=>" |grep -v vdso |awk '{print $1}'`
	for lib in $libs; do
		lib_noabs=${lib#/}
		if [ ! -e $lib_noabs ] ; then
			(
			cd /
			tar chf - $lib_noabs | (cd $d ; tar xf -)
			)
		fi
	done
done

cat<<EOF>init
#!/bin/sh
mount -t proc none /proc
mount -t sysfs none /sys
mount -t devtmpfs none /dev
readonly=ro
debugshell=n
init=/sbin/init
for x in \$(cat /proc/cmdline); do
	case \$x in
	init=*)
		init=\${x#init=}
		;;
	root=*)
		ROOT=\${x#root=}
		;;
	rw)
		readonly=rw
		;;
	debugshell)
		debugshell=y
		;;
	esac
done
if test \$debugshell = y ; then
	exec /bin/sh
fi

lastdate=\`ls /usr/bin/cut -l --time-style=+%Y%m%d | cut -f 6 -d ' '\`
curdate=\`date +%Y%m%d\`
[ \$lastdate -gt \$curdate ] && date -s \$lastdate

check_storage()
{
	files=\`ls /sys/block/\`
	for f in \$files; do
		if [ \${f##ram} = \${f} -a \${f##loop} = \${f} ];then
			return 1
		fi
	done
	return 0
}

i=0
while [ 1 ] ; do
	if check_storage; then
		echo "Wait for root device plugin"
		i=\$((i+1))
		[ \$i -eq 5 ] && exec /bin/sh
		sleep 1
		continue
	fi

	/usr/sbin/vgchange -a y
	mount -o \$readonly \$ROOT /mnt && break
	fsck -V -y \$ROOT || exec /bin/sh
	echo "Waiting for root device to be ready..."
	sleep 1
	[ \$i -eq 5 ] && exec /bin/sh
	i=\$((i+1))
done

lv_devs=\`ls /dev/mapper/*\`
for lv in \$lv_devs;do
	echo \$lv
	if [ "\$lv" != "\$ROOT" ]; then
		/usr/sbin/lvchange -aln \$lv
	fi
done

mount -n --move /proc /mnt/proc
mount -n --move /sys /mnt/sys
mount -n --move /dev /mnt/dev

unset ROOT
unset readonly
unset debugshell
exec /sbin/switch_root /mnt \$init

EOF
chmod 755 init

find . | cpio -o -H newc | gzip -9 > $dest

