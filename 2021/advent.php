<?php

abstract class Advent {
    protected $day;

    abstract public function a();
    abstract public function b();

    public function __construct($day)
    {
        $this->day = $day;
    }

    public function readFileAsArray() {
        return file($this->day.'/input.txt');
    }
}
