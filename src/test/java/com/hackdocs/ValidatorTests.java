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
        Assert.assertTrue(Validator.isValidName("male"));
        Assert.assertTrue(Validator.isValidName("Male"));
        Assert.assertTrue(Validator.isValidName("Girl"));
        Assert.assertTrue(Validator.isValidName("Man"));
        Assert.assertTrue(Validator.isValidName("F"));
        Assert.assertTrue(Validator.isValidName("M"));
        Assert.assertTrue(Validator.isValidName("m"));
        Assert.assertTrue(Validator.isValidName("f"));
        Assert.assertFalse(Validator.isValidName("ivan"));
    }

}