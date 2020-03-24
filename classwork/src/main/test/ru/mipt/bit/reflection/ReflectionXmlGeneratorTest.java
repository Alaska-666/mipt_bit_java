package ru.mipt.bit.reflection;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ReflectionXmlGeneratorTest {
    private ReflectionXmlGenerator xmlGenerator = new ReflectionXmlGenerator();

    @Test
    public void testToXml() {
        String result = null;
        try {
            result = xmlGenerator.toXml(List.of(
                    new Person("Alex", 20),
                    new Person("Bob", 25)
            ));
        } catch (Exception e) {
        }

        assertEquals("<Person>\n" +
                        "   <person>\n" +
                        "     <name>Alex</name>\n" +
                        "     <age>20</age>\n" +
                        "   </person>\n" +
                        "   <person>\n" +
                        "     <name>Alex</name>\n" +
                        "     <age>20</age>\n" +
                        "   </person>\n" +
                        "</Person>\n",
                result);
    }

    private static class Person {
        private final String name;
        private final int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
}