package com.example.newsprojectscg.logic

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import java.util.Locale

class LogicTest : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            // find index middle
            val example1 = listOf(1, 3, 5, 7, 9)
            val example2 = listOf(3, 6, 8, 1, 5, 10, 1, 7)
            val example3 = listOf(3, 5, 6)

            findIndexMiddle(example1)
            findIndexMiddle(example2)
            findIndexMiddle(example3)


            // palindrome string
            val palindrome1 = "aka"
            val palindrome2 = "Level"
            val palindrome3 = "Hello"

            isPalindrome(palindrome1.lowercase(Locale.getDefault()))
            isPalindrome(palindrome2.lowercase(Locale.getDefault()))
            isPalindrome(palindrome3.lowercase(Locale.getDefault()))


            // triple array
            val tripleArray1 = listOf(-1, -5, -3, 0, 1, 2, -1)
            val tripleArray2 = listOf(1, 1, 2)
            val tripleArray3 = listOf(1)

            findTriplets(tripleArray1)
            findTriplets(tripleArray2)
            findTriplets(tripleArray3)
        }
    }
}

fun findIndexMiddle(list: List<Int>) {
    if (list.size <= 3) {
        Log.d("EXAMPLE INDEX", "index not found")
    } else if ((list.size % 2) == 0) {
        Log.d("EXAMPLE INDEX", "middle index is " + ((list.size / 2)).toString())
    } else {
        Log.d("EXAMPLE INDEX", "middle index is " + ((list.size / 2) + 1).toString())
    }
}

fun isPalindrome(str: String): String {
    val reversedStr = str.lowercase(Locale.getDefault()).reversed()
    if (str == reversedStr) {
        Log.d("EXAMPLE INDEX", "$str is a palindrome")
        return "$str is a palindrome"
    }
    Log.d("EXAMPLE INDEX", "$str isn’t a palindrome")
    return "$str isn’t a palindrome"
}

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