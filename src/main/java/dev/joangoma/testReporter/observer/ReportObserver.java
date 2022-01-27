package dev.joangoma.testReporter.observer;

import dev.joangoma.testReporter.observer.entity.ObservedEntity;
import dev.joangoma.testReporter.observer.entity.ReportEntity;

import io.reactivex.rxjava3.core.Observer;

public interface ReportObserver<T extends ObservedEntity> extends ExtentObserver<T> {
    Observer<ReportEntity> getReportObserver();
}
