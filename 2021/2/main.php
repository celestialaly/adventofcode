<?php

require('advent.php');

class Puzzle extends Advent
{
    public function a()
    {
        $file = $this->readFileAsArray();
        $d = $h = 0;
        foreach ($file as $v) {
            $parts = explode(' ', $v);
            switch ($parts[0]) {
                case 'forward':
                    $h += (int) $parts[1];
                    break;
                case 'down':
                    $d += (int) $parts[1];
                    break;
                case 'up':
                    $d -= (int) $parts[1];
            }
        }

        return $d * $h;
    }

    public function b()
    {
        $file = $this->readFileAsArray();
        $d = $h = $a = 0;
        foreach ($file as $v) {
            $parts = explode(' ', $v);
            switch ($parts[0]) {
                case 'forward':
                    $h += (int)$parts[1];
                    $d += $a * (int)$parts[1];
                    break;
                case 'down':
                    $a += (int) $parts[1];
                    break;
                case 'up':
                    $a -= (int) $parts[1];
            }
        }

        return $d * $h;
    }
}
