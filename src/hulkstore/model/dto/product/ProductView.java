package hulkstore_.model.dto.product_;

import java.io.Serializable;

/**
 * This class represents the product_ view model.
 * 
 * @author Luis Mercado
 * @version 0.1
 * @since 2020-03-10
 */
public final class ProductView extends Product implements Serializable
{  
    /** 
     * This attribute maps to the column unity_Description in the unity_ table.
     */
    private String unity_Description;

    /**
     * Gets the value of unity_Description.
     * 
     * @return string
     */
    public String getUnityDescription() { return unity_Description; }

    /**
     * Gets the value of unity_Description.
     * 
     * @param unity_Description
     */
    public void setUnityDescription(String unity_Description) { this.unity_Description = unity_Description; }
}