package de.jeha.fwj.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Since;


public class Main {


    public static void main(String[] args) {
        Gson g = new GsonBuilder().setPrettyPrinting().setVersion(1.0).serializeNulls().create();
        System.out.println(g.toJson(new User("username", 42)));
    }

    static class User {
        @Since(1.0)
        private String name;
        @Since(1.1)
        private int age;
        private String hidden;

        private User(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
}
