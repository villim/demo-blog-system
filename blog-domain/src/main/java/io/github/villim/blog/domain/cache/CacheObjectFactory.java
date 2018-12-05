package io.github.villim.blog.domain.cache;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import org.springframework.util.StringUtils;

import javax.xml.datatype.XMLGregorianCalendar;
import java.io.StringReader;

public class CacheObjectFactory {

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(XMLGregorianCalendar.class, new XMLGregorianCalendarConverter.Deserializer())
            .registerTypeAdapter(XMLGregorianCalendar.class, new XMLGregorianCalendarConverter.Serializer()).create();

    private CacheObjectFactory() {
    }

    public static CacheObject create(String json) {
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObj = (JsonObject) jsonParser.parse(new JsonReader(new StringReader(json)));

        String className = jsonObj.get("className").getAsString();
        if (StringUtils.isEmpty(className)) {
            throw new RuntimeException("className can not be empty!");
        }

        switch (className) {
            case "java.lang.String":
                return new CacheObject(className, jsonObj.get("object").getAsString());
            case "java.lang.Boolean":
                return new CacheObject(className, jsonObj.get("object").getAsBoolean());
            case "java.lang.Integer":
                return new CacheObject(className, jsonObj.get("object").getAsInt());
            case "java.lang.Long":
                return new CacheObject(className, jsonObj.get("object").getAsLong());
            default:
                JsonObject innerJsonObj = jsonObj.get("object").getAsJsonObject();
                if (innerJsonObj == null) {
                    throw new RuntimeException("Object can not be null!");
                }

                Object object = null;
                try {
                    object = gson.fromJson(innerJsonObj, Class.forName(className));
                } catch (JsonSyntaxException | ClassNotFoundException e) {
                    throw new RuntimeException("Ecounter error when building CacheObject", e);
                }

                return new CacheObject(object.getClass().getName(), object);
        }

    }

}
