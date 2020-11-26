package org.mytests.uiobjects.example;

import org.mytests.uiobjects.example.entities.User;

public class TestData {
    public static User DEFAULT_USER = new User().set(
        u -> {u.userName = "Roman"; u.password = "Jdi1234";});
}
