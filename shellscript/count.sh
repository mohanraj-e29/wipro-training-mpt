count=0
 
# lt is less than operator
#Iterate the loop until count less than 10
 
# while (condition) --> true ? terminate
# while(count < 10)
# count = count +!
while [ $count -lt 10 ]
    do
    # Print the values
    echo `expr $count + 1`
    # increment the value
    count=`expr $count + 1`
done