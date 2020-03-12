package hulkstore_.controller.store_;

import hulkstore_.model.dao.store_.*;
import hulkstore_.model.dao.DaoFactory;
import hulkstore_.model.dto.store_.StoreDto;
import hulkstore_.view.store_.UIInsertStore;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * Store Insertion Controller.
 * 
 * Receive and validate data about a new store_ registration.
 *  
 * @author Luis Mercado
 * @version 0.1
 * @since 2020-03-11
 */
public final class CInsertStore
{
    private final UIInsertStore window;
    private final StoreDao store_Dao = DaoFactory.createStoreDao();
    
    /**
     * Empty Constructor.
     */
    public CInsertStore() { window = new UIInsertStore(this); }

    /**
     * Upload the store_ information to the form.
     * 
     * @param txtStoreId 
     */
    public void upload(JTextField txtStoreId) 
    {        
        try { txtStoreId.setText(store_Dao.findNextStoreId()); }
        catch (StoreDaoException exception) {}
    }

    /**
     * Register the new store_.
     * 
     * @param txtStoreId
     * @param txtStoreName
     * @param txtAddress 
     */
    public void accept(JTextField txtStoreId, JTextField txtStoreName, JTextField txtAddress)
    {                
        try {
            StoreDto dto = new StoreDto(Integer.parseInt(txtStoreId.getText()), txtStoreName.getText(), txtAddress.getText());
            
            if(!store_Dao.insert(dto).isStoreIdNull()){
                JOptionPane.showMessageDialog(null, "Se ha agregado el registro nuevo", "INSERCION", JOptionPane.INFORMATION_MESSAGE);
                CStore cStore = new CStore();
                window.dispose();
                
            } else { JOptionPane.showMessageDialog(null, "No se registro", "ERROR", JOptionPane.ERROR_MESSAGE); }
            
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