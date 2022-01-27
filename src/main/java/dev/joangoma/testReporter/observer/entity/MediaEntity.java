package dev.joangoma.testReporter.observer.entity;

import dev.joangoma.testReporter.model.Log;
import dev.joangoma.testReporter.model.Media;
import dev.joangoma.testReporter.model.Test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class MediaEntity implements ObservedEntity {
    private Media media;
    private Test test;
    private Log log;
}
