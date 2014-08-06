# add packages into build
require templates/feature/mysql/image.inc
IMAGE_INSTALL += "unixodbc"
IMAGE_INSTALL += "mysql-connector-odbc"
