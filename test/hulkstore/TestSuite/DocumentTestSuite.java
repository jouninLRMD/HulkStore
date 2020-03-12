package hulkstore_.TestSuite;

import hulkstore_.model.dao.document.DocumentDao;
import hulkstore_.model.dao.document.DocumentDaoException;
import hulkstore_.documents.DocumentDeleteTest;
import hulkstore_.documents.DocumentEnableDisableTest;
import hulkstore_.documents.DocumentInsertTest;
import hulkstore_.model.dto.document.DocumentPk;
import hulkstore_.documents.DocumentUpdateTest;
import hulkstore_.model.dao.DaoFactory;
import static hulkstore_.model.dao.ResourceManager.setConnection;
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
    DocumentInsertTest.class,
    DocumentUpdateTest.class,
    DocumentDeleteTest.class,
    DocumentEnableDisableTest.class
})
public class DocumentTestSuite {
    
    private static final DocumentDao DOCUMENTDAO = DaoFactory.createDocumentDao();
            
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
            assertTrue(DOCUMENTDAO.delete(new DocumentPk(999999)));
            assertTrue(DOCUMENTDAO.delete(new DocumentPk(999998)));
        
        } catch (DocumentDaoException exception) { fail(exception.getMessage()); }
    }   
}