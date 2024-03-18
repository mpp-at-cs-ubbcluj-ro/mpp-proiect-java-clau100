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
        users.CheckUser("george", "ubb122");

        System.out.println("Hello world!");
    }
}