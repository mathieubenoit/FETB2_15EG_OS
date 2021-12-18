sudo rm -fr /media/mbenoit/root/*
rm -fr /media/mbenoit/boot/*
cp images/linux/BOOT.BIN images/linux/Image images/linux/boot.scr /media/mbenoit/boot/
sudo tar xvzf images/linux/rootfs.tar.gz -C /media/mbenoit/root/
sudo sync
