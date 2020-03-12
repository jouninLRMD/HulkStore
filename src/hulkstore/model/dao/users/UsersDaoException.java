package hulkstore_.model.dao.users;

import hulkstore_.model.dao.DaoException;

/**
 * This class controls possible exceptions with user table.
 * 
 * @author Luis Mercado
 * @version 0.1
 * @since 2020-03-10
 */
public final class UsersDaoException extends DaoException
{
    /**
     * Throw an error message.
     * 
     * @param message
     */
    public UsersDaoException(String message) { super(message); }

    /**
     * Throw an error message and its cause.
     * 
     * @param message
     * @param cause
     */
    public UsersDaoException(String message, Throwable cause) { super(message, cause); }
}