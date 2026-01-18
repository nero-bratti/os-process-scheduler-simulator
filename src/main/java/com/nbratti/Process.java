package com.nbratti;

public class Process implements Comparable<Process> {
    @Override
    public int compareTo(Process o) {
        if (this.priority == o.priority) {
            return Integer.compare(this.order, o.order);
        } else return Integer.compare(this.priority, o.priority);
    }

    public enum State {
        READY,
        RUNNING,
        BLOCKED,
        EXIT
    }

    //base variables
    private State state;
    private String name;
    private int estimatedTotalProcessingTime;
    private int order;
    private int priority;
    private int credit;

    //auxiliary variables
    private int runTime;
    private int timeIOExecuted;

    //constructor of a cpu bound process
    public Process(String name, int estimatedTotalProcessingTime, int order, int priority) {
        this.state = State.READY;
        this.name = name;
        this.estimatedTotalProcessingTime = estimatedTotalProcessingTime;
        this.order = order;
        this.priority = priority;

        this.runTime = 0;

        this.credit = priority;
    }
    //should not have any methods besides getters and setter to simulate the fact all the work is done by the scheduler
    //getters and setters
    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public Comparable getPriority() {
        return priority;
    }
    public void setPriority(Comparable priority) {}
}
