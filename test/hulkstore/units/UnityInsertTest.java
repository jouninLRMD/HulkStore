package hulkstore_.units;

import hulkstore_.model.dto.unity_.UnityDto;
import hulkstore_.model.dao.unity_.*;
import hulkstore_.model.dao.DaoFactory;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class UnityInsertTest
{    
    private static final UnityDao UNITYDAO = DaoFactory.createUnityDao();
    private int unity_Id;
    private String unity_Description;
    private short state;
    private int expected;
    
    public UnityInsertTest(int unity_Id, String unity_Description, short state)
    {
        this.unity_Id = unity_Id;
        this.unity_Description = unity_Description;
        this.state = state;
        this.expected = this.unity_Id;
    }
    
    @Parameterized.Parameters
    public static Collection data()
    {
        return  Arrays.asList(new Object[][]
        {
            {999999, "Insert 1", (short) 1},
            {999998, "Insert 2", (short) 0}
        });
    }
    
    @Test
    public void testInsert()
    {
        try {
            UnityDto unity_Dto = new UnityDto(unity_Id, unity_Description, state);          
            
            assertEquals(expected, UNITYDAO.insert(unity_Dto).getUnityId());      
            
        } catch (UnityDaoException exception) { fail(exception.getMessage()); }
    }
}