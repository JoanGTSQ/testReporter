package dev.joangoma.testReporter.observer;

import dev.joangoma.testReporter.observer.entity.MediaEntity;
import dev.joangoma.testReporter.observer.entity.ObservedEntity;

import io.reactivex.rxjava3.core.Observer;

public interface MediaObserver<T extends ObservedEntity> extends ExtentObserver<T> {
    Observer<MediaEntity> getMediaObserver();
}
