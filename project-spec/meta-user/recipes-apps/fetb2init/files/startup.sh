#!/bin/sh

export TZ="GMT+4"

echo "Uploading latest firmware..."
fpgautil -b /home/root/bitstream/fpga_top.bit

sleep 3
echo "Programming si5395..."
./bin/Si5345Tool -f /home/root/config/Si5345/si5345.csv

#INA228
i2c_bus="/dev/i2c-3"
temp_limit="50"

ina_12v_addr="0x40"
ina_12v_ov="12.8"
ina_12v_uv="11.2"
ina_12v_shunt="0.005"
ina_12v_max_power="24.0"

ina_1v8_addr="0x41"
ina_1v8_ov="1.9"
ina_1v8_uv="1.7"
ina_1v8_shunt="0.001"
ina_1v8_max_power="0.5"

ina_3v3_addr="0x44"
ina_3v3_ov="3.4"
ina_3v3_uv="3.2"
ina_3v3_shunt="0.005"
ina_3v3_max_power="0.5"

./bin/INA228Tool -a $ina_12v_addr -r -p -b $i2c_bus -bov $ina_12v_ov -buv $ina_12v_uv -pm $ina_12v_max_power -s $ina_12v_shunt -tm $temp_limit
./bin/INA228Tool -a $ina_1v8_addr -r -p -b $i2c_bus -bov $ina_1v8_ov -buv $ina_1v8_uv -pm $ina_1v8_max_power -s $ina_1v8_shunt -tm $temp_limit
./bin/INA228Tool -a $ina_3v3_addr -r -p -b $i2c_bus -bov $ina_3v3_ov -buv $ina_3v3_uv -pm $ina_3v3_max_power -s $ina_3v3_shunt -tm $temp_limit



#source fetb2_test_scripts/shell_scripts/startup_stable.sh
sleep 3 

python3  /home/root/fetb2_test_scripts/python_debug_driver/startup.py


/etc/init.d/ntpd start
