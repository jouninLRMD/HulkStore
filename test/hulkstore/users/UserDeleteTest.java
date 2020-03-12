package hulkstore_.users;

import hulkstore_.model.dto.users.UsersDto;
import hulkstore_.model.dao.users.*;
import hulkstore_.model.dao.DaoFactory;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class UserDeleteTest
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
    
    public UserDeleteTest(int userId, String user_Name, String userPass, String identification, String realName, String surname, short userProfile, short state)
    {
        this.userId = userId;
        this.user_Name = user_Name;
        this.userPass = userPass;
        this.identification = identification;
        this.realName = realName;
        this.surname = surname;
        this.userProfile = userProfile;
        this.state = state;
    }
    
    @Parameterized.Parameters
    public static Collection data()
    {
        return  Arrays.asList(new Object[][]
        {
            {999999, "TestAdmin", "Test 1", "61914591", "Test", "Update", (short) 1, (short) 3},
            {999998, "TestUser", "Test 2", "61914591", "Test", "Update", (short) 0, (short) 3}
        });
    }
    
    @Test
    public void testUpdate()
    {
        try {
            UsersDto usersDto = new UsersDto(userId, user_Name, userPass, identification, realName, surname, userProfile, state);          
            
            assertTrue(USERSDAO.update(usersDto.createPk(), usersDto));
            
        } catch (UsersDaoException exception) { fail(exception.getMessage()); }
    } 
}