package org.example.Domain;

public class Entity <T>{
    private final T id;
    public T getId() {
        return id;
    }

    public Entity(T id) {
        this.id = id;
    }
}
