extends Label

func _init() -> void:
	var input = Helper.read_input(1)
	var part1 = part1(input)
	var part2 = part2(input)
	
	text = Helper.format_result(part1, part2)

func part1(input: Array) -> int:
	var lock_value = 50
	var result = 0
	
	for line in input:
		var split = line.split("", true, 1)
		var direction = split[0]
		var val = int(split[1])

		if (direction == 'L'):
			lock_value -= val
		else:
			lock_value += val
		
		if lock_value % 100 == 0:
			result += 1
			
	return result

func part2(input: Array) -> int:
	var lock_value = 50
	var result = 0
	
	for line in input:
		var split = line.split("", true, 1)
		var direction = split[0]
		var val = int(split[1])
		var has_stepped_over_zero = false

		# count number of full rotation
		if val >= 100:
			result += floori(val / 100)
			val = val % 100

		if (direction == 'L'):
			has_stepped_over_zero = lock_value % 100 != 0 and posmod(lock_value, 100) - posmod(val, 100) < 0
			lock_value -= val
		else:
			has_stepped_over_zero = lock_value % 100 != 0 and posmod(lock_value, 100) + posmod(val, 100) > 100
			lock_value += val
		
		if lock_value % 100 == 0 or has_stepped_over_zero:
			result += 1
			
	return result
