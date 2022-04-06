#
# This file is the xvcserverinit recipe.
#

SUMMARY = "Simple xvcserver application"
SECTION = "PETALINUX/apps"
LICENSE = "CLOSED"

SRC_URI = "file://xvcserverinit \
	"
S = "${WORKDIR}"


inherit update-rc.d
INITSCRIPT_NAME = "xvcserverinit"
INITSCRIPT_PARAMS = "start 99 5 2 . stop 20 0 1 6 ."

do_install() {
		 install -d ${D}${sysconfdir}/init.d
		 install -m 0755 ${S}/xvcserverinit ${D}${sysconfdir}/init.d/xvcserverinit
		 install -d ${D}${sysconfdir}/rc5.d
		 install -m 0755 ${S}/xvcserverinit ${D}${sysconfdir}/rc5.d/xvcserverinit
}

FILES_${PN} += "${sysconfdir}/*"
