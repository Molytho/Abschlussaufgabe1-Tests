package edu.kit.informatik.tests.network;

import edu.kit.informatik.IP;
import edu.kit.informatik.Network;
import edu.kit.informatik.ParseException;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class NetworkEqualsTest {
    @Test
    public void equalsTest() throws ParseException {
        Network n1 = new Network(new IP("0.0.0.0"), List.of(new IP("1.1.1.1")));
        Network n2 = new Network(new IP("1.1.1.1"), List.of(new IP("0.0.0.0")));
        Network na1 = new Network(new IP("0.0.0.1"), List.of(new IP("1.1.1.2")));
        Network na2 = new Network(new IP("0.0.0.3"), List.of(new IP("1.1.1.3")));

        assert n1.add(na1);
        assert n2.add(na2);
        assert n1.add(na2);
        assert n2.add(na1);
        Assert.assertEquals(n1, n2);
    }
}
