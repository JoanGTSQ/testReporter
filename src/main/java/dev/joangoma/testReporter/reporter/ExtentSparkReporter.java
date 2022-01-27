package dev.joangoma.testReporter.reporter;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

import dev.joangoma.testReporter.config.external.JsonConfigLoader;
import dev.joangoma.testReporter.config.external.XmlConfigLoader;
import dev.joangoma.testReporter.model.Report;
import dev.joangoma.testReporter.observer.ReportObserver;
import dev.joangoma.testReporter.observer.entity.ReportEntity;
import dev.joangoma.testReporter.reporter.configuration.EntityFilters;
import dev.joangoma.testReporter.reporter.configuration.ExtentSparkReporterConfig;
import dev.joangoma.testReporter.reporter.configuration.ViewConfigurer;
import dev.joangoma.testReporter.reporter.configuration.ViewsConfigurable;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import lombok.AccessLevel;
import lombok.Getter;

@Getter
public class ExtentSparkReporter extends AbstractFileReporter
        implements
        ReportObserver<ReportEntity>,
            ReporterConfigurable,
            ViewsConfigurable<ExtentSparkReporter>,
            ReporterFilterable<ExtentSparkReporter> {
    private static final Logger logger = Logger.getLogger(ExtentSparkReporter.class.getName());
    private static final String TEMPLATE_LOCATION = "templates/";
    private static final String ENCODING = "UTF-8";
    private static final String REPORTER_NAME = "spark";
    private static final String SPA_TEMPLATE_NAME = REPORTER_NAME + "/spark.spa.ftl";
    private static final String FILE_NAME = "Index.html";

    private final AtomicBoolean executed = new AtomicBoolean();
    @Getter(value = AccessLevel.NONE)
    private final ViewConfigurer<ExtentSparkReporter> viewConfigurer = new ViewConfigurer<>(this);
    @Getter(value = AccessLevel.NONE)
    private final EntityFilters<ExtentSparkReporter> filter = new EntityFilters<>(this);

    private ExtentSparkReporterConfig conf = ExtentSparkReporterConfig.builder().reporter(this).build();
    private Disposable disposable;
    private Report report;

    public ExtentSparkReporter(String path) {
        super(new File(path));
    }

    public ExtentSparkReporter(File f) {
        super(f);
    }

    @Override
    public EntityFilters<ExtentSparkReporter> filter() {
        return filter;
    }

    @Override
    public ViewConfigurer<ExtentSparkReporter> viewConfigurer() {
        return viewConfigurer;
    }

    public ExtentSparkReporterConfig config() {
        return conf;
    }

    public ExtentSparkReporter config(ExtentSparkReporterConfig conf) {
        conf.setReporter(this);
        this.conf = conf;
        return this;
    }

    @Override
    public void loadJSONConfig(File jsonFile) throws IOException {
        final JsonConfigLoader<ExtentSparkReporterConfig> loader = new JsonConfigLoader<ExtentSparkReporterConfig>(conf,
                jsonFile);
        loader.apply();
    }

    @Override
    public void loadJSONConfig(String jsonString) throws IOException {
        final JsonConfigLoader<ExtentSparkReporterConfig> loader = new JsonConfigLoader<ExtentSparkReporterConfig>(conf,
                jsonString);
        loader.apply();
    }

    @Override
    public void loadXMLConfig(File xmlFile) throws IOException {
        final XmlConfigLoader<ExtentSparkReporterConfig> loader = new XmlConfigLoader<ExtentSparkReporterConfig>(conf,
                xmlFile);
        loader.apply();
    }

    @Override
    public void loadXMLConfig(String xmlFile) throws IOException {
        loadXMLConfig(new File(xmlFile));
    }

    private void executeActions() {
        if (!executed.get()) {
            conf.enableOfflineMode(conf.getOfflineMode());
            executed.compareAndSet(false, true);
        }
    }

    @Override
    public Observer<ReportEntity> getReportObserver() {
        return new Observer<ReportEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                start(d);
            }

            @Override
            public void onNext(ReportEntity value) {
                flush(value);
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        };
    }

    private void start(Disposable d) {
        disposable = d;
        loadTemplateModel();
    }

    private void flush(ReportEntity value) {
        executeActions();
        report = filterAndGet(value.getReport(), filter.statusFilter().getStatus());
        try {
            getTemplateModel().put("this", this);
            getTemplateModel().put("viewOrder", viewConfigurer.viewOrder().getViewOrder());
            getTemplateModel().put("report", report);
            createFreemarkerConfig(TEMPLATE_LOCATION, ENCODING);
            final String filePath = getFileNameAsExt(FILE_NAME, new String[]{".html", ".htm"});
            final Template template = getFreemarkerConfig().getTemplate(SPA_TEMPLATE_NAME);
            processTemplate(template, new File(filePath));
            return;
        } catch (IOException | TemplateException e) {
            disposable.dispose();
            logger.log(Level.SEVERE, "An exception occurred", e);
        }
    }
}
