package es.nanoplayboard.jplayboard.adapter;




import java.io.IOException;

import es.nanoplayboard.jplayboard.adapter.listener.StateChangeListener;
import es.nanoplayboard.jplayboard.common.Command;
import es.nanoplayboard.jplayboard.exception.NotConnectedException;

/**
 * Created by victor on 15/10/16.
 */
public interface Adapter {

    void connect() throws IOException, InterruptedException;
    void disconnect()throws IOException;
    void setonStateChangeListener(StateChangeListener listener);
    void sendCommand(Command command) throws IOException, NotConnectedException;
}
