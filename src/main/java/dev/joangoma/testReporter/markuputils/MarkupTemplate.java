package dev.joangoma.testReporter.markuputils;

import dev.joangoma.testReporter.ExtentReports;
import dev.joangoma.testReporter.templating.FreemarkerTemplate;

class MarkupTemplate {
    protected static final FreemarkerTemplate ft = new FreemarkerTemplate(ExtentReports.class, "markup/", "UTF-8");
}
