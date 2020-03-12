package hulkstore_.model.dto.document;

import java.io.Serializable;

/** 
 * This class represents the primary key of the document table.
 *  
 * @author Luis Mercado
 * @version 0.1
 * @since 2020-03-11
 */
public final class DocumentPk implements Serializable
{
    /** 
     * This attribute maps to the column document_Id in the document table.
     */
    protected int document_Id;

    /** 
     * This attribute represents whether the primitive attribute document_Id is null.
     */
    protected boolean document_IdNull;

    /** 
     * Sets the value of document_Id.
     * 
     * @param document_Id
     */
    public void setDocumentId(int document_Id) { this.document_Id = document_Id; }

    /** 
     * Gets the value of document_Id.
     * 
     * @return int
     */
    public int getDocumentId() { return document_Id; }

    /**
     * Empty Constructor.
     */
    public DocumentPk() {}

    /**
     * Constructor for a new documentPk.
     * 
     * @param document_Id
     */
    public DocumentPk(final int document_Id)
    {
        this.document_Id = document_Id;
        this.document_IdNull = false;
    }

    /** 
     * Gets the value of document_IdNull.
     * 
     * @return boolean
     */
    public boolean isDocumentIdNull() { return document_IdNull; }
}