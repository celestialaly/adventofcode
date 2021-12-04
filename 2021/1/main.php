<?php

require('advent.php');

class Puzzle extends Advent 
{
    public function a() {
        return array_reduce($this->readFileAsArray(), function($c, $v) use (&$p) { $c = $p !== null && $v > $p ? $c+1 : $c; $p = $v; return $c; }, 0);
    }
    
    public function b() {
        $file = $this->readFileAsArray();
        return array_reduce(array_slice(array_keys($file), 0, -2), function($c, $k) use (&$file, &$res) {
            $res[$k] = $file[$k] + $file[$k+1] + $file[$k+2];
            return $k > 0 && $res[$k] > $res[$k-1] ? $c+1 : $c;
        }, 0);
    }
}
