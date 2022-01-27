package dev.joangoma.testReporter.observer.entity;

import dev.joangoma.testReporter.model.Log;
import dev.joangoma.testReporter.model.Test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class LogEntity implements ObservedEntity {
    private Log log;
    private Test test;
}
