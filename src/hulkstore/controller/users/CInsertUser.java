package hulkstore_.controller.users;

import hulkstore_.model.dao.DaoFactory;
import hulkstore_.model.dao.users.UsersDao;
import hulkstore_.model.dao.users.UsersDaoException;
import hulkstore_.model.dto.users.UsersDto;
import hulkstore_.view.users.UIInsertUser;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * User Insertion Controller.
 * 
 * Receive and validate data about a new user registration.
 *  
 * @author Luis Mercado
 * @version 0.1
 * @since 2020-03-10
 */
public final class CInsertUser
{
    private final UIInsertUser window;
    private final UsersDao usersDao = DaoFactory.createUsersDao();
    
    /**
     * Constructor.
     */
    public CInsertUser()
    {
        window = new UIInsertUser(this);
    }
    
    /**
     * Upload the user information to the form.
     * 
     * @param txtUserId 
     */
    public void upload(JTextField txtUserId)
    {
        try {
            txtUserId.setText(usersDao.findNextUserId());
        
        } catch (UsersDaoException exception) {
            Logger.getLogger(CInsertUser.class.getName()).log(Level.SEVERE, null, exception);
        }
    }
    
    /**
     * Register the new user.
     * 
     * @param txtUserId
     * @param txtUserName
     * @param pwdPass
     * @param pwdRepeatPass
     * @param ftxIdentification
     * @param txtRealName
     * @param txtSurname
     * @param jrbAdmin 
     */
    public void accept(JTextField txtUserId, JTextField txtUserName, JPasswordField pwdPass, JPasswordField pwdRepeatPass, JFormattedTextField ftxIdentification, JTextField txtRealName, JTextField txtSurname, JRadioButton jrbAdmin)
    {
        short userProfile = 0;
        
        if(jrbAdmin.isSelected()) { userProfile = 1; }
        
        UsersDto usersDto = new UsersDto(Integer.parseInt(txtUserId.getText()),
                                    txtUserName.getText(),
                                    ftxIdentification.getText(),
                                    txtRealName.getText(),
                                    txtSurname.getText(),
                                    userProfile,
                                    (short) 1);
        
        if(String.valueOf(pwdPass.getPassword()).equals(String.valueOf(pwdRepeatPass.getPassword())))
        {            
            if(String.valueOf(pwdPass.getPassword()).length() >= 5 && String.valueOf(pwdPass.getPassword()).length() <= 12)
            {
                try {
                    usersDto.setUserPass(String.valueOf(pwdPass.getPassword()));
                    
                    if(!usersDao.insert(usersDto).isUserIdNull())
                    {
                        JOptionPane.showMessageDialog(null, "Se ha agregado el registro nuevo", "INSERCION", JOptionPane.INFORMATION_MESSAGE);
                        CUser cUser = new CUser();
                        window.dispose();
                        
                    } else { JOptionPane.showMessageDialog(null, "No se registro", "ERROR", JOptionPane.ERROR_MESSAGE); }
                    
                } catch (UsersDaoException exception) {}
                
            } else {                
                JOptionPane.showMessageDialog(null, "La contraseña debe tener entre 5 y 12 caracteres.", "ERROR", JOptionPane.ERROR_MESSAGE);
                pwdPass.setText("");
                pwdRepeatPass.setText("");
            }
            
        } else {            
            JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden.\nIntente de nuevo", "ERROR", JOptionPane.ERROR_MESSAGE);
            pwdPass.setText("");
            pwdRepeatPass.setText("");
        }
    }
    
    /**
     * Cancel the operation and return to the users menu.
     */
    public void cancel()
    {
        CUser cUser = new CUser();
        window.dispose();
    }
}