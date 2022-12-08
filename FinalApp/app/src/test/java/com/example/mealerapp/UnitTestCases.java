package com.example.mealerapp;

import org.junit.Test;

import static org.junit.Assert.*;


public class UnitTestCases {

    @Mock
    Context mMockContext;

    @Test
    public void ValidateData_ReturnsTrue1() {
         AddMealActivity TESTOBJECT1 = new AddMealActivity(mMockContext);
         addmeal.getText().length() = 0;

         boolean result = TESTOBJECT1.validateData();
         assertTrue(result);
    }

    @Test
    public void ValidateData_ReturnsTrue2() {
        AddMealActivity TESTOBJECT2 = new AddMealActivity(mMockContext);
        description.getText().length() = 0;

        boolean result2 = TESTOBJECT2.validateData();
        assertTrue(result2);
    }

    @Test
    public void ValidateData_ReturnsFalse3() {
        AddMealActivity TESTOBJECT3 = new AddMealActivity(mMockContext);
        addmeal.getText().length() = 1;

        boolean result3 = TESTOBJECT3.validateData();
        assertFalse(result3);
    }

    @Test
    public void ValidateData_ReturnsFalse4() {
        AddMealActivity TESTOBJECT4 = new AddMealActivity(mMockContext);
        description.getText().length() = 1;

        boolean result4 = TESTOBJECT4.validateData();
        assertFalse(result4);
    }
    
        @Test
    public void ValidatePasswordExistence() {
        Register TESTOBJECT5 = new Register(mMockContext);
        TESTOBJECT5.TextUtils.isEmpty(password) = True;
        assertTrue(" Please enter a password ");
    }

    @Test
    public void ValidateEmailExistence() {
        Register TESTOBJECT6 = new Register(mMockContext);
        TESTOBJECT6.TextUtils.isEmpty(email) = True;
        assertTrue(" Please enter an Email ");
    }

    @Test
    public void ValidateEmailExistence() {
        Register TESTOBJECT7 = new Register(mMockContext);
        TESTOBJECT7.password.length() = 7;
        assertTrue(" Password must contain more than 8 characters ");
    }

    @Test
    public void VerifyRegisterUserFunctionality() {
        Register TESTOBJECT8 = new Register(mMockContext);
        TESTOBJECT8.registerUser.onComplete(True);
        assertTrue(updateUI(user));
    }

}
