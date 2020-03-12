package hulkstore_.model.dto.product_;

/**
 * This abstract class represents the product_ model.
 * 
 * @author Luis Mercado
 * @version 0.1
 * @since 2020-03-10
 */
public abstract class Product
{
    /** 
     * This attribute maps to the column product_Id in the product_ table.
     */
    protected int product_Id;

    /** 
     * This attribute maps to the column product_Name in the product_ table.
     */
    protected String product_Name;

    /** 
     * This attribute maps to the column unity_Id in the product_ table.
     */
    protected int unity_Id;

    /** 
     * This attribute maps to the column state in the product_ table.
     */
    protected short state;

    /**
     * Gets the value of product_Id.
     * 
     * @return int
     */
    public int getProductId() { return product_Id; }

    /**
     * Sets the value of product_Id.
     * 
     * @param product_Id
     */
    public void setProductId(int product_Id) { this.product_Id = product_Id; }

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
     * Gets the value of unity_Id.
     * 
     * @return int
     */
    public int getUnityId() { return unity_Id; }

    /**
     * Sets the value of unity_Id.
     * 
     * @param unity_Id
     */
    public void setUnityId(int unity_Id) { this.unity_Id = unity_Id; }

    /**
     * Gets the value of state.
     * 
     * @return short
     */
    public short getState() { return state; }

    /**
     * Sets the value of state.
     * 
     * @param state
     */
    public void setState(short state) { this.state = state; }
}