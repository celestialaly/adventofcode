package days

class Day1 : Day(1) {

    override fun partOne(): Any {
        val list: List<String> = inputList
            .map { it.filter { str -> str.isDigit() } }

        return sumOfFirstAndLastDigits(list)
    }

    override fun partTwo(): Any {
        val list: List<String> = inputList
            .map { findFirstAndLastDigit(it) }

        return sumOfFirstAndLastDigits(list)
    }

    private fun findFirstAndLastDigit(value: String): String {
        val possibleStringMatches = listOf<String>(
            "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"
        )

        val numbersMap = mapOf("one" to 1, "two" to 2, "three" to 3, "four" to 4, "five" to 5, "six" to 6, "seven" to 7, "eight" to 8, "nine" to 9)

        var firstDigit: Int? = null
        var lastDigit: Int? = null
        var firstPosition: Int? = null
        var lastPosition: Int? = null

        possibleStringMatches.forEach lit@ {
            var position = -1
            var prevPosition: Int

            do {
                prevPosition = position
                position = value.indexOf(it, position+1)

                if (-1 == position) return@lit

                if (firstPosition == null || position < firstPosition!!) {
                    firstPosition = position
                    firstDigit = if (it.first().isDigit()) it.toInt() else numbersMap[it]
                }

                if (lastPosition == null || position > lastPosition!!) {
                    lastPosition = position
                    lastDigit = if (it.first().isDigit()) it.toInt() else numbersMap[it]
                }
            } while (prevPosition != position)
        }

        return "${firstDigit}${lastDigit}"
    }

    private fun sumOfFirstAndLastDigits(list: List<String>): Int {
        return list
            .filter { it.isNotEmpty() }
            .map {
                if (it.count() > 1)
                    "${it.first()}${it.last()}"
                else
                    "${it.first()}${it.first()}"
            }
            .sumOf { it -> it.toInt() }
    }
}
