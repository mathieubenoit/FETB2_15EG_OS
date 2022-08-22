SUMMARY = "FETB2 Python tools"
LICENSE = "CLOSED"

RDEPENDS_fetb2pythontools += "bash"

SRC_URI = "git://gitlab.cern.ch/BNL-ATLAS/larphase2/fetb2/fetb2_test_scripts.git;protocol=https;branch=master;user=oauth2:glpat-ST-2FYtPuxuHxXSopgdS"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

FILES_${PN} += "/home/root/*"
FILES_${PN} += "/home/root/fetb2_test_scripts/*"


do_install() {

	install -m 0644 -d ${D}/home/root
	install -m 0644 -d ${D}/home/root/fetb2_test_scripts
	install -m 0644 -d ${D}/home/root/fetb2_test_scripts/jupyter
	install -m 0644 -d ${D}/home/root/fetb2_test_scripts/python_debug_driver

	install -m 0755  -D *.* ${D}/home/root/fetb2_test_scripts 
	#find . -type f -exec install -Dm 755 "{}" "${D}/home/root/fetb2_test_scripts" \;

	# for file in $(find . -type f); do
    # 	install -m 644 -D ${file} ${D}/home/root/fetb2_test_scripts/${file#source/}
	# done

	cp -r * ${D}/home/root/fetb2_test_scripts
	chmod -R 0644 ${D}/home/root/fetb2_test_scripts/*

	install -m 0755  python_debug_driver/xu1_ps_pl_registers/xml_register_info.xml ${D}/home/root/xml_register_info.xml
}
