package edu.kit.informatik.tests;

import edu.kit.informatik.IP;
import edu.kit.informatik.ParseException;
import org.junit.Assert;
import org.junit.Test;

public class IPTests {
    @Test
    public void testConstructor() {
        try {
            IP ip = new IP("100.100.100.100");
        }
        catch(ParseException e) {
            Assert.fail("Throw a Exception on 100.100.100.100 with " + e.toString());
        }
        try {
            IP ip = new IP("255.255.255.255");
        }
        catch(ParseException e) {
            Assert.fail("Throw a Exception on 255.255.255.255 with " + e.toString());
        }
        try {
            IP ip = new IP("000.000.000.000");
            Assert.fail("Accepted 000.000.000.000 as IP");
        }
        catch(ParseException ignored) {

        }
        try {
            IP ip1 = new IP("100.100.100.1007");
            Assert.fail("Accepted 100.100.100.1007 as IP");
        }
        catch (ParseException e) {
            //assertEquals("ParseException: String has wrong pattern", e.toString()); This is my Exception
        }
        try {
            IP ip1 = new IP("100.100.100.1B0");
            Assert.fail("Accepted 100.100.100.1B0 as IP");
        }
        catch (ParseException ignored) {

        }
        try {
            IP ip1 = new IP("100.100.100.10");
        }
        catch (ParseException e) {
            Assert.fail("Not Accepted 100.100.100.10 as IP");
        }
        try {
            IP ip1 = new IP("100.100.100.100.");
            Assert.fail("Accepted 100.100.100.100. as IP");
        }
        catch (ParseException ignored) {
        }
        try {
            IP ip1 = new IP("100.100.100.100.100");
            Assert.fail("Accepted 100.100.100.100.100 as IP");
        }
        catch (ParseException ignored) {

        }
    }


    @Test
    public void testToString() {
        String ipString = "100.100.100.100";
        try {
            IP ip = new IP(ipString);
            Assert.assertEquals(ip.toString(), ipString);
        }
        catch (ParseException e) {
            Assert.fail("Fail Test: testToStrung with: " + e.toString());
        }

    }

    @Test
    public void testCompareTo() {
        IP ip1;
        IP ip2;
        IP ip3;
        IP ip4;
        try {
            ip1 = new IP("255.124.042.253");
            Assert.fail("Address can't contain leading zeros");
        }
        catch (ParseException ignored) {

        }
        try {
            ip2 = new IP("255.124.042.252");
            Assert.fail("Address can't contain leading zeros");
        }
        catch (ParseException ignored) {

        }
        try {
            ip3 = new IP("55.012.042.252");
            Assert.fail("Address can't contain leading zeros");
        }
        catch (ParseException ignored) {

        }
        try {
            ip4 = new IP("5.124.42.022");
            Assert.fail("Address can't contain leading zeros");
        }
        catch (ParseException ignored) {

        }

        try {
            ip1 = new IP("255.124.42.253");
            ip2 = new IP("255.124.42.252");
            ip3 = new IP("55.124.42.252");
            ip4 = new IP("5.124.42.252");
        }
        catch (ParseException e) {
            Assert.fail(e.toString());
            return;
        }

        Assert.assertTrue(ip1.compareTo(ip2) > 0);
        Assert.assertTrue(ip1.compareTo(ip3) > 0);
        Assert.assertTrue(ip1.compareTo(ip4) > 0);
        Assert.assertTrue(ip2.compareTo(ip3) > 0);
        Assert.assertTrue(ip2.compareTo(ip4) > 0);
        Assert.assertTrue(ip3.compareTo(ip4) > 0);

        Assert.assertEquals(0, ip1.compareTo(ip1));
        Assert.assertEquals(0, ip2.compareTo(ip2));
        Assert.assertEquals(0, ip3.compareTo(ip3));
        Assert.assertEquals(0, ip4.compareTo(ip4));
    }
}
