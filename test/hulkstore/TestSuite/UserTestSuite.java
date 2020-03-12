package hulkstore_.TestSuite;

import hulkstore_.model.dao.DaoFactory;
import static hulkstore_.model.dao.ResourceManager.setConnection;
import hulkstore_.users.UserDeleteTest;
import hulkstore_.users.UserInsertTest;
import hulkstore_.users.UserLoginTest;
import hulkstore_.users.UserUpdateTest;
import hulkstore_.model.dao.users.*;
import hulkstore_.model.dto.users.UsersPk;
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
    UserInsertTest.class,
    UserLoginTest.class,
    UserUpdateTest.class,
    UserDeleteTest.class
})
public class UserTestSuite
{
    private static final UsersDao USERSDAO = DaoFactory.createUsersDao();
    
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
            assertTrue(USERSDAO.delete(new UsersPk(999999)));
            assertTrue(USERSDAO.delete(new UsersPk(999998)));
            
        } catch (UsersDaoException exception) { fail(exception.getMessage()); }
    }  
}