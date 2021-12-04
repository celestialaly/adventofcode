<?php 
$file = file('2/input.txt');
$d = $h = $a = 0;
foreach ($file as $v) {
    $parts = explode(' ', $v);
    switch ($parts[0]) {
        case 'forward':
            $h+=(int)$parts[1];
            $d+=$a*(int)$parts[1];
            break;
        case 'down':
            $a+=(int) $parts[1];
            break;
        case 'up':
            $a-=(int) $parts[1];
    }
}

echo $d*$h."\n";
