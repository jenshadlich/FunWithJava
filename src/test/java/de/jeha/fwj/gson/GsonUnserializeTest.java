package de.jeha.fwj.gson;

import com.google.gson.*;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GsonUnserializeTest {

    final String JSON = "{\"content\":[{\"id\":\"42\",\"dateCreated\":1.020204000000e+12}]}";
    final Gson GSON = new Gson();
    final long EXPECTED_DATE_CREATED = 1020204000000l;

    @Test
    public void testWithSimpleGson() {
        TestObject o = GSON.fromJson(JSON, TestObject.class);
        System.out.println(o.content.get(0).dateCreated);

        assertEquals(EXPECTED_DATE_CREATED, o.content.get(0).dateCreated);
    }

    @Test
    public void testWithGsonBuilderAndTypeAdapter() {
        GsonBuilder gsonBuilder = new GsonBuilder().serializeNulls().registerTypeAdapter(TestObject.class,
                new TestObjectDeserializer());
        Gson gson = gsonBuilder.create();

        TestObject o = gson.fromJson(JSON, TestObject.class);
        System.out.println(o.content.get(0).dateCreated);

        assertEquals(EXPECTED_DATE_CREATED, o.content.get(0).dateCreated);
    }

    // -----------------------------------------------------------------------------------------------------------------

    static class TestObject {
        public List<Content> content = new ArrayList<>();

    }

    static class Content {
        public String id;
        public long dateCreated;
    }

    static class TestObjectDeserializer implements JsonDeserializer<TestObject> {
        @Override
        public TestObject deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();

            TestObject testObject = new TestObject();
            JsonArray jsonArray = jsonObject.getAsJsonArray("content");
            for (JsonElement jsonElement : jsonArray) {
                Content content = context.deserialize(jsonElement, Content.class);
                testObject.content.add(content);
            }
            return testObject;
        }
    }

}
