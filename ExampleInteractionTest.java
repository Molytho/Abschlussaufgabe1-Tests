package edu.kit.informatik.tests;

import edu.kit.informatik.IP;
import edu.kit.informatik.Network;
import edu.kit.informatik.ParseException;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ExampleInteractionTest {
    @Test
    public void exampleInteraction() throws ParseException {
        IP root = new IP("141.255.1.133");
        List<List<IP>> levels = List.of(List.of(root),
            List.of(new IP("0.146.197.108"), new IP("122.117.67.158")));
        final Network network = new Network(root, levels.get(1));
        Assert.assertEquals("(141.255.1.133 0.146.197.108 122.117.67.158)", network.toString(root));
        Assert.assertEquals(levels.size() - 1, network.getHeight(root));
        Assert.assertEquals(List.of(List.of(root), levels.get(1)), network.getLevels(root));

        // "Change" root and call toString, getHeight and getLevels again
        root = new IP("122.117.67.158");
        levels = List.of(List.of(root), List.of(new IP("141.255.1.133")),
            List.of(new IP("0.146.197.108")));
        Assert.assertEquals("(122.117.67.158 (141.255.1.133 0.146.197.108))", network.toString(root));
        Assert.assertEquals((levels.size() - 1), network.getHeight(root));
        Assert.assertEquals(levels, network.getLevels(root));

        // Try to add circular dependency
        Assert.assertFalse(network.add(new Network(
            "(122.117.67.158 0.146.197.108)")));

        // Merge two subnets with initial network
        Assert.assertTrue(network.add(new Network(
            "(85.193.148.81 34.49.145.239 231.189.0.127 141.255.1.133)")));
        Assert.assertTrue(network.add(new Network("(231.189.0.127 252.29.23.0"
            + " 116.132.83.77 39.20.222.120 77.135.84.171)")));

        // "Change" root and call toString, getHeight and getLevels again
        root = new IP("85.193.148.81");
        levels = List.of(List.of(root),
            List.of(new IP("34.49.145.239"), new IP("141.255.1.133"),
                new IP("231.189.0.127")),
            List.of(new IP("0.146.197.108"), new IP("39.20.222.120"),
                new IP("77.135.84.171"), new IP("116.132.83.77"),
                new IP("122.117.67.158"), new IP("252.29.23.0")));
        Assert.assertEquals(("(85.193.148.81 34.49.145.239 (141.255.1.133 0.146.197.108"
            + " 122.117.67.158) (231.189.0.127 39.20.222.120"
            + " 77.135.84.171 116.132.83.77 252.29.23.0))"), network.toString(root));
        Assert.assertEquals((levels.size() - 1), network.getHeight(root));
        Assert.assertEquals(levels, network.getLevels(root));
        Assert.assertEquals(List
            .of(new IP("141.255.1.133"), new IP("85.193.148.81"),
                new IP("231.189.0.127")), network.getRoute(new IP("141.255.1.133"),
                new IP("231.189.0.127")));

        // "Change" root and call getHeight again
        root = new IP("34.49.145.239");
        levels = List.of(List.of(root), List.of(new IP("85.193.148.81")),
            List.of(new IP("141.255.1.133"), new IP("231.189.0.127")),
            List.of(new IP("0.146.197.108"), new IP("39.20.222.120"),
                new IP("77.135.84.171"), new IP("116.132.83.77"),
                new IP("122.117.67.158"), new IP("252.29.23.0")));
        Assert.assertEquals((levels.size() - 1), network.getHeight(root));
        // Remove edge and list tree afterwards
        Assert.assertTrue(network.disconnect(new IP("85.193.148.81"),
            new IP("34.49.145.239")));
        Assert.assertEquals(List.of(new IP("0.146.197.108"), new IP("39.20.222.120"),
            new IP("77.135.84.171"), new IP("85.193.148.81"),
            new IP("116.132.83.77"), new IP("122.117.67.158"),
            new IP("141.255.1.133"), new IP("231.189.0.127"),
            new IP("252.29.23.0")), network.list());
    }
}
