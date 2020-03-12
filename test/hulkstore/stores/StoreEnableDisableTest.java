package hulkstore_.store_s;

import hulkstore_.model.dao.store_.*;
import hulkstore_.model.dto.store_.StoreDto;
import hulkstore_.model.dao.DaoFactory;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class StoreEnableDisableTest
{    
    private static final StoreDao STOREDAO = DaoFactory.createStoreDao();
    private int store_Id;
    private String store_Name;
    private String address;
    private short state;
    
    public StoreEnableDisableTest(int store_Id, String store_Name, String address, short state) 
    {        
        this.store_Id = store_Id;
        this.store_Name = store_Name;
        this.address = address;
        this.state = state;
    }
    
    @Parameterized.Parameters
    public static Collection data()
    {
        return  Arrays.asList(new Object[][]
        {
            {999999, "Enable 1", "Test", (short) 1},
            {999999, "Disable 1", "Test", (short) 0},
            {999998, "Enable Test 2", "Test", (short) 1},
            {999998, "Disable 2", "Test", (short) 0}
        });
    }  
        
    @Test
    public void testEnableDisable()
    {
        try {
            StoreDto store_Dto = new StoreDto(store_Id, store_Name, address, state);          
            
            assertTrue(STOREDAO.update(store_Dto.createPk(), store_Dto));
            
        } catch (StoreDaoException exception) { fail(exception.getMessage()); }
    }     
}