extends Node

func read_input(day: int) -> Array:
	var path = str('res://days/', day, '/input.txt')
	var file = FileAccess.open(path, FileAccess.READ)
	
	var line = file.get_line()
	var data = []
	
	while (not line.is_empty()):
		data.append(line)
		line = file.get_line()
	
	return data
