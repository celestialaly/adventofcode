package days

class Day4 : Day(4) {

    private fun parseLine(line: String): Triple<Int, List<String>, List<String>>
    {
        val matchResult = "Card[ ]+([0-9]+): ([0-9 ]+) \\| ([0-9 ]+)".toRegex().find(line)

        val gameNumber = matchResult!!.destructured.component1().toInt()
        val cards = "([0-9]+)".toRegex().findAll(matchResult.destructured.component2()).map({ it.value }).toList()
        val winningCards = "([0-9]+)".toRegex().findAll(matchResult.destructured.component3()).map({ it.value }).toList()

        return Triple(gameNumber, cards, winningCards)
    }
    
    override fun partOne(): Any {
        var totalPoints = 0
        
        inputList
            .forEach { line ->
                val (_, cards, winningCards) = parseLine(line)
                
                var points = 0
                cards.forEach { card ->
                    if (winningCards.contains(card)) {
                        points = if (points == 0) 1 else points*2
                    }
                }
                
                totalPoints += points
        }
        
        return totalPoints
    }
    
    override fun partTwo(): Any {
        val scratchCards = mutableMapOf<Int, Int>()
        
        inputList
            .forEach { line ->
                val (gameNumber, cards, winningCards) = parseLine(line)

                var matchesNumber = 0
                cards.forEach { card ->
                    if (winningCards.contains(card)) {
                        matchesNumber++
                    }
                }

                scratchCards[gameNumber] = matchesNumber
            }

        var totalScratchcards = 0
        scratchCards.forEach { (number, _) ->
            totalScratchcards++
            totalScratchcards += recursiveScratchcardsCount(scratchCards, number)
        }

        return totalScratchcards
    }
    
    private fun recursiveScratchcardsCount(scratchCards: MutableMap<Int, Int>, number: Int): Int
    {
        var winned = scratchCards.getOrDefault(number, 0)
        var count = winned
        
        while (winned > 0) {
            count += recursiveScratchcardsCount(scratchCards, number + winned)
            winned--
        }
        
        return count
    }
}