package hulkstore_.documents;

import hulkstore_.model.dto.document.DocumentDto;
import hulkstore_.model.dao.document.DocumentDaoException;
import hulkstore_.model.dao.document.DocumentDao;
import hulkstore_.model.dao.DaoFactory;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class DocumentEnableDisableTest
{
    private static final DocumentDao DOCUMENTDAO = DaoFactory.createDocumentDao();
    private int document_Id;
    private String documentDescription;
    private short state;

    public DocumentEnableDisableTest(int document_Id, String documentDescription, short state)
    {
        this.document_Id = document_Id;
        this.documentDescription = documentDescription;
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
            DocumentDto documentDto = new DocumentDto(document_Id, documentDescription, state);

            assertTrue(DOCUMENTDAO.update(documentDto.createPk(), documentDto));

        } catch (DocumentDaoException exception) { fail(exception.getMessage()); }
    }
}