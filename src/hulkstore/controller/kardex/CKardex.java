package hulkstore_.controller.inventory_;

import hulkstore_.model.dao.store_.*;
import hulkstore_.controller.menu.CMenu;
import hulkstore_.controller.reports.CReports;
import hulkstore_.model.dao.document.DocumentDao;
import hulkstore_.model.dao.inventory_.*;
import hulkstore_.model.dao.product_.*;
import hulkstore_.model.dao.DaoException;
import hulkstore_.model.dao.DaoFactory;
import hulkstore_.model.dto.document.DocumentDto;
import hulkstore_.model.dto.inventory_.KardexDetailDto;
import hulkstore_.model.dto.inventory_.KardexDetailView;
import hulkstore_.model.dto.inventory_.KardexDto;
import hulkstore_.model.dto.inventory_.KardexView;
import hulkstore_.model.dto.product_.ProductDto;
import hulkstore_.model.dto.store_.StoreDto;
import hulkstore_.model.dao.users.UsersDao;
import hulkstore_.model.dto.users.UsersDto;
import hulkstore_.view.inventory_.UIKardex;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 * Kardex management main controller
 * 
 * Load the product_s - existing store_s with their data,
 * in addition to controlling the redirection to the insertion or modification windows. 
 * Kardex removal is done here.
 *  
 * @author Luis Mercado
 * @version 0.1
 * @since 2020-03-11
 */
public final class CKardex
{
    private UIKardex window;
    private final KardexDao inventory_Dao = DaoFactory.createKardexDao();  
    private final KardexDetailDao inventory_DetailDao = DaoFactory.createKardexDetailDao();
    private KardexDto[] inventory_;
    private KardexDto inventory_Dto;
    private KardexDetailDto[] inventory_Details;
    private KardexDetailDto inventory_DetailDto;
    private DefaultTableModel tableModel;
    private int product_Id;
    private int store_Id;   
        
    /**
     * Empty Contructor.
     * @throws hulkstore_.model.dao.DaoException
     */
    public CKardex() throws DaoException
    {
        try 
        {          
            inventory_ = inventory_Dao.findAll();            
            window = new UIKardex(this);
            
        } catch (KardexDaoException exception) {}
    }
    
    /**
     * Upload the inventory_ registered in the database to the form table and set their status.
     * 
     * @param tblKardex 
     * @throws hulkstore_.model.dao.DaoException 
     */
    public void uploadKardex(JTable tblKardex) throws DaoException
    {
        tableModel = (DefaultTableModel) tblKardex.getModel();
        tableModel.setRowCount(0);
        
        ProductDto product_;
        ProductDao product_Dao = DaoFactory.createProductDao();
        StoreDto store_;
        StoreDao store_Dao = DaoFactory.createStoreDao();
        
	int kardezSize = inventory_.length;
        for(int i = 0; i < kardezSize; i++)
        {
            try {
                product_ = product_Dao.findByPrimaryKey(inventory_[i].getProductId());
                store_ = store_Dao.findByPrimaryKey(inventory_[i].getStoreId());
                String state;
                
                if(inventory_[i].getState() == 1) { state = "A"; }
                else { state = "*"; }
                
                tableModel.addRow(new Object[]{ product_.getProductId(),
                                           product_.getProductName(),
                                           store_.getStoreId(),
                                           store_.getStoreName(),
                                           state}
                );
                
            } catch (ProductDaoException | StoreDaoException ex) {}
        }
    }
    
    /**
     * Upload the inventory_ details registered in the database to the form table and set their status.
     * 
     * @param tblKardex
     * @param tblKardexDetails
     * @param txtQuantity
     * @param txtUnityValue
     * @param txtTotalValue
     */
    public void uploadKardexDetails(JTable tblKardex, JTable tblKardexDetails, JTextField txtQuantity, JTextField txtUnityValue, JTextField txtTotalValue)
    {
        try {
            tableModel = (DefaultTableModel) tblKardexDetails.getModel();
            tableModel.setRowCount(0);
            int i = tblKardex.getSelectedRow();
            
            txtQuantity.setText(String.valueOf(inventory_[i].getQuantity()));
            txtUnityValue.setText(String.valueOf(inventory_[i].getUnityValue()));
            txtTotalValue.setText(String.valueOf(inventory_[i].getTotalValue()));
            
            product_Id = inventory_[i].getProductId();
            store_Id = inventory_[i].getStoreId();
            inventory_Details = inventory_DetailDao.findWhereProductIdAndStoreIdEquals(product_Id, store_Id);
            
            int detailSize = inventory_Details.length;
            String operation;
            String state;
            
            for(i = 0; i < detailSize; i++) {
                if(inventory_Details[i].getOperation()== 1) { operation = "Entrada"; }
                else { operation = "Salida"; }
                
                if(inventory_Details[i].getState()== 1) { state = "A"; }
                else { state = "*"; }
                
                tableModel.addRow(new Object[]{ inventory_Details[i].getDetailId(),
                    inventory_Details[i].getKardexDetailDate(),
                    operation,
                    inventory_Details[i].getQuantity(),
                    inventory_Details[i].getUnityValue(),
                    inventory_Details[i].getTotalValue(),
                    state}
                );
            }
            
        } catch (KardexDetailDaoException exeption) {}
    }
        
