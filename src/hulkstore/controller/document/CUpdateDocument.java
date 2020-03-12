package hulkstore_.controller.document;

import hulkstore_.model.dao.document.DocumentDao;
import hulkstore_.model.dao.document.DocumentDaoException;
import hulkstore_.model.dto.document.DocumentDto;
import hulkstore_.model.dao.DaoFactory;
import hulkstore_.view.document.UIUpdateDocument;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * Document Modification Controller
 * 
 * Load data from the selected document, receive new values and validate them
 *  
 * @author Luis Mercado
 * @version 0.1
 * @since 2020-03-11
 */
public final class CUpdateDocument
{
    private UIUpdateDocument window;
    private final DocumentDao documentDao = DaoFactory.createDocumentDao();
    private DocumentDto documentDto;
    
    /**
     * Constructor.
     * 
     * @param document_Id 
     */
    public CUpdateDocument(int document_Id)
    {
        try {
            documentDto = documentDao.findByPrimaryKey(document_Id);
            window = new UIUpdateDocument(this);
            
        } catch (DocumentDaoException e) {}
    }

    /**
     * Upload the document_Id to the form.
     * 
     * @param txtDocumentId
     * @param txtDescription 
     */
    public void upload(JTextField txtDocumentId, JTextField txtDescription)
    {
        txtDocumentId.setText(String.valueOf(documentDto.getDocumentId()));
        txtDescription.setText(documentDto.getDocumentDescription());
    }

    /**
     * Modify the document.
     * 
     * @param txtDescription 
     */
    public void accept(JTextField txtDescription) {
        
        try {
            documentDto.setDocumentDescription(txtDescription.getText());
            
            if(documentDao.update(documentDto.createPk(), documentDto))
            {
                JOptionPane.showMessageDialog(null, "Se ha modificado el registro", "MODIFICACIÓN", JOptionPane.INFORMATION_MESSAGE);
                CDocument cDocument = new CDocument();
                window.dispose();
                
            } else { JOptionPane.showMessageDialog(null, "No se ha modificado el registro", "ERROR", JOptionPane.ERROR_MESSAGE); }
            
        } catch (DocumentDaoException e) {}
    }

    /**
     * Cancel the operation and return to the documents menu.
     */
    public void cancel() {
        CDocument cDocument = new CDocument();
        window.dispose();
    }    
}