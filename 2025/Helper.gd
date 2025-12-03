extends Node

func read_input(day: int, filename: String = 'input.txt') -> Array:
	var path = str('res://days/', day, '/', filename)
	var file = FileAccess.open(path, FileAccess.READ)
	
	var line = file.get_line()
	var data = []
	
	while (not line.is_empty()):
		data.append(line)
		line = file.get_line()
	
	return data

func read_input_test(day: int) -> Array:
	return read_input(day, 'test.txt')

func format_result(part1, part2) -> String:
	print_debug(str('part 1:', part1))
	print_debug(str('part 2:', part2))
	
	return str('Part 1: ', part1, '\n', 'Part 2: ', part2)
