package edu.kit.informatik.tests;

import edu.kit.informatik.ParseException;
import edu.kit.informatik.IP;
import edu.kit.informatik.Network;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(Parameterized.class)
public class NetworkListTests {
    @Parameterized.Parameters
    public static Collection<Object[]> cases() {
        return Arrays.asList(new Object[][]{
                {"(0.0.0.0 1.1.1.1)", List.of("0.0.0.0", "1.1.1.1")},
                {"(1.1.1.1 0.0.0.0)", List.of("0.0.0.0", "1.1.1.1")},
                {"(1.1.1.1 (2.2.2.2 0.0.0.0 4.4.4.4) 3.3.3.3)", List.of("0.0.0.0", "1.1.1.1", "2.2.2.2", "3.3.3.3", "4.4.4.4")}
        });
    }

    @Parameterized.Parameter(0)
    public String network;

    @Parameterized.Parameter(1)
    public List<String> ipStrs;

    @Test
    public void test() throws ParseException {
        List<IP> ips = ipStrs.stream().map(ip -> {
            try {
                return new IP(ip);
            } catch (ParseException e) {
                Assert.fail(e.toString());
            }
            return null;
        }).collect(Collectors.toList());
        
        Assert.assertEquals(ips, (new Network(network)).list());
    }
}
