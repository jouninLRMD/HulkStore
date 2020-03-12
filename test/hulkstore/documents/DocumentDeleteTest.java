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
public class DocumentDeleteTest
{
    private static final DocumentDao DOCUMENTDAO = DaoFactory.createDocumentDao();
    private int document_Id;
    private String documentDescription;
    private short state;

    public DocumentDeleteTest(int document_Id, String documentDescription, short state)
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
            {999999, "Delete 1", (short) 3},
            {999998, "Delete 2", (short) 3}
        });
    }

    @Test
    public void testDelete()
    {
        try {
            DocumentDto documentDto = new DocumentDto(document_Id, documentDescription, state);

            assertTrue(DOCUMENTDAO.update(documentDto.createPk(), documentDto));

        } catch (DocumentDaoException exception) { fail(exception.getMessage()); }
    }
}