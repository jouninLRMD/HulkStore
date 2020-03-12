package hulkstore_.TestSuite;

import hulkstore_.model.dao.store_.*;
import hulkstore_.model.dao.DaoFactory;
import static hulkstore_.model.dao.ResourceManager.setConnection;
import hulkstore_.store_s.StoreDeleteTest;
import hulkstore_.store_s.StoreEnableDisableTest;
import hulkstore_.store_s.StoreInsertTest;
import hulkstore_.model.dto.store_.StorePk;
import hulkstore_.store_s.StoreUpdateTest;
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
    StoreInsertTest.class,
    StoreUpdateTest.class,
    StoreDeleteTest.class,
    StoreEnableDisableTest.class
})
public class StoreTestSuite 
{    
    private static final StoreDao STOREDAO = DaoFactory.createStoreDao();
    
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
            assertTrue(STOREDAO.delete(new StorePk(999999)));
            assertTrue(STOREDAO.delete(new StorePk(999998)));
            
        } catch (StoreDaoException exception) { fail(exception.getMessage()); }
    }
}