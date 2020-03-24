package ru.mipt.bit.reflection;

import java.lang.reflect.Field;
import java.util.List;

public class ReflectionXmlGenerator implements XmlGenerator {
    @Override
    public <T> String toXml(List<T> entities) throws IllegalAccessException {
        if (entities.isEmpty()) return "</empty>";

        StringBuilder result = new StringBuilder("<" + entities.get(0).getClass().getName() + ">\n");
        for (Object entity : entities) {
            Class entityClass = entity.getClass();
            result.append("   <").append(entityClass.getSimpleName().toLowerCase()).append(">\n");
            for (Field declaredField : entityClass.getDeclaredFields()) {
                String fieldName = declaredField.getName();
                result.append("     <").append(fieldName).append(">").append(declaredField.get(fieldName).toString()).append("</").append(fieldName).append(">\n");
            }
            result.append("   </").append(entityClass.getSimpleName().toLowerCase()).append(">\n");
        }
        result.append("</").append(entities.get(0).getClass().getName()).append(">");
        return result.toString();
    }
}
