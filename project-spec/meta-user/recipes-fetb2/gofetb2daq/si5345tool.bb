SUMMARY = "Si5345 go tools"
LICENSE = "CLOSED"

GO_IMPORT = "gitlab.cern.ch/bnl-omega-go/si5345tool.git"
SRC_URI = "git://gitlab.cern.ch/bnl-omega-go/si5345tool.git;protocol=https;branch=master;user=oauth2:glpat--W8vTMn9NK9y2y_pXWWV"
SRCREV = "${AUTOREV}"

GO_INSTALL = "${GO_IMPORT}/bin/linux_arm64/*"

S = "${WORKDIR}/git"


inherit go 

FILES_${PN} += "/home/root/*"

do_compile() {
	chmod -R 700 ${GOPATH}/pkg
	cd ${S}/src/${GO_IMPORT}
	${GO} mod tidy
	GOOS=${GOOS} GOARCH=${GOARCH} ${GO} build Si5345Tool.go
	chmod -R 700 ${GOPATH}/pkg
}


do_install() {

	install -m 0644 -d ${D}/home/root
	install -m 0755 src/${GO_IMPORT}/Si5345Tool ${D}/home/root/Si5345Tool
	install -m 0755 src/${GO_IMPORT}/si5345.csv ${D}/home/root/si5345.csv

}
