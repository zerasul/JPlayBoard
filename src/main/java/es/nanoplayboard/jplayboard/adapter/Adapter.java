package es.nanoplayboard.jplayboard.adapter;




import es.nanoplayboard.jplayboard.adapter.listener.StateChangeListener;
import es.nanoplayboard.jplayboard.common.Command;
import es.nanoplayboard.jplayboard.common.State;
import es.nanoplayboard.jplayboard.exception.NotConnectedException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by victor on 15/10/16.
 */
public interface Adapter {

    void connect() throws IOException, InterruptedException;
    void disconnect()throws IOException;
    void setonStateChangeListener(StateChangeListener listener);
    void sendCommand(Command command) throws IOException, NotConnectedException;
}
