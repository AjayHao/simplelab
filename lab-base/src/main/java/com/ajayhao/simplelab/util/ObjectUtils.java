package com.ajayhao.simplelab.util;

import com.ajayhao.simplelab.facade.dto.common.BaseDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Created by AjayHao on 2017/7/25.
 */
public class ObjectUtils {
    private static final long serialVersionUID = 3446386328773575785L;

    protected static final Gson GSON = (new GsonBuilder())
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            //.registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory())
            .serializeNulls().create();

    public static String toJson(Object object) {
        return GSON.toJson(object);
    }

    public static <T> T fromJson(String json, Class<T> classOfT) {
        return GSON.fromJson(json, classOfT);
    }

    public static <T> T fromJson(String json, Type typeOfT) {
        return GSON.fromJson(json, typeOfT);
    }

}
