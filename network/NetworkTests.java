package edu.kit.informatik.tests.network;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    NetworkAddTests.class,
    NetworkAddMultipleTests.class,
    NetworkConnectTests.class,
    NetworkContainsTests.class,
    NetworkDisconnectTests.class,
    NetworkGetHeightTests.class,
    NetworkGetRouteTests.class,
    NetworkLevelsTests.class,
    NetworkListTests.class,
    ParserExceptionTests.class,
    ParserNestedTests.class,
    ParserOneLevelTests.class,
    ParserToStringTests.class,
    NetworkEqualsTest.class
})
public class NetworkTests {
}