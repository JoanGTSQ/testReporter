package dev.joangoma.testReporter.observer.entity;

import dev.joangoma.testReporter.model.Report;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class ReportEntity implements ObservedEntity {
    private Report report;
}
