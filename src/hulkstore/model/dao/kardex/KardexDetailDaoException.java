package hulkstore_.model.dao.inventory_;

import hulkstore_.model.dao.DaoException;

/** 
 * This class controls possible exceptions with inventory_ detail.
 *  
 * @author Luis Mercado
 * @version 0.1
 * @since 2020-03-11
 */
public final class KardexDetailDaoException extends DaoException
{
    /**
     * Throw an error message.
     * 
     * @param message
     */
    public KardexDetailDaoException(String message) { super(message); }

    /**
     * Throw an error message and its cause.
     * 
     * @param message
     * @param cause
     */
    public KardexDetailDaoException(String message, Throwable cause) { super(message, cause); }
}