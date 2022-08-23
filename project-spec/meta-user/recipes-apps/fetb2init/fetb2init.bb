#
# This file is the fetb2init recipe.
#

SUMMARY = "Simple fetb2init application"
SECTION = "PETALINUX/apps"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://fetb2init \
	file://.profile"
S = "${WORKDIR}"


inherit update-rc.d
INITSCRIPT_NAME = "fetb2init"
INITSCRIPT_PARAMS = "start 99 5 2 . stop 20 0 1 6 ."

FILES_${PN} += "/home/root/*"
FILES_${PN} += "/home/root/.profile"

do_install() {
		install -d ${D}${sysconfdir}/init.d
		install -m 0755 ${S}/fetb2init ${D}${sysconfdir}/init.d/fetb2init
		install -d ${D}${sysconfdir}/rc5.d
		install -m 0755 ${S}/fetb2init ${D}${sysconfdir}/rc5.d/fetb2init

		install -d ${D}${sysconfdir}/init.d
		install -m 0644 -d ${D}/home/root
		install -m 0755 ${S}/.profile ${D}/home/root/.profile
		install -m 0755 ${S}/startup.sh ${D}/home/root/startup.sh
		chmod +x ${D}/home/root/startup.sh
}

FILES_${PN} += "${sysconfdir}/*"
