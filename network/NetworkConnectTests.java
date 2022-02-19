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
public class NetworkConnectTests {
    public static String networkA = "(0.0.0.0 1.1.1.1 (2.2.2.2 3.3.3.3))";
    public static String networkB = "(4.4.4.4 5.5.5.5 6.6.6.6 7.7.7.7)";

    @Parameterized.Parameters
    public static Collection<Object[]> cases() {
        return Arrays.asList(new Object[][]{
                {"0.0.0.0", "1.1.1.1", "(0.0.0.0 1.1.1.1 (2.2.2.2 3.3.3.3))", false},
                {"3.3.3.3", "0.0.0.0", "(0.0.0.0 1.1.1.1 (2.2.2.2 3.3.3.3))", false},
                {"0.0.0.0", null, "(0.0.0.0 1.1.1.1 (2.2.2.2 3.3.3.3))", false},
                {"4.4.4.4", "3.3.3.3", "(0.0.0.0 1.1.1.1 (2.2.2.2 (3.3.3.3 (4.4.4.4 5.5.5.5 6.6.6.6 7.7.7.7))))", true},
                {"3.3.3.3", "4.4.4.4", "(0.0.0.0 1.1.1.1 (2.2.2.2 (3.3.3.3 (4.4.4.4 5.5.5.5 6.6.6.6 7.7.7.7))))", true},
                {"0.0.0.0", "4.4.4.4", "(0.0.0.0 1.1.1.1 (2.2.2.2 3.3.3.3) (4.4.4.4 5.5.5.5 6.6.6.6 7.7.7.7))", true},
        });
    }

    @Parameterized.Parameter(0)
    public String ipAStr;

    @Parameterized.Parameter(1)
    public String ipBStr;

    @Parameterized.Parameter(2)
    public String expectedNetwork;

    @Parameterized.Parameter(3)
    public boolean expectedResult;

    @Test
    public void test() throws ParseException {
        Network network = new Network(networkA);
        network.add(new Network(networkB));

        IP ipA = ipAStr != null ? new IP(ipAStr) : null;
        IP ipB = ipBStr != null ? new IP(ipBStr) : null;

        Assert.assertEquals(expectedResult, network.connect(ipA, ipB));
        Assert.assertEquals(expectedNetwork, network.toString(new IP("0.0.0.0")));
    }
}
