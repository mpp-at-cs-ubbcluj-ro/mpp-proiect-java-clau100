package org.example.Repository;

import org.example.Domain.Entity;

import java.util.List;
import java.util.Optional;

public interface IRepo<T extends Entity<U>, U>{
    List<T> getAll();
    Optional<T> find(U id);
    Optional<T> add(T toAdd);
    Optional<T> delete(U id);
    Optional<T> update(T toUpdate);
}
