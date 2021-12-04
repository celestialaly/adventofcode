<?php

$day = $argv[1];
require($day.'/main.php');

$puzzle = new Puzzle($day);
echo $puzzle->a()."\n";
echo $puzzle->b()."\n";
