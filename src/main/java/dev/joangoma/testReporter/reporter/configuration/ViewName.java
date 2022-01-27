package dev.joangoma.testReporter.reporter.configuration;

public enum ViewName {
    AUTHOR, CATEGORY, DASHBOARD, DEVICE, EXCEPTION, LOG, TEST;
    
    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
