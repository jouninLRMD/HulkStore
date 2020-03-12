package hulkstore_.controller.users;

import hulkstore_.model.dao.DaoFactory;
import hulkstore_.model.dao.users.UsersDao;
import hulkstore_.model.dao.users.UsersDaoException;
import hulkstore_.model.dto.users.UsersDto;
import hulkstore_.view.users.UIUpdateUser;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * User Modification Controller.
 * 
 * Load data from the selected user, receive new values and validate them.
 *  
 * @author Luis Mercado
 * @version 0.1
 * @since 2020-03-10
 */
public final class CUpdateUser
{
    private UIUpdateUser window;
    private UsersDao usersDao = DaoFactory.createUsersDao();
    private UsersDto user;
    
    /**
     * Constructor.
     * 
     * @param userId 
     */
    public CUpdateUser(int userId)
    {
        try {
            user = usersDao.findByPrimaryKey(userId);
            window = new UIUpdateUser(this);
            
        } catch (UsersDaoException exception) {}
        
    }
    
    /**
     * Upload the user information to the form.
     * 
     * @param txtUserId
     * @param txtUserName
     * @param ftxIdentification
     * @param txtRealName
     * @param txtSurname
     * @param jrbAdmin
     * @param jrbUser 
     */
    public void upload( JTextField txtUserId, JTextField txtUserName, JFormattedTextField ftxIdentification,
                        JTextField txtRealName, JTextField txtSurname, JRadioButton jrbAdmin, JRadioButton jrbUser)
    {
        txtUserId.setText(String.valueOf(user.getUserId()));
        txtUserName.setText(user.getUserName());
        ftxIdentification.setText(user.getIdentification());
        txtRealName.setText(user.getRealName());
        txtSurname.setText(user.getSurname());        
        
        if(user.getUserProfile() == 1)
        {
            jrbAdmin.setSelected(true);
            jrbUser.setSelected(false);
            
        } else {
            jrbAdmin.setSelected(false);
            jrbUser.setSelected(true);
        }
    }   
    
    /**
     * Modify the user.
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
    public void accept( JTextField txtUserId, JTextField txtUserName, JPasswordField pwdPass, JPasswordField pwdRepeatPass,
                        JFormattedTextField ftxIdentification, JTextField txtRealName, JTextField txtSurname, JRadioButton jrbAdmin)
    {
        short userProfile = 0;
        
        if(jrbAdmin.isSelected()) { userProfile = 1; }
        
        user.setUserId(Integer.parseInt(txtUserId.getText()));
        user.setUserName(txtUserName.getText());
        user.setIdentification(ftxIdentification.getText());
        user.setRealName(txtRealName.getText());
        user.setSurname(txtSurname.getText());
        user.setUserProfile(userProfile);
        
        if(String.valueOf(pwdPass.getPassword()).equals(String.valueOf(pwdRepeatPass.getPassword())))
        {
            if(String.valueOf(pwdPass.getPassword()).length() >= 5 && String.valueOf(pwdPass.getPassword()).length() <= 16)
            {
                try {
                    user.setUserPass(String.valueOf(pwdPass.getPassword()));
                    
                    if(usersDao.update(user.createPk(), user))
                    {
                        JOptionPane.showMessageDialog(null, "Se ha modificado el registro", "MODIFICACIÓN", JOptionPane.INFORMATION_MESSAGE);
                        CUser cUser = new CUser();
                        window.dispose();
                        
                    } else { JOptionPane.showMessageDialog(null, "No se modifico", "ERROR", JOptionPane.ERROR_MESSAGE); }
                    
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