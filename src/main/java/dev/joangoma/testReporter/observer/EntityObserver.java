package dev.joangoma.testReporter.observer;

import dev.joangoma.testReporter.observer.entity.ObservedEntity;

public interface EntityObserver<T extends ObservedEntity>
        extends
            AttributesObserver<T>,
            LogObserver<T>,
            MediaObserver<T>,
            ReportObserver<T>,
            TestObserver<T> {
}
