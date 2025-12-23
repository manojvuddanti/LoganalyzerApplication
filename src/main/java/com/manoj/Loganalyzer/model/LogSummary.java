package com.manoj.Loganalyzer.model;

public class LogSummary {
    private int errorCount;
    private int warningCount;
    private String mostFrequentError;
    private String lastErrorTimestamp;

    public LogSummary(int errorCount, int warningCount, String mostFrequentError, String lastErrorTimestamp) {
        this.errorCount = errorCount;
        this.warningCount = warningCount;
        this.mostFrequentError = mostFrequentError;
        this.lastErrorTimestamp = lastErrorTimestamp;
    }

    public int getErrorCount() { return errorCount; }
    public int getWarningCount() { return warningCount; }
    public String getMostFrequentError() { return mostFrequentError; }
    public String getLastErrorTimestamp() { return lastErrorTimestamp; }
}