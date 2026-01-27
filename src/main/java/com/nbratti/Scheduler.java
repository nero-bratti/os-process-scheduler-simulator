package com.nbratti;

import java.util.PriorityQueue;

public class Scheduler {
    private Process processRunning;
    private PriorityQueue<Process> readyProcessesQueue;

    public Scheduler() {
        readyProcessesQueue = new PriorityQueue<>();
    }

    void runScheduler() {
        //Will return the method if no processes are found in the 'ready' queue
        if (!startScheduler()) return;

        while(!finishedRunning()){
            if (processRunning.isAbleToRun()) {
                processRunning.run();
            } else {
                if (hasReadyProcess()) {
                    if (!readyProcessesQueue.peek().isAbleToRun()) {
                        distributeCredit();
                    }
                    pickReadyProcessAndRun();
                } else {
                    System.out.println("Fim de execução.");
                    processRunning = null;
                }
            }
        }
    }

    //other methods
    void addProcess(Process process) {
        readyProcessesQueue.add(process);
    }

    boolean finishedRunning(){
        return (processRunning == null & readyProcessesQueue.isEmpty());
    }

    /**
     * Checks the availability of processes to run and,
     * in a positive case, assigns the first process to {@code processRunning}.
     * @return {@code true} when it finds at least one process to run; or
     *         {@code false} when no processes where admitted.
     */
    boolean startScheduler() {
        if (readyProcessesQueue.peek() == null) {
            System.out.println("Não há processos para executar.");
            return false;
        } else {
            processRunning = readyProcessesQueue.poll();
            processRunning.setStateRunning();
            return true;
        }
    }

    void distributeCredit() {
        for (Process process : readyProcessesQueue) {
            process.giveCredit();
        }
    }

    void requeueProcessToReady(Process process) {
        process.setStateReady();
        readyProcessesQueue.add(process);
    }

    void pickReadyProcessAndRun() {
        if (processRunning.hasCpuTimeRemaining()) requeueProcessToReady(processRunning);
        processRunning = readyProcessesQueue.poll();
        processRunning.setStateRunning();
        processRunning.run();
    }

    boolean hasReadyProcess() {
        return !readyProcessesQueue.isEmpty();
    }
}