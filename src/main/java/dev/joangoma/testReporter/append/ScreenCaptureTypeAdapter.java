package dev.joangoma.testReporter.append;

import java.io.IOException;

import dev.joangoma.testReporter.model.Media;
import dev.joangoma.testReporter.model.ScreenCapture;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

public class ScreenCaptureTypeAdapter extends TypeAdapter<Media> {

    @Override
    public void write(JsonWriter out, Media value) throws IOException {
    }

    @Override
    public Media read(JsonReader reader) throws IOException {
        ScreenCapture sc = ScreenCapture.builder().build();
        reader.beginObject();
        String fieldName = null;
        int cycle = 0;
        while (reader.hasNext()) {
            JsonToken token = reader.peek();
            if (token.equals(JsonToken.NAME)) {
                fieldName = reader.nextName();
            }
            if ("string".equalsIgnoreCase(token.name()) && fieldName.equalsIgnoreCase("path")) {
                token = reader.peek();
                sc.setPath(reader.nextString());
            }
            if ("string".equalsIgnoreCase(token.name()) && fieldName.equalsIgnoreCase("resolvedPath")) {
                token = reader.peek();
                sc.setResolvedPath(reader.nextString());
            }
            if ("string".equalsIgnoreCase(token.name()) && fieldName.equalsIgnoreCase("base64")) {
                token = reader.peek();
                sc.setBase64(reader.nextString());
            }
            if (cycle++ > 10)
                return sc;
        }
        reader.endObject();
        return sc;
    }

}
