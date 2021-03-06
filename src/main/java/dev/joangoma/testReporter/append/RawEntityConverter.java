package dev.joangoma.testReporter.append;

import java.io.File;
import java.io.IOException;
import java.util.List;

import dev.joangoma.testReporter.ExtentReports;
import dev.joangoma.testReporter.ExtentTest;
import dev.joangoma.testReporter.GherkinKeyword;
import dev.joangoma.testReporter.MediaEntityBuilder;
import dev.joangoma.testReporter.model.Log;
import dev.joangoma.testReporter.model.Media;
import dev.joangoma.testReporter.model.ScreenCapture;
import dev.joangoma.testReporter.model.Test;

public class RawEntityConverter {
    private final ExtentReports extent;

    public RawEntityConverter(ExtentReports extent) {
        this.extent = extent;
    }

    public void convertAndApply(File jsonFile) throws IOException {
        if (!jsonFile.exists())
            return;
        extent.setReportUsesManualConfiguration(true);
        List<Test> tests = new JsonDeserializer(jsonFile).deserialize();
        for (Test test : tests) {
            try {
                if (test.getBddType() == null) {
                    createDomain(test, extent.createTest(test.getName(), test.getDescription()));
                } else {
                    ExtentTest extentTest = extent.createTest(new GherkinKeyword(test.getBddType().getSimpleName()),
                            test.getName(), test.getDescription());
                    createDomain(test, extentTest);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void createDomain(Test test, ExtentTest extentTest) throws ClassNotFoundException {
        extentTest.getModel().setStartTime(test.getStartTime());
        extentTest.getModel().setEndTime(test.getEndTime());
        addMedia(test, extentTest);

        // create events
        for (Log log : test.getLogs()) {
            if (log.hasException() && log.hasMedia())
                addMedia(log, extentTest, log.getException().getException());
            else if (log.hasException())
                extentTest.log(log.getStatus(), log.getException().getException());
            else if (log.hasMedia())
                addMedia(log, extentTest, null);
            else
                extentTest.log(log.getStatus(), log.getDetails());
        }

        // assign attributes
        test.getAuthorSet().stream().map(x -> x.getName()).forEach(extentTest::assignAuthor);
        test.getCategorySet().stream().map(x -> x.getName()).forEach(extentTest::assignCategory);
        test.getDeviceSet().stream().map(x -> x.getName()).forEach(extentTest::assignDevice);

        // handle nodes
        for (Test node : test.getChildren()) {
            ExtentTest extentNode = null;
            if (node.getBddType() == null)
                extentNode = extentTest.createNode(node.getName(), node.getDescription());
            else
                extentNode = extentTest.createNode(new GherkinKeyword(node.getBddType().getSimpleName()), node.getName(),
                        node.getDescription());
            addMedia(node, extentNode);
            createDomain(node, extentNode);
        }
    }

    private void addMedia(Log log, ExtentTest extentTest, Throwable ex) {
        Media m = log.getMedia();
        if (m.getPath() != null) {
            extentTest.log(log.getStatus(), log.getDetails(), ex,
                    MediaEntityBuilder.createScreenCaptureFromPath(m.getPath()).build());
        } else if (((ScreenCapture) m).getBase64() != null) {
            extentTest.log(log.getStatus(), log.getDetails(), ex,
                    MediaEntityBuilder.createScreenCaptureFromBase64String(((ScreenCapture) m).getBase64())
                            .build());
        }
    }

    private void addMedia(Test test, ExtentTest extentTest) {
        if (test.getMedia() != null) {
            for (Media m : test.getMedia()) {
                if (m.getPath() != null) {
                    extentTest.addScreenCaptureFromPath(m.getPath());
                } else if (m instanceof ScreenCapture) {
                    extentTest.addScreenCaptureFromBase64String(((ScreenCapture) m).getBase64());
                }
            }
        }
    }
}
