package hulkstore_.TestSuite;

import hulkstore_.model.dao.DaoException;
import hulkstore_.model.dao.DaoFactory;
import static hulkstore_.model.dao.ResourceManager.setConnection;
import hulkstore_.model.dao.product_.ProductDao;
import hulkstore_.product_s.ProductDeleteTest;
import hulkstore_.product_s.ProductEnableDisableTest;
import hulkstore_.product_s.ProductInsertTest;
import hulkstore_.model.dto.product_.ProductPk;
import hulkstore_.product_s.ProductUpdateTest;
import hulkstore_.model.dao.unity_.*;
import hulkstore_.model.dto.unity_.*;
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
    ProductInsertTest.class,
    ProductUpdateTest.class,
    ProductDeleteTest.class,
    ProductEnableDisableTest.class
})
public class ProductTestSuite {
    
    private static final ProductDao PRODUCTDAO = DaoFactory.createProductDao();
    private static final UnityDao UNITYDAO = DaoFactory.createUnityDao();
    
    @BeforeClass
    public static void connect()
    {
        try { setConnection(); }
        catch (SQLException exception) { fail(exception.getMessage()); }
        
        try {
            UnityDto unity_Dto0 = new UnityDto(999999, "Unity Test 1", (short) 1);          
            UnityDto unity_Dto1 = new UnityDto(999998, "Unity Test 2", (short) 1);          
            
            UNITYDAO.insert(unity_Dto0); 
            UNITYDAO.insert(unity_Dto1); 
            
        } catch (UnityDaoException exception) { fail(exception.getMessage()); }
    }
    
    @AfterClass
    public static void deleteDataTest()
    {
        try {            
            assertTrue(PRODUCTDAO.delete(new ProductPk(999999)));
            assertTrue(PRODUCTDAO.delete(new ProductPk(999998)));
            
            assertTrue(UNITYDAO.delete(new UnityPk(999999)));
            assertTrue(UNITYDAO.delete(new UnityPk(999998)));
            
        } catch (DaoException exception) { fail(exception.getMessage()); }
    }
}