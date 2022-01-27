package dev.joangoma.testReporter.observer.entity;

import dev.joangoma.testReporter.model.NamedAttribute;
import dev.joangoma.testReporter.model.Test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class NamedAttributeTestEntity implements ObservedEntity {
    private NamedAttribute attribute;
    private Test test;
}
