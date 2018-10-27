package com.hackdocs;

import com.hackdocs.validators.Validator;
import org.junit.Assert;
import org.junit.Test;

public class ValidatorTests {

    @Test
    public void validateNameTest() {
        Assert.assertTrue(Validator.isValidName("Ivan"));
        Assert.assertFalse(Validator.isValidName("123"));
        Assert.assertTrue(Validator.isValidName("ivan"));
    }

    @Test
    public void validateSexTest() {
        Assert.assertTrue(Validator.isValidSex("male"));
        Assert.assertTrue(Validator.isValidSex("Male"));
        Assert.assertTrue(Validator.isValidSex("Girl"));
        Assert.assertTrue(Validator.isValidSex("Man"));
        Assert.assertTrue(Validator.isValidSex("F"));
        Assert.assertTrue(Validator.isValidSex("M"));
        Assert.assertTrue(Validator.isValidSex("m"));
        Assert.assertTrue(Validator.isValidSex("f"));
        Assert.assertTrue(Validator.isValidSex("woman"));
        Assert.assertFalse(Validator.isValidSex("ivan"));
    }

}