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
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(Parameterized.class)
public class NetworkGetRouteTests {
    public static Network getTestNetwork() throws ParseException {
        Network networkA = new Network("(0.0.0.0 1.1.1.1 (3.3.3.3 2.2.2.2) 4.4.4.4)");
        Network networkB = new Network("(5.5.5.5 6.6.6.6)");
        networkA.add(networkB);
        return networkA;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> cases() {
        return Arrays.asList(new Object[][]{
                {"0.0.0.0", "0.0.0.0", List.of("0.0.0.0")},
                {"0.0.0.0", "1.1.1.1", List.of("0.0.0.0", "1.1.1.1")},
                {"0.0.0.0", "2.2.2.2", List.of("0.0.0.0", "3.3.3.3", "2.2.2.2")},
                {"1.1.1.1", "2.2.2.2", List.of("1.1.1.1", "0.0.0.0", "3.3.3.3", "2.2.2.2")},
                {"0.0.0.0", "5.5.5.5", Collections.emptyList()},
                {"0.0.0.0", null, Collections.emptyList()},
                {null, "0.0.0.0", Collections.emptyList()},
        });
    }

    @Parameterized.Parameter(0)
    public String ipAStr;

    @Parameterized.Parameter(1)
    public String ipBStr;

    @Parameterized.Parameter(2)
    public List<String> expectedOutput;

    @Test
    public void test() throws ParseException {
        IP ipA = ipAStr != null ? new IP(ipAStr) : null;
        IP ipB = ipBStr != null ? new IP(ipBStr) : null;

        List<IP> ips = expectedOutput.stream().map(ipStr -> {
            try {
                return new IP(ipStr);
            } catch (ParseException e) {
                Assert.fail(e.toString());
            }
            return null;
        }).collect(Collectors.toList());

        Assert.assertEquals(ips, getTestNetwork().getRoute(ipA, ipB));
    }
}
