package edu.kit.informatik.tests;

import edu.kit.informatik.tests.ip.IPTests;
import edu.kit.informatik.tests.network.NetworkTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    NetworkTests.class,
    IPTests.class,
    ExampleInteractionTest.class
})
public class Tests {
}
