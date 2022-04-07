source ovveride_devicetree.sh
petalinux-package --boot --fsbl images/linux/zynqmp_fsbl.elf --u-boot images/linux/u-boot.elf --pmufw images/linux/pmufw.elf --fpga images/linux/system.bit --force
petalinux-package --wic 
petalinux-package --prebuilt --force 
cp ../pmu_rom_qemu_sha3.elf pre-built/linux/images

