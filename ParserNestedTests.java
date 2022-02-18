package edu.kit.informatik.tests;

import edu.kit.informatik.Network;
import edu.kit.informatik.ParseException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ParserNestedTests {
    @Parameterized.Parameters
    public static Collection cases() {
        return Arrays.asList(new Object[][] {
            {"(0.0.0.0 (0.1.1.1 2.2.2.2) 3.3.3.3)",
                new String[][] {new String[] {"(0.0.0.0 0.1.1.1 3.3.3.3)"}, new String[] {"(0.1.1.1 2.2.2.2)"}}},
            {"(0.0.0.1 (0.1.1.1 2.2.2.2) 3.3.3.3)",
                new String[][] {new String[] {"(0.0.0.1 0.1.1.1 3.3.3.3)"}, new String[] {"(0.1.1.1 2.2.2.2)"}}},
            {"(0.2.0.0 (0.1.1.1 2.2.2.2) 3.3.3.3)",
                new String[][] {new String[] {"(0.2.0.0 0.1.1.1 3.3.3.3)"}, new String[] {"(0.1.1.1 2.2.2.2)"}}},
            {"(0.0.0.0 (1.1.1.1 2.2.2.2) 3.3.3.3)",
                new String[][] {new String[] {"(0.0.0.0 1.1.1.1 3.3.3.3)"}, new String[] {"(1.1.1.1 2.2.2.2)"}}},
            {"(0.0.0.0 (0.1.1.1 2.2.2.2) 3.3.5.3)",
                new String[][] {new String[] {"(0.0.0.0 0.1.1.1 3.3.5.3)"}, new String[] {"(0.1.1.1 2.2.2.2)"}}},
            {"(0.0.0.0 (0.1.1.1 (2.2.2.2 9.9.9.9)) (3.3.5.3 9.7.7.7))",
                new String[][] {new String[] {"(0.0.0.0 0.1.1.1 3.3.5.3)"}, new String[] {"(0.1.1.1 2.2.2.2)", "(3.3.5.3 9.7.7.7)"}, new String[] {"(2.2.2.2 9.9.9.9)"}}}
        });
    }

    @Parameterized.Parameter(0)
    public String parameter;
    @Parameterized.Parameter(1)
    public String[][] expectedString;

    private Network expected;

    @Before
    public void init() throws ParseException {
        expected = new Network(expectedString[0][0]);
        Arrays.stream(expectedString).skip(1).forEachOrdered(
            level -> {
                Arrays.stream(level).map(bracketNotation -> {
                    try {
                        return new Network(bracketNotation);
                    } catch (ParseException e) {
                        Assert.fail(e.getMessage());
                        return null;
                    }
                }).forEach(
                    expected::add
                );
            }
        );
    }

    @Test
    public void test() throws ParseException {
        Assert.assertEquals(new Network(parameter), expected);
    }
}
