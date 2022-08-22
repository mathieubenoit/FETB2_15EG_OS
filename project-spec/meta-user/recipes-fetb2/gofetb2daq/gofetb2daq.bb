SUMMARY = "FETB2 go tools"
LICENSE = "CLOSED"

GO_IMPORT = "gitlab.cern.ch/BNL-ATLAS/larphase2/fetb2/gofetb2daq.git"
SRC_URI = "git://gitlab.cern.ch/BNL-ATLAS/larphase2/fetb2/gofetb2daq.git;protocol=https;branch=master;user=oauth2:LNKYs1ksNs7sut9ApXTf"
SRCREV = "${AUTOREV}"
PV = "1.0+git${SRCPV}"

GO_INSTALL = "${GO_IMPORT}/bin/linux_arm64/*"

S = "${WORKDIR}/git"


inherit go 

FILES_${PN} += "/home/root/*"

do_compile() {
	chmod -R 700 ${GOPATH}/pkg
	cd ${S}/src/${GO_IMPORT}/Exec
	${GO} mod tidy -compat=1.17
	GOOS=${GOOS} GOARCH=${GOARCH} ${GO} build FETB2ControlTool.go 
	GOOS=${GOOS} GOARCH=${GOARCH} ${GO} build FETB2ALFE2Tool.go 
	GOOS=${GOOS} GOARCH=${GOARCH} ${GO} build AXIStreamDumper.go 
	GOOS=${GOOS} GOARCH=${GOARCH} ${GO} build NoiseScanTool.go 
	GOOS=${GOOS} GOARCH=${GOARCH} ${GO} build LinScanTool.go 
	GOOS=${GOOS} GOARCH=${GOARCH} ${GO} build BaselineScanTool.go 
	GOOS=${GOOS} GOARCH=${GOARCH} ${GO} build PeakingTimeScanTool.go 
	GOOS=${GOOS} GOARCH=${GOARCH} ${GO} build ENIScanTool.go 
	GOOS=${GOOS} GOARCH=${GOARCH} ${GO} build I2CPhaseScanTool.go 
	GOOS=${GOOS} GOARCH=${GOARCH} ${GO} build I2CTool.go 
	GOOS=${GOOS} GOARCH=${GOARCH} ${GO} build SYSFSTool.go  
	GOOS=${GOOS} GOARCH=${GOARCH} ${GO} build AXIRegisterTool.go  	  
	GOOS=${GOOS} GOARCH=${GOARCH} ${GO} build DCSServer.go DCSMonitoring.go
	GOOS=${GOOS} GOARCH=${GOARCH} ${GO} build FETB2ALFE2SEE.go  	  
	GOOS=${GOOS} GOARCH=${GOARCH} ${GO} build ALFE2TIDDAQTool.go  	  

	chmod -R 700 ${GOPATH}/pkg
}


do_install() {

	install -m 0644 -d ${D}/home/root/bin
	install -m 0644 -d ${D}/home/root/config
	install -m 0644 -d ${D}/home/root/bitstream

	find src/${GO_IMPORT}/Config -type f -exec install -Dm 755 "{}" "${D}/home/root/config" \;

	install -m 0755 src/${GO_IMPORT}/Config/fpga_top.bit ${D}/home/root/bitstream/fpga_top.bit
	
	install -m 0755 src/${GO_IMPORT}/Exec/I2CTool ${D}/home/root/bin/I2CTool
	install -m 0755 src/${GO_IMPORT}/Exec/SYSFSTool ${D}/home/root/bin/SYSFSTool
	install -m 0755 src/${GO_IMPORT}/Exec/AXIRegisterTool ${D}/home/root/bin/AXIRegisterTool
	install -m 0755 src/${GO_IMPORT}/Exec/DCSServer ${D}/home/root/bin/DCSServer

	install -m 0755 src/${GO_IMPORT}/Exec/FETB2ControlTool ${D}/home/root/bin/FETB2ControlTool
	install -m 0755 src/${GO_IMPORT}/Exec/FETB2ALFE2Tool ${D}/home/root/bin/FETB2ALFE2Tool
	install -m 0755 src/${GO_IMPORT}/Exec/AXIStreamDumper ${D}/home/root/bin/AXIStreamDumper
	install -m 0755 src/${GO_IMPORT}/Exec/NoiseScanTool ${D}/home/root/bin/NoiseScanTool
	install -m 0755 src/${GO_IMPORT}/Exec/LinScanTool ${D}/home/root/bin/LinScanTool
	install -m 0755 src/${GO_IMPORT}/Exec/LinScanTool ${D}/home/root/bin/LinScanTool
	install -m 0755 src/${GO_IMPORT}/Exec/BaselineScanTool ${D}/home/root/bin/BaselineScanTool
	install -m 0755 src/${GO_IMPORT}/Exec/PeakingTimeScanTool ${D}/home/root/bin/PeakingTimeScanTool
	install -m 0755 src/${GO_IMPORT}/Exec/ENIScanTool ${D}/home/root/bin/ENIScanTool
	install -m 0755 src/${GO_IMPORT}/Exec/I2CPhaseScanTool ${D}/home/root/bin/I2CPhaseScanTool
	install -m 0755 src/${GO_IMPORT}/Exec/FETB2ALFE2SEE ${D}/home/root/bin/FETB2ALFE2SEE
	install -m 0755 src/${GO_IMPORT}/Exec/ALFE2TIDDAQTool ${D}/home/root/bin/ALFE2TIDDAQTool

}
