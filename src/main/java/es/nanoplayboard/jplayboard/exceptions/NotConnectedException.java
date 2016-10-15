package es.nanoplayboard.jplayboard.exceptions;

/**
 * Created by victor on 15/10/16.
 */
public class NotConnectedException extends RuntimeException {

    public NotConnectedException(){
        super("Ilegal State: Serial Port Disconnected");
    }

    public NotConnectedException(String msg){
        super(msg);
    }
}
