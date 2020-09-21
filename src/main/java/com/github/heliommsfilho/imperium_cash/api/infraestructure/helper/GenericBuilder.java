package com.github.heliommsfilho.imperium_cash.api.infraestructure.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Consumer;

public class GenericBuilder<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenericBuilder.class);

    private T instance;

    public GenericBuilder(Class<T> clazz) {
        try {
            this.instance = clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |NoSuchMethodException e) {
            LOGGER.error("Error when creating GenericBuilder for class {}.", clazz.getName(), e);
        }
    }

    public GenericBuilder<T> with(Consumer<T> setter) {
        setter.accept(instance);

        return this;
    }

    public T get() {
        return instance;
    }

    public static <T> GenericBuilder<T> build(Class<T> clazz) {
        return new GenericBuilder<>(clazz);
    }
}
