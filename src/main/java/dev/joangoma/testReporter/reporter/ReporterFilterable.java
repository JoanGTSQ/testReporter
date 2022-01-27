package dev.joangoma.testReporter.reporter;

import dev.joangoma.testReporter.reporter.configuration.EntityFilters;

@FunctionalInterface
public interface ReporterFilterable<T extends AbstractReporter> {
    EntityFilters<T> filter();
}
