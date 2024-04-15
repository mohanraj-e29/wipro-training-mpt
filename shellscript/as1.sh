#!/bin/bash
# echo "Enter numbers (enter 0 to exit):"
# while true; do
#     read -p "Enter a number: " number

#     if [ "$number" -eq 0 ]; then
#         echo "Exiting."
#         break
#     fi

#     if [ "$((number % 2))" -eq 0 ]; then
#         echo "$number is even."
#     else
#         echo "$number is odd."
#     fi
# done

#!/bin/bash

# Prompt the user to enter a number
echo "Enter a number: "

# Read the input provided by the user and assign it to the variable 'number'
read number

# Check if the input is 0 to exit the loop, otherwise determine if it's odd or even
while [ "$number" -ne 0 ]; do
    if [ "$((number % 2))" -eq 0 ]; then
        echo "$number is even."
    else
        echo "$number is odd."
    fi

    # Prompt the user to enter another number
    echo "Enter a number: "
    read number
done

# If the user enters 0, exit the script
echo "Exiting."

