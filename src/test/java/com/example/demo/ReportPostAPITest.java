package com.example.demo;

import org.junit.Assert;
import org.junit.Test;

public class ReportPostAPITest {

    // when id is not exist, then return Invalid input data.
    @Test
    public void test_Id_NotExist() throws Exception {
        Assert.fail();
    }

    // When Id's length is greater than 10, then return Invalid input data.
    @Test
    public void test00_Id_InvalidValue() throws Exception {
        Assert.fail();
    }

    // When Id's length is empty (If id is string and length = 0), then return Invalid input data.
    @Test
    public void test01_Id_InvalidValue() throws Exception {
        Assert.fail();
    }

    // When Id is not number, then return Invalid input data.
    @Test
    public void test_Id_IsNotNumber() throws Exception {
        Assert.fail();
    }

    // when type's value is invalid (not SPAM / IMPOLITE), then return Invalid input data.
    @Test
    public void test_Type_InvalidValue() throws Exception {
        Assert.fail();
    }

    // when type is not exist, then return Invalid input data.
    @Test
    public void test_Type_NotExist() throws Exception {
        Assert.fail();
    }

    // When note's length is out of value domain ([0 - 200]), then return Invalid input data.
    @Test
    public void test_Note_InvalidValue() throws Exception {
        Assert.fail();
    }

    // When note is not exist, then return Not Found
    @Test
    public void test_Note_NotExist() throws Exception {
        Assert.fail();
    }

    // When report SPAM and content has NO duplicated words, then return OK
    @Test
    public void test00_All_Valid() throws Exception {
        Assert.fail();
    }

    // When report SPAM and content has duplicated words, then return NOT OK
    @Test
    public void test01_All_Valid() throws Exception {
        Assert.fail();
    }

    // When report IMPOLITE and content has rude words, then return NOT OK
    @Test
    public void test02_All_Valid() throws Exception {
        Assert.fail();
    }

    // When report IMPOLITE and content has no rude words, then return OK
    @Test
    public void test03_All_Valid() throws Exception {
        Assert.fail();
    }
}
