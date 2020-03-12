package hulkstore_.TestSuite;

import hulkstore_.model.dao.document.DocumentDao;
import hulkstore_.model.dto.document.DocumentDto;
import hulkstore_.model.dto.document.DocumentPk;
import hulkstore_.model.dao.DaoException;
import hulkstore_.model.dao.DaoFactory;
import static hulkstore_.model.dao.ResourceManager.setConnection;
import hulkstore_.model.dao.inventory_.KardexDao;
import hulkstore_.model.dao.inventory_.KardexDetailDao;
import hulkstore_.inventory_.KardexDetailInsertTest;
import hulkstore_.model.dto.inventory_.KardexDetailPk;
import hulkstore_.inventory_.KardexInsertTest;
import hulkstore_.model.dto.inventory_.KardexPk;
import hulkstore_.model.dao.product_.ProductDao;
import hulkstore_.model.dto.product_.ProductDto;
import hulkstore_.model.dto.product_.ProductPk;
import hulkstore_.model.dao.store_.StoreDao;
import hulkstore_.model.dto.store_.*;
import hulkstore_.model.dao.unity_.UnityDao;
import hulkstore_.model.dto.unity_.*;
import hulkstore_.model.dao.users.UsersDao;
import hulkstore_.model.dto.users.*;
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
    KardexInsertTest.class,
    KardexDetailInsertTest.class
})
public class KardexTestSuite 
{   
    private static final UsersDao USERSDAO = DaoFactory.createUsersDao();
    private static final StoreDao STOREDAO = DaoFactory.createStoreDao();
    private static final DocumentDao DOCUMENTDAO = DaoFactory.createDocumentDao();
    private static final UnityDao UNITYDAO = DaoFactory.createUnityDao();
    private static final ProductDao PRODUCTDAO = DaoFactory.createProductDao();
    private static final KardexDao INVENTORYDAO = DaoFactory.createKardexDao();
    private static final KardexDetailDao INVENTORYDETAILDAO = DaoFactory.createKardexDetailDao();
    
    @BeforeClass
    public static void connect()
    {
        try { setConnection(); }
        catch (SQLException exception) { fail(exception.getMessage()); }
        
        try {
            UsersDto usersDto = new UsersDto(999999, "User Test", "Test", "61914591", "Test", "Test", (short) 1, (short) 1);
            StoreDto store_Dto = new StoreDto(999999, "Store Test", "Test", (short) 1);
            DocumentDto documentDto = new DocumentDto(999999, "Document Test", (short) 1);       
            UnityDto unity_Dto = new UnityDto(999999, "Unity Test 1", (short) 1);
            ProductDto product_Dto = new ProductDto(999999, "Product Test", 999999, (short) 1);             
            
            USERSDAO.insert(usersDto);
            STOREDAO.insert(store_Dto); 
            DOCUMENTDAO.insert(documentDto);
            UNITYDAO.insert(unity_Dto); 
            PRODUCTDAO.insert(product_Dto); 
            
        } catch (DaoException exception) { fail(exception.getMessage()); }
    }
    
    @AfterClass
    public static void deleteDataTest()
    {
        try {         
            assertTrue(INVENTORYDETAILDAO.delete(new KardexDetailPk(999999, 999999, 999999)));
            assertTrue(INVENTORYDETAILDAO.delete(new KardexDetailPk(999998, 999999, 999999)));
            assertTrue(INVENTORYDETAILDAO.delete(new KardexDetailPk(999997, 999999, 999999)));
            assertTrue(INVENTORYDAO.delete(new KardexPk(999999, 999999)));
            
            assertTrue(USERSDAO.delete(new UsersPk(999999)));
            assertTrue(PRODUCTDAO.delete(new ProductPk(999999)));
            assertTrue(UNITYDAO.delete(new UnityPk(999999)));
            assertTrue(STOREDAO.delete(new StorePk(999999)));
            assertTrue(DOCUMENTDAO.delete(new DocumentPk(999999)));
            
        } catch (DaoException exception) { fail(exception.getMessage()); }
    }
}