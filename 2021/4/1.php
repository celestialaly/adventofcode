<?php
$file = file('4/input.txt');
$g = [];

$numbers = explode(',', trim($file[0]));
$boards = [];

for ($i = 2; $i < count($file)-2; $i = $i + 6) {
    $boards[] = [
        array_values(array_filter(explode(' ', trim($file[$i])), fn($v) => $v !== "")),
        array_values(array_filter(explode(' ', trim($file[$i+1])), fn($v) => $v !== "")),
        array_values(array_filter(explode(' ', trim($file[$i+2])), fn($v) => $v !== "")),
        array_values(array_filter(explode(' ', trim($file[$i+3])), fn($v) => $v !== "")),
        array_values(array_filter(explode(' ', trim($file[$i+4])), fn($v) => $v !== "")),
    ];
}

$iDraw = 5;
$drawn = array_slice($numbers, 0, 5);
$winner = null;

do {
    foreach ($boards as $b) {
        // comp each row/column to drawn numbers
        for ($i = 0; $i < 5; $i++) {
            $hasWon = count(array_intersect($b[$i], $drawn)) === 5 ||
                count(array_intersect(array_map(fn($v) => $b[$v][$i], range(0,4)), $drawn)) === 5;
            if ($hasWon) {
                $winner = $b;
                break;
            }
        }
    }

    if (is_null($winner)) {
        $iDraw++;
        $drawn[] = $numbers[$iDraw-1];
    }
} while(is_null($winner));


$result = array_sum(array_diff(call_user_func_array('array_merge', $winner), $drawn)) * (int) array_pop($drawn);

echo $result."\n";
