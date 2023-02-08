import kotlinx.coroutines.*

fun main() = runBlocking {
    val pattern = "aab"
    val text = "aaabaaaab"
    
    val result = kmp(text, pattern)
    println(result)
}

suspend fun kmp(text: String, pattern: String): Int {
    val lps = computeLPS(pattern)
    var i = 0
    var j = 0
    while (i < text.length) {
        if (pattern[j] == text[i]) {
            j++
            i++
        }
        if (j == pattern.length) {
            return i - j
        } else if (i < text.length && pattern[j] != text[i]) {
            if (j != 0) {
                j = lps[j - 1]
            } else {
                i++
            }
        }
    }
    return -1
}

fun computeLPS(pattern: String): IntArray {
    val lps = IntArray(pattern.length)
    var len = 0
    var i = 1
    while (i < pattern.length) {
        if (pattern[i] == pattern[len]) {
            len++
            lps[i] = len
            i++
        } else {
            if (len != 0) {
                len = lps[len - 1]
            } else {
                lps[i] = len
                i++
            }
        }
    }
    return lps
}
