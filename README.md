# FETB2 Petalinux OS 

This repository contains the files needed to generate the Petalinux OS (2020.2) for the FETB2, based on a Enclustra XU1 SOM. 


## Dependencies 
To proceed to the image compilation, you must install and source the petalinux-tools, version 2020.2, that can be downloaded from [here](https://www.xilinx.com/support/download/index.html/content/xilinx/en/downloadNav/embedded-design-tools/2020-2.html). 


## Initialisation

First, source the petalinux-tools environement : 
```
source petalinux_folder/settings.sh
```

If a new Bitstream is to be imported, generate the XSA file for this firmware, and copy the file to the root repository folder, then load the file in the project using : 

```
petalinux-config --get-hw-description FIRMWARE.XSA
```

The configuration menu will be spawned, make modifications if required, then save and quit. 

## Customisation

The OS image contains most important package and software needed for operation. The go compiler and the [goFETB2DAQ](https://gitlab.cern.ch/BNL-ATLAS/larphase2/fetb2/gofetb2daq) are precompiled and included in the image. 

The image root file system can be further modified to add additional software to the image. To do so, you can select software from the interactive menu bu using : 

```
petalinux-config -c rootfs
```

If you know the package to be added, you can add it to the [user rootfs config file](https://gitlab.cern.ch/BNL-ATLAS/larphase2/fetb2/fetb2os/fetb2_xu1_15eg/-/blob/master/project-spec/meta-user/conf/user-rootfsconfig), pre-pended by `CONFIG`, and activate it in the interactive menu using the precedent command. 

THe device tree can be appended via the [user device tree file](https://gitlab.cern.ch/BNL-ATLAS/larphase2/fetb2/fetb2os/fetb2_xu1_15eg/-/blob/master/project-spec/meta-user/recipes-bsp/device-tree/files/system-user.dtsi). Add device tree code appendage in there to apply to the final device tree build.

The kernel and u-boot can also be configured, by expert. Contact `mbenoitNOSPAM@bnl.gov` for help on that matter.

## compilation

If you do not need to customize the image, most likely the latest results of the Continuous Integration compilation are sufficient to create an image. You can download the required files from the [latest artifacts](https://gitlab.cern.ch/BNL-ATLAS/larphase2/fetb2/fetb2os/fetb2_xu1_15eg/-/jobs/artifacts/master/browse?job=build) and follow the instruction of the following section. 

To launch the image compilation, use : 

```
petalinux-build
```

Finally, to package the BOOT.BIN and WIC image, use : 

```
./package.sh
```

The files to burn the image will be located in the `images/linux/` folder.


## SD Card creation 

Follow the instruction [here](https://github.com/enclustra/PetalinuxDocumentation/blob/master/doc/SD_boot_mode.md) to copy the files to an already formated file.  To use the WIC file, use a WIC image tool or follow instruction from the **Creating Partitioned Images Using Wic** section from the [petalinux-tools 2020.2 reference guide, p79](https://www.xilinx.com/support/documentation/sw_manuals/xilinx2020_2/ug1144-petalinux-tools-reference-guide.pdf)

Eject the SD card properly. In Linux, use `sudo sync` top make sure the content is copied to the card, then eject the card. Insert in the FETB2 and power on. 
