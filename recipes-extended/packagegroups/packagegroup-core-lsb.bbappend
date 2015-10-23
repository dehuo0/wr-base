def get_libqt3(d):
    if 'linuxstdbase' in d.getVar('DISTROOVERRIDES', False) or "":
        return 'libqt-mt3'
    return ''
