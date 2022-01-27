package dev.joangoma.testReporter.observer;

import dev.joangoma.testReporter.observer.entity.LogEntity;
import dev.joangoma.testReporter.observer.entity.ObservedEntity;

import io.reactivex.rxjava3.core.Observer;

public interface LogObserver<T extends ObservedEntity> extends ExtentObserver<T> {
    Observer<LogEntity> getLogObserver();
}
