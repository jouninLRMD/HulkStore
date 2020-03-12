package hulkstore_.model.dto.unity_;

import java.io.Serializable;

/** 
 * This class represents the primary key of the unity_ table.
 * 
 * @author Luis Mercado
 * @version 0.1
 * @since 2020-03-10
 */
public final class UnityPk implements Serializable
{
    /** 
     * This attribute maps to the column unity_Id in the unity_ table.
     */
    protected int unity_Id;

    /** 
     * This attribute represents whether the primitive attribute unity_Id is null.
     */
    protected boolean unity_IdNull;

    /** 
     * Sets the value of unity_Id.
     * 
     * @param unity_Id
     */
    public void setUnityId(int unity_Id) { this.unity_Id = unity_Id; }

    /** 
     * Gets the value of unity_Id.
     * 
     * @return int
     */
    public int getUnityId() { return unity_Id; }

    /**
     * Empty Constructor.     * 
     */
    public UnityPk() {}

    /**
     * Constructor.
     * 
     * @param unity_Id
     */
    public UnityPk(final int unity_Id)
    {
        this.unity_Id = unity_Id;
        this.unity_IdNull = false;
    }

    /** 
     * Sets the value of unity_IdNull.
     * 
     * @param unity_IdNull
     */
    public void setUnityIdNull(boolean unity_IdNull) { this.unity_IdNull = unity_IdNull; }

    /** 
     * Gets the value of unity_IdNull.
     * 
     * @return boolean
     */
    public boolean isUnityIdNull() { return unity_IdNull; }

    /**
     * Method 'toString'
     * 
     * @return String
     */
    public String toString()
    {
        StringBuilder ret = new StringBuilder();
        ret.append("hulkstore_.dto.UnityPk: ");
        ret.append("unity_Id=").append(unity_Id);
        return ret.toString();
    }
}