arr=(“Jayesh” “Shivang” “1” "Vipul" “Nishant” “2”)
 
echo ${arr[@]:2:3}

echo ${arr[0]:3}
echo ${arr[@]:0:4}
 
 
unset arr[1]
# Jayesh -->
echo "arrayex.${arr[0]:5}"
 
echo "Length of ${#arr[3]}"









arr=(“Jayesh” “Shivang” “1” "Vipul" “Nishant” “2”)
 
echo ${arr[@]:2:3}
echo "-----------------------------------------------------"
echo ${arr[@]:2:3}
echo "-----------------------------------------------------"
echo ${arr[0]:3}
echo "-----------------------------------------------------"
echo ${arr[5]:0:4}
echo "----------------------------------------------------"
echo ${arr[@]:0:6}
echo "-----------------------------------------------------"
 
 
unset arr[1]
# Jayesh -->
echo "-----------------------------------------------------"
echo "arrayex.${arr[0]:5}"
echo "-----------------------------------------------------"
 
echo "Length of ${#arr[3]}"
echo "-----------------------------------------------------"