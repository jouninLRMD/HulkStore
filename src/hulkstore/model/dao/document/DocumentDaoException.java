package hulkstore_.model.dao.document;

import hulkstore_.model.dao.DaoException;

/** 
 * This class controls possible exceptions with documents.
 *  
 * @author Luis Mercado
 * @version 0.1
 * @since 2020-03-11
 */
public final class DocumentDaoException extends DaoException
{
    /**
     * Throw an error message.
     * 
     * @param message
     */
    public DocumentDaoException(String message) { super(message); }

    /**
     * Throw an error message and its cause.
     * 
     * @param message
     * @param cause
     */
    public DocumentDaoException(String message, Throwable cause) { super(message, cause); }
}