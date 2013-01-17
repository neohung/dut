exec 6<>neopipe
ping 127.0.0.1 -c 5 >&6  &
while read line <&6;
do 
	if (grep 'ms' <<< $line); then
		sed 's/ms/aa/g'
		echo "ms"
	fi
	if (grep -q '=2' <<< $line); then
		break	
	fi
done
echo "neo finish"
exec 6>&-
exec 6<&-
