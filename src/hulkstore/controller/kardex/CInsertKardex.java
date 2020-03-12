package hulkstore_.controller.inventory_;

import hulkstore_.model.dao.store_.*;
import hulkstore_.model.dao.DaoException;
import hulkstore_.model.dao.DaoFactory;
import hulkstore_.model.dao.inventory_.KardexDao;
import hulkstore_.model.dto.inventory_.KardexDto;
import hulkstore_.model.dao.product_.*;
import hulkstore_.model.dto.product_.ProductDto;
import hulkstore_.model.dto.store_.StoreDto;
import hulkstore_.view.inventory_.UIInsertKardex;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * Kardex insertion controller
 * 
 * Receive data about a new inventory_ record.
 *  
 * @author Luis Mercado
 * @version 0.1
 * @since 2020-03-11
 */
public final class CInsertKardex 
{
    private UIInsertKardex window;
    private ProductDto[] product_s;
    private StoreDto[] store_s;
    
    /**
     * Empty Contructor.
     */
    public CInsertKardex()
    {
        try {
            ProductDao product_Dao = DaoFactory.createProductDao();
            StoreDao store_Dao = DaoFactory.createStoreDao();
            
            product_s = product_Dao.findAll();
            store_s = store_Dao.findAll();
            
            window = new UIInsertKardex(this);
            
        } catch (ProductDaoException | StoreDaoException ex) {}
    }    
    
    /**
     * Load the description of active product_s and store_s in the database.
     * 
     * @param cmbProductName
     * @param cmbStoreName
     */
    public void upload(JComboBox cmbProductName, JComboBox cmbStoreName)
    {
        for(int i = 0; i < product_s.length; i++)
        {
            cmbProductName.insertItemAt(product_s[i].getProductName(), i);
        }
        for(int i = 0; i < store_s.length; i++)
        {
            cmbStoreName.insertItemAt(store_s[i].getStoreName(), i);
        }
    }
    
    /**
     * Register the new inventory_.
     * 
     * @param txtProductId
     * @param txtStoreId
     */
    public void accept(JTextField txtProductId, JTextField txtStoreId)
    {
        try {
            KardexDto inventory_ = new KardexDto(Integer.parseInt(txtProductId.getText()), Integer.parseInt(txtStoreId.getText()), 0, 0, 0, (short) 1);
            KardexDao inventory_Dao = DaoFactory.createKardexDao();
            
            if(!inventory_Dao.insert(inventory_).isProductIdNull()) {
                JOptionPane.showMessageDialog(null, "Se ha agregado el registro nuevo", "INSERCION", JOptionPane.INFORMATION_MESSAGE);
                CKardex cKardex = new CKardex();
                window.dispose();
                
            } else { JOptionPane.showMessageDialog(null, "No se registro", "ERROR", JOptionPane.ERROR_MESSAGE); }
            
        } catch (DaoException exception) {}
    }
    
    /**
     * Cancel the operation and return to the inventory_ menu.
     */
    public void cancel()
    {
        try {
            CKardex cKardex = new CKardex();
            window.dispose();
            
        } catch (DaoException ex) {}
    }
    
    /**
     * Get the id of the selected product_.
     * 
     * @param txtProductId
     * @param cmbProductName 
     */
    public void seeProduct(JTextField txtProductId, JComboBox cmbProductName)
    {
        txtProductId.setText(String.valueOf(product_s[cmbProductName.getSelectedIndex()].getProductId()));
    }
    
    /**
     * Get the id of the selected store_.
     * 
     * @param txtStoreId
     * @param cmbStoreName 
     */
    public void seeStore(JTextField txtStoreId, JComboBox cmbStoreName)
    {
        txtStoreId.setText(String.valueOf(store_s[cmbStoreName.getSelectedIndex()].getStoreId()));
    }
}