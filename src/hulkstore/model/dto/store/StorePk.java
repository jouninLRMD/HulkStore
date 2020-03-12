package hulkstore_.model.dto.store_;

import java.io.Serializable;

/** 
 * This class represents the primary key of the store_ table.
 * 
 * @author Luis Mercado
 * @version 0.1
 * @since 2020-03-10
 */
public final class StorePk implements Serializable
{
    protected int store_Id;

    /** 
     * This attribute represents whether the primitive attribute store_Id is null.
     */
    protected boolean store_IdNull;

    /** 
     * Sets the value of store_Id.
     * 
     * @param store_Id
     */
    public void setStoreId(int store_Id) { this.store_Id = store_Id; }

    /** 
     * Gets the value of store_Id.
     * 
     * @return int
     */
    public int getStoreId() { return store_Id; }

    /**
     * Empty Constructor.
     * 
     */
    public StorePk() {}

    /**
     * Constructor.
     * 
     * @param store_Id
     */
    public StorePk(final int store_Id)
    {
        this.store_Id = store_Id;
        this.store_IdNull = false;
    }

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
}