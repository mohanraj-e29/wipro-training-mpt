class Marks {
    var m1: Int
    var m2: Int
    var m3: Int
    init(m1: Int, m2: Int, m3: Int) {
        self.m1 = m1
        self.m2 = m2
        self.m3 = m3
    }
    func avg() -> Int {
        return (m1 + m2 + m3) / 3;
    }
}
 
var obj = Marks(m1: 3, m2: 4, m3: 5)
obj.m1 = 10
print(obj.avg())