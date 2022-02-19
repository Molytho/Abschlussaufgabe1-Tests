package edu.kit.informatik.tests.network;

import edu.kit.informatik.Network;
import edu.kit.informatik.ParseException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

@RunWith(Parameterized.class)
public class NetworkAddMultipleTests {
    @Parameterized.Parameters
    public static Collection<Object[]> cases() {
        return Arrays.asList(new Object[][][] {
            {{"(0.0.0.0 1.1.1.1)", "(1.1.1.1 2.2.2.2)", "(0.0.0.0 (1.1.1.1 2.2.2.2))"}},
            {{"(0.0.0.0 1.1.1.1)", "(2.2.2.2 3.3.3.3)", "(2.2.2.2 1.1.1.1)", "(0.0.0.0 (1.1.1.1 (2.2.2.2 3.3.3.3)))"}},
            {{"(0.0.0.0 1.1.1.1 2.2.2.2)", "(2.2.2.2 3.3.3.3)", "(1.1.1.1 4.4.4.4)", "(0.0.0.0 2.2.2.1)", "(0.0.0.0 (1.1.1.1 4.4.4.4) 2.2.2.1 (2.2.2.2 3.3.3.3))"}},
            {{"(0.0.0.0 (1.1.1.1 2.2.2.2))", "(1.1.1.1 3.3.3.3)" , "(1.1.1.1 4.4.4.4)", "(1.1.1.1 2.2.2.2 3.3.3.3 4.4.4.4 0.0.0.0)"}},
        });
    }

    @Parameterized.Parameter(0)
    public Object[] parameter;

    @Before
    public void setup() throws ParseException {
        int size = parameter.length;
        network = new Network(parameter[0].toString());
        toBeAdded = Arrays.copyOfRange(parameter, 1, size - 1, String[].class);
        expected = new Network(parameter[size - 1].toString());
    }

    private Network network;
    private String[] toBeAdded;
    private Network expected;

    @Test
    public void test() throws ParseException {
        for (String add : toBeAdded) {
            network.add(new Network(add));
        }
        Assert.assertEquals(expected, network);
    }
}
