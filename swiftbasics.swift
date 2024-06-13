import Foundation
 
let a = 5
let b = 6
 
// basic operations
print(a / b)
var name = "Aaryan"
let name2 = "Arush"; 
print(name)
print(name2)
print("hello world")
 
// String interpolation
print("the value of a is", a)
print("The value of a is \(a) and the value of b is \(b)")
 
// types defination
var lastName : String = "Kumar"
print(lastName)
 
// final/constant 
let con = 5
con = 6
import Foundation   let a = 7; let b = 7;... by Aaryan (Guest)

import Foundation
 
let a = 7;
let b = 7;
 
if(a > b) {
    print("\(a) is greater than \(b)")
} else if(a < b) {
    print("\(b) is greater than \(a)")
}else {
    print("Both r equal")
}
 
// [1, 2, 3, 4, 5]
for x in 1...5 {
    if(x == 4) {
        break
    }
    print(x)
}
 
/*for x in 1...5 {
    for y in 1...x {
        print(y)
    }
}*/
 
 
var arr:[Int] = [10, 20, 30, 40, 50]
 
for x in arr {
    print(x)
}

// Task: you want to print hello world 20 t... by Aaryan (Guest)

// Task: you want to print hello world 20 times
import Foundation
/*for x in 1..<20 {
    print("hello world", x)
}
 
for x in stride(from: 1, to: 20, by: 3) {
    print(x)
}
 
for i in 1...20 where i % 2 == 0 {
    print(i)
} */
var counter = 0
repeat {
    print(counter)
    counter+=1
} while(counter < 5)
func add(a: Int, b: Int) -> Int {     ret... by Aaryan (Guest)

func add(a: Int, b: Int) -> Int {
    return a + b
}
 
// 5 = 5*4*3*2*1
func factorial(num: Int) -> Int {
    var result = 1
    if(num == 0) {
        return 1
    }
    for x in 2...num {
        result *= x
    }
    return result
}
 
//let result = add(a: 3, b: 2)
let result = factorial(num: 0)
print(result)

func factorial(num: Int) -> Int? {     va... by Aaryan (Guest)

func factorial(num: Int) -> Int? {
    var result = 1
    if(num == 0) {
        return 1
    }
    for x in 2...num {
        result *= x
    }
    if(num > 0 ) {
        return result
    }
}
 
//let result = add(a: 3, b: 2)
let result = factorial(num: 0)
print(result)

var num: Int?   num = nil   if let x = num ... by Aaryan (Guest)

var num: Int?
 
num = nil
 
if let x = num {
    print(x)
} else {
    print("nf")
}
 
var name : String? = "Arusha"   switch name... by Aaryan (Guest)

var name : String? = "Arusha"
 
switch name {
    case "Aaryan":
        print("ok")
    case "Arush":
        print("ok1")
    case "Ananya":
        print("ok2")
    case "Akriti":
        print("ok3")
    default:
        print("bad choice")
}