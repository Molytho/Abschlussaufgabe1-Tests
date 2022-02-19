package edu.kit.informatik.tests.network;

import edu.kit.informatik.ParseException;
import edu.kit.informatik.IP;
import edu.kit.informatik.Network;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class NetworkContainsTests {
    @Parameterized.Parameters
    public static Collection<Object[]> cases() {
        return Arrays.asList(new Object[][] {
                {"(0.0.0.0 1.1.1.1 2.2.2.2 3.3.3.3)", "0.0.0.0", true},
                {"(0.0.0.0 1.1.1.1 2.2.2.2 3.3.3.3)", "1.1.1.1", true},
                {"(0.0.0.0 1.1.1.1 2.2.2.2 3.3.3.3)", "4.4.4.4", false},
                {"(0.0.0.0 1.1.1.1 2.2.2.2 3.3.3.3)", null, false},
        });
    }

    @Parameterized.Parameter(0)
    public String networkStr;

    @Parameterized.Parameter(1)
    public String ipStr;

    @Parameterized.Parameter(2)
    public boolean expectedOutput;

    @Test
    public void test() throws ParseException {
        Network network = new Network(networkStr);
        IP ip = ipStr != null ? new IP(ipStr) : null;
        Assert.assertEquals(expectedOutput, network.contains(ip));
    }
}
