count=1
read num
while [ $count -lt 11 ]
    do
    value=`expr $num \* $count`
    echo "$num * $count = $value"
    count=`expr $count + 1`
done