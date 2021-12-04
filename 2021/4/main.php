<?php

require('advent.php');

class Puzzle extends Advent
{
    public function a()
    {
        $file = $this->readFileAsArray();

        $numbers = explode(',', trim($file[0]));
        $boards = [];

        for ($i = 2; $i < count($file) - 2; $i = $i + 6) {
            $boards[] = array_map(fn($v) => array_values(array_filter(explode(' ', trim($file[$i+$v])), fn ($v) => $v !== "")), range(0, 4));
        }

        $iDraw = 5;
        $drawn = array_slice($numbers, 0, 5);
        $winner = null;

        do {
            foreach ($boards as $b) {
                // comp each row/column to drawn numbers
                for ($i = 0; $i < 5; $i++) {
                    $hasWon = count(array_intersect($b[$i], $drawn)) === 5 ||
                        count(array_intersect(array_map(fn ($v) => $b[$v][$i], range(0, 4)), $drawn)) === 5;
                    if ($hasWon) {
                        $winner = $b;
                        break;
                    }
                }
            }

            if (is_null($winner)) {
                $iDraw++;
                $drawn[] = $numbers[$iDraw - 1];
            }
        } while (is_null($winner));

        return array_sum(array_diff(call_user_func_array('array_merge', $winner), $drawn)) * (int) array_pop($drawn);
    }

    public function b()
    {
        $file = $this->readFileAsArray();

        $numbers = explode(',', trim($file[0]));
        $boards = [];

        for ($i = 2; $i < count($file) - 2; $i = $i + 6) {
            $boards[] = array_map(fn($v) => array_values(array_filter(explode(' ', trim($file[$i+$v])), fn ($v) => $v !== "")), range(0, 4));
        }

        $iDraw = 5;
        $drawn = array_slice($numbers, 0, 5);
        $winner = null;

        do {
            foreach ($boards as $i => $b) {
                // comp each row/column to drawn numbers
                for ($j = 0; $j < 5; $j++) {
                    $hasWon = count(array_intersect($b[$j], $drawn)) === 5 ||
                        count(array_intersect(array_map(fn ($v) => $b[$v][$j], range(0, 4)), $drawn)) === 5;
                    if ($hasWon) {
                        $winner = $b;

                        unset($boards[$i]);
                        break;
                    }
                }
            }

            if (count($boards) > 0) {
                $iDraw++;
                $drawn[] = $numbers[$iDraw - 1];
            }
        } while (count($boards) > 0 && count($drawn) < count($numbers));

        return array_sum(array_diff(call_user_func_array('array_merge', $winner), $drawn)) * (int) array_pop($drawn);
    }
}
