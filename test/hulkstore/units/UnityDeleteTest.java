package hulkstore_.units;

import hulkstore_.model.dto.unity_.UnityDto;
import hulkstore_.model.dao.unity_.*;
import hulkstore_.model.dao.DaoFactory;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class UnityDeleteTest {
    
    private static final UnityDao UNITYDAO = DaoFactory.createUnityDao();
    private int unity_Id;
    private String unity_Description;
    private short state;
    
    public UnityDeleteTest(int unity_Id, String unity_Description, short state)
    {
        this.unity_Id = unity_Id;
        this.unity_Description = unity_Description;
        this.state = state;
    }
    
    @Parameterized.Parameters
    public static Collection data()
    {
        return  Arrays.asList(new Object[][]
        {
            {999999, "Delete 1", (short) 3},
            {999998, "Delete 2", (short) 3}
        });
    }
    
    @Test
    public void testDelete()
    {
        try {
            UnityDto unity_Dto = new UnityDto(unity_Id, unity_Description, state);          
            
            assertTrue(UNITYDAO.update(unity_Dto.createPk(), unity_Dto));
            
        } catch (UnityDaoException exception) { fail(exception.getMessage()); }
    }  
}