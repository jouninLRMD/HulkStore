package hulkstore_.model.dto.inventory_;

/** 
 * This class represents the inventory_ detail view model.
 *  
 * @author Luis Mercado
 * @version 0.1
 * @since 2020-03-11
 */
public final class KardexDetailView extends KardexDetail
{   
    /** 
     * This attribute maps to the column documentDescription in the document table.
     */
    protected String documentDescription;

    /**
     * Gets the value of document description.
     * 
     * @return String
     */
    public String getDocumentDescription() { return documentDescription; }

    /**
     * Sets the value of document description.
     * 
     * @param documentDescription
     */
    public void setDocumentDescription(String documentDescription) { this.documentDescription = documentDescription; }    
}