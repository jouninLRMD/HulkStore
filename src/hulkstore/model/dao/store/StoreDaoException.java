package hulkstore_.model.dao.store_;

import hulkstore_.model.dao.DaoException;

/** 
 * This class controls possible exceptions with store_ table.
 *  
 * @author Luis Mercado
 * @version 0.1
 * @since 2020-03-10
 */
public final class StoreDaoException extends DaoException
{
    /**
     * Throw an error message.
     * 
     * @param message
     */
    public StoreDaoException(String message) { super(message); }

    /**
     * Throw an error message and its cause.
     * 
     * @param message
     * @param cause
     */
    public StoreDaoException(String message, Throwable cause) { super(message, cause); }
}