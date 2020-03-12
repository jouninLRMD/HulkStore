package hulkstore_.users;

import hulkstore_.model.dao.users.*;
import hulkstore_.model.dao.DaoFactory;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class UserLoginTest 
{    
    private static final UsersDao USERSDAO = DaoFactory.createUsersDao();
    private String user_Name;
    private String userPass;
    
    public UserLoginTest(String user_Name, String userPass)
    {
        this.user_Name = user_Name;
        this.userPass = userPass;
    }
    
    @Parameterized.Parameters
    public static Collection data()
    {
        return  Arrays.asList(new Object[][]
        {
            {"TestAdmin", "Test 1"},
            {"TestUser", "Test 2"}
        });
    }
    
    @Test
    public void testLogin()
    {
        try { assertNotNull(USERSDAO.validateUser(user_Name, userPass)); }
        catch (UsersDaoException exception) { fail(exception.getMessage()); }
    }
}