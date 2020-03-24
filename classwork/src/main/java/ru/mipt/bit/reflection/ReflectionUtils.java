package ru.mipt.bit.reflection;

import java.lang.reflect.Field;
import java.util.List;

public class ReflectionUtils {
    public static void printFieldNames(Class<?> clazz) {
        while (clazz != null) {
            for (Field declaredField : clazz.getDeclaredFields()) {
                System.out.println(declaredField.getName());
            }
            clazz = clazz.getSuperclass();
        }
    }

    public static void main(String[] args) throws IllegalAccessException {
        printFieldNames(List.class);
    }
}
