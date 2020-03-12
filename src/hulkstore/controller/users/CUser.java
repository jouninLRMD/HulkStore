package hulkstore_.controller.users;

import hulkstore_.controller.menu.CMenu;
import hulkstore_.model.dao.DaoFactory;
import hulkstore_.model.dao.users.UsersDao;
import hulkstore_.model.dao.users.UsersDaoException;
import hulkstore_.model.dto.users.UsersDto;
import hulkstore_.view.users.UIUser;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * User Management Controller.
 * 
 * Load existing users with their data,
 * in addition to controlling the redirection to the insertion or modification windows.
 * The delete function is performed here.
 *  
 * @author Luis Mercado
 * @version 0.1
 * @since 2020-03-10
 */
public final class CUser
{ 
    private final UIUser window;
    private final UsersDao usersDao = DaoFactory.createUsersDao();
    private UsersDto[] users;
    private UsersDto usersDto;
    private DefaultTableModel tableModel;
    
    /**
     * Empty Constructor.
     */
    public CUser()
    {
        try { users = usersDao.findAll(); }
        catch (UsersDaoException exception) {}
        
        window = new UIUser(this);
    }   
    
    /**
     * Upload the users registered in the database to the form table and set their status and profile.
     * 
     * @param tblUser 
     */
    public void upload(JTable tblUser)
    {
        tableModel = (DefaultTableModel) tblUser.getModel();
        tableModel.setRowCount(0);
        String state;
        String profile;
		
        for (UsersDto user : users) {
            
            if (user.getUserProfile() == 1) { profile = "Administrador"; }
            else { profile = "Usuario"; }
            
            if (user.getState() == 1) { state = "A"; }
            else { state = "*"; }
            
            tableModel.addRow(new Object[]{user.getUserId(), user.getUserName(), user.getIdentification(), user.getRealName(), user.getSurname(), profile, state});
        }
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
     * Show the form to insert a new user.
     */
    public void insert()
    {
        CInsertUser cInsertUser = new CInsertUser();
        window.dispose();
    }
    
    /**
     * Show the form to modify a user.
     * 
     * @param tblUser 
     */
    public void update(JTable tblUser)
    {
        int i = tblUser.getSelectedRow();
        
        if(i != -1)
        {
            usersDto = users[i];
            
            CUpdateUser update;
            
            if(usersDto.getState() == 1)
            {
                update = new CUpdateUser(usersDto.getUserId());
                window.dispose();
                
            } else { JOptionPane.showMessageDialog(null, "Solo se permite modificar registros activos", "ERROR", JOptionPane.ERROR_MESSAGE); }
        
        } else { JOptionPane.showMessageDialog(null, "Seleccione un registro a modificar", "ERROR", JOptionPane.ERROR_MESSAGE); }
    }
    
    /**
     * Change the status of a registered user to deleted.
     * 
     * @param tblUser 
     */
    public void delete(JTable tblUser)
    {
        int i = tblUser.getSelectedRow();
        
        if(i != -1)
        {
            usersDto = users[i];
            
            if(usersDto.getState() != 3)
            {
                if(JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar el registro?", "Eliminar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                {
                    try {
                        usersDto.setState((short) 3);
                        
                        if(usersDao.update(usersDto.createPk(), usersDto)) {
                            tableModel = (DefaultTableModel) tblUser.getModel();
                            tableModel.setValueAt("*", i, 6);
                        }
                        
                    } catch (UsersDaoException exception) {}
                }
                
            } else { JOptionPane.showMessageDialog(null, "El registro ya está eliminado", "ERROR", JOptionPane.ERROR_MESSAGE); }
            
        } else { JOptionPane.showMessageDialog(null, "Seleccione un registro a eliminar", "ERROR", JOptionPane.ERROR_MESSAGE); }
    }
    
    /**
     * Select the row where the searched user is located.
     * 
     * @param keyCode
     * @param filter
     * @param user
     * @param tblUser 
     */
    public void searchUser(int keyCode, String filter, String user, JTable tblUser) {
        

        if(keyCode == KeyEvent.VK_ENTER) {
            tableModel = (DefaultTableModel) tblUser.getModel();
        
            int i;
            for(i = 0; i < tableModel.getColumnCount(); i++)
            {
                if(filter.compareTo(tableModel.getColumnName(i)) == 0) { break; }
            }

            for(int k = 0; k < tableModel.getRowCount(); k++)
            {
                if(user.compareToIgnoreCase(tableModel.getValueAt(k, i).toString()) == 0) {
                    tblUser.setRowSelectionInterval(k, k);
                    break;
                    
                } else { tblUser.clearSelection(); }
            }
        }        
    }
}