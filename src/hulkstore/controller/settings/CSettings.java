package hulkstore_.controller.settings;

import hulkstore_.controller.login.CLogin;
import hulkstore_.controller.menu.CMenu;
import static hulkstore_.model.dao.ResourceManager.getDataConnection;
import static hulkstore_.model.dao.ResourceManager.setDataConnection;
import static hulkstore_.model.dao.ResourceManager.testConnection;
import hulkstore_.view.settings.UISettings;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import org.apache.log4j.Logger;

/**
 * Setting view controller.
 * 
 * Modify configuration values with the database, in addition to checking
 * if the connection provided by the user is correct.
 *  
 * @author Luis Mercado
 * @version 0.1
 * @since 2020-03-10
 */
public final class CSettings {
    
    private static final Logger LOG = Logger.getLogger(CSettings.class.getName());
    private final UISettings window;
    private final boolean returnToLogin;
    
    /**
     * Constructor.
     * 
     * @param returnToLogin 
     */
    public CSettings(boolean returnToLogin) {        
        LOG.info("Initializing view UISettings...");
        this.returnToLogin = returnToLogin;
        window = new UISettings(this);
    }

    /**
     * Load the initial data for the connection.
     * 
     * @param txtHost
     * @param txtUser 
     */
    public void upload(JTextField txtHost, JTextField txtUser) {        
        String[] dataConection = getDataConnection();
        txtHost.setText(dataConection[0]);
        txtUser.setText(dataConection[1]);
    }
    
    /**
     * Return to the initial menu.
     */
    public void cancel() {             
        LOG.info("Closed UISettings view...");
        window.dispose();   
        
        if(returnToLogin) { CLogin login = new CLogin(); }
        else { CMenu cMenu = new CMenu(); }
    }

    /**
     * Validate that the parameters indicated make a successful connection to the database.
     * 
     * @param txtHost
     * @param txtUser
     * @param pwdPass
     * @param lblState 
     */
    public void validate(JTextField txtHost, JTextField txtUser, JPasswordField pwdPass, JLabel lblState) {          
        if(testConnection(txtHost.getText(), txtUser.getText(), pwdPass.getText())) {
            lblState.setForeground(new Color(0, 150, 0));
            lblState.setText("Configuración correcta");
        
        } else {
            lblState.setForeground(new Color(255, 0, 0));
            lblState.setText("Configuración incorrecta");
        }
    }

    /**
     * Registers the connection parameters in the connection.dat file.
     * 
     * @param txtHost
     * @param txtUser
     * @param pwdPass
     * @param lblState 
     */
    public void accept(JTextField txtHost, JTextField txtUser, JPasswordField pwdPass, JLabel lblState)  {        
        if(lblState.getText().equals("Configuración correcta")) {
            LOG.info("Accept connection parameters...");
            setDataConnection(txtHost.getText(), txtUser.getText(), pwdPass.getText());
            cancel();
            
        } else {
            LOG.debug("This connection did not pass the test");
            JOptionPane.showMessageDialog(null, "Esta conexión no pasó el test.\nConfigure la conexión correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }    
}