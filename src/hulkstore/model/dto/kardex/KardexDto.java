package hulkstore_.model.dto.inventory_;

import java.io.Serializable;

/**
 * This class represents the inventory_ model.
 *  
 * @author Luis Mercado
 * @version 0.1
 * @since 2020-03-11
 */
public final class KardexDto extends Kardex implements Serializable
{
    /** 
     * This attribute represents whether the primitive attribute total_Value is null.
     */
    protected boolean total_ValueNull = true;

    /**
     * Empty Constructor    
     */
    public KardexDto() {}

    /**
     * Constructor.
     * 
     * @param product_Id
     * @param store_Id
     * @param quantity
     * @param unity_Value
     * @param total_Value
     * @param state
     */
    public KardexDto(int product_Id, int store_Id, double quantity, double unity_Value, double total_Value, short state)
    {
        this.product_Id = product_Id;
        this.store_Id = store_Id;
        this.quantity = quantity;
        this.unity_Value = unity_Value;
        this.total_Value = total_Value;
        this.state = state;
    }    

    /**
     * Sets the value of total_Value and total_ValueNull.
     * 
     * @param total_Value
     */
    @Override
    public void setTotalValue(double total_Value)
    {
        this.total_Value = total_Value;
        this.total_ValueNull = false;
    }

    /**
     * Sets the value of total_ValueNull
     * 
     * @param value
     */
    public void setTotalValueNull(boolean value) { this.total_ValueNull = value; }

    /**
     * Method 'isTotalValueNull'
     * 
     * @return boolean
     */
    public boolean isTotalValueNull() { return total_ValueNull; }

    /**
     * Method 'createPk'
     * 
     * @return KardexPk
     */
    public KardexPk createPk() { return new KardexPk(product_Id, store_Id); }

	
    /**
     * Method 'toString'
     * 
     * @return String
     */
    @Override
    public String toString()
    {
        StringBuilder response = new StringBuilder();
        response.append("hulkstore_.dto.Kardex: ");
        response.append("product_Id=").append(product_Id);
        response.append(", store_Id=").append(store_Id);
        response.append(", quantity=").append(quantity);
        response.append(", unity_Value=").append(unity_Value);
        response.append(", total_Value=").append(total_Value);
        response.append(", state=").append(state);
        return response.toString();
    }
}