package dev.joangoma.testReporter.reporter.configuration;

@FunctionalInterface
public interface ViewsConfigurable<T> {
    ViewConfigurer<?> viewConfigurer();
}
