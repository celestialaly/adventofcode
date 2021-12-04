<?php
$file = array_map(fn ($v) => str_split(trim($v)), file('3/input.txt'));
$itemLength = count($file[0]);
$o2 = $co2 = [];

$vals = $vals2 = $file;
$i = $j = 0;
do {
    $val = array_reduce($vals, function ($c, $v) use ($i) {
        return (bool) $v[$i] ? $c + 1 : $c - 1;
    }, 0);
    $vals = array_filter($vals, fn ($v) => (bool) $v[$i] === ($val >= 0));
    $i++;
} while (count($vals) > 1);
$o2 = array_shift($vals);

do {
    $val = array_reduce($vals2, function ($c, $v) use ($j) {
        return (bool) $v[$j] ? $c + 1 : $c - 1;
    }, 0);
    $vals2 = array_filter($vals2, fn ($v) => (bool) $v[$j] === ($val < 0));
    $j++;
} while (count($vals2) > 1);
$co2 = array_shift($vals2);

echo bindec(implode('', $o2))*bindec(implode('', $co2))."\n";
