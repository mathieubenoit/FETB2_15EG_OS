SUMMARY = "FETB2 go tools"
LICENSE = "CLOSED"

GO_IMPORT = "gitlab.cern.ch/BNL-ATLAS/larphase2/fetb2/gofetb2daq.git"
SRC_URI = "git://gitlab.cern.ch/BNL-ATLAS/larphase2/fetb2/gofetb2daq.git;protocol=https;branch=master;user=oauth2:LNKYs1ksNs7sut9ApXTf"
SRCREV = "${AUTOREV}"

GO_INSTALL = "${GO_IMPORT}/bin/linux_arm64/*"

S = "${WORKDIR}/git"


inherit go 

FILES_${PN} += "/home/root/*"

do_compile() {
	chmod -R 700 ${GOPATH}/pkg
	cd ${S}/src/${GO_IMPORT}/Exec
	${GO} mod tidy
	GOOS=${GOOS} GOARCH=${GOARCH} ${GO} build I2CTool.go 
	GOOS=${GOOS} GOARCH=${GOARCH} ${GO} build SYSFSTool.go  
	GOOS=${GOOS} GOARCH=${GOARCH} ${GO} build AXIRegisterTool.go  	
	GOOS=${GOOS} GOARCH=${GOARCH} ${GO} build FETB2ADCTool.go   
	chmod -R 700 ${GOPATH}/pkg
}


do_install() {

	install -m 0644 -d ${D}/home/root
	install -m 0755 src/${GO_IMPORT}/Exec/I2CTool ${D}/home/root/I2CTool
	install -m 0755 src/${GO_IMPORT}/Exec/SYSFSTool ${D}/home/root/SYSFSTool
	install -m 0755 src/${GO_IMPORT}/Exec/AXIRegisterTool ${D}/home/root/AXIRegisterTool
	install -m 0755 src/${GO_IMPORT}/Exec/FETB2ADCTool ${D}/home/root/FETB2ADCTool
}
