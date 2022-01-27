package dev.joangoma.testReporter.observer;

import dev.joangoma.testReporter.observer.entity.AttributeEntity;
import dev.joangoma.testReporter.observer.entity.ObservedEntity;

import io.reactivex.rxjava3.core.Observer;

public interface AttributesObserver<T extends ObservedEntity> extends ExtentObserver<T> {
    Observer<AttributeEntity> getAttributesObserver();
}
