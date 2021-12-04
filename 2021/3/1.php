<?php
$file = file('3/input.txt');
$g = [];

foreach ($file as $v) {
    foreach (str_split(trim($v)) as $i => $b) {
        if (!isset($g[$i])) {
            $g[$i] = 0;
        }

        $g[$i] = (bool) $b ? $g[$i] + 1 : $g[$i] - 1;
    }
}

echo bindec(implode('', array_map(fn ($v) => $v > 0 ? 1 : 0, $g))) * bindec(implode('', array_map(fn ($v) => $v > 0 ? 0 : 1, $g))) . "\n";
