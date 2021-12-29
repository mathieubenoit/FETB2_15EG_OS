#
# This file is the Si5345Init recipe.
#

SUMMARY = "Simple Si5345Init application"
SECTION = "PETALINUX/apps"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://si5345init \
	"
S = "${WORKDIR}"


inherit update-rc.d
INITSCRIPT_NAME = "si5345init"


do_install() {
		 install -d ${D}${sysconfdir}/init.d
		 install -m 0755 ${S}/si5345init ${D}${sysconfdir}/init.d/si5345init
}

FILES_${PN} += "${sysconfdir}/*"
