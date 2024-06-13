
var arr: [Int] = [1, 2, 3, 0]
 
// Task1: increase all the elements by 1

//enumerated will return the index of each value
for (index, element) in arr.enumerated() {

    arr[index] = element + 1

    print("Previous value: \(element)")

    print("Updated value: \(index), \(arr[index])")

}
 
// Task2: add a new element

arr += [40]
 
// Task3: how to do the same using while loop?

var index = 0

while index < arr.count {

    print(arr[index])

    index += 1

}
 
// Task4: foreach method

arr.forEach {

    numbers in print(numbers)

}
 
// Task5: add two arrays --> combine

var arr2 = [60, 70, 73, -9]

var arr3: [Int] = []

print(arr + arr2)

print(arr3.isEmpty)
 
 
 
 

// Set --> unique, not allow duplicacy
 
var myset: Set<Int> = [3, 4, 5, 6, 3]

var myset1: Set<Int> = [3, 5, 7, 0]

// A - B -->
 
/*

for (index, x) in myset.enumerated() {

    print(index, x)

}
 
myset.forEach {

    num in print(num)

}*/
 
print(myset.intersection(myset1))

print(myset.union(myset1))

print(myset.subtracting(myset1))

var dict: [String:Any]  = ["name": "Aaryan", "role": "Trainer", "exp": 9]

/*

for key in dict.keys {

    print(key)

}
 
for value in dict.values {

    print(value)

}*/
 
dict.updateValue("Developer", forKey: "role")

dict.removeValue(forKey: "exp")

// dict.removeAll()
 
for (key, value) in dict {

    print("\(key): \(value)")

}
 
// dict into array

let keys = [String](dict.keys)

for x in keys {

    print(x)

}
