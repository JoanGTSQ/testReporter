package dev.joangoma.testReporter.gson;

import dev.joangoma.testReporter.append.ScreenCaptureTypeAdapter;
import dev.joangoma.testReporter.model.Media;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonExtentTypeAdapterBuilder {
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        GsonBuilder builder = new GsonBuilder();

        public Builder withBddTypeAdapterFactory() {
            builder.registerTypeAdapterFactory(new BddTypeAdapterFactory());
            return this;
        }

        public Builder withScreenCaptureTypeAdapter() {
            builder.registerTypeAdapter(Media.class, new ScreenCaptureTypeAdapter());
            return this;
        }

        public Gson build() {
            return builder.create();
        }
    }
}
