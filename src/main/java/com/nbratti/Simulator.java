package com.nbratti;

public class Simulator {
    Scheduler scheduler;

    public Simulator() {
        scheduler = new Scheduler();
    }

    public void runSimulator() {

        scheduler.addProcess(Process.builder("B")
                .estimatedCpuTimeRemaining(4)
                .order(2)
                .priority(2)
                .build());
        scheduler.addProcess(Process.builder("C")
                .estimatedCpuTimeRemaining(2)
                .order(3)
                .priority(3)
                .build());
        scheduler.addProcess(Process.builder("A")
                .estimatedCpuTimeRemaining(2)
                .order(1)
                .priority(2)
                .build());
        scheduler.addProcess(Process.builder("D")
                .estimatedCpuTimeRemaining(2)
                .order(7)
                .priority(1)
                .build());
        scheduler.runScheduler();
    }
}
