package org.example.Repository;

import org.example.Domain.Concurs;

import java.util.List;

public interface IConcursRepo {
    List<Concurs> FindAllForAge(int age);
}
