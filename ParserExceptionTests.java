package edu.kit.informatik.tests;

import edu.kit.informatik.Network;
import edu.kit.informatik.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ParserExceptionTests {
    @Parameterized.Parameters
    public static Collection<String> cases() {
        return Arrays.asList(
            "",
            "()",
            "(aa)",
            "asdfasdf1",
            "(1.1.1.1  9.1.1.1)",
            "1.1.1.1 1.1.1.2",
            " (1.1.1.1 0.0.0.0)",
            "(2.2.2.2.2 0.0.0.0)",
            "( 0.0.0.0 0.0.0.1)",
            "(0.0.0.0 0.0.0.1 )",
            "(0.0.0.0 (1.1.1.1 0.0.0.0)",
            "(0.0.0.0 (1.1.1.1))",
            "(0.0.0.0\n0.0.0.1)",
            "(0.0.0.0 0.0.0.0)",
            "(0.0.0.0)",
            "((0.0.0.0) 1.1.1.1)",
            "(0.0.0.0 )",
            "(0.0.0.0 1)",
            "(0.0.0.0 (0.0.0.1 (0.0.0.2 0.0.0.0)))",
            "(0.0.0.0 (0.0.0.1 (0.0.0.2 0.0.0.3))",
            "(0.0.0.0 1.1.1.1 2.2.2.2 3.3.3.3 0.0.0.0)",
            "(00.0.0.0 1.1.1.1)",
            "(0.0.0.2 01.1.1.1)",
            "(0.0.0.1 (0.2.3.4 0.4.4.4) 0.4.4.4)",
            "( 1.1.1.1)",
            "(266.266.266.266 1.1.1.1)"
        );
    }

    @Parameterized.Parameter
    public String parameter;

    @Test(expected = ParseException.class)
    public void exceptionTest() throws ParseException {
        new Network(parameter);
    }
}
