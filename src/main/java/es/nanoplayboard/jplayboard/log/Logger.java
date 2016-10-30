package es.nanoplayboard.jplayboard.log;

import org.apache.logging.log4j.LogManager;

/**
 * Created by victor on 27/10/16.
 */
public class Logger {

    public static org.apache.logging.log4j.Logger getLogger(Class<?> clazz){
        org.apache.logging.log4j.Logger log = LogManager.getLogger(clazz);
        return log;
    }
}
