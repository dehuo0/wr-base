#
# Copyright (C) 2015 Wind River Systems, Inc.
#
inherit archiver

do_deploy_archives[postfuncs] += "wrl_deployarchive"
python wrl_deployarchive () {
    if not bb.data.inherits_class('wrlcompat', d):
        return
    wrlexport = d.getVar('WRL_EXPORT_DIR', True)
    link = "%s/sources" % wrlexport
    if not os.path.islink(link):
        deploy = d.getVar('DEPLOY_DIR', True)
        target = "%s/sources" % deploy
        wrl_symlink(target, link, d)
}

