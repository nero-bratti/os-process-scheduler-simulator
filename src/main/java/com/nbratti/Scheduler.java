package com.nbratti;

import java.util.PriorityQueue;

public class Scheduler {
    //variables and structures
    private Process processRunning;
    private PriorityQueue<Process> processesReady;

    //contructor
    public Scheduler() {
        processesReady = new PriorityQueue<>();
    }

    //main method
    void runScheduler() {
        if (processesReady.poll() == null) {
            System.out.println("Não há processos para executar.");
            return;
        }
        while(!finishedRunning()){

        }
        System.out.println("Todos os processos foram executados");
    }

    //other methods
    void addProcess(Process process) {
        processesReady.add(process);
    }

    boolean finishedRunning(){
        return ((processRunning == null) & processesReady.isEmpty());
    }

    /**
     * Returns true if the running process still has at least one credit,
     * and false if it doesn't.
     */
    boolean checkCreditFromProcessRunning() {
        return (processRunning.getCredit() > 0);
    }


}
