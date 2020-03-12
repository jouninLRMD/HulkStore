package hulkstore_.model.dto.inventory_;

/** 
 * This class represents the inventory_ model.
 *  
 * @author Luis Mercado
 * @version 0.1
 * @since 2020-03-11
 */
public final class KardexView extends Kardex
{    
    /** 
     * This attribute maps to the column product_Name in the product_ table.
     */
    protected String product_Name;

    /** 
     * This attribute maps to the column store_Name in the store_ table.
     */
    protected String store_Name;

    /** 
     * This attribute maps to the column unity_Description in the unity_ table.
     */
    protected String unity_Description;

    /**
     * Gets the value of product_Name.
     * 
     * @return String
     */
    public String getProductName() { return product_Name; }

    /**
     * Sets the value of product_Name.
     * 
     * @param product_Name
     */
    public void setProductName(String product_Name) { this.product_Name = product_Name; }

    /**
     * Gets the value of store_Name.
     * 
     * @return String
     */
    public String getStoreName() { return store_Name; }

    /**
     * Sets the value of store_Name.
     * 
     * @param store_Name
     */
    public void setStoreName(String store_Name) { this.store_Name = store_Name; }

    /**
     * Gets the value of unity_Description.
     * 
     * @return String
     */
    public String getUnityDescription() { return unity_Description; }

    /**
     * Sets the value of unity_Description.
     * 
     * @param unity_Description
     */
    public void setUnityDescription(String unity_Description) { this.unity_Description = unity_Description; }
}