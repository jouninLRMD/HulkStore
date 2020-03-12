package hulkstore_.controller.product_;

import hulkstore_.model.dao.product_.*;
import hulkstore_.model.dao.DaoFactory;
import hulkstore_.model.dto.product_.ProductDto;
import hulkstore_.model.dao.unity_.*;
import hulkstore_.model.dto.unity_.UnityDto;
import hulkstore_.view.product_.UIUpdateProduct;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * Product Modification Controller.
 * 
 * Load data of the selected product_, receive new values and validate them
 *  
 * @author Luis Mercado
 * @version 0.1
 * @since 2020-03-10
 */
public final class CUpdateProduct
{
    private UIUpdateProduct window;
    private final ProductDao product_Dao = DaoFactory.createProductDao();
    private ProductDto product_;    
    private UnityDto[] units;
    
    /**
     * Constructor.
     * 
     * @param product_Id 
     */
    public CUpdateProduct(int product_Id)
    {
        try {            
            product_ = product_Dao.findByPrimaryKey(product_Id);
            
            UnityDao unity_Dao = DaoFactory.createUnityDao();
            units = unity_Dao.findWhereStateEquals((short) 1);
            
            window = new UIUpdateProduct(this);
            
        } catch (ProductDaoException | UnityDaoException exception) {}
    }

    /**
     * Upload the product_ information to the form.
     * 
     * @param txtProductId
     * @param txtProductName
     * @param txtUnityId
     * @param cmbUnity 
     */
    public void upload(JTextField txtProductId, JTextField txtProductName, JTextField txtUnityId, JComboBox cmbUnity)
    {
        txtProductId.setText(String.valueOf(product_.getProductId()));
        txtProductName.setText(product_.getProductName());
        txtUnityId.setText(String.valueOf(product_.getUnityId()));
        
        for(UnityDto unity_ : units)
        {
            cmbUnity.addItem(unity_.getUnityDescription());
            
            if(unity_.getUnityId() == product_.getUnityId()){
                cmbUnity.setSelectedItem(unity_.getUnityDescription());
            }
        }
    }

    /**
     * Cancel the operation and return to the product_s menu.
     */
    public void cancel() 
    {
        CProduct cProduct = new CProduct();
        window.dispose();
    }

    /**
     * Get the id of the selected unity_.
     * 
     * @param cmbUnity
     * @param txtUnityId 
     */
    public void seeUnity(JComboBox cmbUnity, JTextField txtUnityId) 
    {
        for(UnityDto unity_ : units) {
            if(unity_.getUnityDescription().equals(cmbUnity.getSelectedItem().toString())) {
                txtUnityId.setText(String.valueOf(unity_.getUnityId()));
                break;
            }
        }
    }

    /**
     * Modify the product_.
     * 
     * @param txtProductId
     * @param txtProductName
     * @param txtUnityId 
     */
    public void accept(JTextField txtProductId, JTextField txtProductName, JTextField txtUnityId)
    {                
        try {
            product_.setProductName(txtProductName.getText());
            product_.setUnityId(Integer.parseInt(txtUnityId.getText())); 
            
            if(product_Dao.update(product_.createPk(), product_))
            {
                JOptionPane.showMessageDialog(null, "Se ha modificado el registro", "MODIFICACIÓN", JOptionPane.INFORMATION_MESSAGE);
                CProduct cProduct = new CProduct();
                window.dispose();
            
            } else { JOptionPane.showMessageDialog(null, "No se ha modificado el registro", "ERROR", JOptionPane.ERROR_MESSAGE); }
        
        } catch (ProductDaoException exception) {}
    }    
}