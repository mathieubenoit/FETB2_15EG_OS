#!/bin/bash 
echo "Overriding device tree with our own"
dtc -I dts -O dtb -o system.dtb system.dts
mv system.dtb images/linux/
