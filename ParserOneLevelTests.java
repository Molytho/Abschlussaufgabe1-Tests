package edu.kit.informatik.tests;

import edu.kit.informatik.IP;
import edu.kit.informatik.Network;
import edu.kit.informatik.ParseException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class ParserOneLevelTests {
    @Parameterized.Parameters
    public static Collection cases() throws ParseException {
        return Arrays.asList(new Object[][] {
            {"(0.0.0.0 0.1.1.1 2.2.2.2 3.3.3.3)",
                new Network(new IP("0.0.0.0"), List.of(new IP("0.1.1.1"), new IP("2.2.2.2"), new IP("3.3.3.3")))},
            {"(0.0.0.0 0.1.1.1 2.2.2.2 3.3.3.3)",
                new Network(new IP("0.0.0.0"), List.of(new IP("0.1.1.1"), new IP("3.3.3.3"), new IP("2.2.2.2")))},
        });
    }

    @Parameterized.Parameter(0)
    public String parameter;
    @Parameterized.Parameter(1)
    public Network expected;

    @Test
    public void test() throws ParseException {
        Assert.assertEquals(new Network(parameter), expected);
    }
}
