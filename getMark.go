package uniqueMark

import (
    "math"
    "time"
)

// GetUniqueMark 获得一个独特的字符串，
func GetUniqueMark()string {
    var seed = time.Now().UnixNano() / 1000000 //13毫秒
    var chars = []string{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
        "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
        "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"}
    var indexs []int
    var findNotZero = false
    for i := 50; i >= 0; i-- {
        ret := int(float64(seed) / math.Pow(float64(len(chars)), float64(i)))
        if !findNotZero && ret != 0 {
            findNotZero = true
        }
        if findNotZero{
            seed = seed - int64(math.Pow(float64(len(chars)), float64(i)) * float64(ret))
            indexs = append(indexs, ret)
        }
    }
    var output = ""
    for _,index := range indexs{
        output += chars[index]
    }
    return output
}
