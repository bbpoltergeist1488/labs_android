package com.example.ppolab2;

public class Timer {

    public Timer(String timerName, int color, int warmup, int workout,
                 int rest, int cooldown, int repeat, int cycle,int id){
        this.id = id;
        this.timerName = timerName;
        this.warmup = warmup; //preparation
        this.workout = workout;
        this.rest = rest;
        this.cooldown = cooldown; //rest between sets
        this.repeat = repeat;  //set
        this.cycle = cycle;
        this.color = color;
    }
    public String timerName;
    public int id,warmup,cycle,workout,rest,cooldown,repeat,color;

}
