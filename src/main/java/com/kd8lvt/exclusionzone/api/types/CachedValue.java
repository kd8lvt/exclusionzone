package com.kd8lvt.exclusionzone.api.types;

public class CachedValue<T> {
    private final Getter<T> getter;
    private T cache;
    public CachedValue(Getter<T> getter) {
        this.getter=getter;
    }

    public T get() {
        if (cache == null) cache = getter.get();
        return cache;
    }

    @FunctionalInterface
    public interface Getter<T> {
        T get();
    }
}