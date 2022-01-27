package dev.joangoma.testReporter.reporter.configuration;

import dev.joangoma.testReporter.reporter.AbstractReporter;

import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class EntityFilters<T extends AbstractReporter> {
    private final T reporter;
    private final StatusFilter<T> statusFilter;

    public EntityFilters(T reporter) {
        this.reporter = reporter;
        statusFilter = new StatusFilter<T>(this);
    }

    public T apply() {
        return reporter;
    }
}
