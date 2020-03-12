package hulkstore_.model.dao;

/** 
 * This class controls possible exceptions.
 *  
 * @author Luis Mercado
 * @version 0.1
 * @since 2020-03-11
 */
public class DaoException extends Exception
{
    protected Throwable throwable;

    /**
     * Throw an error message.
     * 
     * @param message
     */
    public DaoException(String message) { super(message); }

    /**
     * Throw an error message and its cause.
     * 
     * @param message
     * @param cause
     */
    public DaoException(String message, Throwable cause)
    {
        super(message);
        this.throwable = cause;
    }

    /**
     * Method 'getCause'
     * 
     * @return Throwable
     */
    public Throwable getCause() { return throwable; }
}