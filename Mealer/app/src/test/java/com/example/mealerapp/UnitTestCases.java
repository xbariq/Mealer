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

}