package com.ajayhao.simplelab.base.dto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.lang.reflect.Type;

/**
 * Created by AjayHao on 2017/7/19.
 */

public abstract class BaseDTO implements Serializable, Cloneable {

    private static final long serialVersionUID = 3446386328773575785L;

    protected static final Gson GSON = (new GsonBuilder()).setDateFormat("yyyy-MM-dd HH:mm:ss")
            .serializeNulls().create();

    public BaseDTO() {
    }

    @Override
    public String toString() {
        return GSON.toJson(this);
    }

    @Override
    public Object clone() {
        return SerializationUtils.clone(this);
    }

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