    /**
     * Show more inventory_ details.
     * 
     * @param tblKardex
     * @param tblKardexDetails
     * @param txtUser
     * @param txtDocumentDescription
     * @param txtDocumentNumber
     * @param txaObservations
     * @param txtState
     */
    public void showDetails(JTable tblKardex, JTable tblKardexDetails, JTextField txtUser, JTextField txtDocumentDescription, JTextField txtDocumentNumber, JTextArea txaObservations, JTextField txtState)
    {
        try {
            int i = tblKardex.getSelectedRow();
            int j = tblKardexDetails.getSelectedRow();
            
            if(i != -1 && j != -1) {            
                product_Id = inventory_[i].getProductId();
                store_Id = inventory_[i].getStoreId();
                inventory_Details = inventory_DetailDao.findWhereProductIdAndStoreIdEquals(product_Id, store_Id);

                UsersDto userdto;
                UsersDao userDao = DaoFactory.createUsersDao();
                userdto = userDao.findByPrimaryKey(inventory_Details[j].getUserId());
                String user_Name = userdto.getRealName() + " " + userdto.getSurname();

                DocumentDto documentDto;
                DocumentDao documentDao = DaoFactory.createDocumentDao();
                documentDto = documentDao.findByPrimaryKey(inventory_Details[j].getDocumentId());

                String state;
                
                txtUser.setText(user_Name);
                txtDocumentDescription.setText(documentDto.getDocumentDescription());
                txtDocumentNumber.setText(String.valueOf(inventory_Details[j].getDocumentNumber()));
                txaObservations.setText(inventory_Details[j].getObservations());
                
                if(inventory_Details[j].getState() == 1) { state = "Activo"; }
                else { state = "Eliminado"; }
                
                txtState.setText(state);
                
            } else {
                txtUser.setText("");
                txtDocumentDescription.setText("");
                txtDocumentNumber.setText("");
                txaObservations.setText("");
                txtState.setText("");
            }
            
        } catch (DaoException exception) {}
    }
    
    /**
     * Show the form to insert a new inventory_.
     */
    public void insertKardex()
    {
        CInsertKardex cInsertKardex = new CInsertKardex();
        window.dispose();
    }
    
