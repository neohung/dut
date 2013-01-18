exec 6<>neofifo
#sudo -s mkfs.ntfs /dev/sdc1 >&6  &
echo "10%20%30%40%" >&6
while read line <&6;
do 
	echo $line
	if (grep '%' <<< $line); then
		grep '%' <<< $line | awk '{ first=match($0,"%"); s=substr($0, first-2, 2); print s }'	
	fi
#	if (grep -q '=2' <<< $line); then
#		break	
#	fi
done
echo "neo finish"
exec 6>&-
exec 6<&-
