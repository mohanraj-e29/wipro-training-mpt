#!/bin/bash

# To declare a static Array
arr=(“Jayesh” “Shivang” “1” “Vipul” “Nishant” “2”)

# To print all elements of the array
echo “All elements of the array:”
echo “${arr[@]}”
echo “${arr[*]}”
echo "------------------------------------------------------------------"

# To print the first element
echo “The first element:”
echo “${arr[0]}”
echo "------------------------------------------------------------------"
# To print a selected index element
selected_index=3
echo “Selected index element at index $selected_index:”
echo “${arr[$selected_index]}”
echo "------------------------------------------------------------------"
# To print elements from a particular index
echo “Elements from a particular index:”
echo “${arr[@]:2}” # Prints elements starting from index 2
echo “${arr[*]:2}” # Prints elements starting from index 2
echo "------------------------------------------------------------------"
# To print elements in a range
echo “Elements in a range:”
echo “${arr[@]:1:3}” # Prints elements from index 1 to 3
echo “${arr[*]:1:3}” # Prints elements from index 1 to 3
echo "------------------------------------------------------------------"
# Count the length of a particular element in the array
element_length=${#arr[2]}
echo “Length of element at index 2: $element_length”
echo "------------------------------------------------------------------"
# Count the length of the entire array
array_length=${#arr[@]}
echo “Length of the array: $array_length”
echo "------------------------------------------------------------------"
# Search in the array
search_result=$(echo “${arr[@]}” | grep -c “Jayesh”)
echo “Search result for ‘Jayesh’: $search_result”
echo "------------------------------------------------------------------"
# Search and replace in the array
replaced_element=$(echo “${arr[@]/Shivang/SHIVANG}”)
echo “Array after search & replace: ${replaced_element[*]}”
echo "------------------------------------------------------------------"
# Delete an element in the array (index 3)
unset arr[3]

echo “Array after deletion: ${arr[*]}”