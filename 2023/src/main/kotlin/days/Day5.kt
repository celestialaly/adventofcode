package days

import java.math.BigInteger
import kotlin.math.*

class Day5 : Day(5) {
    override fun partOne(): Any {
        var lineIndex = 0
        var seedsNumber = "([0-9]+)".toRegex().findAll(inputList[lineIndex]).map({ it.value.toBigInteger() }).toMutableList()
        
        lineIndex += 3

        // run each step
        do {
            val pair = updateSeedPosition(inputList, lineIndex, seedsNumber)
            lineIndex = pair.first
            seedsNumber = pair.second
            
            lineIndex += 2
        } while (lineIndex < inputList.count())
        
        return seedsNumber.sorted().first()
    }
    
    private fun updateSeedPosition(inputList: List<String>, _lineIndex: Int, seedsPosition: MutableList<BigInteger>): Pair<Int, MutableList<BigInteger>> {
        var lineIndex = _lineIndex
        val newSeedsPosition = mutableListOf<BigInteger>()
        val modifiedSeeds = mutableListOf<BigInteger>()
        
        do {
            val (dest, source, range) = "([0-9]+)".toRegex().findAll(inputList[lineIndex]).map({ it.value.toBigInteger() }).toList()
            val sourceEnd = source+range
            
            seedsPosition.forEachIndexed { key, number ->
                if (number in source..sourceEnd) {
                    newSeedsPosition.add(number-source+dest)
                    modifiedSeeds.add(number)
                }
            }
            
            lineIndex++
        } while (lineIndex < inputList.count() && inputList[lineIndex].isNotEmpty())

        // add not modified seeds
        newSeedsPosition.addAll(seedsPosition.filter { !modifiedSeeds.contains(it) })
        
        return Pair(lineIndex, newSeedsPosition)
    }

    private fun getStepData(inputList: List<String>, _lineIndex: Int): Pair<Int, MutableList<Triple<Long, Long, Long>>> {
        var lineIndex = _lineIndex
        val list = mutableListOf<Triple<Long, Long, Long>>()

        do {
            val (dest, source, range) = "([0-9]+)".toRegex().findAll(inputList[lineIndex]).map({ it.value.toLong() }).toList()
            list.add(Triple(dest, source, range))

            lineIndex++
        } while (lineIndex < inputList.count() && inputList[lineIndex].isNotEmpty())

        return Pair(lineIndex, list)
    }

    override fun partTwo(): Any {
        var lineIndex = 0
        var seedsNumber = "([0-9]+)".toRegex().findAll(inputList[lineIndex]).map({ it.value.toLong() }).toMutableList()
        val steps = mutableListOf<MutableList<Triple<Long, Long, Long>>>()

        lineIndex += 3
        
        // get data for each step
        do {
            val pair = getStepData(inputList, lineIndex)
            lineIndex = pair.first
            steps.add(pair.second)

            lineIndex += 2
        } while (lineIndex < inputList.count())
        
        val seedCount = seedsNumber.count() - 1
        var ranges = mutableListOf<Pair<Long, Long>>()
        for (i in 0..seedCount step 2) {
            val seedStart = seedsNumber[i]
            val seedEnd = seedsNumber[i] + seedsNumber[i+1]
            ranges.add(Pair(seedStart, seedEnd))
        }

        println("def. ranges: $ranges")
        var i = 1
        for (step in steps) {
            val prevRanges = ranges
            ranges = getEndingRanges(ranges, step)
//            println("ranges after step $i: $ranges")
            i++
//            return 0
        }
        
//        println("end. ranges: $ranges")
        
        var lowest = Long.MAX_VALUE
        for (range in ranges) {
            if (range.first == 0.toLong()) {
                println(range)
                return 0
            }
            lowest = min(lowest, range.first)
        }
        
        return lowest
    }
    
    private fun getEndingRanges(ranges: MutableList<Pair<Long, Long>>, step: MutableList<Triple<Long, Long, Long>>): MutableList<Pair<Long, Long>>
    {
        var newRanges = mutableListOf<Pair<Long, Long>>()
        
        for (range in ranges) {
            var hasChanged = false
            
            for (stepRange in step) {
                val startOverlapRange = max(range.first, stepRange.second)
                val minEnd = min(range.second, stepRange.second+stepRange.third-1)

                val overlap = max(
                    0.toLong(),
                    minEnd - startOverlapRange
                )
                
//                println("start overlap $startOverlapRange / min end $minEnd / overlap $overlap")

                if (overlap > 0) {
                    hasChanged = true
                    val diff = stepRange.first - stepRange.second
                    
                    if (overlap == range.second - range.first) {
                        newRanges.add(Pair(startOverlapRange+diff, startOverlapRange+diff+overlap+1))
                    } else {
                        newRanges.add(Pair(startOverlapRange+diff, startOverlapRange+diff+overlap))
                    }
                    
                    if (overlap < range.second - range.first) {
                        if (range.first < stepRange.second) {
                            newRanges.add(Pair(range.first, stepRange.second-1))
                        }
                        if (range.second > startOverlapRange+overlap) {
                            newRanges.add(Pair(startOverlapRange+overlap+1, range.second))
                        }
                    }
                }
            }
            
            if (!hasChanged) {
                newRanges.add(range)
            }
        }
        
        return newRanges
    }
}