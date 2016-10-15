package es.nanoplayboard.jplayboard.serial;

import es.nanoplayboard.jplayboard.exceptions.NotConnectedException;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by victor on 15/10/16.
 */
public interface SerialAdapter {

     void connect();
     void close() throws NotConnectedException;
     OutputStream getOutputStream() throws NotConnectedException;
     InputStream getInputStream() throws NotConnectedException;
}
