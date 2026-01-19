package com.nbratti;

public class Simulator {
    Scheduler scheduler;

    public Simulator() {
        scheduler = new Scheduler();
    }

    public void runSimulator() {
        scheduler.addProcess(new Process("A",5,3,5));
        scheduler.runScheduler();
    }

}
