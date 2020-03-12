package hulkstore_.controller.unity_;

import hulkstore_.model.dao.DaoFactory;
import hulkstore_.model.dao.unity_.*;
import hulkstore_.model.dto.unity_.UnityDto;
import hulkstore_.view.unity_.UIUpdateUnity;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * Unit Modification Controller
 * 
 * Load data from the selected unit, receive new values and validate them
 *  
 * @author Luis Mercado
 * @version 0.1
 * @since 2020-03-10
 */
public final class CUpdateUnity
{
    private UIUpdateUnity window;
    private UnityDao unity_Dao = DaoFactory.createUnityDao();
    private UnityDto unity_;
    
    /**
     * Constructor.
     * 
     * @param unity_Id 
     */
    public CUpdateUnity(int unity_Id)
    {
        try {
            unity_ = unity_Dao.findByPrimaryKey(unity_Id);
            window = new UIUpdateUnity(this);
            
        } catch (UnityDaoException e) {}
    }

    /**
     * Upload the unity_ information to the form.
     * 
     * @param txtUnityId 
     * @param txtUnityDescription 
     */
    public void upload(JTextField txtUnityId, JTextField txtUnityDescription)
    {
        txtUnityId.setText(String.valueOf(unity_.getUnityId()));
        txtUnityDescription.setText(unity_.getUnityDescription());
    }

    /**
     * Modify the unity_.
     * 
     * @param txtUnityDescription
     */
    public void accept(JTextField txtUnityDescription) 
    {        
        try {
            unity_.setUnityDescription(txtUnityDescription.getText());
            
            if(unity_Dao.update(unity_.createPk(), unity_))
            {
                JOptionPane.showMessageDialog(null, "Se ha modificado el registro", "MODIFICACIÓN", JOptionPane.INFORMATION_MESSAGE);
                CUnity cUnity = new CUnity();
                window.dispose();
                
            } else { JOptionPane.showMessageDialog(null, "No se ha modificado el registro", "ERROR", JOptionPane.ERROR_MESSAGE); }
            
        } catch (UnityDaoException e) {}
    }

    /**
     * Cancel the operation and return to the unities menu.
     */
    public void cancel() {
        CUnity cUnity = new CUnity();
        window.dispose();
    }    
}