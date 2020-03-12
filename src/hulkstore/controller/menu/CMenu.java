package hulkstore_.controller.menu;

import hulkstore_.controller.document.CDocument;
import hulkstore_.controller.inventory_.CKardex;
import hulkstore_.controller.login.CLogin;
import hulkstore_.controller.product_.CProduct;
import hulkstore_.controller.settings.CSettings;
import hulkstore_.controller.store_.CStore;
import hulkstore_.controller.unity_.CUnity;
import hulkstore_.controller.users.CUser;
import hulkstore_.model.dao.DaoException;
import hulkstore_.model.dao.DaoFactory;
import hulkstore_.model.dao.users.*;
import hulkstore_.model.dto.users.UsersDto;
import hulkstore_.view.menu.UIMenu;
import java.awt.HeadlessException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;

/**
 * Main Menu Controller.
 * 
 * Load the corresponding options depending on the type of user
 * and redirect to the different corresponding views.
 *  
 * @author Luis Mercado
 * @version 0.1
 * @since 2020-03-10
 */
public final class CMenu {
    
    private static final Logger LOG = Logger.getLogger(CMenu.class.getName());
    private UIMenu window;
    
    /**
     * Empty Constructor,
     */
    public CMenu() {
        try {
            UsersDao dao = DaoFactory.createUsersDao();
            UsersDto user = dao.findWhereUserIdEquals(UIMenu.userId)[0];

            if (user != null) { window = new UIMenu(this, user); }
            else { JOptionPane.showMessageDialog(null, "Error al cargar el menú", "Error", JOptionPane.ERROR_MESSAGE, null); }
        
        } catch (UsersDaoException | HeadlessException exception) {}        
    }
    
    /**
     * Constructor.
     * 
     * @param user 
     */
    public CMenu(UsersDto user) { 
        LOG.info("Initializing view UIMenu...");
        window = new UIMenu(this, user);
    }
    
    /**
     * Upload the user information logged in and enable the functions according to your profile.
     * 
     * @param user
     * @param lblRealName
     * @param lblIdentification
     * @param lblProfile
     * @param btnUser
     * @param btnProductExistence
     * @param btnProductEntry
     * @param btnProductExit 
     */
    public void upload(UsersDto user, JLabel lblRealName, JLabel lblIdentification, JLabel lblProfile, JButton btnUser, JButton btnProductExistence, JButton btnProductEntry, JButton btnProductExit) {
        UIMenu.userId = user.getUserId();
        lblRealName.setText(user.getRealName() + " " + user.getSurname());
        lblIdentification.setText("CC Nº " + user.getIdentification());
        
        if(user.getUserProfile() == 1) {
            lblProfile.setText("Administrador");
            
        } else {
            lblProfile.setText("Usuario");
            btnUser.setEnabled(false);
            btnProductExistence.setEnabled(false);
            btnProductEntry.setEnabled(false);
            btnProductExit.setEnabled(false);
        }
    }

    /**
     * Close the current user session and return to the login form.
     */
    public void logOut() {
        CLogin cLogin = new CLogin();
        window.dispose();
    }

    /**
     * Show the form to manage the store_s.
     */
    public void store_() {
        CStore cStore = new CStore();
        window.dispose();
    }

    /**
     * Show the form to manage users.
     */
    public void user() {
        CUser cUser = new CUser();
        window.dispose();
    }

    /**
     * Show the form to manage the units.
     */
    public void unity_() {
        CUnity cUnity = new CUnity();
        window.dispose();
    }

    /**
     * Show the form to manage the documents.
     */
    public void document() {
        CDocument cDocument = new CDocument();
        window.dispose();
    }

    /**
     * Show the form to manage the product_s.
     */
    public void product_() {
        CProduct cProduct = new CProduct();
        window.dispose();
    }

    /**
     * Show the configuration form.
     */
    public void setting() {
        CSettings cSettings = new CSettings(false);
        window.dispose();
    }

    /**
     * Show the form to manage inventory_.
     */
    public void inventory_() {
        try {
            CKardex cKardex = new CKardex();
            window.dispose();
            
        } catch (DaoException exception) {}
    }

    /**
     * Commercial message.
     */
    public void product_Existence() {
        JOptionPane.showMessageDialog(null, "Adquiera una licencia premium plus lite", "Actualice su versión", JOptionPane.INFORMATION_MESSAGE, null);
    }

    /**
     * Commercial message.
     */
    public void product_Exit() {
        JOptionPane.showMessageDialog(null, "Adquiera una licencia premium plus lite", "Actualice su versión", JOptionPane.INFORMATION_MESSAGE, null);
    }

    /**
     * Commercial message.
     */
    public void producEntry() {
        JOptionPane.showMessageDialog(null, "Adquiera una licencia premium plus lite", "Actualice su versión", JOptionPane.INFORMATION_MESSAGE, null);
    }

    /**
     * Show the form.
     */
    public void showForm() {
        window.setVisible(true);
    }    
}