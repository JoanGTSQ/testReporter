package dev.joangoma.testReporter;

@FunctionalInterface
public interface AnalysisTypeConfigurable {
    void setAnalysisStrategy(AnalysisStrategy strategy);
}
