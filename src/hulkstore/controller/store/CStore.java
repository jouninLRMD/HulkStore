package hulkstore_.controller.store_;

import hulkstore_.model.dao.store_.*;
import hulkstore_.controller.menu.CMenu;
import hulkstore_.controller.reports.CReports;
import hulkstore_.model.dao.DaoFactory;
import hulkstore_.model.dto.store_.StoreDto;
import hulkstore_.view.store_.UIStore;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.event.KeyEvent;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * Store Management Controller
 * 
 * Load existing store_s with their data,
 * in addition to controlling the redirection to the insertion or modification windows.
 * Provides options to generate report and search records.
 * The enable, disable and delete functions are performed here.
 *  
 * @author Luis Mercado
 * @version 0.1
 * @since 2020-03-11
 */
public final class CStore
{
    private final UIStore window;
    private final StoreDao store_Dao = DaoFactory.createStoreDao();
    private StoreDto[] store_s;
    private StoreDto store_Dto;
    private DefaultTableModel defaultTableModel;
    
    /**
     * Empty Constructor.
     */
    public CStore()
    {
        try { store_s = store_Dao.findAll(); }
        catch (StoreDaoException exception) {}
        
        window = new UIStore(this);
    }    
    
    /**
     * Upload the store_s registered in the database to the form table and set their status.
     * 
     * @param tblStore
     * @param txtSearch 
     */
    public void upload(JTable tblStore, JTextField txtSearch)
    {
        defaultTableModel = (DefaultTableModel) tblStore.getModel();
        defaultTableModel.setRowCount(0);
        String state;
        
        for (StoreDto store_ : store_s) 
        {
            switch (store_.getState()) {
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
            
            defaultTableModel.addRow(new Object[]{store_.getStoreId(), store_.getStoreName(), store_.getAddress(), state});
        }
    }
    
    /**
     * Depending on the status of the registered store_, the status of the chkActive control changes.
     * 
     * @param tblStore
     * @param chkActive 
     */
    public void updateState(JTable tblStore, JCheckBox chkActive) 
    {
        int i = tblStore.getSelectedRow();
        
        if(i != -1)
        {
            store_Dto = store_s[i];
            
            if(store_Dto.getState() != 3)
            {                
                chkActive.setEnabled(true);
                
                if(store_Dto.getState() == 1) {
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
     * Return to the initial menu.
     */
    public void menu() {
        CMenu cMenu = new CMenu();        
        window.dispose();
    }

    /**
     * Show the form to insert a new store_.
     */
    public void insert() {
        CInsertStore cInsertStore = new CInsertStore();
        window.dispose();
    }

    /**
     * Show the form to modify a store_.
     * 
     * @param tblStore 
     */
    public void update(JTable tblStore)
    {
        int i = tblStore.getSelectedRow();
        
        if(i != -1)
        {
            store_Dto = store_s[i];
            
            if(store_Dto.getState() == 1)
            {
                CUpdateStore update = new CUpdateStore(store_Dto.getStoreId());
                window.dispose();
                
            } else { JOptionPane.showMessageDialog(null, "Solo se permite modificar registros activos", "ERROR", JOptionPane.ERROR_MESSAGE); }
            
        } else { JOptionPane.showMessageDialog(null, "Seleccione un registro a modificar", "ERROR", JOptionPane.ERROR_MESSAGE); }
    }

    /**
     * Enable or disable the status of the registered store_.
     * 
     * @param tblStore
     * @param chkActive 
     */
    public void enableDisable(JTable tblStore, JCheckBox chkActive) {
        int i = tblStore.getSelectedRow();
        
        defaultTableModel = (DefaultTableModel) tblStore.getModel();        
        
        if(i != -1) {
            store_Dto = store_s[i];
            
            if(chkActive.isSelected())
            {
                try {
                    store_Dto.setState((short) 1);
                    if(store_Dao.update(store_Dto.createPk(), store_Dto)) { defaultTableModel.setValueAt("A", i, 3); }
                    
                } catch (StoreDaoException exception) {}
            
            } else {
                try {
                    store_Dto.setState((short) 2);
                    if(store_Dao.update(store_Dto.createPk(), store_Dto)) { defaultTableModel.setValueAt("I", i, 3); }
                    
                } catch (StoreDaoException exception) {}
            }
            
        } else { JOptionPane.showMessageDialog(null, "Seleccione un registro", "ERROR", JOptionPane.ERROR_MESSAGE); }
    }

    /**
     * Change the status of a registered store_ to deleted.
     * 
     * @param tblStore
     * @param chkActive 
     */
    public void delete(JTable tblStore, JCheckBox chkActive) {
        int i = tblStore.getSelectedRow();
        
        if(i != -1)
        {
            store_Dto = store_s[i];
            if(store_Dto.getState() != 3)
            {
                if(JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar el registro?", "Eliminar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                {                    
                    try {
                        store_Dto.setState((short) 3);
                        if(store_Dao.update(store_Dto.createPk(), store_Dto)){
                            defaultTableModel = (DefaultTableModel) tblStore.getModel();
                            defaultTableModel.setValueAt("*", i, 3);
                            chkActive.setEnabled(false);
                        }    
                    } catch (StoreDaoException ex) {}                    
                }
                
            } else { JOptionPane.showMessageDialog(null, "El registro ya está eliminado", "ERROR", JOptionPane.ERROR_MESSAGE); }
            
        } else { JOptionPane.showMessageDialog(null, "Seleccione un registro a eliminar", "ERROR", JOptionPane.ERROR_MESSAGE); }
    }

    /**
     * Generate the report of store_s.
     */
    public void generateReport() {
        try {
            store_s = store_Dao.getViewStore();
            
            CReports report = new CReports();
            report.generateStoreReport(store_s);
            
        } catch (StoreDaoException exception) {}
    }

    /**
     * Add the available records to the autocompleter.
     * 
     * @param filter
     * @param txtSearch
     * @param tblStore 
     */
    public void loadAutoCompleter(String filter, JTextField txtSearch, JTable tblStore)
    {        
        txtSearch.setText("");      
        
        TextAutoCompleter textAutoAcompleter = new TextAutoCompleter(txtSearch);
        textAutoAcompleter.removeAllItems();
        textAutoAcompleter.setMode(0);
        textAutoAcompleter.setCaseSensitive(false);
        
        TableModel tableModel = tblStore.getModel();        
        
        int i;
        for(i = 0; i < tableModel.getColumnCount(); i++)
        {
            if(filter.compareTo(tableModel.getColumnName(i)) == 0) { break; }
        }
        
        for(int k = 0; k < tableModel.getRowCount(); k++)
        {
            textAutoAcompleter.addItem(tableModel.getValueAt(k, i));
        }
    }

    /**
     * Select the row where the searched store_ is located.
     * 
     * @param keyCode
     * @param filter
     * @param store_
     * @param tblStore 
     */
    public void searchStore(int keyCode, String filter, String store_, JTable tblStore)
    {       
        if(keyCode == KeyEvent.VK_ENTER) {
            TableModel tableModel = tblStore.getModel();
        
            int i;
            for(i = 0; i < tableModel.getColumnCount(); i++)
            {
                if(filter.compareTo(tableModel.getColumnName(i)) == 0) { break; }
            }

            for(int k = 0; k < tableModel.getRowCount(); k++)
            {
                if(store_.compareToIgnoreCase(tableModel.getValueAt(k, i).toString()) == 0){
                    tblStore.setRowSelectionInterval(k, k);
                    break;
                    
                } else { tblStore.clearSelection(); }
            }
        }        
    }    
}