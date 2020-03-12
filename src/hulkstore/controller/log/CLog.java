package hulkstore_.controller.log;

import org.apache.log4j.Logger;

/**
 * Logger management main controller
 *  
 * @author Luis Mercado
 * @version 0.1
 * @since 2020-03-11
 */
public final class CLog {
    
    public static void log(String className, String level, String message) {
        
        Logger log = Logger.getLogger(className);
        
        switch (level) {
            case "debug":
                log.debug(message);
                break;
                
            case "info":
                log.info(message); 
                break;
                
            case "warn":
                log.warn(message);
                break;
                
            case "error":
                log.error(message);
                break;
                
            case "fatal":
                log.fatal(message);
                break;
                
            default:
                log.error("This level is not supported.");
                break;
        }
    }
}
