package hulkstore_.controller.inventory_;

import hulkstore_.model.dao.document.DocumentDao;
import hulkstore_.model.dto.document.DocumentDto;
import hulkstore_.model.dao.DaoException;
import hulkstore_.model.dao.DaoFactory;
import hulkstore_.model.dao.inventory_.KardexDetailDao;
import hulkstore_.model.dto.inventory_.KardexDetailDto;
import hulkstore_.view.inventory_.UIUpdateKardexDetails;
import hulkstore_.view.menu.UIMenu;
import com.toedter.calendar.JDateChooser;
import java.util.Calendar;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Kardex detail log modification driver
 * 
 * Load, receive and validate data on an existing entry or exit movement record of a product_
 *  
 * @author Luis Mercado
 * @version 0.1
 * @since 2020-03-11
 */
public final class CUpdateKardexDetails
{
    private UIUpdateKardexDetails window;
    private DocumentDao documentDao = DaoFactory.createDocumentDao();
    private KardexDetailDao inventory_DetailDao = DaoFactory.createKardexDetailDao();
    private DocumentDto[] documents;
    private KardexDetailDto inventory_DetailDto;
    private Calendar calendar;
    
    /**
     * Builder that loads the inventory_ details.
     * 
     * @param inventory_DetailDto
     */
    public CUpdateKardexDetails(KardexDetailDto inventory_DetailDto)
    {
        try {
            this.documents = documentDao.findWhereStateEquals((short) 1);
            this.inventory_DetailDto = inventory_DetailDto;
            
            window = new UIUpdateKardexDetails(this);
        } catch (DaoException exception) {}
    }
    
    /**
     * Load the inventory_ details.
     * 
     * @param txtDetailId
     * @param txtProductId
     * @param txtStoreId
     * @param dtcDate
     * @param txtDocumentId
     * @param cmbDocumentDescription
     * @param txtDocumentNumber
     * @param cmbOperation
     * @param txtQuantity
     * @param txtUnityValue
     * @param txtTotalValue
     * @param txaObservations 
     */
    public void upload(JTextField txtDetailId, JTextField txtProductId, JTextField txtStoreId, JDateChooser dtcDate, JTextField txtDocumentId, JComboBox cmbDocumentDescription, JTextField txtDocumentNumber, JComboBox cmbOperation, JTextField txtQuantity, JTextField txtUnityValue, JTextField txtTotalValue, JTextArea txaObservations)
    {
        txtDetailId.setText(String.valueOf(inventory_DetailDto.getDetailId()));
        txtProductId.setText(String.valueOf(inventory_DetailDto.getProductId()));
        txtStoreId.setText(String.valueOf(inventory_DetailDto.getStoreId()));
        txtDocumentId.setText(String.valueOf(inventory_DetailDto.getDocumentId()));
        txtDocumentNumber.setText(String.valueOf(inventory_DetailDto.getDocumentNumber()));
        txtQuantity.setText(String.valueOf(inventory_DetailDto.getQuantity()));
        txtUnityValue.setText(String.valueOf(inventory_DetailDto.getUnityValue()));
        txtTotalValue.setText(String.valueOf(inventory_DetailDto.getTotalValue()));
        txaObservations.setText(inventory_DetailDto.getObservations());
        
        calendar = Calendar.getInstance();
        calendar.set(inventory_DetailDto.getKardexDetailYear(), inventory_DetailDto.getKardexDetailMonth() - 1, inventory_DetailDto.getKardexDetailday());        
        dtcDate.setCalendar(calendar);
        
        int operation = 0;
        if(inventory_DetailDto.getOperation() == 1) { operation = 1; }        
        cmbOperation.setSelectedIndex(operation);        

        for(int i = 0; i < documents.length; i++) 
        {
            cmbDocumentDescription.insertItemAt(documents[i].getDocumentDescription(), i);
            
            if(documents[i].getDocumentId() == inventory_DetailDto.getDocumentId()) {
                cmbDocumentDescription.setSelectedItem(documents[i].getDocumentDescription());
            }
        }
    }
    
    /**
     * Modify inventory_ detail
     * 
     * @param txtDetailId
     * @param dtcDate
     * @param txtDocumentId
     * @param txtDocumentNumber
     * @param cmbOperation
     * @param txtQuantity
     * @param txtUnityValue
     * @param txtTotalValue
     * @param txaObservations 
     */
    public void accept(JTextField txtDetailId, JDateChooser dtcDate, JTextField txtDocumentId, JTextField txtDocumentNumber, JComboBox cmbOperation, JTextField txtQuantity, JTextField txtUnityValue, JTextField txtTotalValue, JTextArea txaObservations)
    {
        calendar = dtcDate.getCalendar();
        
        try
        {
            short operation;
            if(cmbOperation.getSelectedIndex() == 0) { operation = 0; }
            else { operation = 1; }
            
            inventory_DetailDto.setDetailId(Integer.parseInt(txtDetailId.getText()));
            inventory_DetailDto.setKardexDetailYear(calendar.get(Calendar.YEAR));
            inventory_DetailDto.setKardexDetailMonth(calendar.get(Calendar.MONTH) + 1);
            inventory_DetailDto.setKardexDetailday(calendar.get(Calendar.DATE));
            inventory_DetailDto.setUserId(UIMenu.userId);
            inventory_DetailDto.setDocumentId(Short.valueOf(txtDocumentId.getText()));
            inventory_DetailDto.setDocumentNumber(Integer.parseInt(txtDocumentNumber.getText()));
            inventory_DetailDto.setOperation(operation);
            inventory_DetailDto.setQuantity(Double.parseDouble(txtQuantity.getText()));
            inventory_DetailDto.setUnityValue(Double.parseDouble(txtUnityValue.getText()));
            inventory_DetailDto.setTotalValue(Double.parseDouble(txtTotalValue.getText()));
            inventory_DetailDto.setObservations(txaObservations.getText());
                                          
            if(inventory_DetailDao.update(inventory_DetailDto.createPk(), inventory_DetailDto)) {
                JOptionPane.showMessageDialog(null, "Se ha modificado el registro", "MODIFICACIÓN", JOptionPane.INFORMATION_MESSAGE);
                CKardex cKardex = new CKardex();
                window.dispose();
                
            } else { JOptionPane.showMessageDialog(null, "No se ha modificado el registro", "ERROR", JOptionPane.ERROR_MESSAGE); }
        
        } catch(NumberFormatException | DaoException exception) {
            JOptionPane.showMessageDialog(null, "Exception: " + exception.toString(), "Error", JOptionPane.ERROR_MESSAGE);
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