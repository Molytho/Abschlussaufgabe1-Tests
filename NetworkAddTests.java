package edu.kit.informatik.tests;

import edu.kit.informatik.ParseException;
import edu.kit.informatik.Network;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class NetworkAddTests {
    @Parameterized.Parameters
    public static Collection<Object[]> cases() {
        return Arrays.asList(new Object[][] {
                {"(0.0.0.0 1.1.1.1)", "(1.1.1.1 2.2.2.2)", "(0.0.0.0 (1.1.1.1 2.2.2.2))", true},
                {"(0.0.0.0 1.1.1.1)", "(0.0.0.0 1.1.1.1)", "(0.0.0.0 1.1.1.1)", true},
                {"(0.0.0.0 (1.1.1.1 2.2.2.2))", "(2.2.2.2 0.0.0.0)", "(0.0.0.0 (1.1.1.1 2.2.2.2))", false},
                {"(0.0.0.0 (1.1.1.1 (2.2.2.2 3.3.3.3)))", "(2.2.2.2 3.3.3.3 4.4.4.4)", "(0.0.0.0 (1.1.1.1 (2.2.2.2 3.3.3.3 4.4.4.4)))", true},
                {"(0.0.0.0 1.1.1.1)", "(2.2.2.2 3.3.3.3)", null, true}, // no bracket notation for disjoint trees :(
                {"(0.0.0.0 1.1.1.1)", null, "(0.0.0.0 1.1.1.1)", false},
        });
    }

    @Parameterized.Parameter(0)
    public String networkAStr;

    @Parameterized.Parameter(1)
    public String networkBStr;

    @Parameterized.Parameter(2)
    public String expectedNetwork;

    @Parameterized.Parameter(3)
    public boolean expectedOutput;

    @Test
    public void test() throws ParseException {
        Network networkA = new Network(networkAStr);
        Network networkB = networkBStr != null ? new Network(networkBStr) : null;

        Assert.assertEquals(expectedOutput, networkA.add(networkB));

        if (expectedNetwork != null)
            Assert.assertEquals(new Network(expectedNetwork), networkA);
    }
}
