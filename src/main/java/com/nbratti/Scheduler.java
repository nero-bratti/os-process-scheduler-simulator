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
        if (processesReady.peek() == null) {
            System.out.println("Não há processos para executar.");
            return;
        } else {
            processRunning = processesReady.poll();
            processRunning.setState(Process.State.RUNNING);
        }
        while(!finishedRunning()){
            if (processRunning.getCredit() > 0) {
                processRunning.setCredit(processRunning.getCredit() - 1);
                System.out.println("Processo "+processRunning.getName()+" usou CPU.");
            } else if (processRunning.getCredit() == 0) {
                if (processesReady.peek() != null) {
                    processRunning = processesReady.poll();
                    processRunning.setState(Process.State.RUNNING);
                    processRunning.setCredit(processRunning.getCredit() - 1);
                } else {
                    System.out.println("Fim de execução.");
                    break;
                }
            }
        }
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
