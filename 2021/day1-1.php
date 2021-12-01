<?php 
echo array_reduce(file('input-day1.txt'), function($c, $v) use (&$p) { $c = $p !== null && $v > $p ? $c+1 : $c; $p = $v; return $c; }, 0)."\n";
