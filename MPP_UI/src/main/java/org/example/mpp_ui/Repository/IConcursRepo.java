package org.example.mpp_ui.Repository;

import org.example.mpp_ui.Domain.Concurs;

import java.util.List;

public interface IConcursRepo {
    List<Concurs> FindAllForAge(int age);
}
