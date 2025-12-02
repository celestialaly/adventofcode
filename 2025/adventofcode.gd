extends Node2D

@onready var day_label: Label = $Day
@onready var result_label: Label = $Result

@export var current_day: int = 1

func _ready() -> void:
	day_label.text = str('~ Day ', current_day, ' ~')
	
	# set script according to the current_day
	var path = str('res://days/', current_day, '/day', current_day, '.gd')
	result_label.set_script(load(path))
