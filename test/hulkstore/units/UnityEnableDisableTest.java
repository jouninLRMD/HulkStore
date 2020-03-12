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
public class UnityEnableDisableTest
{    
    private static final UnityDao UNITYDAO = DaoFactory.createUnityDao();
    private int unity_Id;
    private String unity_Description;
    private short state;
    
    public UnityEnableDisableTest(int unity_Id, String unity_Description, short state)
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
            {999999, "Enable 1", (short) 1},
            {999999, "Disable 1", (short) 0},
            {999998, "Enable Test 2", (short) 1},
            {999998, "Disable 2", (short) 0}
        });
    }
    
    @Test
    public void testEnableDisable()
    {
        try {
            UnityDto unity_Dto = new UnityDto(unity_Id, unity_Description, state);          
            
            assertTrue(UNITYDAO.update(unity_Dto.createPk(), unity_Dto));
            
        } catch (UnityDaoException exception) { fail(exception.getMessage()); }
    } 
}