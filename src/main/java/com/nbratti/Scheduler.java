package com.nbratti;

import java.lang.Process;
import java.util.PriorityQueue;

public class Scheduler {
    private PriorityQueue<java.lang.Process> processesReady;
    private java.lang.Process currentProcess;

    void run(){
        while(!processesReady.isEmpty()){
            processesReady.poll();
        }
        System.out.println("Todos os processos foram executados");
    }

    void addProcess(Process process){
        processesReady.add(process);
    }
}
