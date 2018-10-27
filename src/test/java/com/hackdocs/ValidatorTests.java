package com.hackdocs;

import com.hackdocs.validators.Validator;
import org.junit.Assert;
import org.junit.Test;

public class ValidatorTests {

    @Test
    public void validateNameTest() {
        Assert.assertTrue(Validator.isValidFirstName("Ivan"));
        Assert.assertFalse(Validator.isValidFirstName("123"));
        Assert.assertFalse(Validator.isValidFirstName("ivan"));
    }

}