<?php 
$file = file('2/input.txt');
$d = $h = 0;
foreach ($file as $v) {
    $parts = explode(' ', $v);
    switch ($parts[0]) {
        case 'forward':
            $h+=(int) $parts[1];
            break;
        case 'down':
            $d+=(int) $parts[1];
            break;
        case 'up':
            $d-=(int) $parts[1];
    }
}

echo $d*$h."\n";
