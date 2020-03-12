package hulkstore_.model.dto.store_;

import java.io.Serializable;

/**
 * This class represents the product_ model.
 * 
 * @author Luis Mercado
 * @version 0.1
 * @since 2020-03-10
 */
public final class StoreDto implements Serializable
{
    /** 
     * This attribute maps to the column store_Id in the store_ table.
     */
    protected int store_Id;

    /** 
     * This attribute maps to the column store_Name in the store_ table.
     */
    protected String store_Name;

    /** 
     * This attribute maps to the column address in the store_ table.
     */
    protected String address;

    /** 
     * This attribute maps to the column state in the store_ table.
     */
    protected short state;

    /**
     * Empty Constructor.
     * 
     */
    public StoreDto() {}
    
    /**
     * Constructor.
     * 
     * @param store_Id
     * @param store_Name
     * @param address 
     */
    public StoreDto(int store_Id, String store_Name, String address)
    {
        this.store_Id = store_Id;
        this.store_Name = store_Name;
        this.address = address;
        this.state = 1;
    }
    
    /**
     * Constructor.
     * 
     * @param store_Id
     * @param store_Name
     * @param address 
     * @param state 
     */
    public StoreDto(int store_Id, String store_Name, String address, short state) {
        this.store_Id = store_Id;
        this.store_Name = store_Name;
        this.address = address;
        this.state = state;
    }
    
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
     * Gets the value of address.
     * 
     * @return String
     */
    public String getAddress() { return address; }

    /**
     * Sets the value of address.
     * 
     * @param address
     */
    public void setAddress(String address) { this.address = address; }

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

    /**
     * Method 'createPk'
     * 
     * @return StorePk
     */
    public StorePk createPk() { return new StorePk(store_Id); }

    /**
     * Method 'toString'
     * 
     * @return String
     */
    public String toString()
    {
        StringBuilder ret = new StringBuilder();
        ret.append("hulkstore_.dto.Store: ");
        ret.append("store_Id=").append(store_Id);
        ret.append(", store_Name=").append(store_Name);
        ret.append(", address=").append(address);
        ret.append(", state=").append(state);
        return ret.toString();
    }
}