    /**
     * Change the status of a registered inventory_ to deleted.
     * 
     * @param tblKardex
     * @param tblKardexDetails
     * @param txtState
     */
    public void deleteKardex(JTable tblKardex, JTable tblKardexDetails, JTextField txtState)
    {
        int i = tblKardex.getSelectedRow();
        
        if(i != -1) {
            inventory_Dto = inventory_[i];
            
            if(inventory_Dto.getState() != 3 && 
                JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar el registro?", "Eliminar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            {
                try {
                    inventory_Dto.setState((short) 3);
                    
                    if(inventory_Dao.update(inventory_Dto.createPk(), inventory_Dto)) {
                        tableModel = (DefaultTableModel) tblKardex.getModel();
                        tableModel.setValueAt("*", i, 4);
                        
                        if(tblKardexDetails.getSelectedRow() != -1) { txtState.setText("Eliminado"); }
                        
                        inventory_Details = inventory_DetailDao.findWhereProductIdAndStoreIdEquals(product_Id, store_Id);                        
                        for (KardexDetailDto inventory_Detail : inventory_Details) {
                            inventory_Detail.setState((short) 3);
                            inventory_DetailDao.update(inventory_Detail.createPk(), inventory_Detail);
                        }
                    }
                    
                } catch (DaoException exception) {}
                
            } else { JOptionPane.showMessageDialog(null, "El registro ya está eliminado", "ERROR", JOptionPane.ERROR_MESSAGE); }
                
        } else { JOptionPane.showMessageDialog(null, "Seleccione un registro a eliminar", "ERROR", JOptionPane.ERROR_MESSAGE); }
    }
    
    /**
     * Show the form to insert a new inventory_ detail.
     * 
     * @param tblKardex
     */
    public void insertKardexDetails(JTable tblKardex)
    {
        int i = tblKardex.getSelectedRow();
        
        if(i != -1) {
            inventory_Dto = inventory_[i];
            
            if(inventory_Dto.getState() == 1) {                
                CInsertKardexDetails cInsertKardexDetails = new CInsertKardexDetails(product_Id, store_Id);
                window.dispose();
                
            } else { JOptionPane.showMessageDialog(null, "Solo se permite insertar en registros activos", "ERROR", JOptionPane.ERROR_MESSAGE); }
            
        } else { JOptionPane.showMessageDialog(null, "Seleccione un Kardex Cabecera", "ERROR", JOptionPane.ERROR_MESSAGE); }
    }
    
    /**
     * Show the form to modify a inventory_ detail.
     * 
     * @param tblKardex 
     */
    public void updateKardexDetails(JTable tblKardex)
    {
        int i = tblKardex.getSelectedRow();
        
        if(i != -1) {
            try {
                inventory_DetailDto = inventory_DetailDao.findLastKardexDetail(inventory_[i].getProductId(), inventory_[i].getStoreId());
                
                if(inventory_DetailDto.getState() == 1) {        
                    CUpdateKardexDetails cUpdateKardexDetails = new CUpdateKardexDetails(inventory_DetailDto);
                    window.dispose();
                        
                } else { JOptionPane.showMessageDialog(null, "Solo se permite modificar registros activos", "ERROR", JOptionPane.ERROR_MESSAGE); }
                
            } catch(ArrayIndexOutOfBoundsException | DaoException exception) { JOptionPane.showMessageDialog(null, "Nada por modificar", "ERROR", JOptionPane.ERROR_MESSAGE); }
            
        } else { JOptionPane.showMessageDialog(null, "Seleccione un Kardex Cabecera", "ERROR", JOptionPane.ERROR_MESSAGE); }
    }
    
    /**
     * Change the status of a registered inventory_ detail to deleted.
     * 
     * @param tblKardex
     */
    public void deleteKardexDetails(JTable tblKardex)
    {
        int i = tblKardex.getSelectedRow();
        
        if(i != -1) {
            try {
                inventory_DetailDto = inventory_DetailDao.findLastKardexDetail(inventory_[i].getProductId(), inventory_[i].getStoreId());
                
                if(inventory_DetailDto.getState() != 3) {
                    
                    if(JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar el registro?", "Eliminar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        inventory_DetailDto.setState((short) 3);
                        inventory_DetailDao.update(inventory_DetailDto.createPk(), inventory_DetailDto);
                        CKardex cKardex = new CKardex();
                        window.dispose();
                    }
                    
                } else { JOptionPane.showMessageDialog(null, "El registro ya está eliminado", "ERROR", JOptionPane.ERROR_MESSAGE); }
            
            } catch(ArrayIndexOutOfBoundsException | DaoException e) { JOptionPane.showMessageDialog(null, "Exception: " + e.toString(), "Error", JOptionPane.ERROR_MESSAGE); }
            
        } else { JOptionPane.showMessageDialog(null, "Seleccione un Kardex Cabecera", "ERROR", JOptionPane.ERROR_MESSAGE); }
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
     * Generate the report of the selected inventory_
     * 
     * @param tblKardex 
     */
    public void generateReport(JTable tblKardex)
    {
        int i = tblKardex.getSelectedRow();
        
        if(i != -1)
        {
            inventory_Dto = inventory_[i];
            if(inventory_Dto.getState() == 1)
            {
                try {
                    KardexView inventory_View = inventory_Dao.getKardexView(inventory_Dto.getProductId(), inventory_Dto.getStoreId())[0];
                    KardexDetailView[] inventory_DetailView = inventory_DetailDao.getKardexDetailView(inventory_Dto.getProductId(), inventory_Dto.getStoreId());
                    CReports report = new CReports();
                    report.generateKardexReport(inventory_View, inventory_DetailView);
                    
                } catch (KardexDaoException ex) {}
            }
            
            else{ JOptionPane.showMessageDialog(null, "El registro no está activo", "ERROR", JOptionPane.ERROR_MESSAGE); }
            
        } else { JOptionPane.showMessageDialog(null, "Seleccione un registro de Kardex Cabecera", "ERROR", JOptionPane.ERROR_MESSAGE); }
    }
}