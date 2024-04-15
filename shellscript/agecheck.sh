#!/bin/bash
# if-else statement
# TODO: create a script that takes age as an input and displays the eligibility >= 18
 
read age
 
# age >= 18
if [ $age -ge 100 ]; then
    echo "your age exceed the maximum limit"
elif [ "$age" -ge 18 ]; then
    echo "You are eligible"
else
    echo "you are not eligible"
fi