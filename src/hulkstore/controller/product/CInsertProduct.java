package hulkstore_.controller.product_;

import hulkstore_.model.dao.product_.*;
import hulkstore_.model.dao.DaoFactory;
import hulkstore_.model.dto.product_.ProductDto;
import hulkstore_.model.dao.unity_.*;
import hulkstore_.model.dto.unity_.UnityDto;
import hulkstore_.view.product_.UIInsertProduct;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * Product Insertion Controller.
 * 
 * Receive and validate data about a new product_ registration
 * 
 * @author Luis Mercado
 * @version 0.1
 * @since 2020-03-10
 */
public final class CInsertProduct
{
    private UIInsertProduct window;
    private UnityDto[] units;
    
    /**
     * Empty Constructor.
     */
    public CInsertProduct()
    {
        try {
            UnityDao dao = DaoFactory.createUnityDao();
            units = dao.findWhereStateEquals((short) 1);
            window = new UIInsertProduct(this);
            
        } catch (UnityDaoException exception) {}
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
     * Upload the product_Id to the form.
     * 
     * @param txtProductId
     * @param cmbUnity 
     */
    public void upload(JTextField txtProductId, JComboBox cmbUnity)
    {        
        try {
            ProductDao dao = DaoFactory.createProductDao();
            txtProductId.setText(dao.findNextProductId());
            
            for(UnityDto unity_ : units)
            {
                cmbUnity.addItem(unity_.getUnityDescription());
            }
            
        } catch (ProductDaoException exception) {}
    }

    /**
     * Register the new product_.
     * 
     * @param txtProductId
     * @param txtProductName
     * @param txtUnityId 
     */
    public void accept(JTextField txtProductId, JTextField txtProductName, JTextField txtUnityId) {
        
        try {
            ProductDto dto = new ProductDto(Integer.parseInt(txtProductId.getText()), txtProductName.getText(), Integer.parseInt(txtUnityId.getText()));
            ProductDao dao = DaoFactory.createProductDao();
            
            if(!dao.insert(dto).isProductIdNull()){
                JOptionPane.showMessageDialog(null, "Se ha agregado el registro nuevo", "INSERCION", JOptionPane.INFORMATION_MESSAGE);
                CProduct cProduct = new CProduct();
                window.dispose();
            
            } else { JOptionPane.showMessageDialog(null, "No se registro", "ERROR", JOptionPane.ERROR_MESSAGE); }
            
        } catch (ProductDaoException exception) {}
    }

    /**
     * Get the id of the selected unity_.
     * 
     * @param cmbUnity
     * @param txtUnityId 
     */
    public void seeUnity(JComboBox cmbUnity, JTextField txtUnityId) {
        for(UnityDto unity_ : units) {
            if(unity_.getUnityDescription().equals(cmbUnity.getSelectedItem().toString())) {
                txtUnityId.setText(String.valueOf(unity_.getUnityId()));
                break;
            }
        }
    }    
}