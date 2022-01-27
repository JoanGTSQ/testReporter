package dev.joangoma.testReporter.observer.entity;

import dev.joangoma.testReporter.model.Test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class TestEntity implements ObservedEntity {
    private Test test;
    @Builder.Default
    private Boolean removed = false;
}
