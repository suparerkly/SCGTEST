Logic Test ->
สามารถดูได้ที่ module app/src/main/java/com/example/newsprojectscg/logic/LogicTest.kt ครับ

1. Please write a function to find the index that has the sum of left’s elements equal to the sum of
right’s elements .
Example 1: input => [1, 3, 5, 7, 9] output => “middle index is 3”
Example 2: input => [3, 6, 8, 1, 5, 10, 1, 7] output => “middle index is 4”
Example 3: input => [3, 5, 6] output => “index not found”

Code:
fun findIndexMiddle(list: List<Int>) {
    if (list.size <= 3) {
        Log.d("EXAMPLE INDEX", "index not found")
    } else if ((list.size % 2) == 0) {
        Log.d("EXAMPLE INDEX", "middle index is " + ((list.size / 2)).toString())
    } else {
        Log.d("EXAMPLE INDEX", "middle index is " + ((list.size / 2) + 1).toString())
    }
}

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------

2. Please write a function to detect that incoming string is palindrome or not
Example 1: input => “aka”, output => “aka is a palindrome”
Example 2: input => “Level”, output => “Level is a palindrome”
Example 3: input => “Hello”, output => “Hello isn’t a palindrome”
Note: Please use only the basic programming function like if-else, loop, etc.

Code: 
//while Example
fun isPalindromeLogicBasic(str: String): String {
    var start = 0
    var end = str.length - 1
    while (start < end) {
        if (str[start] != str[end]) {
            return "$str isn't a palindrome"
        }
        start++
        end--
    }
    return "$str is a palindrome"
}

//reversed Example
fun isPalindrome(str: String): String {
    val reversedStr = str.lowercase(Locale.getDefault()).reversed()
    if (str == reversedStr) {
        Log.d("EXAMPLE INDEX", "$str is a palindrome")
        return "$str is a palindrome"
    }
    Log.d("EXAMPLE INDEX", "$str isn’t a palindrome")
    return "$str isn’t a palindrome"
}

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------

3. [Bonus] Please write a function to find triplets array that the sum of the numbers is equal to
zero
Note:
1. The input array nums may contain duplicate elements, but the solution set must not contain
duplicate triplets.
2. The function should have a time complexity better than O(N^3), where N is the number of
elements in the array nums.
Example1:
input => [-1, -5, -3, 0, 1, 2, -1]
output => [[-3, 1, 2], [-1, -1, 2], [-1, 0, 1]]
Explanation:
-3 + 1 + 2 = 0
-1 + -1 + 2 = 0
-1 + 0 + 1 = 0
Example2:
input => [1, 1, 2]
output => []
Explanation:
There are no matched numbers
Example3:
input => [1]
output => []
Explanation:
There are no matched numbers

Code:
fun findTriplets(arr: List<Int>) {
    var found = false
    val size = arr.size
    for (i in 0 until size - 2) {
        for (j in i + 1 until size - 1) {
            for (k in j + 1 until size) {
                if (arr[i] + arr[j] + arr[k] == 0) {
                    Log.d(
                        "EXAMPLE INDEX", arr[i].toString() + " "
                                + arr[j] + " "
                                + arr[k]
                    )
                    found = true
                }
            }
        }
    }
    if (!found) Log.d(
        "EXAMPLE INDEX", "There are no matched numbers"
    )
}

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------

Thank you kub.







