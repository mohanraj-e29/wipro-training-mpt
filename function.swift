
func student(name: String) -> String {

    return name

}
 
// default value to the params
//by default (by:1) it means we can change after we are assign the value 
// ( _ ) means we cannot pass the label..
func increment(_ value: Int, by: Int = 1) -> Int {

    return value + by

}
//we are passing one value because we set by default as 1 , we change by assign after..
  print(increment(2))
 
// variadic parameter
// we can pass n number of parameters..

func makeSum(_ num: Int...) -> Int {

    var sum = 0

    for x in num {

        sum += x

    }

    return sum

}
 
func sg(arr: [Int]) -> (largest: Int, smallest: Int) {
    var largest = arr[0]
    var smallest = arr[0]

    for i in arr[1..<arr.count] {
        if i < smallest {
            smallest = i
        } else if i > largest {
            largest = i
        }
    }

    return (largest, smallest)
}

let result = sg(arr: [3, 4, 5, 6])
print("largest: \(result.largest), smallest: \(result.smallest)")


 
func addition(a: Int, b: Int) -> Int {

    return a + b

}
 
var result: (Int, Int) -> Int = addition
 
// print(student(name: "Aaryan"))

// print(increment(value: 2, by: 2))

print("Sum: ", makeSum(10, 20, 40, 5))

var res = sg(arr: [3, 4, 5, 6, -1, 10])

print("Largest num: \(res.largest), Smallest num: \(res.smallest)")

print(result(2, 3))
 

import Foundation
 
var myTuple = (name: "Aaryan", salary: 23, role: "Trainer", loc: "Uk")
 
// print("name:", myTuple.3)

let (name, _, _, loc) = myTuple
 
print(myTuple.salary)

import Foundation
 
// runs after the execution

func validate(_ marks: Int) {

    assert(marks >= 0, "Marks cannot be negative")

    print("marks are valid")

}
 
func division(_ num: Int, deno: Int) -> Int {

    precondition(deno != 0, "Denominator should be non-zero")

    return num / deno

}
 
print(division(2, deno: 0))

import Foundation
 
func factorial(num: Int) -> Int {

    if num == 0 || num == 1{

        return 1

    } else {

        return num * factorial(num: num - 1)

    }

}
 
// 3! = 3 * 2 * 1

print(factorial(num: 5))