package org.example.jsonconverter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ObjectMapper {


    public <T> String convert(T object) {
        Field[] fields = object.getClass().getDeclaredFields();
        List<Field> fieldList = new ArrayList<>(List.of(fields));
        StringBuilder stringBuilder = new StringBuilder("{");
        fieldList.forEach(field -> {
            try {
                field.setAccessible(true);
                stringBuilder.append("\n\t").append(field.getName()).append(":").append(field.get(object)).append(",");
                field.setAccessible(false);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
        return stringBuilder.append("\n}").toString();
    }

}
