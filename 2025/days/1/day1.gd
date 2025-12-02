extends Label

func _init() -> void:
	var input = Helper.read_input(1)
	var part1 = part1(input)
	
	text = str('Part 1: ', part1)

func part1(input: Array) -> int:
	var lock_value = 50
	var result = 0
	
	for line in input:
		var split = line.split("", true, 1)
		var val = int(split[1])

		if (split[0] == 'L'):
			lock_value -= val
		else:
			lock_value += val
		
		if lock_value % 100 == 0:
			result += 1
			
	return result
