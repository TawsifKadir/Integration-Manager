package com.kit;

import android.util.Base64;

import com.google.gson.*;

import java.lang.reflect.Type;

public class GsonUtils {

    // Adapter to handle byte[] <-> Base64 String
    public static class ByteArrayBase64Adapter implements JsonDeserializer<byte[]>, JsonSerializer<byte[]> {

        @Override
        public byte[] deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            try {
                return Base64.decode(json.getAsString(), Base64.DEFAULT);
            } catch (Exception e) {
                throw new JsonParseException("Failed to decode Base64 string to byte[]", e);
            }
        }

        @Override
        public JsonElement serialize(byte[] src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(Base64.encodeToString(src, Base64.NO_WRAP));
        }
    }

    // Provide a Gson instance with byte[] adapter registered
    public static Gson getCustomGson() {
        return new GsonBuilder()
                .registerTypeAdapter(byte[].class, new ByteArrayBase64Adapter())
                .create();
    }
}
