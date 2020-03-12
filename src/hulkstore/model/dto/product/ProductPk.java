package hulkstore_.model.dto.product_;

import java.io.Serializable;

/** 
 * This class represents the primary key of the product_ table.
 * 
 * @author Luis Mercado
 * @version 0.1
 * @since 2020-03-10
 */
public final class ProductPk implements Serializable
{
    /** 
     * This attribute maps to the column product_Id in the product_ table.
     */
    protected int product_Id;

    /** 
     * This attribute represents whether the primitive attribute product_Id is null.
     */
    protected boolean product_IdNull;

    /** 
     * Sets the value of product_Id.
     * 
     * @param product_Id
     */
    public void setProductId(int product_Id) { this.product_Id = product_Id; }

    /** 
     * Gets the value of product_Id.
     * 
     * @return int
     */
    public int getProductId() { return product_Id; }

    /**
     * Empty Constructor.
     */
    public ProductPk() {}

    /**
     * Constructor.
     * 
     * @param product_Id
     */
    public ProductPk(final int product_Id)
    {
        this.product_Id = product_Id;
        this.product_IdNull = false;
    }

    /** 
     * Sets the value of product_IdNull.
     * 
     * @param product_IdNull
     */
    public void setProductIdNull(boolean product_IdNull) { this.product_IdNull = product_IdNull; }

    /** 
     * Gets the value of product_IdNull.
     * 
     * @return boolean
     */
    public boolean isProductIdNull() { return product_IdNull; }

    /**
     * Method 'toString'
     * 
     * @return String
     */
    public String toString()
    {
        StringBuilder response = new StringBuilder();
        response.append("hulkstore_.dto.ProductPk: ");
        response.append("product_Id=").append(product_Id);
        return response.toString();
    }
}