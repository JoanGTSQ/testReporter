package dev.joangoma.testReporter.markuputils;

import java.io.Serializable;

@FunctionalInterface
public interface Markup extends Serializable {
    String getMarkup();
}