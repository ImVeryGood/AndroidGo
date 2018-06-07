package com.newdicooker.tempetek.androidgo.com.all.helper;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by SunPengCheng
 * on 2017/12/14
 * 邮箱：13027699936@163.com.
 */

public class DataFactory {
    public static Object getInstanceByJson(Class<?> clazz, String json) {
        Object obj = null;
        Gson gson = new Gson();
        obj = gson.fromJson(json, clazz);
        return obj;
    }

    /**
     * @param json
     * @param clazz
     * @return
     * @author I321533
     */
    public static <T> List<T> jsonToList(String json, Class<T[]> clazz) {
        Gson gson = new Gson();
        T[] array = gson.fromJson(json, clazz);
        return Arrays.asList(array);
    }

    /**
     * 纯数组，没有头
     *
     * @param json
     * @param clazz
     * @return
     */
    public static <T> List<T> jsonToArrayList(String json, Class<T> clazz) {
        Type type = new TypeToken<ArrayList<JsonObject>>() {
        }.getType();
        ArrayList<JsonObject> jsonObjects = new Gson().fromJson(json, type);

        List<T> list = new ArrayList<>();
        for (JsonObject jsonObject : jsonObjects) {
            list.add(new Gson().fromJson(jsonObject, clazz));
        }
        return list;
    }
}
