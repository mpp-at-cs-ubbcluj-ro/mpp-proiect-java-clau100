package org.example.Repository;

import org.example.Domain.User;

import java.util.Optional;

public class UserRepo implements IRepo<User, Long>{
    private static final String ConnectionString = "jdbc:sqlite:C:/Users/user/Downloads/mpp-proiect-java-clau100/MPP_ProjectJava/DB.sqlite";
    @Override
    public User[] getAll() {

        return new User[0];
    }

    @Override
    public Optional<User> find(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> add(User toAdd) {
        return Optional.empty();
    }

    @Override
    public Optional<User> delete(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> update(User toUpdate) {
        return Optional.empty();
    }
}
