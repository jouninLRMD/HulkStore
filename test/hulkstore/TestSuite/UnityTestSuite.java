package hulkstore_.TestSuite;

import hulkstore_.model.dao.DaoFactory;
import static hulkstore_.model.dao.ResourceManager.setConnection;
import hulkstore_.model.dao.unity_.*;
import hulkstore_.units.UnityDeleteTest;
import hulkstore_.units.UnityEnableDisableTest;
import hulkstore_.units.UnityInsertTest;
import hulkstore_.model.dto.unity_.UnityPk;
import hulkstore_.units.UnityUpdateTest;
import java.sql.SQLException;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses
({
    UnityInsertTest.class,
    UnityUpdateTest.class,
    UnityDeleteTest.class,
    UnityEnableDisableTest.class
})
public class UnityTestSuite
{
    private static final UnityDao UNITYDAO = DaoFactory.createUnityDao();
    
    @BeforeClass
    public static void connect()
    {
        try { setConnection(); }
        catch (SQLException exception) { fail(exception.getMessage()); }
    }
    
    @AfterClass
    public static void deleteDataTest()
    {
        try {            
            assertTrue(UNITYDAO.delete(new UnityPk(999999)));
            assertTrue(UNITYDAO.delete(new UnityPk(999998)));
            
        } catch (UnityDaoException exception) { fail(exception.getMessage()); }
    }    
}