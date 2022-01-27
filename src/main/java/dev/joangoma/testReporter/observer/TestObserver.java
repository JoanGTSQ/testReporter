package dev.joangoma.testReporter.observer;

import dev.joangoma.testReporter.observer.entity.ObservedEntity;
import dev.joangoma.testReporter.observer.entity.TestEntity;

import io.reactivex.rxjava3.core.Observer;

public interface TestObserver<T extends ObservedEntity> extends ExtentObserver<T> {
    Observer<TestEntity> getTestObserver();
}
