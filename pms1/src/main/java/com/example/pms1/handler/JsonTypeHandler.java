package com.example.pms1.handler;

/**
 * @author loself
 * @date 2024-07-05 9:55
 */
import com.baomidou.mybatisplus.extension.handlers.AbstractJsonTypeHandler;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Field;
import java.util.Map;

public class JsonTypeHandler extends AbstractJsonTypeHandler<Map<String, Boolean>> {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public JsonTypeHandler(Class<?> type) {
        super(type);
    }

    public JsonTypeHandler(Class<?> type, Field field) {
        super(type, field);
    }

    @Override
    public Map<String, Boolean> parse(String json) {
        try {
            return objectMapper.readValue(json, new TypeReference<Map<String, Boolean>>() {});
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toJson(Map<String, Boolean> obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
