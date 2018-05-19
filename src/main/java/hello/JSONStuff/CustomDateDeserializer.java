package hello.JSONStuff;

import com.google.gson.*;

import java.lang.reflect.Type;

public class CustomDateDeserializer implements JsonDeserializer<Lesson> {
    public Lesson deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject jobject = jsonElement.getAsJsonObject();

        String date;
//        System.out.println(jobject.get("subject").toString());
//        System.out.println(jobject.get("subject").getAsString());

            if (jobject.get("date") != null) {
                date = jobject.get("date").toString();
                return new Lesson(
                        jobject.get("subject").getAsString(),
                        jobject.get("type").getAsString(),
                        jobject.get("time").getAsJsonObject(),
                        date,
                        jobject.get("audiences").getAsJsonArray(),
                        jobject.get("teachers").getAsJsonArray()
                );
            }

        return new Lesson(
                jobject.get("subject").getAsString(),
                jobject.get("type").getAsString(),
                jobject.get("time").getAsJsonObject(),
                jobject.get("date").getAsJsonArray(),
                jobject.get("audiences").getAsJsonArray(),
                jobject.get("teachers").getAsJsonArray()
        );
    }
}
