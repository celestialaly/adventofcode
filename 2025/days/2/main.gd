extends Label

const DAY = 2

func _init() -> void:
	var input = Helper.read_input(DAY)
	var data: Array = Array(str(input[0]).split(',')).map(func(range): return range.split('-'))
	
	var part1 = part1(data)
	var part2 = part2(input)
	
	text = Helper.format_result(part1, part2)

func part1(input: Array) -> int:
	var result = 0
	
	for range in input:
		var start = str(range[0])
		var end = str(range[1])
		var length_differs = start.length() != end.length()
		
		if start.length() % 2 != 0 and end.length() % 2 != 0:
			continue
			
		var start_length = start.length()
		var end_length = end.length()
		
		if start.substr(0, start_length / 2) == start.substr(start_length / 2):
			result += int(start)
		if end.substr(0, end_length / 2) == end.substr(end_length / 2):
			result += int(end)
		
		if length_differs:
			if start_length % 2 == 0:
				var start_digits = int(start.substr(0, start_length / 2))
				var double_digits = int(str(start_digits, start_digits))
				
				if double_digits > int(start) and double_digits < int(end):
					result += double_digits
			else:
				var end_digits = int(end.substr(0, end_length / 2))
				var double_digits = int(str(end_digits, end_digits))
				
				if double_digits > int(start) and double_digits < int(end):
					result += double_digits
		else:
			var start_digits = int(start.substr(0, start_length / 2))
			var double_digits = int(str(start_digits, start_digits))
			
			if double_digits > int(start) and double_digits < int(end):
				result += double_digits
		
	return result

func part2(input: Array) -> int:
	return 0
