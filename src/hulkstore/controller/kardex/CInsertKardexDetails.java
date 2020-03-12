package hulkstore_.controller.inventory_;

import hulkstore_.model.dao.document.DocumentDao;
import hulkstore_.model.dao.document.DocumentDaoException;
import hulkstore_.model.dto.document.DocumentDto;
import hulkstore_.model.dao.DaoException;
import hulkstore_.model.dao.DaoFactory;
import hulkstore_.model.dao.inventory_.KardexDetailDao;
import hulkstore_.model.dao.inventory_.KardexDetailDaoException;
import hulkstore_.model.dto.inventory_.KardexDetailDto;
import hulkstore_.view.inventory_.UIInsertKardexDetails;
import hulkstore_.view.menu.UIMenu;
import com.toedter.calendar.JDateChooser;
import java.util.Calendar;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Kardex detail registration insert controller
 * 
 * Receive and validate data on a new registration of movement of entry or exit of a product_
 *  
 * @author Luis Mercado
 * @version 0.1
 * @since 2020-03-10
 */
public final class CInsertKardexDetails 
{
    private UIInsertKardexDetails window;
    private final KardexDetailDao inventory_DetailDao = DaoFactory.createKardexDetailDao();
    private DocumentDto[] documents;
    private int product_Id;
    private int store_Id; 
    
    /**
     * Builder that loads the data from the inventory_ header.
     * 
     * @param product_Id
     * @param store_Id 
     */
    public CInsertKardexDetails(int product_Id, int store_Id)
    {
        try {
            this.product_Id = product_Id;
            this.store_Id = store_Id;
            
            DocumentDao documentDao = DaoFactory.createDocumentDao();
            documents = documentDao.findWhereStateEquals((short) 1);
            window = new UIInsertKardexDetails(this);
            
        } catch (DocumentDaoException exception) {}
    }    
    
    /**
     * Load the description of active product_s, store_s ans documents in the database.
     * 
     * @param cmbDocumentDescription
     * @param txtDetailId
     * @param txtProductId
     * @param txtStoreId
     */
    public void upload(JComboBox cmbDocumentDescription, JTextField txtDetailId ,JTextField txtProductId, JTextField txtStoreId)
    {
        try {
            for(int i = 0; i < documents.length; i++)
            {  
                cmbDocumentDescription.insertItemAt(documents[i].getDocumentDescription(), i);
            }
            txtDetailId.setText(inventory_DetailDao.findNextDetailId());
            txtProductId.setText(String.valueOf(product_Id));
            txtStoreId.setText(String.valueOf(store_Id));
            
        } catch (KardexDetailDaoException exception) {}
    }
    
