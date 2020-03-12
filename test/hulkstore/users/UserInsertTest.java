package hulkstore_.users;

import hulkstore_.model.dto.users.UsersDto;
import hulkstore_.model.dao.users.*;
import hulkstore_.model.dao.DaoFactory;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class UserInsertTest
{    
    private static final UsersDao USERSDAO = DaoFactory.createUsersDao();
    private int userId;
    private String user_Name;
    private String userPass;
    private String identification;
    private String realName;
    private String surname;
    private short userProfile;
    private short state;
    private int expected;

    public UserInsertTest(int userId, String user_Name, String userPass, String identification, String realName, String surname, short userProfile, short state)
    {
        this.userId = userId;
        this.user_Name = user_Name;
        this.userPass = userPass;
        this.identification = identification;
        this.realName = realName;
        this.surname = surname;
        this.userProfile = userProfile;
        this.state = state;
        this.expected = this.userId;
    }
    
    @Parameterized.Parameters
    public static Collection data()
    {
        return  Arrays.asList(new Object[][]
        {
            {999999, "TestAdmin", "Test 1", "61914591", "Test", "Insert", (short) 1, (short) 1},
            {999998, "TestUser", "Test 2", "61914591", "Test", "Insert", (short) 0, (short) 1}
        });
    }
    
    @Test
    public void testInsert()
    {
        try {
            UsersDto usersDto = new UsersDto(userId, user_Name, userPass, identification, realName, surname, userProfile, state);          
            
            assertEquals(expected, USERSDAO.insert(usersDto).getUserId());      
            
        } catch (UsersDaoException exception) { fail(exception.getMessage()); }
    }
}