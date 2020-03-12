package hulkstore_.model.dto.inventory_;

import java.io.Serializable;

/** 
 * This class represents the inventory_ detail model.
 *  
 * @author Luis Mercado
 * @version 0.1
 * @since 2020-03-11
 */
public final class KardexDetailDto extends KardexDetail implements Serializable
{
    /** 
     * This attribute represents whether the primitive attribute userId is null.
     */
    protected boolean userIdNull = true;

    /**
     * Empty Constructor. 
     */
    public KardexDetailDto() {}

    /**
     * Constructor for a new inventory_ detail.
     * 
     * @param detailId
     * @param product_Id
     * @param store_Id
     * @param inventory_DetailYear
     * @param inventory_DetailMonth
     * @param inventory_Detailday
     * @param userId
     * @param document_Id
     * @param documentNumber
     * @param operation
     * @param quantity
     * @param unity_Value
     * @param total_Value
     * @param observations 
     */
    public KardexDetailDto(int detailId, int product_Id, int store_Id, int inventory_DetailYear, int inventory_DetailMonth, int inventory_Detailday, int userId, int document_Id, int documentNumber, short operation, double quantity, double unity_Value, double total_Value, String observations)
    {
        this.detailId = detailId;
        this.product_Id = product_Id;
        this.store_Id = store_Id;
        this.inventory_DetailYear = inventory_DetailYear;
        this.inventory_DetailMonth = inventory_DetailMonth;
        this.inventory_Detailday = inventory_Detailday;
        this.userId = userId;
        this.userIdNull = false;
        this.document_Id = document_Id;
        this.documentNumber = documentNumber;
        this.operation = operation;
        this.quantity = quantity;
        this.unity_Value = unity_Value;
        this.total_Value = total_Value;
        this.observations = observations;
        this.state = 1;
    }
    
    /**
     * Constructor.
     * 
     * @param detailId
     * @param product_Id
     * @param store_Id
     * @param inventory_DetailYear
     * @param inventory_DetailMonth
     * @param inventory_Detailday
     * @param userId
     * @param document_Id
     * @param documentNumber
     * @param operation
     * @param quantity
     * @param unity_Value
     * @param total_Value
     * @param observations
     * @param state 
     */
    public KardexDetailDto(int detailId, int product_Id, int store_Id, int inventory_DetailYear, int inventory_DetailMonth, int inventory_Detailday, int userId, int document_Id, int documentNumber, short operation, double quantity, double unity_Value, double total_Value, String observations, short state)
    {
        this.detailId = detailId;
        this.product_Id = product_Id;
        this.store_Id = store_Id;
        this.inventory_DetailYear = inventory_DetailYear;
        this.inventory_DetailMonth = inventory_DetailMonth;
        this.inventory_Detailday = inventory_Detailday;
        this.userId = userId;
        this.userIdNull = false;
        this.document_Id = document_Id;
        this.documentNumber = documentNumber;
        this.operation = operation;
        this.quantity = quantity;
        this.unity_Value = unity_Value;
        this.total_Value = total_Value;
        this.observations = observations;
        this.state = state;
    }   

    /**
     * Sets the value of userId
     * 
     * @param userId
     */
    @Override
    public void setUserId(int userId)
    {
        this.userId = userId;
        this.userIdNull = false;
    }

    /**
     * Sets the value of userIdNull
     * 
     * @param value
     */
    public void setUserIdNull(boolean value) { this.userIdNull = value; }

    /**
     * Method 'isUserIdNull'
     * 
     * @return boolean
     */
    public boolean isUserIdNull() { return userIdNull; }

    /**
     * Method 'createPk'.
     * 
     * @return KardexDetailPk
     */
    public KardexDetailPk createPk() { return new KardexDetailPk(detailId, product_Id, store_Id); }

    /**
     * Method 'toString'
     * 
     * @return String
     */
    @Override
    public String toString()
    {
        StringBuilder response = new StringBuilder();
        response.append("hulkstore_.dto.KardexDetail: ");
        response.append("detailId=").append(detailId);
        response.append(", product_Id=").append(product_Id);
        response.append(", store_Id=").append(store_Id);
        response.append(", inventory_DetailYear=").append(inventory_DetailYear);
        response.append(", inventory_DetailMonth=").append(inventory_DetailMonth);
        response.append(", inventory_Detailday=").append(inventory_Detailday);
        response.append(", userId=").append(userId);
        response.append(", document_Id=").append(document_Id);
        response.append(", documentNumber=").append(documentNumber);
        response.append(", operation=").append(operation);
        response.append(", quantity=").append(quantity);
        response.append(", unity_Value=").append(unity_Value);
        response.append(", total_Value=").append(total_Value);
        response.append(", observations=").append(observations);
        response.append(", state=").append(state);
        return response.toString();
    }
}