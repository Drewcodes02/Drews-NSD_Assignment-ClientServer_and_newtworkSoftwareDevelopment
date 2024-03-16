package Assigment.utils;

public enum Throughput {

    LOW(3),
    HIGH(1000);


    private final int clientConnections;

    Throughput(final int text) {
        this.clientConnections = text;
    }

    public int getThroughputLimit() {
        return clientConnections;
    }

}
