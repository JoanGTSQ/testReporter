package dev.joangoma.testReporter;

import java.io.File;
import java.io.IOException;

import dev.joangoma.testReporter.append.RawEntityConverter;
import dev.joangoma.testReporter.model.Author;
import dev.joangoma.testReporter.model.Category;
import dev.joangoma.testReporter.model.Device;
import dev.joangoma.testReporter.model.Log;
import dev.joangoma.testReporter.model.Media;
import dev.joangoma.testReporter.model.SystemEnvInfo;
import dev.joangoma.testReporter.model.Test;
import dev.joangoma.testReporter.model.service.MediaService;
import dev.joangoma.testReporter.model.service.TestService;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractProcessor extends ReactiveSubject {
    private String[] mediaResolverPath;
    private boolean usingNaturalConf = true;

    @Override
    protected void onTestCreated(Test test) {
        getReport().getTestList().add(test);
        super.onTestCreated(test);
    }

    @Override
    protected void onTestRemoved(Test test) {
        TestService.deleteTest(getReport().getTestList(), test);
        super.onTestRemoved(test);
    }

    protected void onNodeCreated(Test node) {
        super.onTestCreated(node);
    }

    @Override
    protected void onLogCreated(Log log, Test test) {
        super.onLogCreated(log, test);
        if (log.hasException())
            getReport().getExceptionInfoCtx().addContext(log.getException(), test);
    }

    @Override
    protected void onMediaAdded(Media m, Test test) {
        tryResolvePath(m);
        super.onMediaAdded(m, test);
    }

    @Override
    protected void onMediaAdded(Media m, Log log, Test test) {
        tryResolvePath(m);
        super.onMediaAdded(m, log, test);
    }

    private void tryResolvePath(Media m) {
        MediaService.tryResolveMediaPath(m, mediaResolverPath);
    }

    protected void onAuthorAdded(Author x, Test test) {
        getReport().getAuthorCtx().addContext(x, test);
        super.onAuthorAssigned(x, test);
    }

    protected void onCategoryAdded(Category x, Test test) {
        getReport().getCategoryCtx().addContext(x, test);
        super.onCategoryAssigned(x, test);
    }

    protected void onDeviceAdded(Device x, Test test) {
        getReport().getDeviceCtx().addContext(x, test);
        super.onDeviceAssigned(x, test);
    }

    @Override
    protected void onFlush() {
        getReport().refresh();
        if (!usingNaturalConf)
            getReport().applyOverrideConf();
        super.onFlush();
    }

    protected void onReportLogAdded(String log) {
        getReport().getLogs().add(log);
    }

    protected void onSystemInfoAdded(SystemEnvInfo env) {
        getReport().getSystemEnvInfo().add(env);
    }

    protected void convertRawEntities(ExtentReports extent, File f) throws IOException {
        RawEntityConverter converter = new RawEntityConverter(extent);
        converter.convertAndApply(f);
    }
}
