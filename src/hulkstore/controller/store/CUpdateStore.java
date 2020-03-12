package hulkstore_.controller.store_;

import hulkstore_.model.dao.store_.*;
import hulkstore_.model.dao.DaoFactory;
import hulkstore_.model.dto.store_.StoreDto;
import hulkstore_.view.store_.UIUpdateStore;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * Store Modification Controller
 * 
 * Load data from the selected store_, receive new values and validate them
 *  
 * @author Luis Mercado
 * @version 0.1
 * @since 2020-03-11
 */
public final class CUpdateStore
{
    private UIUpdateStore window;
    private StoreDao store_Dao = DaoFactory.createStoreDao();
    private StoreDto store_Dto;
    
    /**
     * Constructor.
     * 
     * @param store_Id 
     */
    public CUpdateStore(int store_Id)
    {
        try {
            store_Dto = store_Dao.findByPrimaryKey(store_Id);            
            window = new UIUpdateStore(this);
            
        } catch (StoreDaoException exception) {}       
    }

    /**
     * Upload the store_ information to the form.
     * 
     * @param txtstore_Id
     * @param txtStoreName
     * @param txtAddress 
     */
    public void upload(JTextField txtstore_Id, JTextField txtStoreName, JTextField txtAddress) {
        txtstore_Id.setText(String.valueOf(store_Dto.getStoreId()));
        txtStoreName.setText(store_Dto.getStoreName());
        txtAddress.setText(store_Dto.getAddress());
    }

    /**
     * Modify the store_.
     * 
     * @param txtStoreName
     * @param txtAddress 
     */
    public void accept(JTextField txtStoreName, JTextField txtAddress) {
        try {
            store_Dto.setStoreName(txtStoreName.getText());
            store_Dto.setAddress(txtAddress.getText());
            
            if(store_Dao.update(store_Dto.createPk(), store_Dto)){
                JOptionPane.showMessageDialog(null, "Se ha modificado el registro", "MODIFICACIÓN", JOptionPane.INFORMATION_MESSAGE);
                CStore cStore = new CStore();
                window.dispose();
                
            } else { JOptionPane.showMessageDialog(null, "No se ha modificado el registro", "ERROR", JOptionPane.ERROR_MESSAGE); }
            
        } catch (StoreDaoException exception) {}
    }

    /**
     * Cancel the operation and return to the store_s menu.
     */
    public void cancel() {
        CStore cStore = new CStore();
        window.dispose();
    }    
}