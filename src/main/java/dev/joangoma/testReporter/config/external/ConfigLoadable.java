package dev.joangoma.testReporter.config.external;

@FunctionalInterface
public interface ConfigLoadable<T> {
    void apply();
}
