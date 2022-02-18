package edu.kit.informatik.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    ParserExceptionTests.class,
    ParserNestedTests.class,
    ParserOneLevelTests.class,
})
public class Tests {
}
