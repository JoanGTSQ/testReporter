package dev.joangoma.testReporter.reporter.filter;

import java.util.Set;

import dev.joangoma.testReporter.Status;
import dev.joangoma.testReporter.model.Report;

@FunctionalInterface
public interface StatusFilterable {
    Report filterAndGet(Report report, Set<Status> set);
}
