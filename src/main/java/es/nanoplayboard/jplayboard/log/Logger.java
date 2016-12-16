package es.nanoplayboard.jplayboard.log;


/**
 * Created by victor on 27/10/16.
 */
public class Logger {

	private  org.apache.logging.log4j.Logger logger;
    
	public Logger(Class<?> clazz){
		this.logger=org.apache.logging.log4j.LogManager.getLogger(clazz);
		
	}
	public static Logger getInstance(Class<?> clazz){
		Logger logger = new Logger(clazz);
		return logger;
	}
	public void error(String msg) {
		
		this.logger.error(msg);
	}
	
	public void info(String msg){
		this.logger.info(msg);
	}
	
	public void warning(String msg){
		this.logger.warn(msg);
	}
	
	public void debug(String msg){
		this.logger.debug(msg);
	}
}
