package hulkstore_.model.dto.inventory_;

/**
 * This abstract class represents the inventory_ detail model.
 *  
 * @author Luis Mercado
 * @version 0.1
 * @since 2020-03-11
 */
public abstract class KardexDetail extends Kardex
{
    /** 
     * This attribute maps to the column detailId in the inventory__detail table.
     */
    protected int detailId;

    /** 
     * This attribute maps to the column inventory_DetailYear in the inventory__detail table.
     */
    protected int inventory_DetailYear;

    /** 
     * This attribute maps to the column inventory_DetailMonth in the inventory__detail table.
     */
    protected int inventory_DetailMonth;

    /** 
     * This attribute maps to the column inventory_Detailday in the inventory__detail table.
     */
    protected int inventory_Detailday;

    /** 
     * This attribute maps to the column userId in the inventory__detail table.
     */
    protected int userId;

    /** 
     * This attribute maps to the column document_Id in the inventory__detail table.
     */
    protected int document_Id;

    /** 
     * This attribute maps to the column documentNumber in the inventory__detail table.
     */
    protected int documentNumber;

    /** 
     * This attribute maps to the column operation in the inventory__detail table.
     */
    protected short operation;

    /** 
     * This attribute maps to the column observations in the inventory__detail table.
     */
    protected String observations;
    
    /**
    * Gets the value of detailId.
    * 
    * @return int
    */
   public int getDetailId() { return detailId; }

   /**
    * Sets the value of detailId.
    * 
    * @param detailId
    */
   public void setDetailId(int detailId) { this.detailId = detailId; }

    /**
     * Gets the value of inventory_ detail date.
     * 
     * @return String
     */
    public String getKardexDetailDate()
    {
        return inventory_Detailday + "/" + inventory_DetailMonth + "/" + inventory_DetailYear;
    }

    /**
     * Gets the value of inventory_DetailYear.
     * 
     * @return int
     */
    public int getKardexDetailYear() { return inventory_DetailYear; }

    /**
     * Sets the value of inventory_DetailYear.
     * 
     * @param inventory_DetailYear
     */
    public void setKardexDetailYear(int inventory_DetailYear) { this.inventory_DetailYear = inventory_DetailYear; }

    /**
     * Gets the value of inventory_DetailMonth.
     * 
     * @return int
     */
    public int getKardexDetailMonth() { return inventory_DetailMonth; }

    /**
     * Sets the value of inventory_DetailMonth.
     * 
     * @param inventory_DetailMonth
     */
    public void setKardexDetailMonth(int inventory_DetailMonth) { this.inventory_DetailMonth = inventory_DetailMonth; }

    /**
     * Gets the value of inventory_Detailday.
     * 
     * @return int
     */
    public int getKardexDetailday() { return inventory_Detailday; }

    /**
     * Sets the value of inventory_Detailday.
     * 
     * @param inventory_Detailday
     */
    public void setKardexDetailday(int inventory_Detailday) { this.inventory_Detailday = inventory_Detailday; }

    /**
     * Gets the value of userId.
     * 
     * @return int
     */
    public int getUserId() { return userId; }

    /**
     * Sets the value of userId.
     * 
     * @param userId
     */
    public void setUserId(int userId) { this.userId = userId; }

    /**
     * Gets the value of document_Id.
     * 
     * @return int
     */
    public int getDocumentId() { return document_Id; }

    /**
     * Sets the value of document_Id.
     * 
     * @param document_Id
     */
    public void setDocumentId(int document_Id) { this.document_Id = document_Id; }

    /**
     * Gets the value of documentNumber.
     * 
     * @return int
     */
    public int getDocumentNumber() { return documentNumber; }

    /**
     * Sets the value of documentNumber.
     * 
     * @param documentNumber
     */
    public void setDocumentNumber(int documentNumber) { this.documentNumber = documentNumber; }

    /**
     * Gets the value of operation.
     * 
     * @return short
     */
    public short getOperation() { return operation; }

    /**
     * Sets the value of operation.
     * 
     * @param operation
     */
    public void setOperation(short operation) { this.operation = operation; }

    /**
     * Gets the value of observations.
     * 
     * @return String
     */
    public String getObservations() { return observations; }

    /**
     * Sets the value of observations.
     * 
     * @param observations
     */
    public void setObservations(String observations) { this.observations = observations; }
}