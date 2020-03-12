package hulkstore_.controller.document;

import hulkstore_.model.dao.DaoFactory;
import hulkstore_.model.dao.document.DocumentDao;
import hulkstore_.model.dao.document.DocumentDaoException;
import hulkstore_.model.dto.document.DocumentDto;
import hulkstore_.view.document.UIInsertDocument;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * Document Insertion Controller
 * 
 * Receive and validate data on a new document record
 *  
 * @author Luis Mercado
 * @version 0.1
 * @since 2020-03-11
 */
public final class CInsertDocument
{
    private final UIInsertDocument window;
    private final DocumentDao documentDao = DaoFactory.createDocumentDao();
    
    /**
     * Empty Contructor.
     */
    public CInsertDocument()
    {
        window = new UIInsertDocument(this);
    }
    
    /**
     * Upload the document_Id to the form.
     * 
     * @param txtDocumentId 
     */
    public void upload(JTextField txtDocumentId) {
        
        try { txtDocumentId.setText(documentDao.findNextDocumentId()); }
        catch (DocumentDaoException exeption) {}
    }

    /**
     * Register the new document.
     * 
     * @param txtDocumentId
     * @param txtDescription 
     */
    public void accept(JTextField txtDocumentId, JTextField txtDescription) 
    {      
        try {
            DocumentDto dto = new DocumentDto(Integer.parseInt(txtDocumentId.getText()), txtDescription.getText());
            
            if(!documentDao.insert(dto).isDocumentIdNull()){
                JOptionPane.showMessageDialog(null, "Se ha agregado el registro nuevo", "INSERCION", JOptionPane.INFORMATION_MESSAGE);
                CDocument cDocument = new CDocument();
                window.dispose();
                
            } else { JOptionPane.showMessageDialog(null, "No se registro", "ERROR", JOptionPane.ERROR_MESSAGE); }
            
        } catch (DocumentDaoException ex) {}
    }

    /**
     * Cancel the operation and return to the documents menu.
     */
    public void cancel()
    {
        CDocument cDocument = new CDocument();
        window.dispose();
    }    
}