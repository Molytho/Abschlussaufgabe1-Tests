package edu.kit.informatik.tests.network;

import edu.kit.informatik.IP;
import edu.kit.informatik.Network;
import edu.kit.informatik.ParseException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ParserToStringTests {
    @Parameterized.Parameters
    public static Collection<String[]> cases() {
        return Arrays.asList(new String[][] {
            {"(0.0.0.0 0.1.1.1 2.2.2.2 3.3.3.3)", "0.0.0.0", "(0.0.0.0 0.1.1.1 2.2.2.2 3.3.3.3)"},
            {"(0.0.0.0 0.1.1.1 3.3.3.3 2.2.2.2)", "0.0.0.0", "(0.0.0.0 0.1.1.1 2.2.2.2 3.3.3.3)"},
            {"(0.0.0.0 3.3.3.3 0.1.1.1 2.2.2.2)", "0.0.0.0", "(0.0.0.0 0.1.1.1 2.2.2.2 3.3.3.3)"},
            {"(0.0.0.0 3.3.3.3 0.1.1.1 2.2.2.2)", "2.2.2.2", "(2.2.2.2 (0.0.0.0 0.1.1.1 3.3.3.3))"},
        });
    }

    @Parameterized.Parameter(0)
    public String parameter;
    @Parameterized.Parameter(1)
    public String root;
    @Parameterized.Parameter(2)
    public String expected;

    @Test
    public void test() throws ParseException {
        Assert.assertEquals(expected, new Network(parameter).toString(new IP(root)));
    }
}
