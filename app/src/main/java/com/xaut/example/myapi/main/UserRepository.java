package com.xaut.example.myapi.main;

/**
 * Created by pc on 2016/12/14.
 */

public class UserRepository {
    public UserRepository() {
    }

    public User getUser() {
        User user = new User();
        user.name = "yxm";
        return user;
    }
}
