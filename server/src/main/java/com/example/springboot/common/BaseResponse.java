package com.example.springboot.common;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class BaseResponse {
    public static JsonObject createFullMessageResponse(int error, String message, JsonElement data) {
        JsonObject json = new JsonObject();
        json.addProperty("error", error);
        json.addProperty("message", message);
        json.add("data", data);
        return json;
    }
    public static JsonObject createFullMessageResponse(int error, String message) {
        JsonObject json = new JsonObject();
        json.addProperty("error", error);
        json.addProperty("message", message);
        return json;
    }
}