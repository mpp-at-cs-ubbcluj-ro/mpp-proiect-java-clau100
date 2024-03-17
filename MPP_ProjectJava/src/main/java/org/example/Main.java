package org.example;

import org.example.Repository.UserRepo;

import java.io.IOException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Properties props=new Properties();
        try {
            props.load(Main.class.getResourceAsStream("/BD.config"));
        } catch (IOException e) {
            System.out.println("Cannot find bd.config "+e);
        }
        UserRepo users = new UserRepo(props);
        users.getAll().forEach(user -> System.out.println(user.getId() + "|" + user.getUsername() + "|" + user.getPassword()));

        System.out.println("Hello world!");
    }
}