    /**
     * Register the new inventory_ detail.
     * 
     * @param txtDetailId
     * @param txtProductId
     * @param txtStoreId
     * @param dtcDate
     * @param txtDocumentId
     * @param txtDocumentNumber
     * @param cmbOperation
     * @param txtQuantity
     * @param txtUnityValue
     * @param txtTotalValue
     * @param txaObservations
     */
    public void accept(JTextField txtDetailId, JTextField txtProductId, JTextField txtStoreId, JDateChooser dtcDate, JTextField txtDocumentId, JTextField txtDocumentNumber ,JComboBox cmbOperation, JTextField txtQuantity, JTextField txtUnityValue, JTextField txtTotalValue, JTextArea txaObservations)
    {
        Calendar c = dtcDate.getCalendar();
        
        try {
            short operation;
            if(cmbOperation.getSelectedIndex() == 0) { operation = 1; }
            else { operation = 0; }
            
            KardexDetailDto inventory_DetailDto = new KardexDetailDto(Integer.parseInt(txtDetailId.getText()),
                                                                  Short.valueOf(txtProductId.getText()),
                                                                  Short.valueOf(txtStoreId.getText()),
                                                                  c.get(Calendar.YEAR),
                                                                  c.get(Calendar.MONTH) + 1,
                                                                  c.get(Calendar.DATE),
                                                                  UIMenu.userId,
                                                                  Short.valueOf(txtDocumentId.getText()),
                                                                  Integer.parseInt(txtDocumentNumber.getText()),
                                                                  operation,
                                                                  Double.parseDouble(txtQuantity.getText()),
                                                                  Double.parseDouble(txtUnityValue.getText()),
                                                                  Double.parseDouble(txtTotalValue.getText()),
                                                                  txaObservations.getText()
                    
            );
                      
            if(!inventory_DetailDao.insert(inventory_DetailDto).isDetailIdNull()) {
                JOptionPane.showMessageDialog(null, "Se ha agregado el registro nuevo", "INSERCION", JOptionPane.INFORMATION_MESSAGE);
                CKardex cKardex = new CKardex();
                window.dispose();
                
            } else { JOptionPane.showMessageDialog(null, "No se registro", "ERROR", JOptionPane.ERROR_MESSAGE); }
            
        } catch(NumberFormatException | DaoException exception) {
            JOptionPane.showMessageDialog(null, "Cantidad o Valor Total inválido", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Cancel the operation and return to the inventory_ menu.
     */
    public void cancel()
    {
        try {
            CKardex cKardex = new CKardex();
            window.dispose();
            
        } catch (DaoException exception) {}
    }
    
    /**
     * Get the id of the selected document.
     * 
     * @param cmbDocumentDescription
     * @param txtDocumentId
     */
    public void seeDocument(JComboBox cmbDocumentDescription, JTextField txtDocumentId)
    {
        txtDocumentId.setText(String.valueOf(documents[cmbDocumentDescription.getSelectedIndex()].getDocumentId()));
    }
    
    /**
     * Calculate the quantity, unit value and total_ value.
     * 
     * @param txtQuantity
     * @param txtUnitValue
     * @param txtTotalValue
     * @param field 
     */
    public void calculate(JTextField txtQuantity, JTextField txtUnitValue, JTextField txtTotalValue, int field)
    {
        boolean validQuantity    = !(txtQuantity.getText().length() == 0);
        boolean validUnitValue = !(txtUnitValue.getText().length() == 0);
        boolean validTotalValue = !(txtTotalValue.getText().length() == 0);
        
        double quantity  = 0;
        double unitValue = 0;
        double total_Value = 0;
        
        try { quantity = Double.parseDouble(txtQuantity.getText()); }
        catch(NumberFormatException e) { validQuantity = false; }
        
        try { unitValue = Double.parseDouble(txtUnitValue.getText()); }
        catch(NumberFormatException e) { validUnitValue = false; }
        
        try { total_Value = Double.parseDouble(txtTotalValue.getText()); }
        catch(NumberFormatException e) { validTotalValue = false; }
             
        if(validQuantity && validUnitValue && field != 3) {
            total_Value = roundOutDecimals((quantity * unitValue), 2);
            txtTotalValue.setText(String.valueOf(total_Value));
        
        } else if(validQuantity && validTotalValue && field != 2) {
            unitValue = roundOutDecimals((total_Value / quantity), 2);
            if(!Double.isFinite(unitValue)) { unitValue = 0; }
            txtUnitValue.setText(String.valueOf(unitValue));
            
        } else if(validUnitValue && validTotalValue && field != 1) {
            quantity = roundOutDecimals((total_Value / unitValue), 2);
            if(!Double.isFinite(quantity)) { quantity = 0; }
            txtQuantity.setText(String.valueOf(quantity));
        }
    }
    
    /**
     * round the decimals of a number according to the specified quantity.
     * 
     * @param initialValue
     * @param decimalNumbers
     * @return 
     */
    private double roundOutDecimals(double initialValue, int decimalNumbers) {
        double integerPart;
        double result;
        
        result = initialValue;
        integerPart = Math.floor(result);
        
        result = (result-integerPart) * Math.pow(10, decimalNumbers);
        result = Math.round(result);
        result = (result/Math.pow(10, decimalNumbers))+integerPart;
        
        return result;
    }        
}