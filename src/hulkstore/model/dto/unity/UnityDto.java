package hulkstore_.model.dto.unity_;

import java.io.Serializable;

/**
 * Main view of Unit Management.
 * 
 * @author Luis Mercado
 * @version 0.1
 * @since 2020-03-10
 */
public final class UnityDto implements Serializable
{
    /** 
     * This attribute maps to the column unity_Id in the unity_ table.
     */
    protected int unity_Id;

    /** 
     * This attribute maps to the column unity_Description in the unity_ table.
     */
    protected String unity_Description;

    /** 
     * This attribute represents whether the primitive attribute unity_Id is null.
     */
    protected boolean unity_IdNull = true;

    /** 
     * This attribute maps to the column state in the unity_ table.
     */
    protected short state;

    /**
     * Empty Constructor.
     * 
     */
    public UnityDto() {}
        
    /**
     * Constructor.
     * 
     * @param unity_Id
     * @param unity_Description 
     */
    public UnityDto(int unity_Id, String unity_Description)
    {
        this.unity_Id = unity_Id;
        this.unity_Description = unity_Description;
        this.unity_IdNull = false;
        this.state = (short) 1;
    }

    public UnityDto(int unity_Id, String unity_Description, short state) {
        this.unity_Id = unity_Id;
        this.unity_Description = unity_Description;
        this.unity_IdNull = false;
        this.state = state;
    }
    
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
     * @return UnityPk
     */
    public UnityPk createPk() { return new UnityPk(unity_Id); }

    /**
     * Method 'toString'
     * 
     * @return String
     */
    public String toString()
    {
        StringBuilder ret = new StringBuilder();
        ret.append("hulkstore_.dto.Unity: ");
        ret.append("unity_Id=").append(unity_Id);
        ret.append(", unity_Description=").append(unity_Description);
        ret.append(", state=").append(state);
        return ret.toString();
    }
}