package hulkstore_.controller.unity_;

import hulkstore_.model.dao.DaoFactory;
import hulkstore_.model.dao.unity_.*;
import hulkstore_.model.dto.unity_.UnityDto;
import hulkstore_.view.unity_.UIInsertUnity;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * Drive Insertion Controller
 * 
 * Receive and validate data on a new unit record
 *  
 * @author Luis Mercado
 * @version 0.1
 * @since 2020-03-10
 */
public final class CInsertUnity
{
    private final UIInsertUnity window;
    private final UnityDao unity_Dao = DaoFactory.createUnityDao();
    
    /**
     * Empty Constructor.
     */
    public CInsertUnity() { window = new UIInsertUnity(this); }
    
    /**
     * Upload the unity_ information to the form.
     * 
     * @param txtUnityId 
     */
    public void upload(JTextField txtUnityId)
    {
        try {
            txtUnityId.setText(unity_Dao.findNextUnityId());
            
        } catch (UnityDaoException exception) {}
    }

    /**
     * Register the new unity_.
     * 
     * @param txtUnityId
     * @param txtUnityDescription
     */
    public void accept(JTextField txtUnityId, JTextField txtUnityDescription) 
    {      
        try {
            UnityDto unity_Dto = new UnityDto(Integer.parseInt(txtUnityId.getText()), txtUnityDescription.getText());
            
            if(!unity_Dao.insert(unity_Dto).isUnityIdNull()){
                JOptionPane.showMessageDialog(null, "Se ha agregado el registro nuevo", "INSERCION", JOptionPane.INFORMATION_MESSAGE);
                CUnity cUnity = new CUnity();
                window.dispose();
                
            } else { JOptionPane.showMessageDialog(null, "No se registro", "ERROR", JOptionPane.ERROR_MESSAGE); }
            
        } catch (UnityDaoException exception) {}
    }

    /**
     * Cancel the operation and return to the unities menu.
     */
    public void cancel()
    {
        CUnity cUnity = new CUnity();
        window.dispose();
    }    
}