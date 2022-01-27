package dev.joangoma.testReporter.reporter;

import java.util.Set;

import dev.joangoma.testReporter.Status;
import dev.joangoma.testReporter.model.Report;
import dev.joangoma.testReporter.model.service.ReportFilterService;
import dev.joangoma.testReporter.reporter.filter.StatusFilterable;
import dev.joangoma.testReporter.util.Assert;

import lombok.Getter;

@Getter
public class AbstractFilterableReporter extends AbstractReporter implements StatusFilterable {
    @Override
    public Report filterAndGet(Report report, Set<Status> set) {
        Assert.notNull(report, "Report must not be null");
        if (set != null)
            return ReportFilterService.filter(report, set);
        return report;
    }
}
