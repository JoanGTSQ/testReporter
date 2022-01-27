package dev.joangoma.testReporter.model;

import dev.joangoma.testReporter.Status;

@FunctionalInterface
public interface RunResult {
    Status getStatus();
}
