def get_libqt3(d):
    if 'linuxstdbase' in d.getVar('DISTROOVERRIDES') or "":
        return 'libqt-mt3'
    return ''
