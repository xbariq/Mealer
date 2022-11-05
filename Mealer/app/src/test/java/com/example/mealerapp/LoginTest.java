package com.example.mealerapp;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class LoginTest {
    @Test
    public void Login_isFunctional(){
        var log = new Login();
        String password = "";
        String email = "";

        assertNull(Login(), log);


    }
}