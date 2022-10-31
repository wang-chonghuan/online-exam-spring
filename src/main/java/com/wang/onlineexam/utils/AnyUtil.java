package com.wang.onlineexam.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * usage:
 * Object obj = new Object();
 * List<String> list = objToList(obj, String.class);
 * list.forEach(System.out::println);
 */
public class AnyUtil {

    public static <T> List<T> objToList(Object obj, Class<T> anyClass) {
        List<T> result = new ArrayList<T>();
        if(obj instanceof List<?>) {
            for(Object o : (List<?>) obj) {
                result.add(anyClass.cast(o));
            }
            return result;
        }
        return null;
    }

    // todo
    public static String mapSoToStr(Map<String, Object> mapSo) {
        return null;
    }

    public static Map<String, String> objToMapSs(Object obj) {
        return mapOoToMapSs(objToMapSo(obj));
    }

    public static Map<String, Object> objToMapSo(Object obj) {
        ObjectMapper oMapper = new ObjectMapper();
        Map<String, Object> mapSo = oMapper.convertValue(obj, Map.class);
        return mapSo;
    }

    public static Map<String, String> mapOoToMapSs(Map<String, Object> mapOo) {
        Map<String,String> MapSs = mapOo
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> (String)e.getValue()));
        return MapSs;
    }

    public static Map<String, String> jsonToMapSS(String jsonStr) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            // convert JSON string to Map
            Map<String, String> mapSS = mapper.readValue(jsonStr, Map.class);
            return mapSS;
            // it works
            // Map<String, String> map = mapper.readValue(json, new TypeReference<Map<String, String>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
