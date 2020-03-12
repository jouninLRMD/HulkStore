package hulkstore_.controller.document;

import hulkstore_.controller.menu.CMenu;
import hulkstore_.model.dao.document.DocumentDao;
import hulkstore_.model.dao.document.DocumentDaoException;
import hulkstore_.model.dto.document.DocumentDto;
import hulkstore_.model.dao.DaoFactory;
import hulkstore_.view.document.UIDocument;
import com.mxrck.autocompleter.TextAutoCompleter;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * Document Management Controller.
 *
 * Load the existing documents with your data,
 * in addition to controlling the redirection to the insertion or modification windows.
 * Provides options to search records.
 * The enable, disable and delete functions are performed here.
 *
 * @author Luis Mercado
 * @version 0.1
 * @since 2020-03-11
 */
public final class CDocument
{
    private final UIDocument window;
    private final DocumentDao documentDao = DaoFactory.createDocumentDao();
    private DocumentDto[] documents;
    private DocumentDto documentDto;

    /**
     * Empty Contructor.
     */
    public CDocument()
    {
        try {
            documents = documentDao.findAll();

        } catch (DocumentDaoException exception) {}

        window = new UIDocument(this);
    }

    /**
     * Upload the documents registered in the database to the form table and set their status.
     *
     * @param tblDocument
     */
    public void upload(JTable tblDocument)
    {
        DefaultTableModel model = (DefaultTableModel) tblDocument.getModel();
        model.setRowCount(0);
        String state;

        for (DocumentDto document : documents) {
            switch (document.getState()) {
                case 1:
                    state = "A";
                    break;
                case 2:
                    state = "I";
                    break;
                default:
                    state = "*";
                    break;
            }
            model.addRow(new Object[]{document.getDocumentId(), document.getDocumentDescription(), state});
        }
    }

    /**
     * Depending on the status of the registered document, the status of the chkActive control changes.
     *
     * @param tblDocument
     * @param chkActive
     */
    public void updateState(JTable tblDocument, JCheckBox chkActive)
    {
        int i = tblDocument.getSelectedRow();

        if(i != -1)
        {
            documentDto = documents[i];

            if(documentDto.getState() != 3)
            {
                chkActive.setEnabled(true);

                if(documentDto.getState() == 1) {
                    chkActive.setSelected(true);}
                else {
                    chkActive.setSelected(false);}

            } else {
                chkActive.setEnabled(false);
                chkActive.setSelected(false);
            }
        } else {
            chkActive.setEnabled(false);
            chkActive.setSelected(false);
        }
    }

    /**
     * Add the available records to the autocompleter.
     *
     * @param txtSearch
     * @param tblDocument
     */
    public void loadAutoCompleter(JTextField txtSearch, JTable tblDocument) {
        TextAutoCompleter textAutoAcompleter = new TextAutoCompleter( txtSearch );
        textAutoAcompleter.setMode(0);
        textAutoAcompleter.setCaseSensitive(false);
        TableModel tableModel = tblDocument.getModel();
        String filter = "Nombre";

        int i;
        for(i = 0; i < tableModel.getColumnCount(); i++)
        {
            if(filter.compareTo(tableModel.getColumnName(i)) == 0)
                break;
        }

        for(int k = 0; k < tableModel.getRowCount(); k++)
        {
            textAutoAcompleter.addItem(tableModel.getValueAt(k, i));
        }
    }

    /**
     * Show the form to insert a new document.
     */
    public void insert()
    {
        CInsertDocument cInsertDocument = new CInsertDocument();
        window.dispose();
    }

    /**
     * Show the form to modify a document.
     *
     * @param tblDocument
     */
    public void update(JTable tblDocument)
    {
        int i = tblDocument.getSelectedRow();

        if(i != -1)
        {
            documentDto = documents[i];

            if(documentDto.getState() == 1)
            {
                CUpdateDocument update = new CUpdateDocument(documentDto.getDocumentId());
                window.dispose();

            } else { JOptionPane.showMessageDialog(null, "No permitido modificar", "ERROR", JOptionPane.ERROR_MESSAGE); }

        } else { JOptionPane.showMessageDialog(null, "Seleccione el registro a modificar", "ERROR", JOptionPane.ERROR_MESSAGE); }
    }

    /**
     * Change the status of a registered document to deleted.
     *
     * @param tblDocument
     * @param chkActive
     */
    public void delete(JTable tblDocument, JCheckBox chkActive) {
        int i = tblDocument.getSelectedRow();

        if(i != -1)
        {
            documentDto = documents[i];
            if(documentDto.getState() != 3)
            {
                if(JOptionPane.showConfirmDialog(null, "¿Seguro que deseas eliminar el registro?", "Eliminar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                {
                    try {
                        documentDto.setState((short) 3);
                        if(documentDao.update(documentDto.createPk(), documentDto)){
                            DefaultTableModel model = (DefaultTableModel) tblDocument.getModel();
                            model.setValueAt("*", i, 2);
                            chkActive.setEnabled(false);
                        } 
                    } catch (DocumentDaoException exception) {}
                }

            } else { JOptionPane.showMessageDialog(null, "El registro ya está eliminado", "ERROR", JOptionPane.ERROR_MESSAGE); }

        } else { JOptionPane.showMessageDialog(null, "Seleccione un registro a eliminar", "ERROR", JOptionPane.ERROR_MESSAGE); }
    }

    /**
     * Return to the initial menu.
     */
    public void menu()
    {
        CMenu cMenu = new CMenu();
        window.dispose();
    }

    /**
     * Enable or disable the status of the registered document.
     *
     * @param tblDocument
     * @param chkActive
     */
    public void enableDisable(JTable tblDocument, JCheckBox chkActive) {
        int i = tblDocument.getSelectedRow();

        DefaultTableModel model = (DefaultTableModel) tblDocument.getModel();

        if(i != -1) {
            documentDto = documents[i];

            if(chkActive.isSelected()) {
                try {
                    documentDto.setState((short) 1);
                    if(documentDao.update(documentDto.createPk(), documentDto)) { model.setValueAt("A", i, 2); }

                } catch (DocumentDaoException e) {}

            } else {
                try {
                    documentDto.setState((short) 2);
                    if(documentDao.update(documentDto.createPk(), documentDto)){ model.setValueAt("I", i, 2); }

                } catch (DocumentDaoException e) {}
            }

        } else { JOptionPane.showMessageDialog(null, "Seleccione un registro", "ERROR", JOptionPane.ERROR_MESSAGE); }
    }

    /**
     * Select the row where the searched document is located.
     *
     * @param txtSearch
     * @param tblDocument
     */
    public void selectRow(JTextField txtSearch, JTable tblDocument)
    {
        TableModel tableModel = tblDocument.getModel();
        String fact = txtSearch.getText();
        String filter = "Nombre";

        int column;
        int columns = tableModel.getColumnCount();

        for(column = 0; column < columns; column++)
            { if(filter.compareTo(tableModel.getColumnName(column)) == 0) { break; } }

        int row;
        try {
            int rows = tableModel.getRowCount();
            for(row = 0; row < rows; row++) {
                if(fact.compareTo((String) tableModel.getValueAt(row, column)) == 0) { break; }
            }

            if(row == 0) { tblDocument.changeSelection(0,0,false,true); }
            else { tblDocument.getSelectionModel().setSelectionInterval(row - 1, row); }

        } catch(Exception exception) { JOptionPane.showMessageDialog(null, "No se encontraron los datos buscados", "ERROR", JOptionPane.ERROR_MESSAGE); }
    }
}