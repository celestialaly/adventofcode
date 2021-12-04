<?php
$file = file('1/input.txt');
echo array_reduce(array_slice(array_keys($file), 0, -2), function($c, $k) use (&$file, &$res) {
    $res[$k] = $file[$k] + $file[$k+1] + $file[$k+2];
    return $k > 0 && $res[$k] > $res[$k-1] ? $c+1 : $c;
}, 0)."\n";