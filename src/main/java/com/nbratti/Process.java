package com.nbratti;

public final class Process implements Comparable<Process> {
    //inner classes
    public enum State {
        READY,
        RUNNING,
        BLOCKED,
        EXIT
    }

    //builder
    public static final class ProcessBuilder {
        private final String name;
        private int estimatedCpuTimeRemaining;
        private int order;
        private int priority;

        public ProcessBuilder(String name) {
            this.name = name;
        }

        public ProcessBuilder estimatedCpuTimeRemaining(int estimatedCpuTimeRemaining) {
            this.estimatedCpuTimeRemaining = estimatedCpuTimeRemaining;
            return this;
        }

        public ProcessBuilder order(int order) {
            this.order = order;
            return this;
        }

        public ProcessBuilder priority(int priority) {
            this.priority = priority;
            return this;
        }

        public Process build() {
            return new Process(this);
        }
    }
    public static ProcessBuilder builder(String processName) {
        return new ProcessBuilder(processName);
    }

    //variables
    private final String name;
    private int estimatedCpuTimeRemaining;
    private int order;
    private int priority;
    private int credit;
    private State state;

    private Process(ProcessBuilder processBuilder) {
        this.name = processBuilder.name;
        this.estimatedCpuTimeRemaining = processBuilder.estimatedCpuTimeRemaining;
        this.priority = processBuilder.priority;
        this.order = processBuilder.order;
        this.credit = priority;
        this.state = State.READY;
    }

    /**
     * Compares this process with another based on scheduling precedence.
     * <p>
     *      Precedence is determined first by credit (higher first) and, in case of
     *      tie, by order (lower first).
     * </p>
     *
     * @param otherProcess the process to compare with
     * @return {@code 0} if neither process has precedence over the other;
     *         a value less than {@code 0} if {@code otherProcess} has higher precedence;
     *         and a value greater than {@code 0} if {@code this} process has higher precedence.
     */
    @Override
    public int compareTo(Process otherProcess) {
        if (this.credit != otherProcess.credit) {
            return Integer.compare(otherProcess.credit,this.credit);
        } else return Integer.compare(this.order,otherProcess.order);
    }

    public void run() {
        credit--;
        estimatedCpuTimeRemaining--;
        System.out.println(name+" usou CPU. " +
                "Tempo de CPU faltando: "+ estimatedCpuTimeRemaining +".");
    }

    /**
     * Checks whether the process has the resources to continue execution.
     * The result is {@code true} if and only if the process has at least one credit,
     * remaining CPU time and is not blocked.
     * @return {@code true} when the process is eligible to run;
     * {@code false} otherwise.
     */
    public boolean isAbleToRun() {
        return ((credit > 0) & (estimatedCpuTimeRemaining > 0) & (state != State.BLOCKED));
    }

    public void setStateReady() {
        state = State.READY;
    }

    public void setStateRunning() {
        state = State.RUNNING;
    }

    public void setStateBlocked() {
        state = State.BLOCKED;
    }

    public void setStateExit() {
        state = State.EXIT;
    }

    public void giveCredit() {
        credit = ((credit/2) + priority);
    }

    public boolean hasCpuTimeRemaining() {
        return estimatedCpuTimeRemaining > 0;
    }
}
