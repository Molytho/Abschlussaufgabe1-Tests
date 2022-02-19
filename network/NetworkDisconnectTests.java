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
public class NetworkDisconnectTests {
    @Parameterized.Parameters
    public static Collection<Object[]> cases() {
        return Arrays.asList(new Object[][]{
                {"(0.0.0.0 1.1.1.1)", "0.0.0.0", "1.1.1.1", "(0.0.0.0 1.1.1.1)", false},
                {"(0.0.0.0 (1.1.1.1 2.2.2.2))", "1.1.1.1", "2.2.2.2", "(0.0.0.0 1.1.1.1)", true},
                {"(0.0.0.0 (1.1.1.1 2.2.2.2))", "2.2.2.2", "1.1.1.1", "(0.0.0.0 1.1.1.1)", true},
                {"(0.0.0.0 1.1.1.1 2.2.2.2)", "2.2.2.2", "3.3.3.3", "(0.0.0.0 1.1.1.1 2.2.2.2)", false},
                {"(0.0.0.0 1.1.1.1 2.2.2.2)", "2.2.2.2", null, "(0.0.0.0 1.1.1.1 2.2.2.2)", false},
                {"(0.0.0.0 (1.1.1.1 (2.2.2.2 3.3.3.3)))", "2.2.2.2", "1.1.1.1", "(0.0.0.0 1.1.1.1)", true},
        });
    }

    @Parameterized.Parameter(0)
    public String networkStr;

    @Parameterized.Parameter(1)
    public String ipAStr;

    @Parameterized.Parameter(2)
    public String ipBStr;

    @Parameterized.Parameter(3)
    public String expectedNetwork;

    @Parameterized.Parameter(4)
    public boolean expectedResult;

    @Test
    public void test() throws ParseException {
        Network network = new Network(networkStr);

        IP ipA = ipAStr != null ? new IP(ipAStr) : null;
        IP ipB = ipBStr != null ? new IP(ipBStr) : null;

        Assert.assertEquals(expectedResult, network.disconnect(ipA, ipB));
        Assert.assertEquals(expectedNetwork, network.toString(new IP("0.0.0.0")));
    }
}
