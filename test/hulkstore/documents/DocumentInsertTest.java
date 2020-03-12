package hulkstore_.documents;

import hulkstore_.model.dto.document.DocumentDto;
import hulkstore_.model.dao.document.DocumentDaoException;
import hulkstore_.model.dao.document.DocumentDao;
import hulkstore_.model.dao.DaoFactory;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class DocumentInsertTest
{
    private static final DocumentDao DOCUMENTDAO = DaoFactory.createDocumentDao();
    private int document_Id;
    private String documentDescription;
    private short state;
    private int expected;

    public DocumentInsertTest(int document_Id, String documentDescription, short state)
    {
        this.document_Id = document_Id;
        this.documentDescription = documentDescription;
        this.state = state;
        this.expected = this.document_Id;
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
            DocumentDto documentDto = new DocumentDto(document_Id, documentDescription, state);

            assertEquals(expected, DOCUMENTDAO.insert(documentDto).getDocumentId());

        } catch (DocumentDaoException exception) { fail(exception.getMessage()); }
    }
}