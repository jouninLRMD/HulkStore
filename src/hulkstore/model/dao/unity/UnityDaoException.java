package hulkstore_.model.dao.unity_;

import hulkstore_.model.dao.DaoException;

/** 
 * This class controls possible exceptions with unity_ table.
 *  
 * @author Luis Mercado
 * @version 0.1
 * @since 2020-03-10
 */
public final class UnityDaoException extends DaoException
{
    /**
     * Throw an error message.
     * 
     * @param message
     */
    public UnityDaoException(String message) { super(message); }

    /**
     * Throw an error message and its cause.
     * 
     * @param message
     * @param cause
     */
    public UnityDaoException(String message, Throwable cause) { super(message, cause); }
}