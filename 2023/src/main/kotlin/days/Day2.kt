package days

const val RED_COLOR = "red"
const val RED_CUBES_THRESOLD: Int = 12

const val GREEN_COLOR = "green"
const val GREEN_CUBES_THRESOLD: Int = 13

const val BLUE_COLOR = "blue"
const val BLUE_CUBES_THRESOLD: Int = 14

class Day2 : Day(2) {

    override fun partOne(): Any {
        var sum = 0;
        
        inputList
            .forEach { line ->
                val (gameInfo, gameValues) = line.split(": ")
                val (_, gameNumber) = gameInfo.split(" ")
                
                val gameLists = gameValues.split("; ")
                
                var gameIsValid = true
                gameLists.forEach { game ->
                    val numberOfEachCubes = mutableMapOf<String, Int>(
                        RED_COLOR to 0,
                        GREEN_COLOR to 0,
                        BLUE_COLOR to 0,
                    )

                    // increment numberOfEachCubes for each game listed (separated by comma)
                    game.split(", ").forEach { cube ->
                        val (numberOfCubes, cubeColor) = cube.split(" ")
                        
                        numberOfEachCubes.merge(cubeColor, numberOfCubes.toInt(), Int::plus)
                    }

                    // if we are below the thresold, add the game number to total
                    if (
                        numberOfEachCubes.get(RED_COLOR)!! > RED_CUBES_THRESOLD ||
                        numberOfEachCubes.get(GREEN_COLOR)!! > GREEN_CUBES_THRESOLD ||
                        numberOfEachCubes.get(BLUE_COLOR)!! > BLUE_CUBES_THRESOLD
                        ) {
                        gameIsValid = false
                    }
                }
                
                if (gameIsValid) {
                    sum += gameNumber.toInt()
                }
            }
        
        return sum
    }
    
    override fun partTwo(): Any {
        var sum = 0;

        inputList
            .forEach { line ->
                val (_, gameValues) = line.split(": ")

                val gameLists = gameValues.split("; ")

                val numberOfEachCubesRequired = mutableMapOf<String, Int>(
                    RED_COLOR to 0,
                    GREEN_COLOR to 0,
                    BLUE_COLOR to 0,
                )
                gameLists.forEach { game ->
                    // increment numberOfEachCubes for each game listed (separated by comma)
                    game.split(", ").forEach { cube ->
                        val (numberOfCubes, cubeColor) = cube.split(" ")
                        
                        if (numberOfCubes.toInt() > numberOfEachCubesRequired.get(cubeColor)!!) {
                            numberOfEachCubesRequired.replace(cubeColor, numberOfCubes.toInt())
                        }
                    }
                }
                
                sum += numberOfEachCubesRequired.get(RED_COLOR)!! * numberOfEachCubesRequired.get(GREEN_COLOR)!! * numberOfEachCubesRequired.get(BLUE_COLOR)!!;
            }

        return sum
    }
}