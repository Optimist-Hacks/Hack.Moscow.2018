package com.hackdocs;

import com.hackdocs.util.DateUtils;
import org.junit.Assert;
import org.junit.Test;

public class DateUtilsTests {

    @Test
    public void formatTest() {
        Assert.assertEquals("09.20.2019", DateUtils.formatDate("2019-09-20T12:00:00+03:00"));
    }

}