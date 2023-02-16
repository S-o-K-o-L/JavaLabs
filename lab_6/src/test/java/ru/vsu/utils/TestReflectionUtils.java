package ru.vsu.utils;

import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.InvocationTargetException;
import java.util.NoSuchElementException;
import java.util.Objects;

public class TestReflectionUtils {
    private TestReflectionUtils() {
        throw new RuntimeException();
    }

    public static <T> T findImplementationOf(Class<T> interfaceType) {
        Objects.requireNonNull(interfaceType);
        if (interfaceType.isInterface()) {
            try {
                Reflections reflections = new Reflections(
                        new ConfigurationBuilder()
                                .forPackage("ru.vsu")
                                .setScanners(Scanners.SubTypes));

                Class<? extends T> implementationClass = reflections.getSubTypesOf(interfaceType)
                        .stream()
                        .findFirst()
                        .orElseThrow(() -> new NoSuchElementException("Implementation not found"));

                return implementationClass.getConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                throw new RuntimeException();
            }
        } else {
            throw new IllegalArgumentException();
        }
    }
}
