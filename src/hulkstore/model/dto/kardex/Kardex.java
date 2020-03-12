package hulkstore_.model.dto.inventory_;

/**
 * This abstract class represents the inventory_ model.
 *  
 * @author Luis Mercado
 * @version 0.1
 * @since 2020-03-11
 */
public abstract class Kardex
{
    /** 
     * This attribute maps to the column product_Id.
     */
    protected int product_Id;

    /** 
     * This attribute maps to the column store_Id.
     */
    protected int store_Id;

    /** 
     * This attribute maps to the column quantity.
     */
    protected double quantity;

    /** 
     * This attribute maps to the column unity_Value.
     */
    protected double unity_Value;

    /** 
     * This attribute maps to the column total_Value.
     */
    protected double total_Value;
    
    /** 
     * This attribute maps to the column state.
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
     * Gets the value of store_Id.
     * 
     * @return int
     */
    public int getStoreId() { return store_Id; }

    /**
     * Sets the value of store_Id.
     * 
     * @param store_Id
     */
    public void setStoreId(int store_Id) { this.store_Id = store_Id; }

    /**
     * Gets the value of quantity.
     * 
     * @return double
     */
    public double getQuantity() { return quantity; }

    /**
     * Sets the value of quantity.
     * 
     * @param quantity
     */
    public void setQuantity(double quantity) { this.quantity = quantity; }

    /**
     * Gets the value of unity_Value.
     * 
     * @return double
     */
    public double getUnityValue() { return unity_Value; }

    /**
     * Sets the value of unity_Value.
     * 
     * @param unity_Value
     */
    public void setUnityValue(double unity_Value) { this.unity_Value = unity_Value; }

    /**
     * Gets the value of total_Value.
     * 
     * @return double
     */
    public double getTotalValue() { return total_Value; }

    /**
     * Sets the value of total_Value.
     * 
     * @param total_Value
     */
    public void setTotalValue(double total_Value) { this.total_Value = total_Value; }

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