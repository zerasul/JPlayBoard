package es.nanoplayboard.jplayboard.common;

import java.util.Arrays;

/**
 * Created by victor on 26/10/16.
 */
public abstract class Command {

    private int type;
    
    public enum CommandTypes{
    	DINIT,
    	INIT,
    	STATE,
    	LEDRGB,
    	BUZZER,
    	STOPBUZZER,
    	MATRIX
    }
    public Command( int type){
        this.type=type;
    }

    public  int getType() {
        return type;
    }

    public void setType( int type) {
        this.type = type;
    }

    

    public abstract byte[] getAT();
    
    protected String generatechecksum(String value){
    	byte[] bvalue=value.getBytes();
    	Integer n=0;
    	for (byte b : bvalue) {
			n+=b;
		}
    	return Integer.toString(n);
    }
}
