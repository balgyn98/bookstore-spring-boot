package com.example.in_memory_db_starter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryRepository<T> {
    private final List<T> store = new ArrayList<>();

    public T save(T entity) {
        store.add(entity);
        return entity;
    }

    public Optional<T> findById(Long id) {
        return store.stream()
                .filter(entity -> {
                    try {
                        return entity.getClass().getMethod("getId").invoke(entity).equals(id);
                    } catch (Exception e) {
                        return false;
                    }
                })
                .findFirst();
    }

    public List<T> findAll() {
        return new ArrayList<>(store);
    }

    public void deleteById(Long id) {
        store.removeIf(entity -> {
            try {
                return entity.getClass().getMethod("getId").invoke(entity).equals(id);
            } catch (Exception e) {
                return false;
            }
        });
    }
}
