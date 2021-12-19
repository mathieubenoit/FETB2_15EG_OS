# FETB2 Petalinux OS 

This repository contains the files needed to generate the Petalinux OS (2020.2) for the FETB2, based on a Enclustra XU1 SOM. 


## Dependencies 
To proceed to the image compilation, you must install and source the petalinux-tools, version 2020.2, that can be downloaded from [here](https://www.xilinx.com/support/download/index.html/content/xilinx/en/downloadNav/embedded-design-tools/2020-2.html). 


## compilation

First, source the petalinux-tools environement : 
```
source petalinux_folder/settings.sh
```

If a new Bitstream is to be imported, generate the XSA file for this firmware, and copy the file to the root repository folder, then load the file in the project using : 

```
petalinux-config --get-hw-description FIRMWARE.XSA
```

The configuration menu will be spawned, make modifications if required, then save and quit. 

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
