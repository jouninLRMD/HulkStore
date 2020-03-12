package hulkstore_.model.dto.document;

import hulkstore_.model.dto.document.DocumentPk;
import java.io.Serializable;

/**
 * This class represents the document model.
 *  
 * @author Luis Mercado
 * @version 0.1
 * @since 2020-03-11
 */
public final class DocumentDto implements Serializable
{
    /** 
     * This attribute maps to the column document_Id in the document table.
     */
    private int document_Id;

    /** 
     * This attribute maps to the column documentDescription in the document table.
     */
    private String documentDescription;

    /** 
     * This attribute represents whether the primitive attribute document_Id is null.
     */
    private boolean document_IdNull = true;

    /** 
     * This attribute maps to the column state in the document table.
     */
    private short state;

    /**
     * Empty Constructor.
     */
    public DocumentDto() {}
        
    /**
     * Constructor for a new document.
     * 
     * @param document_Id
     * @param documentDescription
     */
    public DocumentDto(int document_Id, String documentDescription)
    {
        this.document_Id = document_Id;
        this.documentDescription = documentDescription;
        this.document_IdNull = false;
        this.state = (short) 1;
    }

    /**
     * Constructor.
     * 
     * @param document_Id
     * @param documentDescription
     * @param state 
     */
    public DocumentDto(int document_Id, String documentDescription, short state) 
    {
        this.document_Id = document_Id;
        this.documentDescription = documentDescription;
        this.document_IdNull = false;
        this.state = state;
    }
    
    /**
     * Gets the value of document_Id.
     * 
     * @return document_Id
     */
    public int getDocumentId() { return document_Id; }

    /**
     * Sets the value of document_Id.
     * 
     * @param document_Id
     */
    public void setDocumentId(int document_Id) { this.document_Id = document_Id; }

    /**
     * Gets the value of documentDescription.
     * 
     * @return documentDescription
     */
    public String getDocumentDescription() { return documentDescription; }

    /**
     * Sets the value of documentDescription.
     * 
     * @param documentDescription
     */
    public void setDocumentDescription(String documentDescription) { this.documentDescription = documentDescription; }

    /**
     * Gets the value of state.
     * 
     * @return state
     */
    public short getState() { return state; }

    /**
     * Sets the value of state.
     * 
     * @param state
     */
    public void setState(short state) { this.state = state; }

    /**
     * Method 'createPk'.
     * 
     * @return DocumentPk
     */
    public DocumentPk createPk() { return new DocumentPk(document_Id); }

    /**
     * Method 'toString'.
     * 
     * @return String
     */
    public String toString()
    {
        StringBuilder response = new StringBuilder();
        response.append("hulkstore_.dto.Document: ");
        response.append("document_Id=").append(document_Id);
        response.append(", documentDescription=").append(documentDescription);
        response.append(", state=").append(state);
        return response.toString();
    }
}