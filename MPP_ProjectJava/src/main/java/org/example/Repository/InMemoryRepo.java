package org.example.Repository;

import org.example.Domain.Entity;

public interface InMemoryRepo<T extends Entity<Long>>{
    T[] getAll();
    T find(Long id);
    T add(T toAdd);
    T delete(Long id);
    T update(T toUpdate);
}
