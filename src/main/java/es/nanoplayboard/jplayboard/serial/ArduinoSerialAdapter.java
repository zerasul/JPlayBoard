package es.nanoplayboard.jplayboard.serial;

import es.nanoplayboard.jplayboard.exceptions.NotConnectedException;
import gnu.io.NRSerialPort;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by victor on 15/10/16.
 */
public class ArduinoSerialAdapter implements SerialAdapter{

    NRSerialPort serialport;
    private static final int DEFAULTBAUDRATE=9600;
    boolean connected=false;

    public ArduinoSerialAdapter(String port){
        serialport= new NRSerialPort(port,DEFAULTBAUDRATE);
    }

    public ArduinoSerialAdapter(String port, int baudrate){
        serialport= new NRSerialPort(port,baudrate);
    }
    @Override
    public void connect() {
        connected=serialport.connect();
    }

    @Override
    public void close() throws NotConnectedException {
        if(!connected){
            throw new NotConnectedException();
        }
        serialport.disconnect();
    }

    @Override
    public OutputStream getOutputStream() throws NotConnectedException{
        if(!connected) throw new NotConnectedException();

        return serialport.getOutputStream();
    }

    @Override
    public InputStream getInputStream() throws NotConnectedException{
        if(!connected) throw new NotConnectedException();
        return serialport.getInputStream();
    }


}
