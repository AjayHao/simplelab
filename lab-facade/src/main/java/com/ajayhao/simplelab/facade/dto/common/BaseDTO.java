package com.ajayhao.simplelab.facade.dto.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import org.apache.commons.lang3.SerializationUtils;

import java.io.IOException;
import java.io.Serializable;

/**
 * Created by AjayHao on 2017/7/19.
 */

public abstract class BaseDTO implements Serializable, Cloneable {

    private static final long serialVersionUID = 3446386328773575785L;

    protected static final Gson GSON = (new GsonBuilder())
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .serializeNulls()
            .create();

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

}



