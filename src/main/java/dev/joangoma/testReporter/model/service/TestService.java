package dev.joangoma.testReporter.model.service;

import java.util.Calendar;
import java.util.List;

import dev.joangoma.testReporter.gherkin.model.IGherkinFormatterModel;
import dev.joangoma.testReporter.model.Log;
import dev.joangoma.testReporter.model.Test;
import dev.joangoma.testReporter.util.Assert;

public class TestService {
    public static Boolean testHasScreenCapture(Test test, Boolean deep) {
        if (deep) {
            Boolean hasScreenCapture = !test.getMedia().isEmpty()
                    || test.getLogs().stream().anyMatch(Log::hasMedia);
            if (!hasScreenCapture)
                hasScreenCapture = test.getChildren().stream().anyMatch(x -> testHasScreenCapture(x, deep));
            return hasScreenCapture;
        }
        return test.hasScreenCapture();
    }

    public static Test createTest(Class<? extends IGherkinFormatterModel> type, String name, String description) {
    	Assert.notEmpty(name, "Test name must not be null or empty");
        return Test.builder()
                .bddType(type)
                .name(name)
                .description(description)
                .endTime(Calendar.getInstance().getTime()).build();
    }

    public static Test createTest(String name, String description) {
        return createTest(null, name, description);
    }

    public static Test createTest(String name) {
        return createTest(name, null);
    }
    
    public static boolean deleteTest(List<Test> list, Test test) {
        boolean removed = list.removeIf(x -> x.getId() == test.getId());
        if (!removed)
            list.forEach(x -> deleteTest(x.getChildren(), test));
        return removed;
    }
}
