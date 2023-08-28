package com.hxd.fsystemback;

import java.lang.reflect.Field;
import java.util.List;

public class ListPrintUtil {
    public static void printList(List<?> objectList) throws IllegalAccessException {
        for (Object obj : objectList) {
            System.out.println(formatObject(obj, 0));
        }
    }

    private static String formatObject(Object object, int indentLevel) throws IllegalAccessException {
        StringBuilder result = new StringBuilder();
        Field[] fields = object.getClass().getDeclaredFields();

        String indent = getIndent(indentLevel);
        result.append(indent).append(object.getClass().getSimpleName()).append("{\n");

        for (Field field : fields) {
            field.setAccessible(true);
            Object fieldValue = field.get(object);
            result.append(formatField(field.getName(), fieldValue, indentLevel + 1));
        }

        result.append(indent).append("}");

        return result.toString();
    }

    private static String formatField(String fieldName, Object fieldValue, int indentLevel) throws IllegalAccessException {
        String indent = getIndent(indentLevel);
        String fieldValueString = fieldValue != null ? fieldValue.toString() : "null";
        return indent + fieldName + "=" + fieldValueString + "\n";
    }

    private static String getIndent(int indentLevel) {
        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < indentLevel; i++) {
            indent.append("  ");
        }
        return indent.toString();
    }
}

