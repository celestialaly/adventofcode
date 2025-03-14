package days

class Day3 : Day(3) {

    override fun partOne(): Any {
        val lineCount = inputList.count()
        var sum = 0
        
        for ((lineIndex, line) in inputList.withIndex()) {
            val charCount = line.count()
            
            var i = 0
            while (i < charCount) {
                var number = ""
                var startPos: Int? = null
                var endPos: Int? = null

                while (i < charCount && line[i].isDigit()) {
                    if (startPos == null) {
                        startPos = i
                    }
                    
                    number += line[i]
                    i++
                }
                
                if (number.isNotEmpty() && startPos !== null) {
                    endPos = i - 1
                    
                    // find if symbol is adjacent
                    if (
                        hasSymbolInRangePosition(line, startPos-1, endPos+1)
                        || hasSymbolInRangePosition(inputList.getOrNull(lineIndex-1), startPos-1, endPos+1)
                        || hasSymbolInRangePosition(inputList.getOrNull(lineIndex+1), startPos-1, endPos+1)
                    ) {
                        sum += number.toInt()
                    }
                }
                
                if (i < charCount && !line[i].isDigit()) {
                    i++
                }
            }
            
        }
        
        return sum
    }
    
    private fun hasSymbolInRangePosition(line: String?, startPos: Int, endPos: Int): Boolean {
        if (line == null) return false
        
        val charCount = line.count()
//        println("$line: $startPos;$endPos;$charCount")
        
        val pStart = if (startPos < 0) 0 else startPos
        val pEnd = if (endPos >= charCount) charCount-1 else endPos
        
        for (i in pStart..pEnd) {
            if (!line[i].isLetterOrDigit() && line[i] != '.') {
                return true
            }
        }
        
        return false
    }

    override fun partTwo(): Any {
        return "test"
    }
}