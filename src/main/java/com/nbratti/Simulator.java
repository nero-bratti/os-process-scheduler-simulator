package com.nbratti;

public class Simulator {
    Scheduler scheduler;

    public Simulator() {
        scheduler = new Scheduler();
    }

    public void runSimulator() {

        scheduler.addProcess(new Process("B",4,2,2));
        scheduler.addProcess(new Process("C",2,3,3));
        scheduler.addProcess(new Process("A",2,1,2));
        scheduler.addProcess(new Process("D",2,7,1));
        scheduler.runScheduler();
    }
}
