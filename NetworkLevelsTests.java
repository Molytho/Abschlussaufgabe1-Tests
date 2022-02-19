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
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(Parameterized.class)
public class NetworkLevelsTests {
    @Parameterized.Parameters
    public static Collection<Object[]> cases() {
        return Arrays.asList(new Object[][] {
                {"(0.0.0.0 1.1.1.1 2.2.2.2 3.3.3.3)", "0.0.0.0", List.of(List.of("0.0.0.0"), List.of("1.1.1.1", "2.2.2.2", "3.3.3.3"))},
                {"(0.0.0.0 1.1.1.1 2.2.2.2 3.3.3.3)", "1.1.1.1", List.of(List.of("1.1.1.1"), List.of("0.0.0.0"), List.of("2.2.2.2", "3.3.3.3"))},
                {"(0.0.0.0 1.1.1.1 (2.2.2.2 3.3.3.3))", "0.0.0.0", List.of(List.of("0.0.0.0"), List.of("1.1.1.1", "2.2.2.2"), List.of("3.3.3.3"))},
                {"(0.0.0.0 1.1.1.1 (2.2.2.2 3.3.3.3))", "1.1.1.1", List.of(List.of("1.1.1.1"), List.of("0.0.0.0"), List.of("2.2.2.2"), List.of("3.3.3.3"))},
                {"(0.0.0.0 1.1.1.1 (2.2.2.2 3.3.3.3))", "3.3.3.3", List.of(List.of("3.3.3.3"), List.of("2.2.2.2"), List.of("0.0.0.0"), List.of("1.1.1.1"))},
                {"(0.0.0.0 1.1.1.1 2.2.2.2 3.3.3.3)", "4.4.4.4", Collections.emptyList()},
                {"(0.0.0.0 1.1.1.1 2.2.2.2 3.3.3.3)", null, Collections.emptyList()},
        });
    }

    @Parameterized.Parameter(0)
    public String networkStr;

    @Parameterized.Parameter(1)
    public String ipStr;

    @Parameterized.Parameter(2)
    public List<List<String>> expectedOutput;

    @Test
    public void test() throws ParseException {
        Network network = new Network(networkStr);
        IP ip = ipStr != null ? new IP(ipStr) : null;

        List<List<IP>> ips = expectedOutput.stream().map(
                level -> level.stream().map(levelIP -> {
                    try {
                        return new IP(levelIP);
                    } catch (ParseException e) {
                        Assert.fail(e.toString());
                    }
                    return null;
                }).collect(Collectors.toList())
        ).collect(Collectors.toList());

        Assert.assertEquals(ips, network.getLevels(ip));
    }
}
