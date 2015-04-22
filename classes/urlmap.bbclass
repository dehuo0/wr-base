# This class mangles the SRC_URI to change the network location to the value
# found in URLMAP.  If the params specify the protocol as "file" and there is
# no network location, then the path is checked for validity and SRC_URI is
# only altered if the path does not exist.  The path is stripped to the last
# directory and .git is removed from the last directory name, if present.

python __anonymous () {
    if d.getVar('BB_NO_NETWORK', True) == "1":
        return

    pkgname = d.expand(d.getVar('PN'))
    newsrc = d.getVarFlag('URLMAP', pkgname)

    if not newsrc:
        newsrc = d.getVar('URLMAP', True)

    if not newsrc:
        return

    srcuri = d.getVar('SRC_URI', True)
    scheme, network, path, user, passwd, param = bb.fetch.decodeurl(srcuri)


    # Drop hard-coded protocol from param if it exists (like protocol=file)
    if "protocol" in param and param["protocol"] == "file":
        if not network:
            if os.path.exists(path):
                # If the path exists and we're not fetching over the network,
                # use the local copy.
                return
            else:
                del param["protocol"]

    # Remove everything but the last directory name from the path
    dirs = path.split("/")
    repo = "/" + dirs[-1]

    if (repo.endswith(".git")):
       repo = repo[:-4]

    scheme, network, path, user, passwd, newparam = bb.fetch.decodeurl(newsrc)
    path += repo

    # Copy or overwrite any params with the new param values.
    for part in newparam:
        param[part] = newparam[part]

    result = bb.fetch.encodeurl([scheme, network, path, user, passwd, param])
    bb.debug(2, "Changed %s to %s for package %s" % (srcuri, result, pkgname))
    d.setVar("SRC_URI", result);
}

