package dev.joangoma.testReporter;

import dev.joangoma.testReporter.model.Author;
import dev.joangoma.testReporter.model.Category;
import dev.joangoma.testReporter.model.Device;
import dev.joangoma.testReporter.model.Log;
import dev.joangoma.testReporter.model.Media;
import dev.joangoma.testReporter.model.Report;
import dev.joangoma.testReporter.model.Test;
import dev.joangoma.testReporter.observer.AttributesObserver;
import dev.joangoma.testReporter.observer.ExtentObserver;
import dev.joangoma.testReporter.observer.LogObserver;
import dev.joangoma.testReporter.observer.MediaObserver;
import dev.joangoma.testReporter.observer.ReportObserver;
import dev.joangoma.testReporter.observer.TestObserver;
import dev.joangoma.testReporter.observer.entity.AttributeEntity;
import dev.joangoma.testReporter.observer.entity.LogEntity;
import dev.joangoma.testReporter.observer.entity.MediaEntity;
import dev.joangoma.testReporter.observer.entity.ReportEntity;
import dev.joangoma.testReporter.observer.entity.TestEntity;

import io.reactivex.rxjava3.subjects.PublishSubject;
import lombok.Getter;

@Getter
abstract class ReactiveSubject {
    private final Report report = Report.builder().build();
    private final PublishSubject<ReportEntity> reportSubject = PublishSubject.create();
    private final PublishSubject<TestEntity> testSubject = PublishSubject.create();
    private final PublishSubject<LogEntity> logSubject = PublishSubject.create();
    private final PublishSubject<MediaEntity> mediaSubject = PublishSubject.create();
    private final PublishSubject<AttributeEntity> attribSubject = PublishSubject.create();

    @SuppressWarnings({"rawtypes", "unchecked"})
    protected void attachReporter(ExtentObserver... observers) {
        for (ExtentObserver o : observers) {
            if (o instanceof ReportObserver)
                reportSubject.subscribe(((ReportObserver) o).getReportObserver());
            if (o instanceof TestObserver)
                testSubject.subscribe(((TestObserver) o).getTestObserver());
            if (o instanceof LogObserver)
                logSubject.subscribe(((LogObserver) o).getLogObserver());
            if (o instanceof MediaObserver)
                mediaSubject.subscribe(((MediaObserver) o).getMediaObserver());
            if (o instanceof AttributesObserver)
                attribSubject.subscribe(((AttributesObserver) o).getAttributesObserver());
        }
    }

    protected void onTestCreated(Test test) {
        testSubject.onNext(TestEntity.builder().test(test).build());
    }

    protected void onTestRemoved(Test test) {
        testSubject.onNext(TestEntity.builder().test(test).removed(true).build());
    }

    protected void onLogCreated(Log log, Test test) {
        logSubject.onNext(LogEntity.builder().log(log).test(test).build());
    }

    protected void onAuthorAssigned(Author x, Test test) {
        attribSubject.onNext(AttributeEntity.builder().author(x).test(test).build());
    }

    protected void onCategoryAssigned(Category x, Test test) {
        attribSubject.onNext(AttributeEntity.builder().category(x).test(test).build());
    }

    protected void onDeviceAssigned(Device x, Test test) {
        attribSubject.onNext(AttributeEntity.builder().device(x).test(test).build());
    }

    protected void onMediaAdded(Media m, Test test) {
        mediaSubject.onNext(MediaEntity.builder().media(m).test(test).build());
    }

    protected void onMediaAdded(Media m, Log log, Test test) {
        mediaSubject.onNext(MediaEntity.builder().media(m).test(test).log(log).build());
    }

    protected void onFlush() {
        reportSubject.onNext(ReportEntity.builder().report(report).build());
    }
}
