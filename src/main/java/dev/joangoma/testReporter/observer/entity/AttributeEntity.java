package dev.joangoma.testReporter.observer.entity;

import dev.joangoma.testReporter.model.Author;
import dev.joangoma.testReporter.model.Category;
import dev.joangoma.testReporter.model.Device;
import dev.joangoma.testReporter.model.Test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class AttributeEntity implements ObservedEntity {
    private Test test;
    private Author author;
    private Category category;
    private Device device;
}
