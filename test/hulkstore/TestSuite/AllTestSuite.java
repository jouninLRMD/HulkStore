package hulkstore_.TestSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses
({
    StoreTestSuite.class,
    DocumentTestSuite.class,
    UnityTestSuite.class,
    ProductTestSuite.class,
    UserTestSuite.class,
    KardexTestSuite.class
})
public class AllTestSuite {}