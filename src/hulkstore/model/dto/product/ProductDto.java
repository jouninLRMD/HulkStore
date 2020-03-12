package hulkstore_.model.dto.product_;

import java.io.Serializable;

/**
 * This class represents the product_ model.
 * 
 * @author Luis Mercado
 * @version 0.1
 * @since 2020-03-10
 */
public final class ProductDto extends Product implements Serializable
{
    /** 
     * This attribute represents whether the primitive attribute unity_Id is null.
     */
    protected boolean unity_IdNull = true;

    /**
     * Empty Constructor.
     */
    public ProductDto() {}
    
    /**
     * Constructor. 
     * 
     * @param product_Id
     * @param product_Name
     * @param unity_Id 
     */
    public ProductDto(int product_Id, String product_Name, int unity_Id)
    {
        this.product_Id = product_Id;
        this.product_Name = product_Name;
        this.unity_Id = unity_Id;
        this.unity_IdNull = false;
        this.state = (short) 1;
    }

    /**
     * Constructor.
     * 
     * @param product_Id
     * @param product_Name
     * @param unity_Id
     * @param state 
     */
    public ProductDto(int product_Id, String product_Name, int unity_Id, short state) 
    {
        this.product_Id = product_Id;
        this.product_Name = product_Name;
        this.unity_Id = unity_Id;
        this.unity_IdNull = false;
        this.state = state;
    }    

    /**
     * Sets the value of unity_IdNull.
     * 
     * @param value
     */
    public void setUnityIdNull(boolean value) { this.unity_IdNull = value; }

    /**
     * Method 'isUnityIdNull'
     * 
     * @return boolean
     */
    public boolean isUnityIdNull() { return unity_IdNull; }

    /**
     * Method 'createPk'
     * 
     * @return ProductPk
     */
    public ProductPk createPk() { return new ProductPk(product_Id); }

    /**
     * Method 'toString'
     * 
     * @return String
     */
    public String toString()
    {
        StringBuilder response = new StringBuilder();
        response.append("hulkstore_.dto.Product: ");
        response.append("product_Id=").append(product_Id);
        response.append(", product_Name=").append(product_Name);
        response.append(", unity_Id=").append(unity_Id);
        response.append(", state=").append(state);
        return response.toString();
    }
}