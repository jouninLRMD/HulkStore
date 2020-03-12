package hulkstore_.model.dao.product_;

import hulkstore_.model.dao.DaoException;

/** 
 * This class controls possible exceptions with product_ table.
 *  
 * @author Luis Mercado
 * @version 0.1
 * @since 2020-03-10
 */
public final class ProductDaoException extends DaoException
{
    /**
     * Throw an error message.
     * 
     * @param message
     */
    public ProductDaoException(String message) { super(message); }

    /**
     * Throw an error message and its cause.
     * 
     * @param message
     * @param cause
     */
    public ProductDaoException(String message, Throwable cause) { super(message, cause); }
}