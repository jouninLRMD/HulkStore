package hulkstore_.model.dto.inventory_;

import java.io.Serializable;

/** 
 * This class represents the primary key of the inventory__detail table.
 *  
 * @author Luis Mercado
 * @version 0.1
 * @since 2020-03-11
 */
public final class KardexDetailPk implements Serializable
{
    /** 
     * This attribute maps to the column detailId in the inventory_ detail table.
     */
    protected int detailId;
    
    /** 
     * This attribute maps to the column product_Id in the inventory_ detail table.
     */
    protected int product_Id;
    
    /** 
     * This attribute maps to the column store_Id in the inventory_ detail table.
     */
    protected int store_Id;

    /** 
     * This attribute represents whether the primitive attribute detailId is null.
     */
    protected boolean detailIdNull;

    /** 
     * This attribute represents whether the primitive attribute product_Id is null.
     */
    protected boolean product_IdNull;

    /** 
     * This attribute represents whether the primitive attribute store_Id is null.
     */
    protected boolean store_IdNull;

    /** 
     * Sets the value of detailId.
     * 
     * @param detailId
     */
    public void setDetailId(int detailId) { this.detailId = detailId; }

    /** 
     * Gets the value of detailId.
     * 
     * @return int
     */
    public int getDetailId() { return detailId; }

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
     * Sets the value of store_Id
     * 
     * @param store_Id
     */
    public void setStoreId(int store_Id) { this.store_Id = store_Id; }

    /** 
     * Gets the vaue of store_Id.
     * 
     * @return int
     */
    public int getStoreId() { return store_Id; }

    /**
     * Empty Constructor
     */
    public KardexDetailPk() {}

    /**
     * Costructor.
     * 
     * @param detailId
     * @param product_Id
     * @param store_Id
     */
    public KardexDetailPk(final int detailId, final int product_Id, final int store_Id)
    {
        this.detailId = detailId;
        this.product_Id = product_Id;
        this.store_Id = store_Id;
        this.detailIdNull = false;
    }

    /** 
     * Sets the value of detailIdNull.
     * 
     * @param detailIdNull
     */
    public void setDetailIdNull(boolean detailIdNull) { this.detailIdNull = detailIdNull; }

    /** 
     * Gets the value of detailIdNull.
     * 
     * @return boolean
     */
    public boolean isDetailIdNull() { return detailIdNull; }

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
     * Sets the value of store_IdNull.
     * 
     * @param store_IdNull
     */
    public void setStoreIdNull(boolean store_IdNull) { this.store_IdNull = store_IdNull; }

    /** 
     * Gets the value of store_IdNull.
     * 
     * @return boolean
     */
    public boolean isStoreIdNull() { return store_IdNull; }
    
    /**
     * Method 'equals'
     * 
     * @param other
     * @return boolean
     */
    @Override
    public boolean equals(Object other)
    {
        if (other == null) { return false; }
        if (other == this) { return true; }
        if (!(other instanceof KardexDetailPk)) { return false; }

        final KardexDetailPk cast = (KardexDetailPk) other;

        if (detailId != cast.detailId) { return false; }
        if (product_Id != cast.product_Id) { return false; }
        if (store_Id != cast.store_Id) { return false; }
        if (detailIdNull != cast.detailIdNull) { return false; }
        if (product_IdNull != cast.product_IdNull) { return false; }
        if (store_IdNull != cast.store_IdNull) { return false; }

        return true;
    }
    
    /**
     * Method 'hashCode'
     * 
     * @return int
     */
    @Override
    public int hashCode()
    {
        int _hashCode = 13;
        _hashCode *= (_hashCode + detailId);
        _hashCode *= (_hashCode + product_Id);
        _hashCode *= (_hashCode + store_Id);
        _hashCode *= (_hashCode + (detailIdNull ? 1 : 0));
        _hashCode *= (_hashCode + (product_IdNull ? 1 : 0));
        _hashCode *= (_hashCode + (store_IdNull ? 1 : 0));

        return _hashCode;
    }
}